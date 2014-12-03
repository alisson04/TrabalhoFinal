/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.trabalho.entidade;

/**
 *
 * @author Andre
 */
public class Atividade {
    private int id;
    private String nome;
    private Projeto projeto;
    private Usuario encarregado;
    private double duracaoPrevista;
    private double totalHoras;
    private double percentual_conclusao;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Projeto getProjeto() {
        return projeto;
    }

    public void setProjeto(Projeto projeto) {
        this.projeto = projeto;
    }

    public Usuario getEncarregado() {
        return encarregado;
    }

    public void setEncarregado(Usuario encarregado) {
        this.encarregado = encarregado;
    }

    public double getDuracaoPrevista() {
        return duracaoPrevista;
    }

    public void setDuracaoPrevista(double duracaoPrevista) {
        this.duracaoPrevista = duracaoPrevista;
    }

    public double getTotalHoras() {
        return totalHoras;
    }

    public void setTotalHoras(double totalHoras) {
        this.totalHoras = totalHoras;
    }

    public double getPercentual_conclusao() {
        return percentual_conclusao;
    }

    public void setPercentual_conclusao(double percentual_conclusao) {
        this.percentual_conclusao = percentual_conclusao;
    }

    @Override
    public String toString() {
        return this.nome;
    }
 
    
    
}