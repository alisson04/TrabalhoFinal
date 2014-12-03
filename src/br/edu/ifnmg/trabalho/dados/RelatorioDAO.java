/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.trabalho.dados;

import br.edu.ifnmg.trabalho.entidade.Atividade;
import br.edu.ifnmg.trabalho.entidade.Departamento;
import br.edu.ifnmg.trabalho.entidade.Relatorio;
import br.edu.ifnmg.trabalho.entidade.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Andre
 */
public class RelatorioDAO {
    
    public List<Relatorio> listaRelatorioPorDepartamento(int idDepartamento) {
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        List<Relatorio> lista = new ArrayList<>();
        try {
            conexao = BDUtil.getConnection();
            comando = conexao.prepareStatement("SELECT * FROM PROJETO WHERE DEPARTAMENTO=?");
            comando.setInt(1, idDepartamento);
            resultado = comando.executeQuery();
            while (resultado.next()) {
                Relatorio relatorio = new Relatorio();
                relatorio.setNomeProjeto(resultado.getString("NOME"));
                for (Departamento departamento : new DepartamentoDAO().listaDepartamento()) {
                    if (departamento.getId() == resultado.getInt("DEPARTAMENTO")) {
                        relatorio.setNomeDepartamento(departamento.getNome());
                        for (Usuario usuario : new UsuarioDAO().listaGerente()) {
                            if (usuario.getDepartamento().getId() == departamento.getId()) {
                                relatorio.setNomeGerente(usuario.getNome());
                            }
                        }
                    }
                }
                relatorio.setInicioProjeto(resultado.getDate("INICIO"));
                relatorio.setFinalProjeto(resultado.getDate("FIM"));
                int qtdAtividade = 0, qtdConcluida = 0;
                for (Atividade atividade : new AtividadeDAO().listaAtividadeporProjeto(resultado.getInt("ID"))) {
                    qtdAtividade++;
                    if (atividade.getPercentual_conclusao() == 100) {
                        qtdConcluida++;
                    }
                }
                relatorio.setTotalAtividade(qtdAtividade);
                relatorio.setTotalAtividadeConcluida(qtdConcluida);
                lista.add(relatorio);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return lista;
    }

    public List<Relatorio> listaRelatorio() {
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        List<Relatorio> lista = new ArrayList<>();
        try {
            conexao = BDUtil.getConnection();
            comando = conexao.prepareStatement("SELECT * FROM PROJETO");
            resultado = comando.executeQuery();
            while (resultado.next()) {
                Relatorio relatorio = new Relatorio();
                relatorio.setNomeProjeto(resultado.getString("NOME"));
                for (Departamento departamento : new DepartamentoDAO().listaDepartamento()) {
                    if (departamento.getId() == resultado.getInt("DEPARTAMENTO")) {
                        relatorio.setNomeDepartamento(departamento.getNome());
                        for (Usuario usuario : new UsuarioDAO().listaGerente()) {
                            if (usuario.getDepartamento().getId() == departamento.getId()) {
                                relatorio.setNomeGerente(usuario.getNome());
                            }
                        }
                    }
                }
                relatorio.setInicioProjeto(resultado.getDate("INICIO"));
                relatorio.setFinalProjeto(resultado.getDate("FIM"));
                int qtdAtividade = 0, qtdConcluida = 0;
                for (Atividade atividade : new AtividadeDAO().listaAtividadeporProjeto(resultado.getInt("ID"))) {
                    qtdAtividade++;
                    if (atividade.getPercentual_conclusao() == 100) {
                        qtdConcluida++;
                    }
                }
                relatorio.setTotalAtividade(qtdAtividade);
                relatorio.setTotalAtividadeConcluida(qtdConcluida);
                lista.add(relatorio);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return lista;
    }
}
