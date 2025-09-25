package br.com.mercadoapp.ms_pedidos.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record PagamentoRequestDto(
        UUID pedidoId,
        BigDecimal valor,
        String formaPagamento
) { }
