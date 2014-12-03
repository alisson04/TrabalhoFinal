/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.trabalho.apresentacao;

import br.edu.ifnmg.trabalho.dados.UsuarioDAO;
import br.edu.ifnmg.trabalho.entidade.Usuario;

/**
 *
 * @author Andre
 */
public class EncarregadoForm extends javax.swing.JFrame {

    /**
     * Creates new form EncarregadoForm
     */
    private Usuario usuario;
    public EncarregadoForm(Usuario usuario) {
        initComponents();
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        this.usuario = usuarioDAO.info(usuario);
        lblUsuario.setText(this.usuario.getNome());
        lblTipo.setText(usuario.getCargo());
        lblDepartamento.setText(usuario.getDepartamento().getNome());
        AtrasoForm atrasoForm = new AtrasoForm(this.usuario);
        pnlPrincipal.add(atrasoForm);
        atrasoForm.setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlInformacao = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lblUsuario = new javax.swing.JLabel();
        lblTipo = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblDepartamento = new javax.swing.JLabel();
        pnlPrincipal = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        itmDadosPessoais = new javax.swing.JMenuItem();
        itmLogoff = new javax.swing.JMenuItem();
        itmSair = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        itmHorasTrabalhada = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jLabel1.setText("Usuario:");

        lblUsuario.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N

        lblTipo.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jLabel2.setText("Departamento:");

        lblDepartamento.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N

        javax.swing.GroupLayout pnlInformacaoLayout = new javax.swing.GroupLayout(pnlInformacao);
        pnlInformacao.setLayout(pnlInformacaoLayout);
        pnlInformacaoLayout.setHorizontalGroup(
            pnlInformacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInformacaoLayout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDepartamento, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(190, Short.MAX_VALUE))
        );
        pnlInformacaoLayout.setVerticalGroup(
            pnlInformacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE)
            .addComponent(lblUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblTipo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblDepartamento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout pnlPrincipalLayout = new javax.swing.GroupLayout(pnlPrincipal);
        pnlPrincipal.setLayout(pnlPrincipalLayout);
        pnlPrincipalLayout.setHorizontalGroup(
            pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pnlPrincipalLayout.setVerticalGroup(
            pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 466, Short.MAX_VALUE)
        );

        jMenu1.setText("Opção");

        itmDadosPessoais.setText("Dados Pessoais");
        itmDadosPessoais.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itmDadosPessoaisActionPerformed(evt);
            }
        });
        jMenu1.add(itmDadosPessoais);

        itmLogoff.setText("Logoff");
        itmLogoff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itmLogoffActionPerformed(evt);
            }
        });
        jMenu1.add(itmLogoff);

        itmSair.setText("Sair");
        itmSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itmSairActionPerformed(evt);
            }
        });
        jMenu1.add(itmSair);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Lançar Horas");

        itmHorasTrabalhada.setText("Horas Trabalhada");
        itmHorasTrabalhada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itmHorasTrabalhadaActionPerformed(evt);
            }
        });
        jMenu2.add(itmHorasTrabalhada);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlInformacao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlInformacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void itmSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itmSairActionPerformed
        System.exit(0);
    }//GEN-LAST:event_itmSairActionPerformed

    private void itmLogoffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itmLogoffActionPerformed
        LoginForm form = new LoginForm();
        form.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_itmLogoffActionPerformed

    private void itmDadosPessoaisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itmDadosPessoaisActionPerformed
        DadosPessoaisForm dadosPessoaisForm = new DadosPessoaisForm(usuario);
        pnlPrincipal.add(dadosPessoaisForm);
        dadosPessoaisForm.setVisible(true);
    }//GEN-LAST:event_itmDadosPessoaisActionPerformed

    private void itmHorasTrabalhadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itmHorasTrabalhadaActionPerformed
        LancaHorasForm form = new LancaHorasForm(this.usuario);
        pnlPrincipal.add(form);
        form.setVisible(true);
    }//GEN-LAST:event_itmHorasTrabalhadaActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem itmDadosPessoais;
    private javax.swing.JMenuItem itmHorasTrabalhada;
    private javax.swing.JMenuItem itmLogoff;
    private javax.swing.JMenuItem itmSair;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JLabel lblDepartamento;
    private javax.swing.JLabel lblTipo;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JPanel pnlInformacao;
    private javax.swing.JPanel pnlPrincipal;
    // End of variables declaration//GEN-END:variables
}
