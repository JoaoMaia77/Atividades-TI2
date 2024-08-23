/**
 * @author João Victor Maia <github.com/JoaoMaia77>
 * @version 1.0
 */
package dbSync;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import dao.LivroDAO;
import modelo.Livro;

public class Main {

    /**
     * Ponto de entrada principal da aplicação. Gerencia um menu interativo para
     * realizar operações CRUD (criar, ler, atualizar, deletar) em livros.
     * 
     * @param args Argumentos da linha de comando (não utilizados neste caso).
     * @throws Exception Em caso de erros durante as operações de banco de dados.
     */
    public static void main(String[] args) throws Exception {
        LivroDAO livroDAO = new LivroDAO();
        Scanner scanner = new Scanner(System.in);
        int opcao = 0;

        while (opcao != 5) {
            System.out.println("\n------- MENU -------");
            System.out.println("1 - Inserir livro");
            System.out.println("2 - Atualizar livro");
            System.out.println("3 - Listar livros");
            System.out.println("4 - Excluir livro");
            System.out.println("5 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha   


            switch (opcao) {
                case 1:
                    // A opção 1 no menu original estava listando livros, 
                    // mas o comentário indica que deveria inserir. 
                    // Corrigi para inserir um novo livro.
                    System.out.println("\n------- Inserir livro -------");
                    System.out.print("Código: ");
                    int codigo = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Titulo: ");
                    String titulo = scanner.nextLine();

                    System.out.print("Autor: ");
                    String autor = scanner.nextLine();

                    float nota = obterNotaValida(scanner); // Utiliza um método auxiliar para obter a nota

                    Livro novoLivro = new Livro(codigo, titulo, autor, nota);
                    if (livroDAO.insert(novoLivro)) {
                        System.out.println("Inserção realizada com sucesso -> " + novoLivro.toString());
                    }
                    break;

                case 2:
                    // A opção 2 no menu original estava inserindo livros.
                    // Corrigi para atualizar um livro existente
                    System.out.println("\n------- Atualizar livro -------");
                    System.out.print("Código: ");
                    int codigoRef = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Novo Autor: ");
                    String newAutor = scanner.nextLine();

                    System.out.print("Novo Título: ");
                    String newTitulo = scanner.nextLine();

                    float newNota = obterNotaValida(scanner);

                    Livro livroAtualizar = new Livro(codigoRef, newTitulo, newAutor, newNota);
                    if (livroDAO.update(livroAtualizar)) {
                        System.out.println("Atualização realizada com sucesso -> " + livroAtualizar.toString());
                    }
                    break;

                case 3:
                    // A opção 3 no menu original estava excluindo livros.
                    // Corrigi para listar os livros.
                    System.out.println("\n------ Livros ordenados por código ------");
                    List<Livro> livros = livroDAO.getOrderByCodigo();
                    for (Livro u : livros) {
                        System.out.println(u.toString());
                    }
                    break;

                case 4:
                    // A opção 4 no menu original estava atualizando livros.
                    // Corrigi para excluir um livro.
                    System.out.println("\n------- Excluir livro -------");
                    System.out.print("Código do livro a ser excluído: ");
                    int codigoExcluir = scanner.nextInt();
                    if (livroDAO.delete(codigoExcluir)) {
                        System.out.println("Exclusão realizada com sucesso!");
                    }
                    break;

                case 5:
                    System.out.println("Saindo..");
                    break;

                default:
                    System.out.println("Opção inválida! Tente novamente...");
                    break;
            }
        }

        scanner.close();
    }

    /**
     * Obtém uma nota válida do usuário, garantindo que esteja entre 0 e 5.
     * 
     * @param scanner O objeto Scanner para ler a entrada do usuário.
     * @return A nota válida fornecida pelo usuário.
     */
    private static float obterNotaValida(Scanner scanner) {
        float nota = 0;
        boolean notaValida = false;
        while (!notaValida) {
            try {
                System.out.print("Nota (0/5): ");
                nota = scanner.nextFloat();
                if (nota < 0 || nota > 5) {
                    System.out.println("Nota deve estar entre 0 e 5.");
                } else {
                    notaValida = true;
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, insira um número válido entre 0 e 5.");
                scanner.next(); // Consumir a entrada inválida
            }
        }
        return nota;
    }
}