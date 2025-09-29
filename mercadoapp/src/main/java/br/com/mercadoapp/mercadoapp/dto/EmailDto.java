package br.com.mercadoapp.mercadoapp.dto;

public record EmailDto(
        String nome,
        String cpf,
        String pedidoId,
        String status) {
}
