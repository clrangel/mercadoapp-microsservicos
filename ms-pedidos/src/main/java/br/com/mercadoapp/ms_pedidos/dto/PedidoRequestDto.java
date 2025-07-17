package br.com.mercadoapp.ms_pedidos.dto;

import br.com.mercadoapp.ms_pedidos.model.ItemPedido;

import java.util.List;

public record PedidoRequestDto(
        Long usuarioId,
        List<ItemPedidoRequestDTO> itens
)
{}
