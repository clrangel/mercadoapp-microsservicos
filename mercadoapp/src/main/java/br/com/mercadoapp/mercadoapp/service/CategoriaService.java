package br.com.mercadoapp.mercadoapp.service;

import br.com.mercadoapp.mercadoapp.model.Categoria;
import br.com.mercadoapp.mercadoapp.repository.CategoriaRepository;
import jakarta.persistence.EntityNotFoundException;
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

    @Transactional
    public void deletarCategoria(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Categoria com ID " + id + " não encontrada.");
        }
        repository.deleteById(id);
    }

    @Transactional
    public Categoria atualizarCategoria(Long id, Categoria categoriaAtualizada) {
        Categoria categoriaExistente = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Categoria com ID " + id + " não encontrada."));

        categoriaExistente.setNomeCategoria(categoriaAtualizada.getNomeCategoria());

        return repository.save(categoriaExistente);
    }

}
