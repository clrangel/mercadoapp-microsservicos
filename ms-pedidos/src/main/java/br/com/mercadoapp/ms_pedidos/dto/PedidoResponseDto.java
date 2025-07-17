package br.com.mercadoapp.ms_pedidos.dto;

import br.com.mercadoapp.ms_pedidos.model.Status;

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
        List<ItemPedidoResponseDTO> itens) {
}
