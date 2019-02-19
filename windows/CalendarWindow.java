
package CrazyClients.windows;

import CrazyClients.AppointmentDate;
import CrazyClients.CrazyClient;
import CrazyClients.DateLabel;
import CrazyClients.MySQLClient;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Александр Машьянов, mashyanov1987@gmail.com
 */
public class CalendarWindow extends javax.swing.JDialog {
    private final MySQLClient msqlc= MySQLClient.GetInstance();
    private final CrazyClient crazyClient;
    private int coef;
    private LocalDate localDate;
    private final static ArrayList<DateLabel> dateLabels = new ArrayList<>();
    private ArrayList<AppointmentDate> dates = new ArrayList<>();
    
    public CalendarWindow(int clientID) {
        
        initComponents();
        this.crazyClient = msqlc.getClient(clientID);
        
        nameLabel.setText("<html>"+crazyClient.getFirstName().trim() + 
                " " + crazyClient.getLastName().trim()+"<font color = gray> #"+
                crazyClient.getId()+"</font></html>");
        
        forwardButton.setEnabled(true);
        dateLabels.clear();
        dateLabels.add(dateLabel0);
        dateLabels.add(dateLabel1);
        dateLabels.add(dateLabel2);
        dateLabels.add(dateLabel3);
        dateLabels.add(dateLabel4);
        dateLabels.add(dateLabel5);
        dateLabels.add(dateLabel6);
        dateLabels.add(dateLabel7);
        dateLabels.add(dateLabel8);
        dateLabels.add(dateLabel9);
        dateLabels.add(dateLabel10);
        dateLabels.add(dateLabel11);
        dateLabels.add(dateLabel12);
        dateLabels.add(dateLabel13);
        dateLabels.add(dateLabel14);
        dateLabels.add(dateLabel15);
        dateLabels.add(dateLabel16);
        dateLabels.add(dateLabel17);
        dateLabels.add(dateLabel18);
        dateLabels.add(dateLabel19);
        dateLabels.add(dateLabel20);
        dateLabels.add(dateLabel21);
        dateLabels.add(dateLabel22);
        dateLabels.add(dateLabel23);
        dateLabels.add(dateLabel24);
        dateLabels.add(dateLabel25);
        dateLabels.add(dateLabel26);
        dateLabels.add(dateLabel27);
        dateLabels.add(dateLabel28);
        dateLabels.add(dateLabel29);
        dateLabels.add(dateLabel30);
        dateLabels.add(dateLabel31);
        dateLabels.add(dateLabel32);
        dateLabels.add(dateLabel33);
        dateLabels.add(dateLabel34);
        dateLabels.add(dateLabel35);
        dateLabels.add(dateLabel36);
        dateLabels.add(dateLabel37);
        dateLabels.add(dateLabel38);
        dateLabels.add(dateLabel39);
        dateLabels.add(dateLabel40);
        dateLabels.add(dateLabel41);
        monthBox.setSelectedIndex(LocalDate.now().getMonthValue()-1);
        
        yearLabel.setText(String.valueOf(LocalDate.now().getYear())); 
        repaintTheFieldAccordingToDate();
               
        fillTheField();
    }

    public static ArrayList<DateLabel> getCalendarField(){
        return dateLabels;
    }
    
    
    private DateLabel getSelectedLabel(){
        for (DateLabel dateLabel : dateLabels) 
            if(dateLabel.isSelected()) return  dateLabel;
        return null;
    }
    
    private void repaintTheFieldAccordingToDate(){
        //получаем заданную пользователем дату
        localDate = LocalDate.of(Integer.parseInt(yearLabel.getText()), monthBox.getSelectedIndex()+1, 1);
        //высчитываем сдвиг первого дня месяца относиельно понедельника
        coef = localDate.getDayOfWeek().getValue() - 1;
        dates = msqlc.getAppointmentsForMonth(crazyClient.getId(), monthBox.getSelectedIndex()+1, Integer.parseInt(yearLabel.getText()));
        //подготавливаем поле для заполнения
        for (DateLabel dateLabel : dateLabels) {
            dateLabel.setText(" ");
            dateLabel.setVisible(true);
            dateLabel.setSelected(false);
            dateLabel.setEnabled(false);
            dateLabel.setAppointment(AppointmentDate.NO_APPOINTMENT);
        }
        //заполняем дни текущего месяца
        for (int i = 0; i < localDate.lengthOfMonth(); i++) {
            dateLabels.get(i+coef).setEnabled(true);
            dateLabels.get(i+coef).setText(String.valueOf(i + 1));
        }
        //заполняем дни последующего месяца
        for (int i = localDate.lengthOfMonth()  + coef; i <  42 ; i++) {
            dateLabels.get(i).setText(String.valueOf(i + 1 - coef - localDate.lengthOfMonth()));
            
        }
        //заполняем дни предыдущего месяца
        localDate = localDate.minusMonths(1);
        for (int i = 0; i < coef; i++) {
            dateLabels.get(i).setText(
                    String.valueOf(localDate.getMonth().length(
                            localDate.isLeapYear()) - coef + i + 1));
        }
        
        if(!dateLabels.get(35).isEnabled())
            for (int i = 35; i < 42; i++) {
                dateLabels.get(i).setVisible(false);
            }
                    
    }
    
