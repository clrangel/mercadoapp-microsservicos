package br.com.mercadoapp.ms_pedidos.dto;

import java.math.BigDecimal;

public record ItemPedidoRequestDTO(
        Long produtoId,
        Integer quantidade,
        BigDecimal valorUnitario
) {}
