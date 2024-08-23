/**
 * Classe base para Data Access Objects (DAOs), fornecendo funcionalidades básicas de conexão e 
 * gerenciamento de banco de dados, além de um método utilitário para criptografia MD5.
 *
 * @author João Victor Maia <github.com/JoaoMaia77>
 * @version 1.0
 */
package dao;

import java.sql.*;
import java.security.*;
import java.math.*;

public class DAO {
    /**
     * Conexão com o banco de dados.
     */
    protected Connection conexao;

    /**
     * Construtor da classe DAO. Inicializa a conexão como null.
     */
    public DAO() {
        conexao = null;
    }

    /**
     * Estabelece uma conexão com o banco de dados PostgreSQL.
     *
     * @return true se a conexão for estabelecida com sucesso, false caso contrário.
     */
    public boolean conectar() {
        String driverName = "org.postgresql.Driver";            
        String serverName = "localhost";
        String mydatabase = "postgres";
        int porta = 5432;
        String url = "jdbc:postgresql://" + serverName + ":" + porta +"/" + mydatabase;
        String username = "Seu username";
        String password = "Sua senha";
        boolean status = false;

        try {
            Class.forName(driverName);
            conexao = DriverManager.getConnection(url, username, password);
            status = (conexao != null); // Inverte a lógica para indicar sucesso na conexão
            System.out.println("Conexão efetuada com o postgres!");
        } catch (ClassNotFoundException e) { 
            System.err.println("Conexão NÃO efetuada com o postgres -- Driver não encontrado -- " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Conexão NÃO efetuada com o postgres -- " + e.getMessage());
        }

        return status;
    }

    /**
     * Fecha a conexão com o banco de dados.
     *
     * @return true se a conexão for fechada com sucesso, false caso contrário.
     */
    public boolean close() {
        boolean status = false;

        try {
            conexao.close();
            status = true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return status;
    }

    /**
     * Converte uma senha em texto plano para um hash MD5.
     *
     * @param senha A senha em texto plano a ser convertida.
     * @return O hash MD5 da senha, representado como uma string hexadecimal.
     * @throws Exception Se ocorrer um erro durante o processo de hashing.
     */
    public static String toMD5(String senha) throws Exception {
        MessageDigest m = MessageDigest.getInstance("MD5");
        m.update(senha.getBytes(), 0, senha.length());
        return new BigInteger(1, m.digest()).toString(16);
    }
}