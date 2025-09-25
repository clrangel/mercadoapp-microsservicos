package br.com.mercadoapp.ms_pedidos.dto;

import br.com.mercadoapp.ms_pedidos.client.enums.StatusPagamento;

public record AutorizacaoDto(String idPedido,
                             String status) {
}
