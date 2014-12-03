/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.trabalho.dados;

import br.edu.ifnmg.trabalho.entidade.Departamento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Andre
 */
public class DepartamentoDAO {

    public void excluir(int id) throws SQLException {
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        try {
            conexao = BDUtil.getConnection();
            comando = conexao.prepareStatement("DELETE FROM DEPARTAMENTO WHERE ID=?");
            comando.setInt(1, id);
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

    public void editar(Departamento departamento) throws SQLException {
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        String codigoDepartamento = null, nomeDepartamento = null;
        try {
            conexao = BDUtil.getConnection();
            comando = conexao.prepareStatement("SELECT * FROM DEPARTAMENTO WHERE ID=?");
            comando.setInt(1, departamento.getId());
            resultado = comando.executeQuery();
            if (resultado.next()) {
                codigoDepartamento = resultado.getString("CODIGO");
                nomeDepartamento = resultado.getString("NOME");
            }
            if (!departamento.getNome().equals(nomeDepartamento)) {
                comando = conexao.prepareStatement("SELECT * FROM DEPARTAMENTO WHERE NOME=?");
                comando.setString(1, departamento.getNome());
                resultado = comando.executeQuery();
                if (resultado.next()) {
                    JOptionPane.showMessageDialog(null, "Nome ja possui cadastro!", "Erro", JOptionPane.WARNING_MESSAGE);
                } else {
                    comando = conexao.prepareStatement("UPDATE DEPARTAMENTO SET NOME=? WHERE ID=?");
                    comando.setString(1, departamento.getNome());
                    comando.setInt(2, departamento.getId());
                    comando.execute();
                }
            }
            if (!departamento.getCodigo().equals(codigoDepartamento)) {
                comando = conexao.prepareStatement("SELECT * FROM DEPARTAMENTO WHERE CODIGO=?");
                comando.setString(1, departamento.getCodigo());
                resultado = comando.executeQuery();
                if (resultado.next()) {
                    JOptionPane.showMessageDialog(null, "Codigo ja possui cadastro!", "Erro", JOptionPane.WARNING_MESSAGE);
                } else {
                    comando = conexao.prepareStatement("UPDATE DEPARTAMENTO SET CODIGO=? WHERE ID=?");
                    comando.setString(1, departamento.getCodigo());
                    comando.setInt(2, departamento.getId());
                    comando.execute();
                }
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
    }

    public List<Departamento> listaDepartamento() {
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        List<Departamento> lista = new ArrayList<>();
        try {
            conexao = BDUtil.getConnection();
            comando = conexao.prepareStatement("SELECT * FROM DEPARTAMENTO ORDER BY NOME");
            resultado = comando.executeQuery();
            while (resultado.next()) {
                Departamento departamento = new Departamento();
                departamento.setId(resultado.getInt("Id"));
                departamento.setCodigo(resultado.getString("Codigo"));
                departamento.setNome(resultado.getString("Nome"));
                lista.add(departamento);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }

    public void cadastrar(Departamento departamento) throws SQLException {
        Connection conexao = null;
        PreparedStatement comando = null;
        try {
            conexao = BDUtil.getConnection();
            comando = conexao.prepareStatement("INSERT INTO DEPARTAMENTO(CODIGO,NOME) VALUES(?,?)");
            comando.setString(1, departamento.getCodigo());
            comando.setString(2, departamento.getNome());
            comando.execute();
            conexao.commit();
        } catch (SQLException e) {
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

    public boolean codigo(String codigo) {
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        try {
            conexao = BDUtil.getConnection();
            comando = conexao.prepareStatement("SELECT * FROM DEPARTAMENTO WHERE CODIGO=?");
            comando.setString(1, codigo);
            resultado = comando.executeQuery();
            if (resultado.next()) {
                return false;
            } else {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean nome(String nome) {
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        try {
            conexao = BDUtil.getConnection();
            comando = conexao.prepareStatement("SELECT * FROM DEPARTAMENTO WHERE NOME=?");
            comando.setString(1, nome);
            resultado = comando.executeQuery();
            if (resultado.next()) {
                return false;
            } else {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
