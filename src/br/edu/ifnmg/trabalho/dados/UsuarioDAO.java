/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.trabalho.dados;

import br.edu.ifnmg.trabalho.entidade.Departamento;
import br.edu.ifnmg.trabalho.entidade.Usuario;
import br.edu.ifnmg.trabalho.negocio.UsuarioBO;
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
public class UsuarioDAO {

    public List<Usuario> encarregadoPorDepartamento(int idDepartamento) {
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        List<Usuario> lista = new ArrayList<>();
        try {
            conexao = BDUtil.getConnection();
            comando = conexao.prepareStatement("SELECT * FROM USUARIO WHERE CARGO=? AND DEPARTAMENTO=?");
            comando.setString(1, "ENCARREGADO");
            comando.setInt(2, idDepartamento);
            resultado = comando.executeQuery();
            while (resultado.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(resultado.getInt("ID"));
                usuario.setNome(resultado.getString("NOME"));
                usuario.setEmail(resultado.getString("EMAIL"));
                usuario.setSenha(resultado.getString("SENHA"));
                usuario.setCargo(resultado.getString("CARGO"));
                DepartamentoDAO departamentoDAO = new DepartamentoDAO();
                for (Departamento departamento : departamentoDAO.listaDepartamento()) {
                    if (departamento.getId() == idDepartamento) {
                        usuario.setDepartamento(departamento);
                    }
                }
                lista.add(usuario);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return lista;
    }

    public void demitir(int id) throws SQLException {
        Connection conexao = null;
        PreparedStatement comando = null;
        try {
            conexao = BDUtil.getConnection();
            comando = conexao.prepareStatement("DELETE FROM USUARIO WHERE ID=?");
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

    public List<Usuario> listaEncarregado() {
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        List<Usuario> lista = new ArrayList<>();
        try {
            conexao = BDUtil.getConnection();
            comando = conexao.prepareStatement("SELECT * FROM USUARIO WHERE CARGO=?");
            comando.setString(1, "ENCARREGADO");
            resultado = comando.executeQuery();
            while (resultado.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(resultado.getInt("ID"));
                usuario.setNome(resultado.getString("NOME"));
                usuario.setEmail(resultado.getString("EMAIL"));
                usuario.setSenha(resultado.getString("SENHA"));
                usuario.setCargo(resultado.getString("CARGO"));
                DepartamentoDAO departamentoDAO = new DepartamentoDAO();
                for (Departamento departamento : departamentoDAO.listaDepartamento()) {
                    if (departamento.getId() == resultado.getInt("DEPARTAMENTO")) {
                        usuario.setDepartamento(departamento);
                    }
                }
                lista.add(usuario);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return lista;
    }

    public void editar(Usuario usuario) throws SQLException {
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        String nomeUsuario = null, emailUsuario = null;
        try {
            conexao = BDUtil.getConnection();
            comando = conexao.prepareStatement("SELECT * FROM USUARIO WHERE ID=?");
            comando.setInt(1, usuario.getId());
            resultado = comando.executeQuery();
            if (resultado.next()) {
                nomeUsuario = resultado.getString("NOME");
                emailUsuario = resultado.getString("EMAIL");
            }
            if (!usuario.getNome().equals(nomeUsuario)) {
                comando = conexao.prepareStatement("SELECT * FROM USUARIO WHERE NOME=?");
                comando.setString(1, usuario.getNome());
                resultado = comando.executeQuery();
                if (resultado.next()) {
                    JOptionPane.showMessageDialog(null, "Nome ja possui cadastro!", "Erro", JOptionPane.WARNING_MESSAGE);
                } else {
                    comando = conexao.prepareStatement("UPDATE USUARIO SET NOME=? WHERE ID=?");
                    comando.setString(1, usuario.getNome());
                    comando.setInt(2, usuario.getId());
                    comando.execute();
                }
            }
            if (!usuario.getEmail().equals(emailUsuario)) {
                UsuarioBO usuarioBO = new UsuarioBO();
                if (usuarioBO.ValidaEmail(usuario.getEmail())) {
                    comando = conexao.prepareStatement("SELECT * FROM USUARIO WHERE EMAIL=?");
                    comando.setString(1, usuario.getEmail());
                    resultado = comando.executeQuery();
                    if (resultado.next()) {
                        JOptionPane.showMessageDialog(null, "Email ja possui cadastro!", "Erro", JOptionPane.WARNING_MESSAGE);
                    } else {
                        comando = conexao.prepareStatement("UPDATE USUARIO SET EMAIL=? WHERE ID=?");
                        comando.setString(1, usuario.getEmail());
                        comando.setInt(2, usuario.getId());
                        comando.execute();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Email invalido!", "Erro", JOptionPane.WARNING_MESSAGE);
                }
            }
            if (!usuario.getSenha().equals("")) {
                comando = conexao.prepareStatement("UPDATE USUARIO SET SENHA=? WHERE ID=?");
                comando.setString(1, usuario.getSenha());
                comando.setInt(2, usuario.getId());
                comando.execute();
            }
            if (usuario.getCargo().equals("GERENTE")) {
                if (usuario.getDepartamento() != null) {
                    UsuarioBO usuarioBO = new UsuarioBO();
                    if (gerente(usuario.getDepartamento().getId())) {
                        comando = conexao.prepareStatement("UPDATE USUARIO SET DEPARTAMENTO=? WHERE ID=?");
                        comando.setInt(1, usuario.getDepartamento().getId());
                        comando.setInt(2, usuario.getId());
                        comando.execute();
                    } else {
                        JOptionPane.showMessageDialog(null, "Ja possui um gerente nesse departamento!", "Erro", JOptionPane.WARNING_MESSAGE);
                    }
                }
            } else if (usuario.getCargo().equals("ENCARREGADO")) {
                if (usuario.getDepartamento() != null) {
                    UsuarioBO usuarioBO = new UsuarioBO();
                    comando = conexao.prepareStatement("UPDATE USUARIO SET DEPARTAMENTO=? WHERE ID=?");
                    comando.setInt(1, usuario.getDepartamento().getId());
                    comando.setInt(2, usuario.getId());
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

    public List<Usuario> listaGerente() {
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        List<Usuario> lista = new ArrayList<>();
        try {
            conexao = BDUtil.getConnection();
            comando = conexao.prepareStatement("SELECT * FROM USUARIO WHERE CARGO=?");
            comando.setString(1, "GERENTE");
            resultado = comando.executeQuery();
            while (resultado.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(resultado.getInt("ID"));
                usuario.setNome(resultado.getString("NOME"));
                usuario.setEmail(resultado.getString("EMAIL"));
                usuario.setSenha(resultado.getString("SENHA"));
                usuario.setCargo(resultado.getString("CARGO"));
                DepartamentoDAO departamentoDAO = new DepartamentoDAO();
                for (Departamento departamento : departamentoDAO.listaDepartamento()) {
                    if (departamento.getId() == resultado.getInt("DEPARTAMENTO")) {
                        usuario.setDepartamento(departamento);
                    }
                }
                lista.add(usuario);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return lista;
    }

    public void cadastrar(Usuario usuario) throws SQLException {
        Connection conexao = null;
        PreparedStatement comando = null;
        try {
            conexao = BDUtil.getConnection();
            comando = conexao.prepareStatement("INSERT INTO USUARIO(NOME,EMAIL,SENHA,CARGO,DEPARTAMENTO) VALUES(?,?,?,?,?)");
            comando.setString(1, usuario.getNome());
            comando.setString(2, usuario.getEmail());
            comando.setString(3, usuario.getSenha());
            comando.setString(4, usuario.getCargo());
            comando.setInt(5, usuario.getDepartamento().getId());
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

    public Usuario info(Usuario usuario) {
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        try {
            conexao = BDUtil.getConnection();
            comando = conexao.prepareStatement("SELECT * FROM USUARIO WHERE EMAIL=?");
            comando.setString(1, usuario.getEmail());
            resultado = comando.executeQuery();
            if (resultado.next()) {
                usuario.setId(resultado.getInt("Id"));
                usuario.setNome(resultado.getString("Nome"));
                usuario.setCargo(resultado.getString("Cargo"));
                DepartamentoDAO departamentoDAO = new DepartamentoDAO();
                for (Departamento departamento : departamentoDAO.listaDepartamento()) {
                    if (departamento.getId() == resultado.getInt("Departamento")) {
                        usuario.setDepartamento(departamento);
                    }
                }
            }
            return usuario;
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public String logar(Usuario usuario) {
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        try {
            conexao = BDUtil.getConnection();
            comando = conexao.prepareStatement("SELECT * FROM USUARIO WHERE EMAIL=? AND SENHA=?");
            comando.setString(1, usuario.getEmail());
            comando.setString(2, usuario.getSenha());
            resultado = comando.executeQuery();
            if (resultado.next()) {
                return resultado.getString("CARGO");
            } else {
                return "INVALIDO";
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean gerente(int departamento) {
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        try {
            conexao = BDUtil.getConnection();
            comando = conexao.prepareStatement("SELECT * FROM USUARIO WHERE CARGO=? and DEPARTAMENTO=?");
            comando.setString(1, "GERENTE");
            comando.setInt(2, departamento);
            resultado = comando.executeQuery();
            if (resultado.next()) {
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean email(String email) {
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        try {
            conexao = BDUtil.getConnection();
            comando = conexao.prepareStatement("SELECT * FROM USUARIO WHERE EMAIL=?");
            comando.setString(1, email);
            resultado = comando.executeQuery();
            if (resultado.next()) {
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean nome(String nome) {
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        try {
            conexao = BDUtil.getConnection();
            comando = conexao.prepareStatement("SELECT * FROM USUARIO WHERE NOME=?");
            comando.setString(1, nome);
            resultado = comando.executeQuery();
            if (resultado.next()) {
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
