/**
 * Classe que representa um livro, contendo informações como código, título, autor e nota.
 *
 * @author João Victor Maia <github.com/JoaoMaia77>
 * @version 1.0
 */
package modelo;

public class Livro {

    /**
     * Código único do livro.
     */
    private int codigo;

    /**
     * Título do livro.
     */
    private String titulo;

    /**
     * Autor do livro.
     */
    private String autor;

    /**
     * Nota atribuída ao livro (entre 0 e 5).
     */
    private float nota;

    /**
     * Construtor padrão da classe Livro. Inicializa os atributos com valores padrão.
     */
    public Livro() {
        this.codigo = -1;
        this.titulo = "";
        this.autor = "";
        this.nota = 0;
    }

    /**
     * Construtor da classe Livro que recebe todos os atributos como parâmetros.
     *
     * @param codigo O código do livro.
     * @param titulo O título do livro.
     * @param autor O autor do livro.
     * @param nota A nota do livro (entre 0 e 5).
     */
    public Livro(int codigo, String titulo, String autor, float nota) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.autor = autor;
        this.nota = nota;
    }

    /**
     * Obtém o código do livro.
     *
     * @return O código do livro.
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * Define o código do livro.
     *
     * @param codigo O novo código do livro.
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    /**
     * Obtém o título do livro.
     *
     * @return O título do livro.
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Define o título do livro.
     *
     * @param titulo O novo título do livro.
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * Obtém o autor do livro.
     *
     * @return O autor do livro.
     */
    public String getAutor() {
        return autor;
    }

    /**
     * Define o autor do livro.
     *
     * @param autor O novo autor do livro.
     */
    public void setAutor(String autor) {
        this.autor = autor;
    }

    /**
     * Obtém a nota do livro.
     *
     * @return A nota do livro.
     */
    public float getNota() {
        return nota;
    }

    /**
     * Define a nota do livro.
     *
     * @param nota A nova nota do livro (entre 0 e 5).
     */
    public void setNota(float nota) {
        this.nota = nota;
    }

    /**
     * Retorna uma representação em string do objeto Livro.
     *
     * @return Uma string contendo as informações do livro.
     */
    @Override
    public String toString() {
        return "Livro [codigo=" + codigo + ", titulo=" + titulo + ", autor=" + autor + ", nota=" + nota + "]";
    }
}