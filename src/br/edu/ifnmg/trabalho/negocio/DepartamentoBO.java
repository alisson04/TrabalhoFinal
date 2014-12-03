/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.trabalho.negocio;

import br.edu.ifnmg.trabalho.dados.DepartamentoDAO;
import br.edu.ifnmg.trabalho.entidade.Departamento;
import javax.swing.JOptionPane;

/**
 *
 * @author Andre
 */
public class DepartamentoBO {

    public boolean validar(Departamento departamento) {
        DepartamentoDAO departamentoDAO = new DepartamentoDAO();
        if (departamentoDAO.codigo(departamento.getCodigo())) {
            if (departamentoDAO.nome(departamento.getNome())) {
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Nome invalido!", "Erro", JOptionPane.WARNING_MESSAGE);
                return false;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Codigo invalido!", "Erro", JOptionPane.WARNING_MESSAGE);
            return false;
        }
    }
}
