package br.com.mercadoapp.mercadoapp.service;

import br.com.mercadoapp.mercadoapp.dto.ProdutoRequestDTO;
import br.com.mercadoapp.mercadoapp.dto.ProdutoResponseDTO;
import br.com.mercadoapp.mercadoapp.model.Categoria;
import br.com.mercadoapp.mercadoapp.model.Produto;
import br.com.mercadoapp.mercadoapp.repository.CategoriaRepository;
import br.com.mercadoapp.mercadoapp.repository.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Transactional
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
                //salvo.getId(),
                salvo.getNome(),
                salvo.getDescricao(),
                salvo.getPreco(),
                nomesCategorias
        );
    }

    @Transactional
    public void deletarProduto(Long id) {
        if (!produtoRepository.existsById(id)) {
            throw new EntityNotFoundException("Produto com ID " + id + " não encontrado.");
        }
        produtoRepository.deleteById(id);
    }

    @Transactional
    public ProdutoResponseDTO atualizarProduto(Long id, ProdutoRequestDTO dto) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Produto com ID " + id + " não encontrado."));

        // Atualiza os campos do produto
        produto.setNome(dto.nome());
        produto.setDescricao(dto.descricao());
        produto.setPreco(dto.preco());

        // Atualiza as categorias
        Set<Categoria> categorias = dto.categoriaIds().stream()
                .map(catId -> categoriaRepository.findById(catId)
                        .orElseThrow(() -> new RuntimeException("Categoria com ID " + catId + " não encontrada.")))
                .collect(Collectors.toSet());

        produto.getCategorias().clear();
        produto.getCategorias().addAll(categorias);

        Produto atualizado = produtoRepository.save(produto);

        // Mapeamento manual para ProdutoResponseDTO
        Set<String> nomesCategorias = atualizado.getCategorias().stream()
                .map(Categoria::getNomeCategoria)
                .collect(Collectors.toSet());

        return new ProdutoResponseDTO(
                //atualizado.getId(),
                atualizado.getNome(),
                atualizado.getDescricao(),
                atualizado.getPreco(),
                nomesCategorias
        );
    }

    public ProdutoResponseDTO buscarProdutoPorId(Long id) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Produto com ID " + id + " não encontrado."));

        //Mapeamento manual - ProdutoResponseDTO
        Set<String> nomesCategorias = produto.getCategorias().stream()
                .map(Categoria::getNomeCategoria)
                .collect(Collectors.toSet());

        return new ProdutoResponseDTO(
                //produto.getId(),
                produto.getNome(),
                produto.getDescricao(),
                produto.getPreco(),
                nomesCategorias
        );
    }

    public Page<ProdutoResponseDTO> listarProdutos(Pageable pageable) {
        Page<Produto> produtos = produtoRepository.findAll(pageable);

        return produtos.map(produto -> {
            Set<String> nomesCategorias = produto.getCategorias().stream()
                    .map(Categoria::getNomeCategoria)
                    .collect(Collectors.toSet());

            return new ProdutoResponseDTO(
                    //produto.getId(),
                    produto.getNome(),
                    produto.getDescricao(),
                    produto.getPreco(),
                    nomesCategorias
            );
        });
    }




}

