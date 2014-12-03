/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.trabalho.negocio;

import br.edu.ifnmg.trabalho.dados.GerarLog;
import br.edu.ifnmg.trabalho.dados.UsuarioDAO;
import br.edu.ifnmg.trabalho.entidade.Usuario;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JOptionPane;

/**
 *
 * @author Andre
 */
public class UsuarioBO {

    public boolean validarEncarregado(Usuario usuario) {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        if (usuarioDAO.nome(usuario.getNome())) {
            if (usuarioDAO.email(usuario.getEmail()) || ValidaEmail(usuario.getEmail())) {
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Email invalido", "Erro", JOptionPane.WARNING_MESSAGE);
                return false;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Nome invalido", "Erro", JOptionPane.WARNING_MESSAGE);
            return false;
        }
    }

    public boolean validarGerente(Usuario usuario) {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        if (usuarioDAO.nome(usuario.getNome())) {
            if (usuarioDAO.email(usuario.getEmail()) || ValidaEmail(usuario.getEmail())) {
                if (usuarioDAO.gerente(usuario.getDepartamento().getId())) {
                    return true;
                } else {
                    JOptionPane.showMessageDialog(null, "Ja possui gerente neste departamento!", "Erro", JOptionPane.WARNING_MESSAGE);
                    return false;
                }
            } else {
                JOptionPane.showMessageDialog(null, "Email invalido", "Erro", JOptionPane.WARNING_MESSAGE);
                return false;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Nome invalido", "Erro", JOptionPane.WARNING_MESSAGE);
            return false;
        }
    }

    public boolean ValidaEmail(String email) {
        if ((email.contains("@")) && (email.contains(".")) && (!email.contains(" "))) {
            String nomeEmail = new String(email.substring(0, email.lastIndexOf('@')));
            String dominio = new String(email.substring(email.lastIndexOf('@') + 1, email.length()));
            if ((nomeEmail.length() >= 1) && (!nomeEmail.contains("@")) && (dominio.contains(".")) && (!dominio.contains("@")) && (dominio.indexOf(".") >= 1) && (dominio.lastIndexOf(".") < dominio.length() - 1)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public String criptografar(String senha) {
        String chaveencriptacao = "x9x$%&ab54@opa.0";
        String IV = "AAAAAAAAAAAAAAAA";
        String senhaCriptografada = null;
        try {

            byte[] textoencriptado = encrypt(senha, chaveencriptacao, IV);

            for (int i = 0; i < textoencriptado.length; i++) {
                senhaCriptografada += Integer.toString(new Integer(textoencriptado[i]));
            }
        } catch (Exception e) {
            //e.printStackTrace();
            String erro = e.getMessage();
            GerarLog log = new GerarLog();
            log.LogTxt("Erro na criptografia!", erro);
        }
        return senhaCriptografada;
    }

    public static byte[] encrypt(String senha, String chaveencriptacao, String IV) throws Exception {
        Cipher encripta = Cipher.getInstance("AES/CBC/PKCS5Padding", "SunJCE");
        SecretKeySpec key = new SecretKeySpec(chaveencriptacao.getBytes("UTF-8"), "AES");
        encripta.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(IV.getBytes("UTF-8")));
        return encripta.doFinal(senha.getBytes("UTF-8"));
    }
}
