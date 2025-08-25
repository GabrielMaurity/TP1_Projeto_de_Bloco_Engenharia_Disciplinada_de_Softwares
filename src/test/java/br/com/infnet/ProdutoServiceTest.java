package br.com.infnet;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class ProdutoServiceTest {

    private ProdutoService produtoService;


    @BeforeEach
    void setUp() {
        produtoService = new ProdutoService();
    }

    @Test
    @DisplayName("Deve criar um produto com sucesso e atribuir um ID")
    public void deveCriarProdutoComSucesso() {
        // Cenário 1: Cadastrar um novo produto
        // Arrange (Preparação)
        String nome = "Notebook Gamer";
        double preco = 5500.0;

        // Act (Ação)
        Produto produtoCriado = produtoService.criar(nome, preco);

        // Assert (Verificação)
        assertNotNull(produtoCriado);
        assertEquals(1, produtoCriado.getId()); // O primeiro produto deve ter ID 1
        assertEquals(nome, produtoCriado.getNome());
        assertEquals(preco, produtoCriado.getPreco());
    }

    @Test
    @DisplayName("Deve listar todos os produtos cadastrados")
    public void deveListarProdutos() {
        // Cenário 2: Listar múltiplos produtos
        // Arrange
        produtoService.criar("Produto A", 10.0);
        produtoService.criar("Produto B", 20.0);

        // Act
        List<Produto> produtos = produtoService.listar();

        // Assert
        assertNotNull(produtos);
        assertEquals(2, produtos.size());
    }

    @Test
    @DisplayName("Deve retornar uma lista vazia quando não há produtos")
    public void deveRetornarListaVazia() {
        // Cenário 5: Listar produtos com a lista vazia
        // Arrange: Nenhum produto foi adicionado

        // Act
        List<Produto> produtos = produtoService.listar();

        // Assert
        assertNotNull(produtos);
        assertTrue(produtos.isEmpty());
    }

    @Test
    @DisplayName("Deve atualizar um produto existente com sucesso")
    public void deveAtualizarProdutoExistente() {
        // Cenário 3: Atualizar um produto
        // Arrange
        Produto produtoOriginal = produtoService.criar("Mouse", 80.0);
        String novoNome = "Mouse Gamer RGB";
        double novoPreco = 150.0;

        // Act
        boolean atualizado = produtoService.atualizar(produtoOriginal.getId(), novoNome, novoPreco);

        // Assert
        assertTrue(atualizado);
        Optional<Produto> produtoAtualizadoOpt = produtoService.buscarPorId(produtoOriginal.getId());
        assertTrue(produtoAtualizadoOpt.isPresent());
        assertEquals(novoNome, produtoAtualizadoOpt.get().getNome());
        assertEquals(novoPreco, produtoAtualizadoOpt.get().getPreco());
    }

    @Test
    @DisplayName("Não deve atualizar um produto com ID inexistente")
    public void naoDeveAtualizarProdutoInexistente() {
        // Cenário 6: Tentar atualizar produto que não existe
        // Arrange: Nenhum produto foi adicionado

        // Act
        boolean atualizado = produtoService.atualizar(99, "Nome Falso", 10.0);

        // Assert
        assertFalse(atualizado);
    }

    @Test
    @DisplayName("Deve deletar um produto existente com sucesso")
    public void deveDeletarProdutoExistente() {
        // Cenário 4: Excluir um produto
        // Arrange
        Produto produtoParaDeletar = produtoService.criar("Teclado Mecânico", 350.0);

        // Act
        boolean deletado = produtoService.deletar(produtoParaDeletar.getId());

        // Assert
        assertTrue(deletado);
        assertTrue(produtoService.listar().isEmpty());
    }

    @Test
    @DisplayName("Não deve deletar um produto com ID inexistente")
    public void naoDeveDeletarProdutoInexistente() {
        // Cenário 7: Tentar excluir produto que não existe
        // Arrange
        produtoService.criar("Monitor", 1200.0);

        // Act
        boolean deletado = produtoService.deletar(99);

        // Assert
        assertFalse(deletado);
        assertEquals(1, produtoService.listar().size()); // A lista deve continuar com 1 item
    }
}