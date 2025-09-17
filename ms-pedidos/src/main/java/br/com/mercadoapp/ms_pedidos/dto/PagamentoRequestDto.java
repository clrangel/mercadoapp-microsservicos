package br.com.mercadoapp.ms_pedidos.dto;

import java.util.UUID;

public record PagamentoRequestDto(
        UUID pedidoId,
        String formaPagamento
) { }
