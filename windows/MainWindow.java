
package CrazyClients.windows;

import CrazyClients.CrazyClient;
import CrazyClients.data.Data;
import CrazyClients.MyFTPClient;
import CrazyClients.MySQLClient;
import CrazyClients.SearchFilter;
import java.awt.Component;
import java.awt.Dialog;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Александр Машьянов, mashyanov1987@gmail.com
 */
public  final class MainWindow extends javax.swing.JFrame {
    private final MySQLClient msqlc = MySQLClient.GetInstance();
    private final ArrayList<CrazyClient> crazyClients = new ArrayList<>();
    private final Data data = new Data();
    private final SearchFilter searchFilter = new SearchFilter(true);
    private int active  = 0;
    private int archive = 0;
    
    public MainWindow() {
       
        initComponents();
        
        wipeButton.setVisible(false);
        wipeButton.setEnabled(false);
//        wipeButton.setVisible(true);
//        wipeButton.setEnabled(true);
        clientList.setModel(data); 
        fillTheList();
   
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane = new javax.swing.JScrollPane();
        clientList = new javax.swing.JList<>();
        wipeButton = new javax.swing.JButton();
        addButton = new javax.swing.JButton();
        editButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        appointmentsButton = new javax.swing.JButton();
        searchField = new javax.swing.JTextField();
        shirkerButton = new javax.swing.JButton();
        archiveButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        archiveToggleButton = new javax.swing.JToggleButton();
        reportButton = new javax.swing.JButton();
        filterButton = new javax.swing.JToggleButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("CrazyClients");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        clientList.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        clientList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        clientList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        clientList.setMaximumSize(new java.awt.Dimension(32767, 32767));
        clientList.setMinimumSize(new java.awt.Dimension(125, 125));
        clientList.setValueIsAdjusting(true);
        clientList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                clientListMouseClicked(evt);
            }
        });
        clientList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                clientListValueChanged(evt);
            }
        });
        jScrollPane.setViewportView(clientList);

        wipeButton.setText("ВАЙП");
        wipeButton.setEnabled(false);
        wipeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                wipeButtonActionPerformed(evt);
            }
        });

        addButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        addButton.setText("Добавить");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        editButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        editButton.setText("Просмотр/правка");
        editButton.setEnabled(false);
        editButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editButtonActionPerformed(evt);
            }
        });

        deleteButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        deleteButton.setText("Удалить");
        deleteButton.setEnabled(false);
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        appointmentsButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        appointmentsButton.setText("Приёмы");
        appointmentsButton.setEnabled(false);
        appointmentsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                appointmentsButtonActionPerformed(evt);
            }
        });

        searchField.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        searchField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                searchFieldFocusGained(evt);
            }
        });
        searchField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchFieldKeyReleased(evt);
            }
        });

        shirkerButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        shirkerButton.setText("Кто долго не ходил");
        shirkerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                shirkerButtonActionPerformed(evt);
            }
        });

        archiveButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        archiveButton.setText("В архив");
        archiveButton.setEnabled(false);
        archiveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                archiveButtonActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Поиск по фамилии");

        archiveToggleButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        archiveToggleButton.setText("Показывать архив");
        archiveToggleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                archiveToggleButtonActionPerformed(evt);
            }
        });

        reportButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        reportButton.setText("Отчет по клиенту");
        reportButton.setEnabled(false);
        reportButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reportButtonActionPerformed(evt);
            }
        });

        filterButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        filterButton.setText("Использовать доп. фильтры");
        filterButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filterButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(searchField, javax.swing.GroupLayout.DEFAULT_SIZE, 340, Short.MAX_VALUE)
                            .addComponent(jScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 340, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(addButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(editButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(appointmentsButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(archiveToggleButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(deleteButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(shirkerButton, javax.swing.GroupLayout.DEFAULT_SIZE, 293, Short.MAX_VALUE)
                            .addComponent(reportButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(archiveButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(wipeButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(filterButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel1)
                .addGap(2, 2, 2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(editButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(appointmentsButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(reportButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(archiveButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(filterButton)
                        .addGap(46, 46, 46)
                        .addComponent(shirkerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(archiveToggleButton, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(wipeButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(deleteButton))
                    .addComponent(jScrollPane))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    

    private void wipeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_wipeButtonActionPerformed
        msqlc.wipeClientsTable();
        msqlc.wipeAppointmentsTable();
    }//GEN-LAST:event_wipeButtonActionPerformed
    
    private void changeClientActiveStatus(CrazyClient client){
        client.setActual(!client.isActual());
        String message = client.isActual() ? " возвращен из архива" : " перемещен в архив";
        if(msqlc.editClient(client))
            JOptionPane.showMessageDialog(this, 
                    "Клиент " + client.getLastName() +" "+client.getFirstName() 
                            + " "+ message, "Успешно!", JOptionPane.PLAIN_MESSAGE);
        
        if(msqlc.getClientsCount(false)==0){
            JOptionPane.showMessageDialog(this, "Архив пуст", "Архив пуст", 
                    JOptionPane.INFORMATION_MESSAGE);
        archiveToggleButton.setSelected(false);
        archiveToggleButton.setText("Показывать архив");
        }
        
        
        fillTheList();
       
        archiveButton.setText(clientList.getSelectedIndex()==-1 || 
                crazyClients.get(clientList.getSelectedIndex()).isActual() ? 
                "В архив" : "Из архива");
        clientList.grabFocus();
    }
    
    private void fillTheList(){
        crazyClients.clear();
        data.clear();
        for (CrazyClient crazyClient : msqlc.getClientsList(searchFilter)) {
            crazyClients.add(crazyClient);
            data.add(crazyClient.toString());
        }
        data.fire();
        
        StringBuilder stb = new StringBuilder("CrazyClients ");
        active = msqlc.getClientsCount(true);
        archive= msqlc.getClientsCount(false);
        stb.append('(');
        stb.append(active);
        stb.append(active%10 == 1 ? " активный, " : " активных, ");
        stb.append(archive);
        stb.append(" в архиве)");
        setTitle(stb.toString());
    }
    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        new ClientsWindow(this).setVisible(true);
        fillTheList();   
    }//GEN-LAST:event_addButtonActionPerformed

    private void editButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editButtonActionPerformed
        if(clientList.getSelectedIndex()!=-1){
            new ClientsWindow(this, msqlc.getClient(crazyClients.get(clientList.getSelectedIndex()).getId())).setVisible(true);
            fillTheList();
        }
    }//GEN-LAST:event_editButtonActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        CrazyClient client;
        if(clientList.getSelectedIndex()!=-1){
            client = crazyClients.get(clientList.getSelectedIndex());
            if(client.isActual() && JOptionPane.showConfirmDialog(this,  
                    "Нельзя удалить актуального клиента!\nДля удаления сперва "
                            + "его необходиомо перенести в архив!\n"
                            + "Перенести клиента " + client.getLastName() + " " + 
                            client.getFirstName() + " в архив?", "Так нельзя", 
                            JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE) == 0)
                
                changeClientActiveStatus(msqlc.getClient(crazyClients.get(clientList.getSelectedIndex()).getId()));        
            
            else if(!client.isActual() && 
                    JOptionPane.showConfirmDialog(this, "Удалить клиента " + 
                            client.getLastName() +" " + client.getFirstName() + "?\n"
                            + "Все данные о клиенте будут утеряны!", "Подтверждение удаления",
                            JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE)==0){
 
                msqlc.removeClient(crazyClients.get(clientList.getSelectedIndex()));
                fillTheList();
            }
        }
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void appointmentsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_appointmentsButtonActionPerformed
        if(clientList.getSelectedIndex()!=-1)
           new CalendarWindow(crazyClients.get(clientList.getSelectedIndex()).getId()).setVisible(true);
            
    }//GEN-LAST:event_appointmentsButtonActionPerformed

    private void shirkerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_shirkerButtonActionPerformed
        StringBuilder stb = new StringBuilder();
        String title = "Прогульщики не найдены";
        String days = JOptionPane.showInputDialog(this, "Введите количество пропущенных дней", 
                "Поиск прогульщиков", JOptionPane.QUESTION_MESSAGE);
        if(days==null) return;
        days = days.replaceAll("[^\\d]", "");
        if(!days.isEmpty() && Integer.parseInt(days) > 0){
            
            ArrayList<CrazyClient> clients = msqlc.getClientsShirkers(Integer.parseInt(days));
            for (CrazyClient crazyClient : clients) {
                stb.append(crazyClient.getFirstName());
                stb.append(' ');
                stb.append(crazyClient.getLastName());
                stb.append(", последнее посещение ");
                stb.append(crazyClient.getLastAppointment().format(DateTimeFormatter.ofPattern("dd.MM.uuuu, ")));
                stb.append(crazyClient.getEditedPhoneNumber());
                stb.append("\n");
                switch(Integer.parseInt(days) % 10){
                    case 1:
                        title = "Прогуляли больше " + days + " день";
                        break;
                    case 2: case 3: case 4:
                        title = "Прогуляли больше " + days + " дня";
                        break;
                    default:
                        title = "Прогуляли больше " + days + " дней";
                        break;
                }
            }
            JOptionPane.showMessageDialog(this, stb.toString().isEmpty() ? 
                    "Прогульщики не найдены!" : stb.toString(), title, 
                     stb.toString().isEmpty() ? JOptionPane.INFORMATION_MESSAGE :
                             JOptionPane.PLAIN_MESSAGE);
            clientList.grabFocus();
        }
    }//GEN-LAST:event_shirkerButtonActionPerformed

    private void archiveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_archiveButtonActionPerformed
        if(clientList.getSelectedIndex()!=-1)
            changeClientActiveStatus(msqlc.getClient(crazyClients.get(clientList.getSelectedIndex()).getId()));        
    }//GEN-LAST:event_archiveButtonActionPerformed

    private void clientListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_clientListValueChanged
        editButton.setEnabled(clientList.getSelectedIndex()!= -1);
        appointmentsButton.setEnabled(clientList.getSelectedIndex()!= -1);
        archiveButton.setEnabled(clientList.getSelectedIndex()!= -1);
        reportButton.setEnabled(clientList.getSelectedIndex()!= -1);
        deleteButton.setEnabled(clientList.getSelectedIndex()!= -1);
        archiveButton.setText(clientList.getSelectedIndex()==-1 || 
                crazyClients.get(clientList.getSelectedIndex()).isActual() ? 
                "В архив" : "Из архива");
    }//GEN-LAST:event_clientListValueChanged

    private void searchFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_searchFieldFocusGained
        clientList.clearSelection();
    }//GEN-LAST:event_searchFieldFocusGained

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        windowClosing();
    }//GEN-LAST:event_formWindowClosing

    private void clientListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clientListMouseClicked
       if(clientList.getSelectedIndex()!=-1 && evt.getButton()==1 && evt.getClickCount()==2)
           new CalendarWindow(crazyClients.get(clientList.getSelectedIndex()).getId()).setVisible(true);
    }//GEN-LAST:event_clientListMouseClicked

    private void reportButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reportButtonActionPerformed
        JOptionPane.showMessageDialog(this, new ReportPanel(crazyClients.get(
                clientList.getSelectedIndex())), " ", JOptionPane.PLAIN_MESSAGE);
    }//GEN-LAST:event_reportButtonActionPerformed

    private void archiveToggleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_archiveToggleButtonActionPerformed
        if(archiveToggleButton.isSelected() && msqlc.getClientsCount(false)==0 ){
            JOptionPane.showMessageDialog(this, "Архив пуст", "Архив пуст", 
                    JOptionPane.INFORMATION_MESSAGE);
            archiveToggleButton.setSelected(false);
            return;
        }
        archiveToggleButton.setText(archiveToggleButton.isSelected() ? 
                "Не показывать архив" : "Показывать архив");
        searchFilter.setActualOnly(!archiveToggleButton.isSelected());
        fillTheList();
        clientList.grabFocus();
    }//GEN-LAST:event_archiveToggleButtonActionPerformed
    private void filterButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filterButtonActionPerformed
        if(filterButton.isSelected()){
            JOptionPane.showMessageDialog(this, new FilterPanel(searchFilter), 
                    "Фильтры", JOptionPane.PLAIN_MESSAGE);
            filterButton.setText("Не использовать доп. фильтры");
        }
        else 
            filterButton.setText("Использовать доп. фильтры");
        searchFilter.setUseAdditionalFilters(filterButton.isSelected());
        fillTheList();
        clientList.grabFocus();       
    }//GEN-LAST:event_filterButtonActionPerformed

    private void searchFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchFieldKeyReleased
        searchField.setText(searchField.getText().replaceAll("[^\\D]", "").trim());
        if(searchField.getText()!=null &&
                (Character.isLetter(evt.getKeyChar())) || 
                evt.getKeyCode() == 8 || evt.getKeyCode() == 127) {
            
            searchFilter.setNameString(searchField.getText());
            fillTheList();
        }
        
    }//GEN-LAST:event_searchFieldKeyReleased

    /**Действия при закртыии программы: создаем бэкап БД и заливаем его на FTP*/
    private void windowClosing(){
        setModalExclusionType(Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
        new ExitWindow().exit();
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JButton appointmentsButton;
    private javax.swing.JButton archiveButton;
    private javax.swing.JToggleButton archiveToggleButton;
    private javax.swing.JList<String> clientList;
    private javax.swing.JButton deleteButton;
    private javax.swing.JButton editButton;
    private javax.swing.JToggleButton filterButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane;
    private javax.swing.JButton reportButton;
    private javax.swing.JTextField searchField;
    private javax.swing.JButton shirkerButton;
    private javax.swing.JButton wipeButton;
    // End of variables declaration//GEN-END:variables
}
