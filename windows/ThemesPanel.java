
package CrazyClients.windows;

import CrazyClients.CrazyClient;
import CrazyClients.MySQLClient;
import CrazyClients.data.Data;
import java.util.ArrayList;
import javax.swing.JOptionPane;
/**
 *
 * @author Александр Машьянов, mashyanov1987@gmail.com
 */

public class ThemesPanel extends javax.swing.JPanel {
    private final MySQLClient msqlc = MySQLClient.GetInstance();
    private final Data data = new Data();
    
    public ThemesPanel() {
        initComponents();
        themesList.setModel(data);
        fillTheList();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        themesList = new javax.swing.JList<>();
        addButton = new javax.swing.JButton();
        editButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();

        themesList.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        themesList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        themesList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        themesList.setFixedCellHeight(30);
        themesList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                themesListValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(themesList);

        addButton.setText("Добавить");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        editButton.setText("Редактировать");
        editButton.setEnabled(false);
        editButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editButtonActionPerformed(evt);
            }
        });

        deleteButton.setText("Удалить");
        deleteButton.setEnabled(false);
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(addButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(editButton, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
                    .addComponent(deleteButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(editButton, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(deleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void themesListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_themesListValueChanged
        editButton.setEnabled(!themesList.isSelectionEmpty());
        deleteButton.setEnabled(!themesList.isSelectionEmpty());
    }//GEN-LAST:event_themesListValueChanged

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        String s = JOptionPane.showInputDialog("Введите название запроса");
        if(s==null) return;
        s = s.trim();
        s = s.replaceAll("( )+", " ");
        s = s.toLowerCase();
        s = s.replaceFirst(s.substring(0, 1), s.substring(0, 1).toUpperCase());
        msqlc.addTheme(s);
        fillTheList();
    }//GEN-LAST:event_addButtonActionPerformed

    private void editButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editButtonActionPerformed
        String s = JOptionPane.showInputDialog("Редактирование запроса", themesList.getSelectedValue());
        s = s.trim();
        s = s.replaceAll("( )+", " ");
        s = s.toLowerCase();
        s = s.replaceFirst(s.substring(0, 1), s.substring(0, 1).toUpperCase());
        msqlc.editTheme(themesList.getSelectedValue(), s);
        fillTheList();
    }//GEN-LAST:event_editButtonActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
       
        StringBuilder stb = new StringBuilder("Невозможно удалить \"");
        stb.append(themesList.getSelectedValue());
        stb.append("\",\nт.к. этот запрос присвоен следующим клиентам:\n\n");
        ArrayList<CrazyClient> clients = msqlc.getClientsByTheme(themesList.getSelectedValue());
        if(!clients.isEmpty()){
            for (CrazyClient client : clients) {
                stb.append(client.getLastName());
                stb.append(" ");
                stb.append(client.getFirstName());
                stb.append("\n\n");
            }
            JOptionPane.showMessageDialog(this, stb.toString(), "Ошибка", JOptionPane.ERROR_MESSAGE);
            return;
        }
        msqlc.removeTheme(themesList.getSelectedValue());
        
        fillTheList();
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void fillTheList(){
        data.clear();
        for (String theme : msqlc.getThemes()) 
            data.add(theme);
        data.fire();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JButton deleteButton;
    private javax.swing.JButton editButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList<String> themesList;
    // End of variables declaration//GEN-END:variables
}
