/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.trabalho.dados;

import br.edu.ifnmg.trabalho.entidade.Atividade;
import br.edu.ifnmg.trabalho.entidade.Projeto;
import br.edu.ifnmg.trabalho.entidade.Usuario;
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
public class AtividadeDAO {

    public List<Atividade> listaAtividadeporProjeto(int idProjeto) {
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        List<Atividade> lista = new ArrayList<>();
        try {
            conexao = BDUtil.getConnection();
            comando = conexao.prepareStatement("SELECT * FROM ATIVIDADE WHERE PROJETO=? ORDER BY DURACAOPREVISTA");
            comando.setInt(1, idProjeto);
            resultado = comando.executeQuery();
            while (resultado.next()) {
                Atividade atividade = new Atividade();
                atividade.setId(resultado.getInt("ID"));
                atividade.setNome(resultado.getString("NOME"));
                ProjetoDAO projetoDAO = new ProjetoDAO();
                for (Projeto projeto : projetoDAO.listaProjetoAll()) {
                    if (projeto.getId() == resultado.getInt("Projeto")) {
                        atividade.setProjeto(projeto);
                    }
                }
                UsuarioDAO usuarioDAO = new UsuarioDAO();
                for (Usuario usuario : usuarioDAO.listaEncarregado()) {
                    if (usuario.getId() == resultado.getInt("ENCARREGADO")) {
                        atividade.setEncarregado(usuario);
                    }
                }
                atividade.setDuracaoPrevista(resultado.getDouble("DURACAOPREVISTA"));
                atividade.setTotalHoras(resultado.getDouble("TOTALHORAS"));
                atividade.setPercentual_conclusao(resultado.getDouble("PERCENTUAL_CONCLUSAO"));
                lista.add(atividade);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return lista;
    }
    
    public List<Atividade> listaAtividadeAtrasada(int encarregado) {
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        List<Atividade> lista = new ArrayList<>();
        try {
            conexao = BDUtil.getConnection();
            comando = conexao.prepareStatement("SELECT * FROM ATIVIDADE WHERE ENCARREGADO=?");
            comando.setInt(1, encarregado);
            resultado = comando.executeQuery();
            while (resultado.next()) {
                double duracaoPrevista = resultado.getDouble("DURACAOPREVISTA");
                double duracaoTotal = resultado.getDouble("TOTALHORAS");
                double percentual = resultado.getDouble("PERCENTUAL_CONCLUSAO");
                if (duracaoPrevista <= duracaoTotal && percentual < 100) {
                    Atividade atividade = new Atividade();
                    atividade.setId(resultado.getInt("ID"));
                    atividade.setNome(resultado.getString("NOME"));
                    for (Projeto projeto : new ProjetoDAO().listaProjetoAll()) {
                        if (projeto.getId() == resultado.getInt("PROJETO")) {
                            atividade.setProjeto(projeto);
                        }
                    }
                    for (Usuario usuario : new UsuarioDAO().listaEncarregado()) {
                        if (usuario.getId() == resultado.getInt("ENCARREGADO")) {
                            atividade.setEncarregado(usuario);
                        }
                    }
                    atividade.setDuracaoPrevista(duracaoPrevista);
                    atividade.setTotalHoras(duracaoTotal);
                    atividade.setPercentual_conclusao(percentual);
                    lista.add(atividade);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return lista;
    }

    public List<Atividade> listaAtividade(int idEncarregado) {
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        List<Atividade> lista = new ArrayList<>();
        try {
            conexao = BDUtil.getConnection();
            comando = conexao.prepareStatement("SELECT * FROM ATIVIDADE WHERE ENCARREGADO=?");
            comando.setInt(1, idEncarregado);
            resultado = comando.executeQuery();
            while (resultado.next()) {
                Atividade atividade = new Atividade();
                atividade.setId(resultado.getInt("ID"));
                atividade.setNome(resultado.getString("NOME"));
                ProjetoDAO projetoDAO = new ProjetoDAO();
                for (Projeto projeto : projetoDAO.listaProjetoAll()) {
                    if (projeto.getId() == resultado.getInt("Projeto")) {
                        atividade.setProjeto(projeto);
                    }
                }
                UsuarioDAO usuarioDAO = new UsuarioDAO();
                for (Usuario usuario : usuarioDAO.listaEncarregado()) {
                    if (usuario.getId() == resultado.getInt("ENCARREGADO")) {
                        atividade.setEncarregado(usuario);
                    }
                }
                atividade.setDuracaoPrevista(resultado.getDouble("DURACAOPREVISTA"));
                atividade.setTotalHoras(resultado.getDouble("TOTALHORAS"));
                atividade.setPercentual_conclusao(resultado.getDouble("PERCENTUAL_CONCLUSAO"));
                lista.add(atividade);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return lista;
    }

    public void lancaHora(int atividade, double hora, double percentual) throws SQLException {
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        try {
            double horaAtual = 0, percentual_atual = 0;
            conexao = BDUtil.getConnection();
            comando = conexao.prepareStatement("SELECT * FROM ATIVIDADE WHERE ID=?");
            comando.setInt(1, atividade);
            resultado = comando.executeQuery();
            while (resultado.next()) {
                horaAtual = resultado.getDouble("TOTALHORAS");
                percentual_atual = resultado.getDouble("PERCENTUAL_CONCLUSAO");
            }
            comando = conexao.prepareStatement("UPDATE ATIVIDADE SET TOTALHORAS=?, PERCENTUAL_CONCLUSAO=? WHERE ID=?");
            comando.setDouble(1, hora + horaAtual);
            comando.setDouble(2, percentual);
            comando.setInt(3, atividade);
            comando.execute();
            JOptionPane.showMessageDialog(null, "Lançamento realizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
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

    public void cadastrar(Atividade atividade) throws SQLException {
        Connection conexao = null;
        PreparedStatement comando = null;
        try {
            conexao = BDUtil.getConnection();
            comando = conexao.prepareStatement("INSERT INTO ATIVIDADE(NOME,PROJETO,ENCARREGADO,DURACAOPREVISTA,TOTALHORAS,PERCENTUAL_CONCLUSAO) VALUES(?,?,?,?,?,?)");
            comando.setString(1, atividade.getNome());
            comando.setInt(2, atividade.getProjeto().getId());
            comando.setInt(3, atividade.getEncarregado().getId());
            comando.setDouble(4, atividade.getDuracaoPrevista());
            comando.setDouble(5, 0);
            comando.setDouble(6, 0);
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
