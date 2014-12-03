/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.trabalho.entidade;

import java.sql.Date;

/**
 *
 * @author Andre
 */
public class Relatorio {
    private String nomeProjeto;
    private String nomeDepartamento;
    private String nomeGerente;
    private Date inicioProjeto;
    private Date finalProjeto;
    private int totalAtividade;
    private int totalAtividadeConcluida;

    public String getNomeProjeto() {
        return nomeProjeto;
    }

    public void setNomeProjeto(String nomeProjeto) {
        this.nomeProjeto = nomeProjeto;
    }

    public String getNomeDepartamento() {
        return nomeDepartamento;
    }

    public void setNomeDepartamento(String nomeDepartamento) {
        this.nomeDepartamento = nomeDepartamento;
    }

    public String getNomeGerente() {
        return nomeGerente;
    }

    public void setNomeGerente(String nomeGerente) {
        this.nomeGerente = nomeGerente;
    }

    public Date getInicioProjeto() {
        return inicioProjeto;
    }

    public void setInicioProjeto(Date inicioProjeto) {
        this.inicioProjeto = inicioProjeto;
    }

    public Date getFinalProjeto() {
        return finalProjeto;
    }

    public void setFinalProjeto(Date finalProjeto) {
        this.finalProjeto = finalProjeto;
    }

    public int getTotalAtividade() {
        return totalAtividade;
    }

    public void setTotalAtividade(int totalAtividade) {
        this.totalAtividade = totalAtividade;
    }

    public int getTotalAtividadeConcluida() {
        return totalAtividadeConcluida;
    }

    public void setTotalAtividadeConcluida(int totalAtividadeConcluida) {
        this.totalAtividadeConcluida = totalAtividadeConcluida;
    }
    
    
}
