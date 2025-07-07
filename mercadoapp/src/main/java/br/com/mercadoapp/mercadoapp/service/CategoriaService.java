package br.com.mercadoapp.mercadoapp.service;

import br.com.mercadoapp.mercadoapp.model.Categoria;
import br.com.mercadoapp.mercadoapp.repository.CategoriaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repository;

    @Transactional
    public Categoria cadastrarCategoria(Categoria categoria) {
        return repository.save(categoria);
    }


}
