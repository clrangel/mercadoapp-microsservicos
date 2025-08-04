package br.com.mercadoapp.ms.pagamentos;

import br.com.mercadoapp.ms.pagamentos.model.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {
}
