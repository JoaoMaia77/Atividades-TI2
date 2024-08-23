/**
 * Data Access Object (DAO) para gerenciar operações relacionadas a livros no banco de dados.
 * 
 * @author João Victor Maia <github.com/JoaoMaia77>
 * @version 1.0
 */
package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import modelo.Livro;

public class LivroDAO extends DAO {

    /**
     * Construtor da classe LivroDAO. Inicializa a conexão com o banco de dados.
     */
    public LivroDAO() {
        super();
        conectar();
    }

    /**
     * Finalizador da classe LivroDAO. Fecha a conexão com o banco de dados.
     */
    public void finalize() {
        close();
    }

    /**
     * Insere um novo livro no banco de dados.
     *
     * @param livro O objeto Livro a ser inserido.
     * @return true se a inserção for bem-sucedida, false caso contrário.
     */
    public boolean insert(Livro livro) {
        boolean status = false;
        try {
            Statement st = conexao.createStatement();
            String sql = "INSERT INTO livros (codigo, titulo, autor, nota) "
                    + "VALUES (" + livro.getCodigo() + ", '" + livro.getTitulo() + "', '"
                    + livro.getAutor() + "', '" + livro.getNota() + "');";
            System.out.println(sql);
            st.executeUpdate(sql);
            st.close();
            status = true;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
        return status;
    }

    /**
     * Obtém um livro do banco de dados com base no código fornecido.
     *
     * @param codigo O código do livro a ser buscado.
     * @return O objeto Livro correspondente ao código, ou null se não encontrado.
     */
    public Livro get(int codigo) {
        Livro livro = null;

        try {
            Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT * FROM livros WHERE codigo=" + codigo;
            System.out.println(sql);
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                livro = new Livro(rs.getInt("codigo"), rs.getString("titulo"), rs.getString("autor"), rs.getFloat("nota"));
            }
            st.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return livro;
    }

    /**
     * Obtém todos os livros do banco de dados.
     *
     * @return Uma lista contendo todos os livros encontrados.
     */
    public List<Livro> get() {
        return get("");
    }

    /**
     * Obtém todos os livros do banco de dados, ordenados por código.
     *
     * @return Uma lista contendo todos os livros encontrados, ordenados por código.
     */
    public List<Livro> getOrderByCodigo() {
        return get("codigo");
    }

    /**
     * Obtém todos os livros do banco de dados, ordenados por título.
     *
     * @return Uma lista contendo todos os livros encontrados, ordenados por título.
     */
    public List<Livro> getOrderByTitulo() {
        return get("titulo");
    }

    /**
     * Obtém todos os livros do banco de dados, ordenados por autor.
     *
     * @return Uma lista contendo todos os livros encontrados, ordenados por autor.
     */
    public List<Livro> getOrderByAutor() {
        return get("autor");
    }

    /**
     * Método auxiliar para obter livros do banco de dados, com opção de ordenação.
     *
     * @param orderBy A coluna pela qual os resultados devem ser ordenados (pode ser vazio para nenhuma ordenação).
     * @return Uma lista contendo os livros encontrados, possivelmente ordenados.
     */
    private List<Livro> get(String orderBy) {

        List<Livro> livros = new ArrayList<Livro>();

        try {
            Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT * FROM livros" + ((orderBy.trim().length() == 0) ? "" : (" ORDER BY " + orderBy));
            System.out.println(sql);
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Livro u = new Livro(rs.getInt("codigo"), rs.getString("titulo"), rs.getString("autor"), rs.getFloat("nota"));
                livros.add(u);
            }
            st.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return livros;
    }

    /**
     * Atualiza um livro existente no banco de dados.
     *
     * @param livro O objeto Livro com as informações atualizadas.
     * @return true se a atualização for bem-sucedida, false caso contrário.
     */
    public boolean update(Livro livro) {
        boolean status = false;
        try {
            Statement st = conexao.createStatement();
            String sql = "UPDATE livros SET titulo = '" + livro.getTitulo() + "', autor = '"
                    + livro.getAutor() + "', nota = '" + livro.getNota() + "'"
                    + " WHERE codigo = " + livro.getCodigo();
            System.out.println(sql);
            st.executeUpdate(sql);
            st.close();
            status = true;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
        return status;
    }

    /**
     * Exclui um livro do banco de dados com base no código fornecido.
     *
     * @param codigo O código do livro a ser excluído.
     * @return true se a exclusão for bem-sucedida, false caso contrário.
     */
    public boolean delete(int codigo) {
        boolean status = false;
        try {
            Statement st = conexao.createStatement();
            String sql = "DELETE FROM livros WHERE codigo = " + codigo + ";";
            System.out.println(sql);
            st.executeUpdate(sql);
            st.close();
            status = true;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
        return status;
    }

    /**
     * Autentica um livro com base no título e autor fornecidos.
     *
     * @param titulo O título do livro.
     * @param autor O autor do livro.
     * @return true se o livro for encontrado com o título e autor fornecidos, false caso contrário.
     */
    public boolean autenticar(String titulo, String autor) {
        boolean resp = false;

        try {
            Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT * FROM livros WHERE titulo LIKE '" + titulo + "' AND autor LIKE '" + autor + "'";
            System.out.println(sql);
            ResultSet rs = st.executeQuery(sql);
            resp = rs.next();
            st.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return resp;
    }
}