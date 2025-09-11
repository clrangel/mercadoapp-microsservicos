package br.com.mercadoapp.ms.pagamentos.dto;

import br.com.mercadoapp.ms.pagamentos.model.StatusPagamento;

public record AutorizacaoDto(String idPedido,
                             StatusPagamento status) {
}
