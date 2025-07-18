package br.com.mercadoapp.ms_pedidos.service;

import br.com.mercadoapp.ms_pedidos.dto.ItemPedidoResponseDTO;
import br.com.mercadoapp.ms_pedidos.dto.PedidoRequestDto;
import br.com.mercadoapp.ms_pedidos.dto.PedidoResponseDto;
import br.com.mercadoapp.ms_pedidos.model.ItemPedido;
import br.com.mercadoapp.ms_pedidos.model.Pedido;
import br.com.mercadoapp.ms_pedidos.model.Status;
import br.com.mercadoapp.ms_pedidos.repository.PedidoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PedidoService {

    private final PedidoRepository repository;

    public PedidoService(PedidoRepository repository) {
        this.repository = repository;
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
                    ItemPedido item = new ItemPedido();
                    item.setProdutoId(itemDto.produtoId());
                    item.setQuantidade(itemDto.quantidade());
                    item.setValorUnitario(itemDto.valorUnitario());
                    item.setPedido(pedido);  // link bidirecional
                    return item;
                })
                .collect(Collectors.toList());

        pedido.setItens(itens);

        // Calcular valor total
        pedido.calcularValorTotal();

        // Salvar pedido
        Pedido pedidoSalvo = repository.save(pedido);

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

}