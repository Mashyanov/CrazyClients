
package CrazyClients.windows;

import CrazyClients.AppointmentDate;
import CrazyClients.CrazyClient;
import CrazyClients.MySQLClient;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

/**
 *
 * @author Александр Машьянов, mashyanov1987@gmail.com
 */
public class ReportPanel extends javax.swing.JPanel {

    private ArrayList<AppointmentDate> dates = null;
    private final MySQLClient msqlc = MySQLClient.GetInstance();
    private final JPopupMenu  menu  = new JPopupMenu();
    private final JMenuItem   copyItem = new JMenuItem("Копировать");
    private final JMenuItem   sAllItem = new JMenuItem("Выделить всё");
    
    public ReportPanel(CrazyClient client) {
        initComponents();
        menu.add(copyItem);
        menu.add(sAllItem);
        copyItem.addActionListener((e) -> {
              StringBuilder stb = new StringBuilder();
               for (AppointmentDate date : dates) {
            
            stb.append(date.getDate().format(DateTimeFormatter.ofPattern("dd.MM.uuuu:")));
            stb.append(System.lineSeparator());
            stb.append((date.getAppointmentProcess() == null || 
                    date.getAppointmentProcess().replaceAll(" ", "").replaceAll("\n", "").isEmpty()) ?
                    "Сведения не заданы" : date.getAppointmentProcess());
            stb.append(System.lineSeparator());
            stb.append(System.lineSeparator());
                   
                              
        }  
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(
                new StringSelection(stb.toString()), null);
        });
        sAllItem.addActionListener((e) -> {
            reportPane.grabFocus();
            reportPane.selectAll();
        });
        dates = msqlc.getAppointmentsForClient(client.getId());
        headLabel.setText("Отчет по клиенту " + client.getLastName() + " " + client.getFirstName() + ':');
        StringBuilder stb = new StringBuilder(
                "<html><style type=\"text/css\">"
                        + ".date{"
                        + "font-size: 110%;"
                        + "font-weight: bold;}"
                        + ".text{"
                        + "font-size:105%;"
                        + "margin-left:18px;"
                        + "}"
                        + "</style>"
                        );
        for (AppointmentDate date : dates) {
            stb.append("<div class=\"date\">");
            stb.append(date.getDate().format(DateTimeFormatter.ofPattern("dd.MM.uuuu:")));
            stb.append("</div><div class=\"text\">");
            stb.append((date.getAppointmentProcess()== null || 
                    date.getAppointmentProcess().replaceAll(" ", "").isEmpty())
                    ? "Сведения не заданы" : date.getAppointmentProcess());
            stb.append("</div><br><br>");
        }
        stb.append("</html>");
        String result = stb.toString();
        result = result.replaceAll("\n", "<br>");
        reportPane.setContentType("text/html");
        reportPane.setText(result);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        reportPane = new javax.swing.JTextPane();
        headLabel = new javax.swing.JLabel();

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setMaximumSize(new java.awt.Dimension(300, 307));

        reportPane.setEditable(false);
        reportPane.setContentType("text/html\n"); // NOI18N
        reportPane.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        reportPane.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                reportPaneMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                reportPaneMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(reportPane);

        headLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        headLabel.setText("jLabel1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(headLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 585, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(headLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 514, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void reportPaneMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reportPaneMouseClicked
              if(evt.getButton()==3){
            if(menu.isShowing()) menu.setVisible(false);
                copyItem.setEnabled(reportPane.getSelectedText()!=null);
            menu.show(reportPane, evt.getX(), evt.getY());
            
        }
    }//GEN-LAST:event_reportPaneMouseClicked

    private void reportPaneMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reportPaneMousePressed
   
    }//GEN-LAST:event_reportPaneMousePressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel headLabel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextPane reportPane;
    // End of variables declaration//GEN-END:variables
}
