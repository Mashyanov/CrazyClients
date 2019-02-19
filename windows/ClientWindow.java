package CrazyClients.windows;

import CrazyClients.AppointmentDate;
import CrazyClients.CrazyClient;
import CrazyClients.MySQLClient;
import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.time.LocalDate;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
/**
 *
 * @author Александр Машьянов, mashyanov1987@gmail.com
 */
public class ClientWindow extends javax.swing.JDialog {
private final CrazyClient crazyClient;
private final MySQLClient msqlc = MySQLClient.GetInstance();
private final LocalDate currentDate = LocalDate.now();

     public ClientWindow (JFrame parent) {
        super(parent, true);
        initComponents();
        this.crazyClient = new CrazyClient();
        okButton.setText("Создать");
        fillThemesBox();
        responseBox.setSelectedItem(null);
        yearField.setText(String.valueOf(currentDate.getYear()));
        monthBox.setSelectedIndex(currentDate.getMonthValue()-1);
        dayField.setText(String.valueOf(currentDate.getDayOfMonth()));
        
        
        birthMonthBox.setSelectedIndex(currentDate.getMonthValue()-1);
        birthDayField.setText(String.valueOf(currentDate.getDayOfMonth()));
        okButton.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                firstNameLabel.setForeground(Color.black);
                lastNameLabel.setForeground(Color.black);
                firstDateLabel.setForeground(Color.black);
                birthdayLabel.setForeground(Color.black);
                mainResponseLabel.setForeground(Color.black);
            }
            
        }); 
        
        okButton.addActionListener((e) -> {
           
            yearField.setText(yearField.getText().replaceAll("[^\\d]", ""));
            dayField.setText(dayField.getText().replaceAll("[^\\d]", ""));
            birthDayField.setText(birthDayField.getText().replaceAll("[^\\d]", ""));
            birthYearField.setText(birthYearField.getText().replaceAll("[^\\d]", ""));
            
            if(!firstNameField.getText().isEmpty() && !lastNameField.getText().isEmpty()
                    && !yearField.getText().isEmpty() && !dayField.getText().isEmpty() &&
                    !birthDayField.getText().isEmpty() && !birthYearField.getText().isEmpty()
                    && responseBox.getSelectedItem()!=null){
   
                if(firstNameField.getText().length()>=20){
                    JOptionPane.showMessageDialog(this, "Неверные данные!\n"
                            + "Имя слишком длинное\n"
                            + "Максимальная длина - 20 символов", "ОШИБКА", 
                            JOptionPane.ERROR_MESSAGE);
                    return;  }
                
                if(lastNameField.getText().length()>=20){
                    JOptionPane.showMessageDialog(this, "Неверные данные!\n"
                            + "Фамилия слишком длинная\n"
                            + "Максимальная длина - 20 символов", "ОШИБКА", 
                            JOptionPane.ERROR_MESSAGE);
                    return;  }
                
                if(recommenderField.getText().length()>=20){
                    JOptionPane.showMessageDialog(this, "Неверные данные!\n"
                            + "\"От кого\" слишком длинное значение\n"
                            + "Максимальная длина - 20 символов", "ОШИБКА", 
                            JOptionPane.ERROR_MESSAGE);
                    return;  }
                
                crazyClient.setFirstName(firstNameField.getText().trim());
                crazyClient.setLastName(lastNameField.getText().trim());
                crazyClient.setRecommender(recommenderField.getText().trim());
                LocalDate tempDate = LocalDate.of(Integer.parseInt(
                        yearField.getText()), monthBox.getSelectedIndex()+1, 
                        Integer.parseInt(dayField.getText()));
                crazyClient.setFirstAppointment(tempDate);
                crazyClient.setLastAppointment(tempDate);
                crazyClient.getAppointmentsList().add(
                        LocalDate.of(Integer.parseInt(yearField.getText()), 
                                monthBox.getSelectedIndex()+1, 
                                Integer.parseInt(dayField.getText())));
                tempDate = LocalDate.of(Integer.parseInt(
                        birthYearField.getText()), birthMonthBox.getSelectedIndex()+1, 
                        Integer.parseInt(birthDayField.getText()));
                crazyClient.setDateOfBirth(tempDate);
                crazyClient.setResponse1(response1Field.getText().trim());
                crazyClient.setResponse2(response2Field.getText().trim());
                crazyClient.setResponse3(response3Field.getText().trim());
                crazyClient.setResponse4(response4Field.getText().trim());
                crazyClient.setResponse5(response5Field.getText().trim());
                crazyClient.setCorrectionTarget(correctionTargetField.getText().trim());
                crazyClient.setPhoneNumber(phoneNumberField.getText().replaceAll("[^\\d]", ""));
                crazyClient.setMedicine(medicineField.getText().trim());
                crazyClient.setResponseMain(responseBox.getSelectedItem().toString());
                msqlc.addClient(crazyClient);
                msqlc.addAppointment(new AppointmentDate(crazyClient.getId(), 
                        LocalDate.of(Integer.valueOf(yearField.getText()), 
                                monthBox.getSelectedIndex()+1, 
                                Integer.valueOf(dayField.getText()))));
                dispose();
            }
            else{
                JOptionPane.showMessageDialog(this, "Обязательные поля не заполнены!");
                firstNameLabel.setForeground(Color.red);
                lastNameLabel.setForeground(Color.red);
                firstDateLabel.setForeground(Color.red);
                birthdayLabel.setForeground(Color.red);
                mainResponseLabel.setForeground(Color.red);
                
            }
        });
    }

    public ClientWindow (JFrame parent, CrazyClient crazyClient) {
        super(parent, true);
        initComponents();
        this.crazyClient = crazyClient;
        fillThemesBox();
        responseBox.setSelectedItem(crazyClient.getResponseMain());
        LocalDate date = crazyClient.getFirstAppointment();
        yearField.setText(String.valueOf(date.getYear()));
        yearField.setEnabled(false);
        monthBox.setSelectedIndex(date.getMonthValue()-1);
        monthBox.setEnabled(false);
        dayField.setText(String.valueOf(date.getDayOfMonth()));
        dayField.setEnabled(false);
        date = crazyClient.getDateOfBirth();
        birthYearField.setText(String.valueOf(date.getYear()));
        birthMonthBox.setSelectedIndex(date.getMonthValue()-1);
        birthDayField.setText(String.valueOf(date.getDayOfMonth()));
        ageTextField.setText(String.valueOf(currentDate.getYear() - date.getYear() - 1));
        firstNameField.setText(crazyClient.getFirstName().trim());
        lastNameField.setText(crazyClient.getLastName().trim());
        recommenderField.setText(crazyClient.getRecommender().trim());
        phoneNumberField.setText(parsePhoneNumber(crazyClient.getPhoneNumber()).trim());
        response1Field.setText(crazyClient.getResponse1());
        response2Field.setText(crazyClient.getResponse2());
        response3Field.setText(crazyClient.getResponse3());
        response4Field.setText(crazyClient.getResponse4());
        response5Field.setText(crazyClient.getResponse5());
        correctionTargetField.setText(crazyClient.getCorrectionTarget());
        medicineField.setText(crazyClient.getMedicine());
        okButton.setText("Сохранить");
        okButton.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                firstNameLabel.setForeground(Color.black);
                lastNameLabel.setForeground(Color.black);
                firstDateLabel.setForeground(Color.black);
                birthdayLabel.setForeground(Color.black);
            }
            
        });
        okButton.addActionListener((e) -> {
            
            yearField.setText(yearField.getText().replaceAll("[^\\d]", ""));
            dayField.setText(dayField.getText().replaceAll("[^\\d]", ""));
            birthDayField.setText(birthDayField.getText().replaceAll("[^\\d]", ""));
            birthYearField.setText(birthYearField.getText().replaceAll("[^\\d]", ""));
            
            if(!firstNameField.getText().isEmpty() && !lastNameField.getText().isEmpty() && 
                    !yearField.getText().isEmpty() && !dayField.getText().isEmpty() &&
                    !birthDayField.getText().isEmpty() && !birthYearField.getText().isEmpty()){
   
                if(firstNameField.getText().length()>=20){
                    JOptionPane.showMessageDialog(this, "Неверные данные!\n"
                            + "Имя слишком длинное\n"
                            + "Максимальная длина - 20 символов", "ОШИБКА", 
                            JOptionPane.ERROR_MESSAGE);
                    return;  }
                
                if(lastNameField.getText().length()>=20){
                    JOptionPane.showMessageDialog(this, "Неверные данные!\n"
                            + "Фамилия слишком длинная\n"
                            + "Максимальная длина - 20 символов", "ОШИБКА", 
                            JOptionPane.ERROR_MESSAGE);
                    return;  }
                
                if(recommenderField.getText().length()>=20){
                    JOptionPane.showMessageDialog(this, "Неверные данные!\n"
                            + "\"От кого\" слишком длинное значение\n"
                            + "Максимальная длина - 20 символов", "ОШИБКА", 
                            JOptionPane.ERROR_MESSAGE);
                    return;  }
                
                crazyClient.setFirstName(firstNameField.getText().trim());
                crazyClient.setLastName(lastNameField.getText().trim());
                crazyClient.setRecommender(recommenderField.getText().trim());
                
                LocalDate dateOfBirth = LocalDate.of(Integer.parseInt(birthYearField.getText()), birthMonthBox.getSelectedIndex()+1, 
                        Integer.parseInt(birthDayField.getText()));
                
                this.crazyClient.setFirstAppointment(LocalDate.of(Integer.parseInt(yearField.getText()), monthBox.getSelectedIndex()+1, Integer.parseInt(dayField.getText())));
                this.crazyClient.setResponse1(response1Field.getText().trim());
                this.crazyClient.setResponse2(response2Field.getText().trim());
                this.crazyClient.setResponse3(response3Field.getText().trim());
                this.crazyClient.setResponse4(response4Field.getText().trim());
                this.crazyClient.setResponse5(response5Field.getText().trim());
                this.crazyClient.setCorrectionTarget(correctionTargetField.getText().trim());
                this.crazyClient.setPhoneNumber(phoneNumberField.getText().replaceAll("[^\\d]", ""));
                this.crazyClient.setMedicine(medicineField.getText().trim());
                this.crazyClient.setDateOfBirth(dateOfBirth);
                crazyClient.setResponseMain(responseBox.getSelectedItem().toString());
                msqlc.editClient(this.crazyClient);
                dispose();
            }
                else{
                    JOptionPane.showMessageDialog(this, "Обязательные поля не заполнены!");    
                    firstNameLabel.setForeground(Color.red);
                    lastNameLabel.setForeground(Color.red);
                    firstDateLabel.setForeground(Color.red);
                    birthdayLabel.setForeground(Color.red);
                }
        });
    }

    private void fillThemesBox(){
        responseBox.removeAllItems();
        for (String theme: msqlc.getThemes()) 
            responseBox.addItem(theme);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        firstNameField = new javax.swing.JTextField();
        lastNameField = new javax.swing.JTextField();
        response1Field = new javax.swing.JTextField();
        response2Field = new javax.swing.JTextField();
        response3Field = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        medicineField = new javax.swing.JEditorPane();
        okButton = new javax.swing.JButton();
        firstNameLabel = new javax.swing.JLabel();
        lastNameLabel = new javax.swing.JLabel();
        recommenderLabel = new javax.swing.JLabel();
        recommenderField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        response4Field = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        response5Field = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        cancelButton = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        phoneNumberField = new javax.swing.JTextField();
        firstDateLabel = new javax.swing.JLabel();
        dayField = new javax.swing.JTextField();
        monthBox = new javax.swing.JComboBox<>();
        yearField = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        correctionTargetField = new javax.swing.JEditorPane();
        phoneBeginningLabel = new javax.swing.JLabel();
        birthdayLabel = new javax.swing.JLabel();
        birthMonthBox = new javax.swing.JComboBox<>();
        birthDayField = new javax.swing.JTextField();
        birthYearField = new javax.swing.JTextField();
        mainResponseLabel = new javax.swing.JLabel();
        responseBox = new javax.swing.JComboBox<>();
        ageTextField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        editButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        firstNameField.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        firstNameField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                firstNameFieldFocusLost(evt);
            }
        });

        lastNameField.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        lastNameField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                lastNameFieldFocusLost(evt);
            }
        });

        response1Field.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        response2Field.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        response3Field.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        jScrollPane1.setViewportView(medicineField);

        okButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        okButton.setText("Создать");

        firstNameLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        firstNameLabel.setText("Имя");

        lastNameLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lastNameLabel.setText("Фамилия");

        recommenderLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        recommenderLabel.setText("От кого");

        recommenderField.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        recommenderField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                recommenderFieldFocusLost(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Запрос 1");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Запрос 2");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Запрос 3");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Запрос 4");

        response4Field.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("Запрос 5");

        response5Field.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("Главные мишени коррекции");

        cancelButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cancelButton.setText("Отмена");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setText("Телефон");

        phoneNumberField.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        phoneNumberField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                phoneNumberFieldKeyReleased(evt);
            }
        });

        firstDateLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        firstDateLabel.setText("Первое посещение");

        dayField.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        dayField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        dayField.setText("03");
        dayField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                dayFieldFocusGained(evt);
            }
        });

        monthBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Январь", "Февраль", "Март", "Апрель", "Май", "Июнь", "Июль", "Авгус", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь" }));

        yearField.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        yearField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        yearField.setText("2019");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setText("Медицинский анамнез");

        jScrollPane2.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        correctionTargetField.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jScrollPane2.setViewportView(correctionTargetField);

        phoneBeginningLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        phoneBeginningLabel.setText("+7");

        birthdayLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        birthdayLabel.setText("Дата рождения");

        birthMonthBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Январь", "Февраль", "Март", "Апрель", "Май", "Июнь", "Июль", "Авгус", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь" }));

        birthDayField.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        birthDayField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        birthDayField.setText("03");
        birthDayField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                birthDayFieldFocusGained(evt);
            }
        });

        birthYearField.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        birthYearField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        birthYearField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                birthYearFieldKeyReleased(evt);
            }
        });

        mainResponseLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        mainResponseLabel.setText("Основной запрос");

        responseBox.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        ageTextField.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        ageTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        ageTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                ageTextFieldKeyReleased(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText(" Лет");

        editButton.setText("редактировать");
        editButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(firstNameField, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
                            .addComponent(recommenderField))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(phoneBeginningLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(phoneNumberField, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addComponent(birthDayField, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(birthMonthBox, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(birthYearField, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addComponent(birthdayLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                                                    .addComponent(ageTextField))))
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(lastNameField))
                                .addContainerGap())))
                    .addComponent(mainResponseLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(dayField, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(monthBox, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(yearField, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(firstDateLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
                                .addComponent(recommenderLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(firstNameLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(79, 79, 79)
                        .addComponent(lastNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(response2Field)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(response5Field)
                            .addComponent(response4Field)
                            .addComponent(response3Field)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(responseBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(editButton, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(response1Field)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cancelButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(okButton)))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(firstNameLabel)
                    .addComponent(lastNameLabel))
                .addGap(3, 3, 3)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(firstNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lastNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(recommenderLabel)
                    .addComponent(jLabel10))
                .addGap(3, 3, 3)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(recommenderField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(phoneNumberField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(phoneBeginningLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(firstDateLabel)
                        .addComponent(birthdayLabel))
                    .addComponent(jLabel2))
                .addGap(3, 3, 3)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(monthBox, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(yearField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(birthMonthBox, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(birthDayField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(birthYearField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ageTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dayField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(mainResponseLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(responseBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(editButton, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addGap(3, 3, 3)
                .addComponent(response1Field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addGap(3, 3, 3)
                .addComponent(response2Field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6)
                .addGap(3, 3, 3)
                .addComponent(response3Field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7)
                .addGap(3, 3, 3)
                .addComponent(response4Field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel8)
                .addGap(3, 3, 3)
                .addComponent(response5Field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel9)
                .addGap(3, 3, 3)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel12)
                .addGap(3, 3, 3)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(okButton)
                    .addComponent(cancelButton))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        dispose();
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void dayFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_dayFieldFocusGained
        dayField.selectAll();
    }//GEN-LAST:event_dayFieldFocusGained

    private void birthDayFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_birthDayFieldFocusGained
        birthDayField.selectAll();
    }//GEN-LAST:event_birthDayFieldFocusGained

    private void firstNameFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_firstNameFieldFocusLost
        if(firstNameField.getText().isEmpty()) return;
        String firstName = firstNameField.getText().toLowerCase();
        firstName = firstName.trim().replaceAll(" +", " ");
        firstName = firstName.replaceFirst(firstName.substring(0, 1), 
                                        firstName.substring(0, 1).toUpperCase());
        firstNameField.setText(firstName);
        if(firstName.length()>=20) {
            firstNameField.setForeground(Color.red);
            firstNameLabel.setForeground(Color.red);
            firstNameLabel.setText("Имя слишком длинное");
        }
        else {
            firstNameField.setForeground(Color.black);
            firstNameLabel.setForeground(Color.black);
            firstNameLabel.setText("Имя");
        }
    }//GEN-LAST:event_firstNameFieldFocusLost

    private void lastNameFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_lastNameFieldFocusLost
        if(lastNameField.getText().isEmpty()) return;
        String lastName = lastNameField.getText().toLowerCase();
        lastName = lastName.trim().replaceAll(" +", " ");
        lastName = lastName.replaceFirst(lastName.substring(0, 1), 
                                        lastName.substring(0, 1).toUpperCase());
        lastNameField.setText(lastName);
        if(lastName.length()>=20) {
            lastNameField.setForeground(Color.red);
            lastNameLabel.setForeground(Color.red);
            lastNameLabel.setText("Фамилия слишком длинная");
        }
        else {
            lastNameField.setForeground(Color.black);
            lastNameLabel.setForeground(Color.black);
            lastNameLabel.setText("Фамилия");
        }
    }//GEN-LAST:event_lastNameFieldFocusLost

    private void recommenderFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_recommenderFieldFocusLost
         String recommender;
            if(recommenderField.getText().isEmpty()) return;
            recommender = recommenderField.getText().trim().replaceAll(" +", " ");
            String [] strings = recommender.split(" ");
            recommender = "";
            for (String string : strings) {
                string = string.toLowerCase();
                string = string.replaceFirst(string.substring(0, 1), 
                        string.substring(0, 1).toUpperCase());
                recommender+=(string+" ");
            }
            recommender = recommender.trim();
            recommenderField.setText(recommender);
            if(recommender.length()>=20){
                recommenderField.setForeground(Color.red);
                recommenderField.setForeground(Color.red);
                recommenderLabel.setText("Значение слишком длинное");
            }
            else {
                recommenderField.setForeground(Color.black);
                recommenderField.setForeground(Color.black);
                recommenderLabel.setText("От кого");
            }
    }//GEN-LAST:event_recommenderFieldFocusLost

    private void phoneNumberFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_phoneNumberFieldKeyReleased
        if(evt.getKeyCode()==37 || evt.getKeyCode()==39) return;
        int caretPosition = phoneNumberField.getCaretPosition();
        int length = phoneNumberField.getText().replaceAll("[^\\d]", "").length();
        int counter =  (length < 3) ? 0 : (length >=3 && length < 5) ? 1 : (length >=5 && length< 7) ? 2 :(length >=7 && length< 9) ? 3 : 4;;
        phoneNumberField.setText(parsePhoneNumber(phoneNumberField.getText()));
        phoneNumberField.setCaretPosition(caretPosition +counter <= phoneNumberField.getText().length() ? caretPosition + counter :phoneNumberField.getText().length());
    }//GEN-LAST:event_phoneNumberFieldKeyReleased

    private void birthYearFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_birthYearFieldKeyReleased
        
        int caretPosition = birthYearField.getCaretPosition();
        birthYearField.setText(birthYearField.getText().replaceAll("[^\\d]", ""));
        if(birthYearField.getText().isEmpty()){
            ageTextField.setText("");
            return;
        }
        birthYearField.setCaretPosition(caretPosition <= yearField.getText().length() ? caretPosition : yearField.getText().length());
        ageTextField.setText(String.valueOf(currentDate.getYear() - Integer.parseInt(birthYearField.getText())));
        
        
    }//GEN-LAST:event_birthYearFieldKeyReleased

    private void ageTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ageTextFieldKeyReleased
        
        int caretPosition = ageTextField.getCaretPosition();
        ageTextField.setText(ageTextField.getText().replaceAll("[^\\d]", ""));
        if(ageTextField.getText().isEmpty()){
            birthYearField.setText("");
            return;
        }
        ageTextField.setCaretPosition(caretPosition <= ageTextField.getText().length() ? caretPosition : ageTextField.getText().length());
        birthYearField.setText(String.valueOf(currentDate.getYear() - Integer.parseInt(ageTextField.getText()) -1 ));
    }//GEN-LAST:event_ageTextFieldKeyReleased

    private void editButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editButtonActionPerformed
        setVisible(false);
        JOptionPane.showMessageDialog(this, new ThemesPanel(), "Редактирование", 
                JOptionPane.PLAIN_MESSAGE);
        setVisible(true);
        fillThemesBox();
    }//GEN-LAST:event_editButtonActionPerformed
        
    private String parsePhoneNumber(String number){
        
        number = number.replaceAll("[^\\d]", "");
        StringBuilder stb = new StringBuilder();   
        char [] tempArray = number.toCharArray();
        for (int i = 0; i < number.length(); i++) {
            switch(i){
                case 0:
                    if(number.length() > 7)
                        stb.append("(");
                    
                    break;
                case 3:
                    if(number.length() > 7){
                        phoneBeginningLabel.setText("+7");  
                        stb.append(")");
                    }
                    else {
                        stb.append("-");
                        phoneBeginningLabel.setText("");
                    }
                    break;
                case 5:
                    if(number.length() <= 7)
                        stb.append("-");
                    
                    break;
                case 6:case 8:
                    if(number.length() > 7)
                        stb.append('-');
                    break;
            }
            stb.append(tempArray[i]);
            
        }           
        
        return stb.toString();
    }

   


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField ageTextField;
    private javax.swing.JTextField birthDayField;
    private javax.swing.JComboBox<String> birthMonthBox;
    private javax.swing.JTextField birthYearField;
    private javax.swing.JLabel birthdayLabel;
    private javax.swing.JButton cancelButton;
    private javax.swing.JEditorPane correctionTargetField;
    private javax.swing.JTextField dayField;
    private javax.swing.JButton editButton;
    private javax.swing.JLabel firstDateLabel;
    private javax.swing.JTextField firstNameField;
    private javax.swing.JLabel firstNameLabel;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField lastNameField;
    private javax.swing.JLabel lastNameLabel;
    private javax.swing.JLabel mainResponseLabel;
    private javax.swing.JEditorPane medicineField;
    private javax.swing.JComboBox<String> monthBox;
    private javax.swing.JButton okButton;
    private javax.swing.JLabel phoneBeginningLabel;
    private javax.swing.JTextField phoneNumberField;
    private javax.swing.JTextField recommenderField;
    private javax.swing.JLabel recommenderLabel;
    private javax.swing.JTextField response1Field;
    private javax.swing.JTextField response2Field;
    private javax.swing.JTextField response3Field;
    private javax.swing.JTextField response4Field;
    private javax.swing.JTextField response5Field;
    private javax.swing.JComboBox<String> responseBox;
    private javax.swing.JTextField yearField;
    // End of variables declaration//GEN-END:variables


       
        
}
