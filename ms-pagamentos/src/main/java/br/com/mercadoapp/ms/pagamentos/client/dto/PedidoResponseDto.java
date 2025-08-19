package br.com.mercadoapp.ms.pagamentos.client.dto;

import br.com.mercadoapp.ms.pagamentos.client.enums.Status;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

public record PedidoResponseDto(
        UUID id,
        Instant dateMoment,
        BigDecimal valorTotal,
        Status status,
        Long usuarioId,
        List<ItemPedidoResponseDTO> itens
) {}
/*
public record PedidoResponseDto(
        UUID id,
        Instant dateMoment,
        BigDecimal valorTotal,
        Status status,
        Long usuarioId,
        List<ItemPedidoDto> itens
) {}

record ItemPedidoDto(
        Long id,
        UUID produtoId,
        String nomeProduto,
        Integer quantidade,
        BigDecimal valorUnitario
) {}
*/