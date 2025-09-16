package br.com.mercadoapp.ms_pedidos.service;

import br.com.mercadoapp.ms_pedidos.client.AutorizacaoPagamentoClient;
import br.com.mercadoapp.ms_pedidos.dto.*;
import br.com.mercadoapp.ms_pedidos.model.ItemPedido;
import br.com.mercadoapp.ms_pedidos.model.Pedido;
import br.com.mercadoapp.ms_pedidos.model.Status;
import br.com.mercadoapp.ms_pedidos.repository.PedidoRepository;
import br.com.mercadoapp.ms_pedidos.client.ProdutoClient;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PedidoService {

    private final PedidoRepository repository;

    private final ProdutoClient produtoClient;

    private final AutorizacaoPagamentoClient autorizacaoPagamentoClient;

    public PedidoService(PedidoRepository repository, ProdutoClient produtoClient, AutorizacaoPagamentoClient autorizacaoPagamentoClient) {
        this.repository = repository;
        this.produtoClient = produtoClient;
        this.autorizacaoPagamentoClient = autorizacaoPagamentoClient;
    }

    public PedidoResponseDto cadastrarPedido(PedidoRequestDto dto) {
        // Criar entidade Pedido
        Pedido pedido = new Pedido();
        pedido.setDateMoment(Instant.now());
        pedido.setUsuarioId(dto.usuarioId());
        pedido.setStatus(Status.AGUARDANDO_PAGAMENTO); // ou outro status inicial

        // Montar itens do pedido
        List<ItemPedido> itens = dto.itens().stream()
                .map(itemDto -> {
                    ProdutoResponseDto produto = produtoClient.buscarPorId(itemDto.produtoId());

                    ItemPedido item = new ItemPedido();
                    item.setProdutoId(itemDto.produtoId());
                    item.setNomeProduto(produto.nome());
                    item.setQuantidade(itemDto.quantidade());
                    item.setValorUnitario(itemDto.valorUnitario());
                    item.setPedido(pedido);  // link bidirecional
                    return item;
                })
                .collect(Collectors.toList());

        pedido.setItens(itens);

        // Calcular valor total
        pedido.calcularValorTotal();

        // Salvar pedido inicialmente
        Pedido pedidoSalvo = repository.save(pedido);

        // Obter status de pagamento (consulta ao ms-pagamentos via FeignClient)
        Status statusPagamento = obterStatusPagamento(pedidoSalvo.getId().toString());
        pedidoSalvo.setStatus(statusPagamento);

        // Atualizar pedido com o status correto
        pedidoSalvo = repository.save(pedidoSalvo);

        // Montar e retornar DTO de resposta
        return new PedidoResponseDto(
                pedidoSalvo.getId(),
                pedidoSalvo.getDateMoment(),
                pedidoSalvo.getValorTotal(),
                pedidoSalvo.getStatus(),
                pedidoSalvo.getUsuarioId(),
                pedidoSalvo.getItens().stream()
                        .map(item -> new ItemPedidoResponseDTO(
                                item.getId(),
                                item.getProdutoId(),
                                item.getNomeProduto(),
                                item.getQuantidade(),
                                item.getValorUnitario()))
                        .collect(Collectors.toList())
        );
    }

    //Vizualizar todos os pedidos
    //Metodo para testar o API Gateway no navegador
    //Atualizar esse metodo futuramente
    public Page<PedidoResponseDto> obterTodos(Pageable pageable) {
        return repository.findAll(pageable)
                .map(pedido -> new PedidoResponseDto(
                        pedido.getId(),
                        pedido.getDateMoment(),
                        pedido.getValorTotal(),
                        pedido.getStatus(),
                        pedido.getUsuarioId(),
                        pedido.getItens().stream()
                                .map(item -> new ItemPedidoResponseDTO(
                                        item.getId(),
                                        item.getProdutoId(),
                                        item.getNomeProduto(),
                                        item.getQuantidade(),
                                        item.getValorUnitario()))
                                .collect(Collectors.toList())
                ));
    }

    public PedidoResponseDto buscarPorId(UUID id) {
        Pedido pedido = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pedido com ID " + id + " não encontrado"));

        return new PedidoResponseDto(
                pedido.getId(),
                pedido.getDateMoment(),
                pedido.getValorTotal(),
                pedido.getStatus(),
                pedido.getUsuarioId(),
                pedido.getItens().stream()
                        .map(item -> new ItemPedidoResponseDTO(
                                item.getId(),
                                item.getProdutoId(),
                                item.getNomeProduto(),
                                item.getQuantidade(),
                                item.getValorUnitario()))
                        .collect(Collectors.toList())
        );
    }


    @Transactional
    public void deletarPedido(UUID id) {
        Pedido pedido = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pedido com ID " + id + " não encontrado."));

        repository.delete(pedido);
    }

    private Status obterStatusPagamento(String id) {
        AutorizacaoDto autorizacao = autorizacaoPagamentoClient.obterAutorizacao(id);
        if (autorizacao.status().equalsIgnoreCase("autorizado")) {
            return Status.PREPARANDO;
        }

        return Status.RECUSADO;
    }

}