package br.com.mercadoapp.ms.pagamentos.repository;

import br.com.mercadoapp.ms.pagamentos.model.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {
    Optional<Pagamento> findByPedidoId(UUID pedidoId);
}
