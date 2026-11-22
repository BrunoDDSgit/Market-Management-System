
package com.ucb.mercadoapi.service;

import com.ucb.mercadoapi.dto.ProdutoDTO;
import com.ucb.mercadoapi.entity.CategoriaEntity;
import com.ucb.mercadoapi.entity.ProdutoEntity;
import com.ucb.mercadoapi.repository.CategoriaRepository;
import com.ucb.mercadoapi.repository.ProdutoRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final CategoriaRepository categoriaRepository;
    private final HistoricoService historicoService;

    public List<ProdutoEntity> findAll() {
        return produtoRepository.findAll();
    }

    public ProdutoEntity findById(Integer id) {
        return produtoRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Produto não encontrado"));
    }

    @Transactional
    public ProdutoEntity createProduct(ProdutoDTO produtoDTO) {

        CategoriaEntity categoria = categoriaRepository
                .findById(produtoDTO.getCategoriaId())
                .orElseThrow(() ->
                        new EntityNotFoundException("Categoria não encontrada"));

        ProdutoEntity produto = new ProdutoEntity(
                produtoDTO.getNome(),
                produtoDTO.getDescricao(),
                produtoDTO.getCodigoBarras(),
                produtoDTO.getQuantidade(),
                produtoDTO.getPreco(),
                produtoDTO.getDesconto(),
                produtoDTO.getValidade(),
                categoria
        );

        ProdutoEntity produtoSalvo = produtoRepository.save(produto);

        historicoService.registrarAlteracao(
                "CADASTRO",
                "Produto \"" + produtoSalvo.getNome() + "\" adicionado ao estoque."
        );

        return produtoSalvo;
    }

    @Transactional
    public ProdutoEntity updateProduct(ProdutoDTO produtoDTO, Integer id) {

        ProdutoEntity produto = findById(id);

        String nomeAntigo = produto.getNome();
        int quantidadeAntiga = produto.getQuantidade();
        var precoAntigo = produto.getPreco();

        CategoriaEntity categoria = categoriaRepository
                .findById(produtoDTO.getCategoriaId())
                .orElseThrow(() ->
                        new EntityNotFoundException("Categoria não encontrada"));

        produto.setNome(produtoDTO.getNome());
        produto.setDescricao(produtoDTO.getDescricao());
        produto.setCodigoBarras(produtoDTO.getCodigoBarras());
        produto.setQuantidade(produtoDTO.getQuantidade());
        produto.setPreco(produtoDTO.getPreco());
        produto.setDesconto(produtoDTO.getDesconto());
        produto.setValidade(produtoDTO.getValidade());
        produto.setCategoria(categoria);

        ProdutoEntity produtoAtualizado = produtoRepository.save(produto);

        List<String> alteracoes = new ArrayList<>();

        if (quantidadeAntiga != produtoDTO.getQuantidade()) {
            alteracoes.add(
                    "Quantidade do produto \"" + nomeAntigo +
                            "\" alterada de " + quantidadeAntiga +
                            " para " + produtoDTO.getQuantidade()
            );
        }

        if (precosDiferentes(precoAntigo, produtoDTO.getPreco())) {
            alteracoes.add(
                    "Preço do produto \"" + nomeAntigo +
                            "\" alterado de " + formatarPreco(precoAntigo) +
                            " para " + formatarPreco(produtoDTO.getPreco())
            );
        }

        String mensagem;

        if (alteracoes.isEmpty()) {
            mensagem = "Produto \"" + produtoAtualizado.getNome() +
                    "\" atualizado.";
        } else {
            mensagem = String.join(". ", alteracoes) + ".";
        }

        historicoService.registrarAlteracao("EDICAO", mensagem);

        return produtoAtualizado;
    }

    @Transactional
    public void removerProduto(Integer id) {

        ProdutoEntity produto = findById(id);

        String nomeProduto = produto.getNome();

        produtoRepository.delete(produto);

        historicoService.registrarAlteracao(
                "EXCLUSAO",
                "Produto \"" + nomeProduto + "\" removido do estoque."
        );
    }

    private boolean precosDiferentes(java.math.BigDecimal antigo,
                                     java.math.BigDecimal novo) {

        if (antigo == null) {
            return novo != null;
        }

        if (novo == null) {
            return true;
        }

        return antigo.compareTo(novo) != 0;
    }

    private String formatarPreco(java.math.BigDecimal preco) {

        if (preco == null) {
            return "não informado";
        }

        NumberFormat formato = NumberFormat.getCurrencyInstance(
                new Locale("pt", "BR")
        );

        return formato.format(preco);
    }
}
