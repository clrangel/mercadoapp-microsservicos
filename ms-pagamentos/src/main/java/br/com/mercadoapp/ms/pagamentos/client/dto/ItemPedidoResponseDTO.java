package br.com.mercadoapp.ms.pagamentos.client.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record ItemPedidoResponseDTO(
        Long id,
        Long produtoId,
        String nomeProduto,
        Integer quantidade,
        BigDecimal valorUnitario
) {}
