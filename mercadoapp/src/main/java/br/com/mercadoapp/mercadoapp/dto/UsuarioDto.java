package br.com.mercadoapp.mercadoapp.dto;

public record UsuarioDto(
        Long id,
        String nome,
        String telefone,
        String email
) {
}
