package br.com.mercadoapp.ms_pedidos.dto;

import java.math.BigDecimal;

public record ItemPedidoResponseDTO(
        Long id,
        Long produtoId,
        Integer quantidade,
        BigDecimal valorUnitario) {
}
