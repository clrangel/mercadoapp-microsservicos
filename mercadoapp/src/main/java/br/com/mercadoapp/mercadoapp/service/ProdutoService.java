package br.com.mercadoapp.mercadoapp.service;

import br.com.mercadoapp.mercadoapp.dto.ProdutoRequestDTO;
import br.com.mercadoapp.mercadoapp.dto.ProdutoResponseDTO;
import br.com.mercadoapp.mercadoapp.model.Categoria;
import br.com.mercadoapp.mercadoapp.model.Produto;
import br.com.mercadoapp.mercadoapp.repository.CategoriaRepository;
import br.com.mercadoapp.mercadoapp.repository.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    public ProdutoResponseDTO cadastrarProduto(ProdutoRequestDTO dto) {
        Produto produto = new Produto();
        produto.setNome(dto.nome());
        produto.setDescricao(dto.descricao());
        produto.setPreco(dto.preco());

        Set<Categoria> categorias = dto.categoriaIds().stream()
                .map(id -> categoriaRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Categoria não encontrada: " + id)))
                .collect(Collectors.toSet());

        produto.getCategorias().addAll(categorias);

        Produto salvo = produtoRepository.save(produto);

        Set<String> nomesCategorias = salvo.getCategorias().stream()
                .map(Categoria::getNomeCategoria)
                .collect(Collectors.toSet());

        return new ProdutoResponseDTO(
                salvo.getId(),
                salvo.getNome(),
                salvo.getDescricao(),
                salvo.getPreco(),
                nomesCategorias
        );
    }

    public void deletarProduto(Long id) {
        if (!produtoRepository.existsById(id)) {
            throw new EntityNotFoundException("Produto com ID " + id + " não encontrado.");
        }
        produtoRepository.deleteById(id);
    }
}

