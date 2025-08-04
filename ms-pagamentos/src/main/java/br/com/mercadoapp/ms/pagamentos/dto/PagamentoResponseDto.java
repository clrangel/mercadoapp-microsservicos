package br.com.mercadoapp.ms.pagamentos.dto;

import br.com.mercadoapp.ms.pagamentos.model.FormaPagamento;
import br.com.mercadoapp.ms.pagamentos.model.StatusPagamento;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record PagamentoResponseDto(
        Long id,
        UUID pedidoId,
        BigDecimal valor,
        FormaPagamento formaPagamento,
        StatusPagamento status,
        Instant dataPagamento
) {}