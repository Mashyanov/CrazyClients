
package CrazyClients.windows;

import CrazyClients.data.Data;
import CrazyClients.MySQLClient;
import CrazyClients.SearchFilter;
import java.awt.Color;
import javax.swing.border.LineBorder;

/**
 *
 * @author Александр Машьянов, mashyanov1987@gmail.com
 */
public class FilterPanel extends javax.swing.JPanel {
    private final MySQLClient msqlc = MySQLClient.GetInstance();
    private final SearchFilter filter;
    private boolean leftSelected;
    private final Data data = new Data();
   
    public FilterPanel(SearchFilter filter) {
        this.filter = filter;
        initComponents();
        
        for (String theme : msqlc.getThemes()) 
            data.add(theme);
        
        themesList.setModel(data);
        changeSelection(filter.isLeftSelected());
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        leftPanel = new javax.swing.JPanel();
        themesCheckBox = new javax.swing.JCheckBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        themesList = new javax.swing.JList<>();
        ageCheckBox = new javax.swing.JCheckBox();
        ageLabel0 = new javax.swing.JLabel();
        ageMinTextField = new javax.swing.JTextField();
        ageLabel1 = new javax.swing.JLabel();
        ageMaxTextField = new javax.swing.JTextField();
        ageLabel2 = new javax.swing.JLabel();
        leftHeaderLabel = new javax.swing.JLabel();
        rightPanel = new javax.swing.JPanel();
        recommenderButton = new javax.swing.JRadioButton();
        phoneNumberButton = new javax.swing.JRadioButton();
        resposneButton_1 = new javax.swing.JRadioButton();
        resposneButton_2 = new javax.swing.JRadioButton();
        resposneButton_3 = new javax.swing.JRadioButton();
        resposneButton_4 = new javax.swing.JRadioButton();
        resposneButton_5 = new javax.swing.JRadioButton();
        targetButton = new javax.swing.JRadioButton();
        medicineButton = new javax.swing.JRadioButton();
        rightHeaderLabel = new javax.swing.JLabel();

        leftPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        leftPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                leftPanelMousePressed(evt);
            }
        });

        themesCheckBox.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        themesCheckBox.setText("Основные запросы");
        themesCheckBox.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                themesCheckBoxStateChanged(evt);
            }
        });
        themesCheckBox.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                themesCheckBoxMousePressed(evt);
            }
        });

        themesList.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        themesList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        themesList.setDropMode(javax.swing.DropMode.ON);
        themesList.setEnabled(false);
        themesList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                themesListMousePressed(evt);
            }
        });
        themesList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                themesListValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(themesList);

        ageCheckBox.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        ageCheckBox.setText("Возрастной интервал");
        ageCheckBox.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                ageCheckBoxStateChanged(evt);
            }
        });
        ageCheckBox.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                ageCheckBoxMousePressed(evt);
            }
        });

        ageLabel0.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        ageLabel0.setText("От");
        ageLabel0.setEnabled(false);
        ageLabel0.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                ageLabel0MousePressed(evt);
            }
        });

        ageMinTextField.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        ageMinTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        ageMinTextField.setText("0");
        ageMinTextField.setEnabled(false);
        ageMinTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                ageMinTextFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                ageMinTextFieldFocusLost(evt);
            }
        });
        ageMinTextField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                ageMinTextFieldMousePressed(evt);
            }
        });
        ageMinTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                ageMinTextFieldKeyReleased(evt);
            }
        });

        ageLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        ageLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ageLabel1.setText("до");
        ageLabel1.setEnabled(false);
        ageLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                ageLabel1MousePressed(evt);
            }
        });

        ageMaxTextField.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        ageMaxTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        ageMaxTextField.setText("99");
        ageMaxTextField.setEnabled(false);
        ageMaxTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                ageMaxTextFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                ageMaxTextFieldFocusLost(evt);
            }
        });
        ageMaxTextField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                ageMaxTextFieldMousePressed(evt);
            }
        });
        ageMaxTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                ageMaxTextFieldKeyReleased(evt);
            }
        });

        ageLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        ageLabel2.setText("лет");
        ageLabel2.setEnabled(false);
        ageLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                ageLabel2MousePressed(evt);
            }
        });

        leftHeaderLabel.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        leftHeaderLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        leftHeaderLabel.setText("Поиск по параметрам");
        leftHeaderLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                leftHeaderLabelMousePressed(evt);
            }
        });

        javax.swing.GroupLayout leftPanelLayout = new javax.swing.GroupLayout(leftPanel);
        leftPanel.setLayout(leftPanelLayout);
        leftPanelLayout.setHorizontalGroup(
            leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(leftPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(leftHeaderLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 321, Short.MAX_VALUE)
                    .addGroup(leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(ageCheckBox, javax.swing.GroupLayout.DEFAULT_SIZE, 321, Short.MAX_VALUE)
                        .addGroup(leftPanelLayout.createSequentialGroup()
                            .addGroup(leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(themesCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(leftPanelLayout.createSequentialGroup()
                                    .addGap(21, 21, 21)
                                    .addComponent(ageLabel0)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(ageMinTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(ageLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(ageMaxTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(ageLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGap(11, 11, 11))
                        .addGroup(leftPanelLayout.createSequentialGroup()
                            .addGap(21, 21, 21)
                            .addComponent(jScrollPane1))))
                .addContainerGap())
        );
        leftPanelLayout.setVerticalGroup(
            leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(leftPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(leftHeaderLabel)
                .addGap(18, 18, 18)
                .addComponent(themesCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addComponent(ageCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ageLabel0, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ageMinTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ageLabel1)
                    .addComponent(ageMaxTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ageLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        rightPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        rightPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                rightPanelMousePressed(evt);
            }
        });

        recommenderButton.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        recommenderButton.setText("От кого");
        recommenderButton.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                recommenderButtonStateChanged(evt);
            }
        });
        recommenderButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                recommenderButtonMousePressed(evt);
            }
        });

        phoneNumberButton.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        phoneNumberButton.setText("Телефон");
        phoneNumberButton.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                phoneNumberButtonStateChanged(evt);
            }
        });
        phoneNumberButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                phoneNumberButtonMousePressed(evt);
            }
        });

        resposneButton_1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        resposneButton_1.setText("Запрос 1");
        resposneButton_1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                resposneButton_1StateChanged(evt);
            }
        });
        resposneButton_1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                resposneButton_1MousePressed(evt);
            }
        });

        resposneButton_2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        resposneButton_2.setText("Запрос 2");
        resposneButton_2.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                resposneButton_2StateChanged(evt);
            }
        });
        resposneButton_2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                resposneButton_2MousePressed(evt);
            }
        });

        resposneButton_3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        resposneButton_3.setText("Запрос 3");
        resposneButton_3.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                resposneButton_3StateChanged(evt);
            }
        });
        resposneButton_3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                resposneButton_3MousePressed(evt);
            }
        });

        resposneButton_4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        resposneButton_4.setText("Запрос 4");
        resposneButton_4.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                resposneButton_4StateChanged(evt);
            }
        });
        resposneButton_4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                resposneButton_4MousePressed(evt);
            }
        });

        resposneButton_5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        resposneButton_5.setText("Запрос 5");
        resposneButton_5.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                resposneButton_5StateChanged(evt);
            }
        });
        resposneButton_5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                resposneButton_5MousePressed(evt);
            }
        });

        targetButton.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        targetButton.setText("Главные мишени коррекции");
        targetButton.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                targetButtonStateChanged(evt);
            }
        });
        targetButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                targetButtonMousePressed(evt);
            }
        });

        medicineButton.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        medicineButton.setText("Медицинский анамнез");
        medicineButton.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                medicineButtonStateChanged(evt);
            }
        });
        medicineButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                medicineButtonMousePressed(evt);
            }
        });

        rightHeaderLabel.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        rightHeaderLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        rightHeaderLabel.setText("Поиск по незаполененным полям");
        rightHeaderLabel.setEnabled(false);
        rightHeaderLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                rightHeaderLabelMousePressed(evt);
            }
        });

        javax.swing.GroupLayout rightPanelLayout = new javax.swing.GroupLayout(rightPanel);
        rightPanel.setLayout(rightPanelLayout);
        rightPanelLayout.setHorizontalGroup(
            rightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rightPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(rightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rightHeaderLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 321, Short.MAX_VALUE)
                    .addGroup(rightPanelLayout.createSequentialGroup()
                        .addGroup(rightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(recommenderButton)
                            .addComponent(phoneNumberButton)
                            .addComponent(resposneButton_2)
                            .addComponent(resposneButton_4)
                            .addComponent(resposneButton_5)
                            .addComponent(medicineButton)
                            .addComponent(resposneButton_1)
                            .addComponent(resposneButton_3)
                            .addComponent(targetButton))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        rightPanelLayout.setVerticalGroup(
            rightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, rightPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rightHeaderLabel)
                .addGap(18, 18, 18)
                .addComponent(recommenderButton)
                .addGap(18, 18, 18)
                .addComponent(phoneNumberButton)
                .addGap(18, 18, 18)
                .addComponent(resposneButton_1)
                .addGap(18, 18, 18)
                .addComponent(resposneButton_2)
                .addGap(18, 18, 18)
                .addComponent(resposneButton_3)
                .addGap(18, 18, 18)
                .addComponent(resposneButton_4)
                .addGap(18, 18, 18)
                .addComponent(resposneButton_5)
                .addGap(18, 18, 18)
                .addComponent(targetButton)
                .addGap(18, 18, 18)
                .addComponent(medicineButton)
                .addContainerGap(49, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(leftPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rightPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(leftPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(rightPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    private void themesCheckBoxStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_themesCheckBoxStateChanged
        themesList.setEnabled(themesCheckBox.isSelected());
        filter.setThemeSearch(themesCheckBox.isSelected());
    }//GEN-LAST:event_themesCheckBoxStateChanged
    private void themesListMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_themesListMousePressed
        if(!leftSelected) {
           evt.consume();
           changeSelection(true);
        }
    }//GEN-LAST:event_themesListMousePressed
    private void themesListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_themesListValueChanged
        filter.setThemes(themesList.getSelectedValuesList());
        filter.setThemesIndexes(themesList.getSelectedIndices());
    }//GEN-LAST:event_themesListValueChanged
    private void leftPanelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_leftPanelMousePressed
        changeSelection(true);
    }//GEN-LAST:event_leftPanelMousePressed
    private void rightPanelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rightPanelMousePressed
        changeSelection(false);
    }//GEN-LAST:event_rightPanelMousePressed
    private void recommenderButtonStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_recommenderButtonStateChanged
        filter.setRecommenderSearch(recommenderButton.isSelected());
    }//GEN-LAST:event_recommenderButtonStateChanged
    private void phoneNumberButtonStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_phoneNumberButtonStateChanged
        filter.setPhoneSearch(phoneNumberButton.isSelected());
    }//GEN-LAST:event_phoneNumberButtonStateChanged
    private void resposneButton_1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_resposneButton_1StateChanged
        filter.setResponseSearch_1(resposneButton_1.isSelected());
    }//GEN-LAST:event_resposneButton_1StateChanged
    private void resposneButton_2StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_resposneButton_2StateChanged
        filter.setResponseSearch_2(resposneButton_2.isSelected());
    }//GEN-LAST:event_resposneButton_2StateChanged
    private void resposneButton_3StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_resposneButton_3StateChanged
        filter.setResponseSearch_3(resposneButton_3.isSelected());
    }//GEN-LAST:event_resposneButton_3StateChanged
    private void resposneButton_4StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_resposneButton_4StateChanged
        filter.setResponseSearch_4(resposneButton_4.isSelected());
    }//GEN-LAST:event_resposneButton_4StateChanged
    private void resposneButton_5StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_resposneButton_5StateChanged
        filter.setResponseSearch_5(resposneButton_5.isSelected());
    }//GEN-LAST:event_resposneButton_5StateChanged
    private void targetButtonStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_targetButtonStateChanged
        filter.setTargetSearch(targetButton.isSelected());
    }//GEN-LAST:event_targetButtonStateChanged
    private void medicineButtonStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_medicineButtonStateChanged
        filter.setMedicineSearch(medicineButton.isSelected());
    }//GEN-LAST:event_medicineButtonStateChanged
    private void ageCheckBoxStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_ageCheckBoxStateChanged
        ageLabel0.setEnabled(ageCheckBox.isSelected());
        ageLabel1.setEnabled(ageCheckBox.isSelected());
        ageLabel2.setEnabled(ageCheckBox.isSelected());
        ageMinTextField.setEnabled(ageCheckBox.isSelected());
        ageMaxTextField.setEnabled(ageCheckBox.isSelected());
        filter.setAgeSearch(ageCheckBox.isSelected());
    
    }//GEN-LAST:event_ageCheckBoxStateChanged

    private void ageMinTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ageMinTextFieldKeyReleased
        if(evt.getKeyCode()==37 || evt.getKeyCode()==39) return;
        ageMinTextField.setText(ageMinTextField.getText().replaceAll("[^\\d]", "").trim());
        filter.setMinAge(ageMinTextField.getText().isEmpty() ? 0 : Integer.valueOf(ageMinTextField.getText()));
        
       
        
    }//GEN-LAST:event_ageMinTextFieldKeyReleased
    private void ageMaxTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ageMaxTextFieldKeyReleased
        if(evt.getKeyCode()==37 || evt.getKeyCode()==39) return;
        ageMaxTextField.setText(ageMaxTextField.getText().replaceAll("[^\\d]", "").trim());
        filter.setMaxAge(ageMaxTextField.getText().isEmpty() ? 99 : Integer.valueOf(ageMaxTextField.getText()));
        
    }//GEN-LAST:event_ageMaxTextFieldKeyReleased
    private void ageMinTextFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ageMinTextFieldFocusGained
        ageMinTextField.selectAll();
    }//GEN-LAST:event_ageMinTextFieldFocusGained
    private void ageMaxTextFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ageMaxTextFieldFocusGained
        ageMaxTextField.selectAll();
    }//GEN-LAST:event_ageMaxTextFieldFocusGained
    private void ageMaxTextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ageMaxTextFieldFocusLost
        if(ageMaxTextField.getText().equals("0")){
            ageMaxTextField.setText("1");
            ageMinTextField.setText("0");
        }
        if(!ageMinTextField.getText().isEmpty()&&!ageMaxTextField.getText().isEmpty() &&
                Integer.valueOf(ageMinTextField.getText())>=Integer.valueOf(ageMaxTextField.getText()))
            ageMinTextField.setText(String.valueOf(Integer.parseInt(ageMaxTextField.getText()) - 1));
        filter.setMinAge(Integer.valueOf(ageMinTextField.getText()));
    }//GEN-LAST:event_ageMaxTextFieldFocusLost
    private void ageMinTextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ageMinTextFieldFocusLost
        if(!ageMinTextField.getText().isEmpty()&&!ageMaxTextField.getText().isEmpty() &&
                Integer.valueOf(ageMinTextField.getText())>=Integer.valueOf(ageMaxTextField.getText()))
            ageMaxTextField.setText(String.valueOf(Integer.parseInt(ageMinTextField.getText()) + 1));
        filter.setMaxAge(Integer.valueOf(ageMaxTextField.getText()));
    }//GEN-LAST:event_ageMinTextFieldFocusLost
    private void themesCheckBoxMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_themesCheckBoxMousePressed
        if(!leftSelected) {
           evt.consume();
           changeSelection(true);
        }
    }//GEN-LAST:event_themesCheckBoxMousePressed
    private void leftHeaderLabelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_leftHeaderLabelMousePressed
        if(!leftSelected) {
           evt.consume();
           changeSelection(true);
        }
    }//GEN-LAST:event_leftHeaderLabelMousePressed
    private void ageCheckBoxMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ageCheckBoxMousePressed
        if(!leftSelected) {
           evt.consume();
           changeSelection(true);
        }
    }//GEN-LAST:event_ageCheckBoxMousePressed
    private void ageLabel0MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ageLabel0MousePressed
        if(!leftSelected) {
           evt.consume();
           changeSelection(true);
        }
    }//GEN-LAST:event_ageLabel0MousePressed
    private void ageMinTextFieldMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ageMinTextFieldMousePressed
         if(!leftSelected) {
           evt.consume();
           changeSelection(true);
        }
    }//GEN-LAST:event_ageMinTextFieldMousePressed
    private void ageLabel1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ageLabel1MousePressed
         if(!leftSelected) {
           evt.consume();
           changeSelection(true);
        }
    }//GEN-LAST:event_ageLabel1MousePressed
    private void ageMaxTextFieldMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ageMaxTextFieldMousePressed
         if(!leftSelected) {
           evt.consume();
           changeSelection(true);
        }
    }//GEN-LAST:event_ageMaxTextFieldMousePressed
    private void ageLabel2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ageLabel2MousePressed
        if(!leftSelected) {
           evt.consume();
           changeSelection(true);
        }
    }//GEN-LAST:event_ageLabel2MousePressed
    private void rightHeaderLabelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rightHeaderLabelMousePressed
        if(leftSelected) {
            evt.consume();
            changeSelection(false);
        }
    }//GEN-LAST:event_rightHeaderLabelMousePressed
    private void recommenderButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_recommenderButtonMousePressed
        if(leftSelected) {
            evt.consume();
            changeSelection(false);
        }
    }//GEN-LAST:event_recommenderButtonMousePressed
    private void phoneNumberButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_phoneNumberButtonMousePressed
        if(leftSelected) {
           evt.consume();
           changeSelection(false);
        }
    }//GEN-LAST:event_phoneNumberButtonMousePressed
    private void resposneButton_1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_resposneButton_1MousePressed
        if(leftSelected) {
           evt.consume();
           changeSelection(false);
        }
    }//GEN-LAST:event_resposneButton_1MousePressed
    private void resposneButton_2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_resposneButton_2MousePressed
        if(leftSelected) {
           evt.consume();
           changeSelection(false);
        }
    }//GEN-LAST:event_resposneButton_2MousePressed
    private void resposneButton_3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_resposneButton_3MousePressed
        if(leftSelected) {
           evt.consume();
           changeSelection(false);
        }
    }//GEN-LAST:event_resposneButton_3MousePressed
    private void resposneButton_4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_resposneButton_4MousePressed
        if(leftSelected) {
           evt.consume();
           changeSelection(false);
        }
    }//GEN-LAST:event_resposneButton_4MousePressed
    private void resposneButton_5MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_resposneButton_5MousePressed
        if(leftSelected) {
           evt.consume();
           changeSelection(false);
        }
    }//GEN-LAST:event_resposneButton_5MousePressed
    private void targetButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_targetButtonMousePressed
        if(leftSelected) {
           evt.consume();
           changeSelection(false);
        }
    }//GEN-LAST:event_targetButtonMousePressed
    private void medicineButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_medicineButtonMousePressed
        if(leftSelected) {
           evt.consume();
           changeSelection(false);
        }
    }//GEN-LAST:event_medicineButtonMousePressed

     private void changeSelection(boolean leftSelected){
        this.leftSelected = leftSelected;
        leftHeaderLabel.setEnabled(leftSelected);
        rightHeaderLabel.setEnabled(!leftSelected);
        leftPanel.setBorder(leftSelected ? new LineBorder(Color.ORANGE, 3): new LineBorder(Color.gray, 3));
        rightPanel.setBorder(!leftSelected ? new LineBorder(Color.ORANGE, 3): new LineBorder(Color.gray, 3));
        filter.setLeftSelected(leftSelected);
        themesList.setSelectedIndices(filter.getThemesIndexes());
        themesCheckBox.setSelected(filter.isThemeSearch());
        themesCheckBox.setEnabled(leftSelected);
        themesList.setEnabled(leftSelected && themesCheckBox.isSelected());
        ageCheckBox.setSelected(filter.isAgeSearch());
        ageCheckBox.setEnabled(leftSelected);
        
        ageLabel0.setEnabled(leftSelected && ageCheckBox.isSelected());
        ageLabel1.setEnabled(leftSelected && ageCheckBox.isSelected());
        ageLabel2.setEnabled(leftSelected && ageCheckBox.isSelected());
        ageMaxTextField.setEnabled(leftSelected && ageCheckBox.isSelected());
        ageMinTextField.setEnabled(leftSelected && ageCheckBox.isSelected());
        ageMinTextField.setText(String.valueOf(filter.getMinAge()));
        ageMaxTextField.setText(String.valueOf(filter.getMaxAge()));
        /////////////////////////////////////////////////////
                
        recommenderButton.setSelected(filter.isRecommenderSearch());
        phoneNumberButton.setSelected(filter.isPhoneSearch());
        resposneButton_1.setSelected(filter.isResponseSearch_1());
        resposneButton_2.setSelected(filter.isResponseSearch_2());
        resposneButton_3.setSelected(filter.isResponseSearch_3());
        resposneButton_4.setSelected(filter.isResponseSearch_4());
        resposneButton_5.setSelected(filter.isResponseSearch_5());
        targetButton.setSelected(filter.isTargetSearch());
        medicineButton.setSelected(filter.isMedicineSearch());
        
        recommenderButton.setEnabled(!leftSelected);
        phoneNumberButton.setEnabled(!leftSelected);
        resposneButton_1.setEnabled(!leftSelected);
        resposneButton_2.setEnabled(!leftSelected);
        resposneButton_3.setEnabled(!leftSelected);
        resposneButton_4.setEnabled(!leftSelected);
        resposneButton_5.setEnabled(!leftSelected);
        targetButton.setEnabled(!leftSelected);
        medicineButton.setEnabled(!leftSelected);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox ageCheckBox;
    private javax.swing.JLabel ageLabel0;
    private javax.swing.JLabel ageLabel1;
    private javax.swing.JLabel ageLabel2;
    private javax.swing.JTextField ageMaxTextField;
    private javax.swing.JTextField ageMinTextField;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel leftHeaderLabel;
    private javax.swing.JPanel leftPanel;
    private javax.swing.JRadioButton medicineButton;
    private javax.swing.JRadioButton phoneNumberButton;
    private javax.swing.JRadioButton recommenderButton;
    private javax.swing.JRadioButton resposneButton_1;
    private javax.swing.JRadioButton resposneButton_2;
    private javax.swing.JRadioButton resposneButton_3;
    private javax.swing.JRadioButton resposneButton_4;
    private javax.swing.JRadioButton resposneButton_5;
    private javax.swing.JLabel rightHeaderLabel;
    private javax.swing.JPanel rightPanel;
    private javax.swing.JRadioButton targetButton;
    private javax.swing.JCheckBox themesCheckBox;
    private javax.swing.JList<String> themesList;
    // End of variables declaration//GEN-END:variables
}
