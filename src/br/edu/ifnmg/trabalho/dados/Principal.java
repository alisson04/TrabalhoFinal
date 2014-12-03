/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.trabalho.dados;

import br.edu.ifnmg.trabalho.apresentacao.CadastroDiretorForm;
import br.edu.ifnmg.trabalho.apresentacao.LoginForm;
import br.edu.ifnmg.trabalho.negocio.DiretorBO;
import java.sql.SQLException;

/**
 *
 * @author Andre
 */
public class Principal {

    public static void main(String args[]) throws SQLException{
        DiretorBO diretorBO = new DiretorBO();
        
        if(diretorBO.verificarDiretor().equals("NAO POSSUI")){
            CadastroDiretorForm cadastroDiretorForm = new CadastroDiretorForm();
            cadastroDiretorForm.setVisible(true);
        }else if(diretorBO.verificarDiretor().equals("POSSUI")){
            LoginForm loginForm = new LoginForm();
            loginForm.setVisible(true);
        }
    }
}
