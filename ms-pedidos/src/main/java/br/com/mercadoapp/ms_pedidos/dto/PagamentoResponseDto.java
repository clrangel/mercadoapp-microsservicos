package br.com.mercadoapp.ms_pedidos.dto;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record PagamentoResponseDto(
        Long id,
        UUID pedidoId,
        BigDecimal valor,
        String formaPagamento,
        String status,
        Instant dataPagamento
) { }
