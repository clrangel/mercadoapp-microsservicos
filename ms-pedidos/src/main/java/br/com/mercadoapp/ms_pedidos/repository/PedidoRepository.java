package br.com.mercadoapp.ms_pedidos.repository;

import br.com.mercadoapp.ms_pedidos.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PedidoRepository extends JpaRepository<Pedido, UUID> {
}
