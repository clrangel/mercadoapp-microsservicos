package br.com.mercadoapp.ms.pagamentos.service;

import br.com.mercadoapp.ms.pagamentos.repository.PagamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PagamentoService {

    private final PagamentoRepository pagamentoRepository;
}
