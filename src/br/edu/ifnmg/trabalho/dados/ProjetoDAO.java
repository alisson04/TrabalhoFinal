/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.trabalho.dados;

import br.edu.ifnmg.trabalho.entidade.Departamento;
import br.edu.ifnmg.trabalho.entidade.Projeto;
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
public class ProjetoDAO {

    public List<Projeto> listaProjetoAll(){
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        List<Projeto> lista = new ArrayList<>();
        try{
            conexao = BDUtil.getConnection();
            comando = conexao.prepareStatement("SELECT * FROM PROJETO");
            resultado = comando.executeQuery();
            while(resultado.next()){
                Projeto projeto = new Projeto();
                projeto.setId(resultado.getInt("ID"));
                projeto.setNome(resultado.getString("NOME"));
                projeto.setDescricao(resultado.getString("DESCRICAO"));
                projeto.setInicio(resultado.getDate("INICIO"));
                projeto.setFim(resultado.getDate("FIM"));
                DepartamentoDAO departamentoDAO = new DepartamentoDAO();
                for(Departamento departamento: departamentoDAO.listaDepartamento()){
                    if(departamento.getId() == resultado.getInt("DEPARTAMENTO")) projeto.setDepartamento(departamento);
                }
                lista.add(projeto);
            }
        }catch(Exception e){
            throw new RuntimeException(e);
        }
        return lista;
    }
    public List<Projeto> listaProjeto(int idDepartamento){
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        List<Projeto> lista = new ArrayList<>();
        try{
            conexao = BDUtil.getConnection();
            comando = conexao.prepareStatement("SELECT * FROM PROJETO WHERE DEPARTAMENTO=?");
            comando.setInt(1, idDepartamento);
            resultado = comando.executeQuery();
            while(resultado.next()){
                Projeto projeto = new Projeto();
                projeto.setId(resultado.getInt("ID"));
                projeto.setNome(resultado.getString("NOME"));
                projeto.setDescricao(resultado.getString("DESCRICAO"));
                projeto.setInicio(resultado.getDate("INICIO"));
                projeto.setFim(resultado.getDate("FIM"));
                DepartamentoDAO departamentoDAO = new DepartamentoDAO();
                for(Departamento departamento: departamentoDAO.listaDepartamento()){
                    if(departamento.getId() == resultado.getInt("DEPARTAMENTO")) projeto.setDepartamento(departamento);
                }
                lista.add(projeto);
            }
        }catch(Exception e){
            throw new RuntimeException(e);
        }
        return lista;
    }
    public void cadastrar(Projeto projeto) throws SQLException {
        Connection conexao = null;
        PreparedStatement comando = null;
        try {
            conexao = BDUtil.getConnection();
            comando = conexao.prepareStatement("INSERT INTO PROJETO(NOME,DESCRICAO,INICIO,FIM,DEPARTAMENTO) VALUES(?,?,?,?,?)");
            comando.setString(1, projeto.getNome());
            comando.setString(2, projeto.getDescricao());
            comando.setDate(3, projeto.getInicio());
            comando.setDate(4, projeto.getFim());
            comando.setInt(5, projeto.getDepartamento().getId());
            comando.execute();
            JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
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
}
