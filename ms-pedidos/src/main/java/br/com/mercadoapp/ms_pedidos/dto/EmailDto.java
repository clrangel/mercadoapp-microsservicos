package br.com.mercadoapp.ms_pedidos.dto;

public record EmailDto(
        String nome,
        String cpf,
        String pedidoId,
        String status) {
}
