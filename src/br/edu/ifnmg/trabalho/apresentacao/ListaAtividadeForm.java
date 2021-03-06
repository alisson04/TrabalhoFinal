/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.trabalho.apresentacao;

import br.edu.ifnmg.trabalho.dados.AtividadeDAO;
import br.edu.ifnmg.trabalho.entidade.Atividade;
import br.edu.ifnmg.trabalho.entidade.Usuario;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Andre
 */
public class ListaAtividadeForm extends javax.swing.JInternalFrame {

    /**
     * Creates new form ListaAtividadeForm
     */
    private Usuario usuario;
    public ListaAtividadeForm(Usuario usuario) {
        initComponents();
        this.usuario = usuario;
        this.ConfigurarTblAtividade();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblAtividade = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Atividade");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Dados das Atividades", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 18))); // NOI18N

        tblAtividade.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblAtividade);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 750, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(206, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblAtividade;
    // End of variables declaration//GEN-END:variables

    private void ConfigurarTblAtividade(){
        TableAtividadeModel model = new TableAtividadeModel(new AtividadeDAO().listaAtividadeAtrasada(this.usuario.getDepartamento().getId()));
        tblAtividade.setModel(model);
    }
    private class TableAtividadeModel extends AbstractTableModel{

        private List<Atividade> lista;

        public TableAtividadeModel(List<Atividade> lista) {
            this.lista = lista;
        }
        
        @Override
        public int getRowCount() {
            return lista.size();
        }

        @Override
        public int getColumnCount() {
            return 6;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            Atividade atividade = lista.get(rowIndex);
            if(columnIndex == 0){
                return atividade.getNome();
            }else if(columnIndex == 1){
                return atividade.getProjeto().getNome();
            }else if(columnIndex == 2){
                return atividade.getEncarregado().getNome();
            }else if(columnIndex == 3){
                return atividade.getDuracaoPrevista();
            }else if(columnIndex == 4){
                return atividade.getTotalHoras();
            }else if(columnIndex == 5){
                return atividade.getPercentual_conclusao() + "%";
            }
            return null;
        }

        @Override
        public String getColumnName(int column) {
            if(column == 0){
                return "Nome";
            }else if(column == 1){
                return "Projeto";
            }else if(column == 2){
                return "Encarregado";
            }else if(column == 3){
                return "Duração Prevista";
            }else if(column == 4){
                return "Horas Tabalhada";
            }else if(column == 5){
                return "Percentual Concluido";
            }
            return null;
        }
        
        
    }
}
