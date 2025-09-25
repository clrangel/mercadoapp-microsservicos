package br.com.mercadoapp.ms.pagamentos.service;

import br.com.mercadoapp.ms.pagamentos.client.PedidoClient;
import br.com.mercadoapp.ms.pagamentos.client.dto.PedidoResponseDto;
import br.com.mercadoapp.ms.pagamentos.dto.AutorizacaoDto;
import br.com.mercadoapp.ms.pagamentos.dto.PagamentoRequestDto;
import br.com.mercadoapp.ms.pagamentos.dto.PagamentoResponseDto;
import br.com.mercadoapp.ms.pagamentos.mapper.PagamentoMapper;
import br.com.mercadoapp.ms.pagamentos.model.FormaPagamento;
import br.com.mercadoapp.ms.pagamentos.model.GeradorAutorizacao;
import br.com.mercadoapp.ms.pagamentos.model.Pagamento;
import br.com.mercadoapp.ms.pagamentos.model.StatusPagamento;
import br.com.mercadoapp.ms.pagamentos.repository.PagamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Service
//@RequiredArgsConstructor
public class PagamentoService {

    private final PagamentoRepository pagamentoRepository;

    private final PedidoClient pedidoClient;

    private final PagamentoMapper pagamentoMapper;

    //Problemas com o Lombok
    //Não consegui resolver o erro ao compilar o projeto com a utilização do @RequiredArgsConstructor, então criei o construtor para que o erro parasse e eu pudesse continuar o projeto.
    public PagamentoService(PagamentoRepository pagamentoRepository, PedidoClient pedidoClient, PagamentoMapper pagamentoMapper) {
        this.pagamentoRepository = pagamentoRepository;
        this.pedidoClient = pedidoClient;
        this.pagamentoMapper = pagamentoMapper;
    }

    public PagamentoResponseDto criarPagamento(PagamentoRequestDto dto) {
        // Busca os dados do pedido via Feign Client
        PedidoResponseDto pedido = pedidoClient.buscarPedidoPorId(dto.pedidoId());

        // Converte o DTO para entidade base com MapStruct
        Pagamento pagamento = pagamentoMapper.toEntity(dto);

        // Preenche os dados adicionais que não estão no DTO
        pagamento.setId(null);
        pagamento.setStatus(StatusPagamento.AGUARDANDO_PAGAMENTO);
        pagamento.setDataPagamento(Instant.now());
        pagamento.setFormaPagamento(dto.formaPagamento());
        pagamento.setValor(pedido.valorTotal());
        pagamento.setPedidoId(pedido.id());

        // Salva no banco
        Pagamento pagamentoSalvo = pagamentoRepository.save(pagamento);

        // Converte a entidade salva para DTO de resposta
        return pagamentoMapper.toDto(pagamentoSalvo);
    }

    // Atualiza o status do pagamento existente
    public AutorizacaoDto autorizarPagamento(String id) {
        UUID pedidoId = UUID.fromString(id);

        // Se existe, pega; se não, cria um novo pagamento mínimo (evita 500)
        Pagamento pagamento = pagamentoRepository.findByPedidoId(pedidoId)
                .orElseGet(() -> {
                    Pagamento p = new Pagamento();
                    p.setPedidoId(pedidoId);
                    p.setValor(BigDecimal.ZERO);
                    p.setFormaPagamento(null); // ou uma forma default se quiser
                    p.setStatus(StatusPagamento.AGUARDANDO_PAGAMENTO);
                    p.setDataPagamento(Instant.now());
                    return pagamentoRepository.save(p);
                });

        // Simula status aprovado/recusado
        StatusPagamento novoStatus = GeradorAutorizacao.getRandomBoolean()
                ? StatusPagamento.APROVADO
                : StatusPagamento.RECUSADO;

        pagamento.setStatus(novoStatus);
        pagamento.setDataPagamento(Instant.now());

        pagamentoRepository.save(pagamento);

        return new AutorizacaoDto(pagamento.getPedidoId().toString(), novoStatus.name());
    }

}
