package br.com.mercadoapp.ms_pedidos.model;

public enum Status {
    AGUARDANDO_PAGAMENTO,
    PAGO,
    ERRO_CONSULTA_PGTO,
    RECUSADO,
    PREPARANDO,
    SAIU_ENTREGA,
    ENTREGUE,
    CANCELADO
}
