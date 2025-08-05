package br.com.mercadoapp.ms.pagamentos.dto;

import br.com.mercadoapp.ms.pagamentos.model.FormaPagamento;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.UUID;

public record PagamentoRequestDto(

        @NotNull(message = "O ID do pedido é obrigatório.")
        UUID pedidoId,

        @NotNull(message = "O valor do pagamento é obrigatório.")
        BigDecimal valor,

        @NotNull(message = "A forma de pagamento é obrigatória.")
        FormaPagamento formaPagamento
) {}