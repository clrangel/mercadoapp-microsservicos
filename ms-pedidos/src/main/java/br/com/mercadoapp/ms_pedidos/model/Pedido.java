package br.com.mercadoapp.ms_pedidos.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "pedidos")
public class Pedido implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private Instant dateMoment;
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.PERSIST)
    private List<ItemPedido> itens = new ArrayList<>();
    private BigDecimal valorTotal;
    @Enumerated(EnumType.STRING)
    private Status status;

    private Long usuarioId;

    public Pedido(UUID id, Instant dateMoment, List<ItemPedido> itens, BigDecimal valorTotal, Status status, Long usuarioId) {
        this.id = id;
        this.dateMoment = dateMoment;
        this.itens = itens;
        this.valorTotal = valorTotal;
        this.status = status;
        this.usuarioId = usuarioId;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Instant getDateMoment() {
        return dateMoment;
    }

    public void setDateMoment(Instant dateMoment) {
        this.dateMoment = dateMoment;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedido> itens) {
        itens.forEach(i -> i.setPedido(this));
        this.itens = itens;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Pedido pedido = (Pedido) o;
        return Objects.equals(id, pedido.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
