package br.com.mercadoapp.ms.pagamentos.service;

import br.com.mercadoapp.ms.pagamentos.client.PedidoClient;
import br.com.mercadoapp.ms.pagamentos.client.dto.PedidoResponseDto;
import br.com.mercadoapp.ms.pagamentos.dto.PagamentoRequestDto;
import br.com.mercadoapp.ms.pagamentos.dto.PagamentoResponseDto;
import br.com.mercadoapp.ms.pagamentos.mapper.PagamentoMapper;
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

    private final PagamentoMapper pagamentoMapper;

    public PagamentoResponseDto criarPagamento(PagamentoRequestDto dto) {
        // Busca os dados do pedido via Feign Client
        PedidoResponseDto pedido = pedidoClient.buscarPedidoPorId(dto.pedidoId());

        // Converte o DTO para entidade base com MapStruct
        Pagamento pagamento = pagamentoMapper.toEntity(dto);

        // Preenche os dados adicionais que não estão no DTO
        pagamento.setPedidoId(pedido.id());
        pagamento.setValor(pedido.valorTotal());
        pagamento.setStatus(StatusPagamento.AGUARDANDO_PAGAMENTO);
        pagamento.setDataPagamento(Instant.now());

        // Salva no banco
        Pagamento pagamentoSalvo = pagamentoRepository.save(pagamento);

        // Converte a entidade salva para DTO de resposta
        return pagamentoMapper.toDto(pagamentoSalvo);
    }
}
