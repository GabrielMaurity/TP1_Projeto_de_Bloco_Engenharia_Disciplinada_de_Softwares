package br.com.infnet;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public class ProdutoService {

    // Nossa "base de dados" em memória
    private final List<Produto> produtos = new ArrayList<>();
    // Um contador para gerar IDs únicos
    private final AtomicInteger contadorId = new AtomicInteger();

    // C - Create (Criar)
    public Produto criar(String nome, double preco) {
        int novoId = contadorId.incrementAndGet();
        Produto novoProduto = new Produto(novoId, nome, preco);
        produtos.add(novoProduto);
        return novoProduto;
    }

    // R - Read (Ler)
    public List<Produto> listar() {
        return new ArrayList<>(produtos); // Retorna uma cópia para proteger a lista original
    }

    public Optional<Produto> buscarPorId(int id) {
        return produtos.stream()
                .filter(p -> p.getId() == id)
                .findFirst();
    }

    // U - Update (Atualizar)
    public boolean atualizar(int id, String novoNome, double novoPreco) {
        Optional<Produto> produtoOptional = buscarPorId(id);
        if (produtoOptional.isPresent()) {
            Produto produtoExistente = produtoOptional.get();
            produtoExistente.setNome(novoNome);
            produtoExistente.setPreco(novoPreco);
            return true;
        }
        return false;
    }

    // D - Delete (Deletar)
    public boolean deletar(int id) {
        return produtos.removeIf(produto -> produto.getId() == id);
    }
}
