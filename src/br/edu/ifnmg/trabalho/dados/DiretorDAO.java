/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.trabalho.dados;

import br.edu.ifnmg.trabalho.entidade.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Andre
 */
public class DiretorDAO {

    public void cadastrar(Usuario usuario) throws SQLException {
        Connection conexao = null;
        PreparedStatement comando = null;
        try {
            conexao = BDUtil.getConnection();
            comando = conexao.prepareStatement("INSERT INTO USUARIO(NOME,EMAIL,SENHA,CARGO) VALUES(?,?,?,?)");
            comando.setString(1, usuario.getNome());
            comando.setString(2, usuario.getEmail());
            comando.setString(3, usuario.getSenha());
            comando.setString(4, usuario.getCargo());
            comando.execute();
            conexao.commit();
        } catch (Exception e) {
            if (conexao != null) {
                conexao.rollback();
            }
            throw new RuntimeException(e);
        } finally {
            if (comando != null && !comando.isClosed()) {
                comando.close();
            }
            if (conexao != null && !conexao.isClosed()) {
                conexao.close();
            }
        }
    }

    public String verificar() throws SQLException {
        String validar = null;
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        try {
            conexao = BDUtil.getConnection();
            comando = conexao.prepareStatement("SELECT * FROM USUARIO WHERE CARGO=?");
            comando.setString(1, "DIRETOR");
            resultado = comando.executeQuery();
            if (resultado.next()) {
                validar = "POSSUI";
            } else {
                validar = "NAO POSSUI";
            }
            conexao.commit();
        } catch (Exception e) {
            if (conexao != null) {
                conexao.rollback();
            }
            throw new RuntimeException(e);
        } finally {
            if (comando != null && !comando.isClosed()) {
                comando.close();
            }
            if (conexao != null && !conexao.isClosed()) {
                conexao.close();
            }
        }
        return validar;
    }
}
