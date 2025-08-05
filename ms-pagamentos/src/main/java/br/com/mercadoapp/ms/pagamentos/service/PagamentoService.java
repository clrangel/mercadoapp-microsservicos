package br.com.mercadoapp.ms.pagamentos.service;

import br.com.mercadoapp.ms.pagamentos.client.PedidoClient;
import br.com.mercadoapp.ms.pagamentos.client.dto.PedidoResponseDto;
import br.com.mercadoapp.ms.pagamentos.dto.PagamentoRequestDto;
import br.com.mercadoapp.ms.pagamentos.model.FormaPagamento;
import br.com.mercadoapp.ms.pagamentos.model.Pagamento;
import br.com.mercadoapp.ms.pagamentos.model.StatusPagamento;
import br.com.mercadoapp.ms.pagamentos.repository.PagamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PagamentoService {

    private final PagamentoRepository pagamentoRepository;

    private final PedidoClient pedidoClient;

    public Pagamento criarPagamento(PagamentoRequestDto dto) {
        // Busca os dados do pedido via Feign Client
        PedidoResponseDto pedido = pedidoClient.buscarPedidoPorId(dto.pedidoId());

        // Cria pagamento com base nos dados do pedido e na formaPagamento do DTO
        Pagamento pagamento = Pagamento.builder()
                .pedidoId(pedido.id())
                .valor(pedido.valorTotal())
                .formaPagamento(dto.formaPagamento()) // Busca forma de pagamento no DTO
                .status(StatusPagamento.AGUARDANDO_PAGAMENTO)
                .dataPagamento(Instant.now())
                .build();

        // Salva no banco
        return pagamentoRepository.save(pagamento);
    }
}