    private void fillTheField(){
        dates = msqlc.getAppointmentsForMonth(crazyClient.getId(), monthBox.getSelectedIndex()+1, Integer.parseInt(yearLabel.getText()));
        for (AppointmentDate date : dates) {
            dateLabels.get(coef + date.getDate().getDayOfMonth() -1).setAppointment(date.getId());
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dateLabel0 = new DateLabel();
        dateLabel2 = new DateLabel();
        dateLabel1 = new DateLabel();
        dateLabel3 = new DateLabel();
        dateLabel4 = new DateLabel();
        dateLabel5 = new DateLabel();
        dateLabel6 = new DateLabel();
        dateLabel7 = new DateLabel();
        dateLabel8 = new DateLabel();
        dateLabel9 = new DateLabel();
        dateLabel10 = new DateLabel();
        dateLabel11 = new DateLabel();
        dateLabel12 = new DateLabel();
        dateLabel13 = new DateLabel();
        dateLabel14 = new DateLabel();
        dateLabel15 = new DateLabel();
        dateLabel16 = new DateLabel();
        dateLabel17 = new DateLabel();
        dateLabel18 = new DateLabel();
        dateLabel19 = new DateLabel();
        dateLabel20 = new DateLabel();
        dateLabel21 = new DateLabel();
        dateLabel22 = new DateLabel();
        dateLabel23 = new DateLabel();
        dateLabel24 = new DateLabel();
        dateLabel25 = new DateLabel();
        dateLabel26 = new DateLabel();
        dateLabel27 = new DateLabel();
        dateLabel28 = new DateLabel();
        dateLabel29 = new DateLabel();
        dateLabel30 = new DateLabel();
        monthBox = new javax.swing.JComboBox<>();
        nameLabel = new javax.swing.JLabel();
        yearLabel = new javax.swing.JLabel();
        backButton = new javax.swing.JButton();
        forwardButton = new javax.swing.JButton();
        dateLabel32 = new DateLabel();
        dateLabel31 = new DateLabel();
        dateLabel33 = new DateLabel();
        dateLabel34 = new DateLabel();
        dateLabel35 = new DateLabel();
        dateLabel36 = new DateLabel();
        dateLabel37 = new DateLabel();
        dateLabel38 = new DateLabel();
        dateLabel39 = new DateLabel();
        dateLabel40 = new DateLabel();
        dateLabel41 = new DateLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        AddDeleteButton = new javax.swing.JButton();
        editButton = new javax.swing.JButton();
        closeButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setAlwaysOnTop(true);
        setResizable(false);

        dateLabel0.setText("jLabel1");
        dateLabel0.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        dateLabel2.setText("jLabel1");
        dateLabel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        dateLabel1.setText("jLabel1");
        dateLabel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        dateLabel3.setText("jLabel1");
        dateLabel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        dateLabel4.setText("jLabel1");
        dateLabel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        dateLabel5.setText("jLabel1");
        dateLabel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        dateLabel6.setText("jLabel1");
        dateLabel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        dateLabel7.setText("jLabel1");
        dateLabel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        dateLabel8.setText("jLabel1");
        dateLabel8.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        dateLabel9.setText("jLabel1");
        dateLabel9.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        dateLabel10.setText("jLabel1");
        dateLabel10.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        dateLabel11.setText("jLabel1");
        dateLabel11.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        dateLabel12.setText("jLabel1");
        dateLabel12.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        dateLabel13.setText("jLabel1");
        dateLabel13.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        dateLabel14.setText("jLabel1");
        dateLabel14.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        dateLabel15.setText("jLabel1");
        dateLabel15.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        dateLabel16.setText("jLabel1");
        dateLabel16.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        dateLabel17.setText("jLabel1");
        dateLabel17.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        dateLabel18.setText("jLabel1");
        dateLabel18.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        dateLabel19.setText("jLabel1");
        dateLabel19.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        dateLabel20.setText("jLabel1");
        dateLabel20.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        dateLabel21.setText("jLabel1");
        dateLabel21.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        dateLabel22.setText("jLabel1");
        dateLabel22.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        dateLabel23.setText("jLabel1");
        dateLabel23.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        dateLabel24.setText("jLabel1");
        dateLabel24.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        dateLabel25.setText("jLabel1");
        dateLabel25.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        dateLabel26.setText("jLabel1");
        dateLabel26.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        dateLabel27.setText("jLabel1");
        dateLabel27.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        dateLabel28.setText("jLabel1");
        dateLabel28.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        dateLabel29.setText("jLabel1");
        dateLabel29.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        dateLabel30.setText("jLabel1");
        dateLabel30.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        monthBox.setMaximumRowCount(12);
        monthBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Январь", "Февраль", "Март", "Апрель", "Май", "Июнь", "Июль", "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь" }));
        monthBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                monthBoxItemStateChanged(evt);
            }
        });

        nameLabel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        nameLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nameLabel.setText("jLabel1");

        yearLabel.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        yearLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        yearLabel.setText("1987");

        backButton.setText("<");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        forwardButton.setText(">");
        forwardButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                forwardButtonActionPerformed(evt);
            }
        });

        dateLabel32.setText("jLabel1");
        dateLabel32.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        dateLabel31.setText("jLabel1");
        dateLabel31.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        dateLabel33.setText("jLabel1");
        dateLabel33.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        dateLabel34.setText("jLabel1");
        dateLabel34.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        dateLabel35.setText("jLabel1");
        dateLabel35.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        dateLabel36.setText("jLabel1");
        dateLabel36.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        dateLabel37.setText("jLabel1");
        dateLabel37.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        dateLabel38.setText("jLabel1");
        dateLabel38.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        dateLabel39.setText("jLabel1");
        dateLabel39.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        dateLabel40.setText("jLabel1");
        dateLabel40.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        dateLabel41.setText("jLabel1");
        dateLabel41.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("пн");

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("вт");

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("ср");

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("чт");

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("пт");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("сб");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("вс");

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        AddDeleteButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        AddDeleteButton.setText("<html>Добавить или<br>удалить приём</html>");
        AddDeleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddDeleteButtonActionPerformed(evt);
            }
        });

        editButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        editButton.setText("Редактировать");
        editButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editButtonActionPerformed(evt);
            }
        });

        closeButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        closeButton.setText("Закрыть");
        closeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(closeButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(AddDeleteButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                    .addComponent(editButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(AddDeleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(editButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(closeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(240, 240, 240)
                                .addComponent(dateLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(dateLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(dateLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(dateLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(dateLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(dateLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(dateLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(dateLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dateLabel0, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(dateLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(dateLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(dateLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(dateLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(dateLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, 0)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(monthBox, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(yearLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(forwardButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(dateLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(dateLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(dateLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(dateLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(dateLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(dateLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(dateLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(dateLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(dateLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(dateLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(dateLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(dateLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(dateLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(dateLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(dateLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(dateLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(dateLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(dateLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(dateLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(dateLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dateLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dateLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dateLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dateLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dateLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dateLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dateLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dateLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(nameLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(nameLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(monthBox, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(yearLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(forwardButton, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(dateLabel0, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dateLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dateLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dateLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dateLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dateLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dateLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, 0)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(dateLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dateLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dateLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dateLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dateLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dateLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dateLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, 0)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(dateLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dateLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dateLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dateLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dateLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dateLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dateLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, 0)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(dateLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dateLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dateLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dateLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dateLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dateLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dateLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, 0)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(dateLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dateLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dateLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dateLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dateLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dateLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dateLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, 0)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(dateLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dateLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dateLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dateLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dateLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dateLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dateLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void monthBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_monthBoxItemStateChanged
        repaintTheFieldAccordingToDate();
        fillTheField();
    }//GEN-LAST:event_monthBoxItemStateChanged

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
       yearLabel.setText(String.valueOf(Integer.parseInt(yearLabel.getText()) - 1));
       monthBox.setSelectedIndex(11);
       forwardButton.setEnabled(true);
       repaintTheFieldAccordingToDate();
       fillTheField();
    }//GEN-LAST:event_backButtonActionPerformed

    private void forwardButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_forwardButtonActionPerformed
        localDate = LocalDate.now();
        yearLabel.setText(String.valueOf(Integer.parseInt(yearLabel.getText()) + 1));
        monthBox.setSelectedIndex(0);
        if(Integer.parseInt(yearLabel.getText()) > localDate.getYear())
           forwardButton.setEnabled(false);
                  
        repaintTheFieldAccordingToDate();
        fillTheField();
    }//GEN-LAST:event_forwardButtonActionPerformed

    private void AddDeleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddDeleteButtonActionPerformed
        if(getSelectedLabel()!=null) {
            
            LocalDate newDate = LocalDate.of(Integer.parseInt(yearLabel.getText()), 
                                monthBox.getSelectedIndex()+1, 
                                dateLabels.indexOf(getSelectedLabel())+1-coef);
            
            switch (getSelectedLabel().getAppointmentId()){
                
                case AppointmentDate.NO_APPOINTMENT:
                
                    if(crazyClient.getFirstAppointment().isAfter(newDate)){
                        crazyClient.setFirstAppointment(newDate);
                        msqlc.editClient(crazyClient);
                    }
                    if(crazyClient.getLastAppointment().isBefore(newDate)){
                        crazyClient.setLastAppointment(newDate);
                        msqlc.editClient(crazyClient);
                    }

                    getSelectedLabel().setAppointment(crazyClient.getId());
                    AppointmentDate ad = new AppointmentDate(crazyClient.getId(), 
                            LocalDate.of(Integer.parseInt(yearLabel.getText()), 
                                    monthBox.getSelectedIndex()+1, dateLabels.indexOf(getSelectedLabel())+1 - coef));
                    new AppointmentEditWindow(new JFrame(), ad, crazyClient.getLastName() + 
                            " " + crazyClient.getFirstName()).setVisible(true);
                    msqlc.addAppointment(ad);
                    fillTheField();
                    break;

                default:
                    if(crazyClient.getFirstAppointment().isEqual(crazyClient.getLastAppointment())) 
                        return;
                    if(JOptionPane.showConfirmDialog(this, "Удалить приём?\nВсе данные о  приеме будут утеряны!", "Удаление приёма", 
                            JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE)==0)    {
                        msqlc.removeAppointment(getSelectedLabel().getAppointmentId());
                        getSelectedLabel().setAppointment(AppointmentDate.NO_APPOINTMENT);
                        if(crazyClient.getFirstAppointment().isEqual(newDate)){
                            crazyClient.setFirstAppointment(msqlc.getFirstAppointment(crazyClient).getDate());
                            msqlc.editClient(crazyClient);
                        }
                        if(crazyClient.getLastAppointment().isEqual(newDate)){
                            crazyClient.setLastAppointment(msqlc.getLastAppointment(crazyClient).getDate());
                            msqlc.editClient(crazyClient);
                        }
                    }
                    break;
            }
        }
    }//GEN-LAST:event_AddDeleteButtonActionPerformed

    private void closeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeButtonActionPerformed
        dispose();
    }//GEN-LAST:event_closeButtonActionPerformed

    private void editButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editButtonActionPerformed
        if(getSelectedLabel()!=null && getSelectedLabel().getAppointmentId()!=-1) {
            AppointmentDate ad = msqlc.getAppointmentDate(getSelectedLabel().
                    getAppointmentId());
            new AppointmentEditWindow(new JFrame(), ad, crazyClient.getLastName() + 
                            " " + crazyClient.getFirstName()).setVisible(true);
            msqlc.editAppointmet(ad);
            fillTheField();
         }
    }//GEN-LAST:event_editButtonActionPerformed

     
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddDeleteButton;
    private javax.swing.JButton backButton;
    private javax.swing.JButton closeButton;
    private DateLabel dateLabel0;
    private DateLabel dateLabel1;
    private DateLabel dateLabel10;
    private DateLabel dateLabel11;
    private DateLabel dateLabel12;
    private DateLabel dateLabel13;
    private DateLabel dateLabel14;
    private DateLabel dateLabel15;
    private DateLabel dateLabel16;
    private DateLabel dateLabel17;
    private DateLabel dateLabel18;
    private DateLabel dateLabel19;
    private DateLabel dateLabel2;
    private DateLabel dateLabel20;
    private DateLabel dateLabel21;
    private DateLabel dateLabel22;
    private DateLabel dateLabel23;
    private DateLabel dateLabel24;
    private DateLabel dateLabel25;
    private DateLabel dateLabel26;
    private DateLabel dateLabel27;
    private DateLabel dateLabel28;
    private DateLabel dateLabel29;
    private DateLabel dateLabel3;
    private DateLabel dateLabel30;
    private DateLabel dateLabel31;
    private DateLabel dateLabel32;
    private DateLabel dateLabel33;
    private DateLabel dateLabel34;
    private DateLabel dateLabel35;
    private DateLabel dateLabel36;
    private DateLabel dateLabel37;
    private DateLabel dateLabel38;
    private DateLabel dateLabel39;
    private DateLabel dateLabel4;
    private DateLabel dateLabel40;
    private DateLabel dateLabel41;
    private DateLabel dateLabel5;
    private DateLabel dateLabel6;
    private DateLabel dateLabel7;
    private DateLabel dateLabel8;
    private DateLabel dateLabel9;
    private javax.swing.JButton editButton;
    private javax.swing.JButton forwardButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JComboBox<String> monthBox;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JLabel yearLabel;
    // End of variables declaration//GEN-END:variables



}
