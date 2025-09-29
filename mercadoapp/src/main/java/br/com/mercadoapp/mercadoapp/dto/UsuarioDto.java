package br.com.mercadoapp.mercadoapp.dto;

public record UsuarioDto(
        Long id,
        String nome,
        String cpf,
        String telefone,
        String email,
        String senha
) {
}
