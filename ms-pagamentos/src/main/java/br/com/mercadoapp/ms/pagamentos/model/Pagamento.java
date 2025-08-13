package br.com.mercadoapp.ms.pagamentos.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "pagamentos")
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private UUID pedidoId;

    private BigDecimal valor;

    @Enumerated(EnumType.STRING)
    private FormaPagamento formaPagamento;

    @Enumerated(EnumType.STRING)
    private StatusPagamento status;

    private Instant dataPagamento;

    public Pagamento() {
    }

    public Pagamento(Long id, UUID pedidoId, BigDecimal valor, FormaPagamento formaPagamento, StatusPagamento status, Instant dataPagamento) {
        this.id = id;
        this.pedidoId = pedidoId;
        this.valor = valor;
        this.formaPagamento = formaPagamento;
        this.status = status;
        this.dataPagamento = dataPagamento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(UUID pedidoId) {
        this.pedidoId = pedidoId;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public StatusPagamento getStatus() {
        return status;
    }

    public void setStatus(StatusPagamento status) {
        this.status = status;
    }

    public Instant getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Instant dataPagamento) {
        this.dataPagamento = dataPagamento;
    }
}

