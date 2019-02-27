package CrazyClients;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Александр Машьянов, mashyanov1987@gmail.com
 */
public final class MySQLClient {
    
    private static MySQLClient msqlc = null;
    private Properties prop = new Properties();
    
    private MySQLClient() {
        try {   prop.load(new FileInputStream("DBProperties.properties"));
             
            Class.forName(prop.getProperty("driver"));
            try (Connection con = DriverManager.getConnection(prop.getProperty("url"),prop.getProperty("user"),prop.getProperty("password"))) {
                System.out.println("Connected to " + con.getCatalog());
            }
        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(new JPanel(), 
                    "Ошибка при подключении к базе данных\nПрограмма будет закрыта", 
                    "Критическая ошибка", JOptionPane.ERROR_MESSAGE);
             System.exit(1);
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(new JPanel(), 
                    "Ненайден файл конфигурации DBProperties.properties\nПрограмма будет закрыта", 
                    "Критическая ошибка", JOptionPane.ERROR_MESSAGE);
             System.exit(1);
        } catch (IOException ex) {
            Logger.getLogger(MySQLClient.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    public final static MySQLClient GetInstance(){
        if(msqlc == null) msqlc = new MySQLClient();
        return msqlc;
    }
    /*Метод создает бэкап БД. Файл cc.bak можно найти в папке backup в корневом 
    каталоге программы*/
    public final void createBackup(){
        File dir = new File("backup");
        File ccFile = new File("backup/cc.bak");
        if(!dir.exists()) dir.mkdir();
        if(ccFile.exists()) ccFile.delete();
        try (Connection con = DriverManager.getConnection(prop.getProperty("url"),prop.getProperty("user"),prop.getProperty("password"))) {
            String query = 
                    "BACKUP DATABASE CrazyClients " +
                    "TO DISK = '"+dir.getCanonicalPath()+"\\cc.bak'";
            con.createStatement().execute(query);
        } catch (SQLException ex) {
            Logger.getLogger(MySQLClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MySQLClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
//---------------------------------------------------------------------------//
//-------------------         РАБОТА С CLIENTS          ---------------------//
//---------------------------------------------------------------------------//  
    /**Метод получения клиента со всей игнформацией из БД
     * @param id - id клиента
     * @return СrazeClient или null  в случае возникновения исключений     */
     public CrazyClient getClient(int id){
        CrazyClient crazyClient = null;
        ResultSet rs;
        try (Connection con = DriverManager.getConnection(prop.getProperty("url"),prop.getProperty("user"),prop.getProperty("password"))) {
            String query = "Select * from Clients WHERE CLIENT_ID = "+ id;
                 
            rs = con.createStatement().executeQuery(query);
            rs.next();
            crazyClient = new CrazyClient(rs.getInt(1));
            crazyClient.setFirstName(rs.getString(2).trim());
            crazyClient.setLastName(rs.getString(3).trim());
            crazyClient.setRecommender(rs.getString(4).trim());
            crazyClient.setFirstAppointment(rs.getDate(5).toLocalDate());
            crazyClient.setResponse1(rs.getString(6));
            crazyClient.setResponse2(rs.getString(7));
            crazyClient.setResponse3(rs.getString(8));
            crazyClient.setResponse4(rs.getString(9));
            crazyClient.setResponse5(rs.getString(10));
            crazyClient.setCorrectionTarget(rs.getString(11));
            crazyClient.setPhoneNumber(rs.getString(12));
            crazyClient.setMedicine(rs.getString(13));
            crazyClient.setActual(rs.getInt(14)!=0);
            crazyClient.setLastAppointment(rs.getDate(15).toLocalDate());
            crazyClient.setDateOfBirth(rs.getDate(16).toLocalDate());
            crazyClient.setResponseMain(rs.getString(17) == null ? "" : rs.getString(17).trim());
        
             return crazyClient;
        } catch (SQLException ex) {
            Logger.getLogger(MySQLClient.class.getName()).log(Level.SEVERE, null, ex);
            return crazyClient;
        }
        
    }
    
    public int getClientsCount(boolean active){
        int result;
        String activeString = active ? "1" : "0";
        try (Connection con = DriverManager.getConnection(prop.getProperty("url"),prop.getProperty("user"),prop.getProperty("password"))) {
            ResultSet rs = con.createStatement().executeQuery(
                    "SELECT COUNT(CLIENT_ID) FROM Clients  WHERE IS_ACTUAL = " + activeString);
            rs.next();
            result = rs.getInt(1);
            } catch (SQLException ex) {
            Logger.getLogger(MySQLClient.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
        return result;
    }
     
    /**Метод получения списка клиентов, не посещавших больше 
     * определенного количества дней. 
     * @param days - количество дней с последнего приема
     * @return коллекцию клиентов. Пустую - если результаты не найдены. NULL - SQLException ex*/
    public ArrayList<CrazyClient> getClientsShirkers(int days){
        ArrayList<CrazyClient> list = new ArrayList<>();
        ResultSet rs;
        CrazyClient crazyClient;
        try (Connection con = DriverManager.getConnection(prop.getProperty("url"),prop.getProperty("user"),prop.getProperty("password"))) {
            rs = con.createStatement().executeQuery(
                    "SELECT CLIENT_ID, FIRST_NAME, LAST_NAME, PHONE_NUMBER, DATE_LAST "
                            + "FROM Clients "
                            + "WHERE IS_ACTUAL = 1 AND DATE_LAST < DATEADD(dd, -" + days +", GETDATE())");
            while(rs.next()){
                crazyClient = new CrazyClient(rs.getInt(1));
                crazyClient.setFirstName(rs.getString(2).trim());
                crazyClient.setLastName(rs.getString(3).trim());
                crazyClient.setPhoneNumber(rs.getString(4));
                crazyClient.setLastAppointment(rs.getDate(5).toLocalDate());
                list.add(crazyClient);                
        }
        return list;
        } catch (SQLException ex) {
            Logger.getLogger(MySQLClient.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
     
    /** Возвращает коллекцию клиентов, содержащую минимально-необходимую информацию 
     *  для заполнения списка клиентов
     * @param filter  фильтр для выбора клиентов из БД 
     * @return коллекцию клиентов. Пустую, если ничего не нашлось. NULL - если SQLException */
    public ArrayList<CrazyClient> getClientsList(SearchFilter filter){
        CrazyClient crazyClient;
        ArrayList<CrazyClient> list = new ArrayList<>();
        ResultSet rs;
        try (Connection con = DriverManager.getConnection(prop.getProperty("url"),prop.getProperty("user"),prop.getProperty("password"))) {
                        
            rs = con.createStatement().executeQuery(filter.createQuery());
            while(rs.next()){
                crazyClient = new CrazyClient(rs.getInt(1));
                crazyClient.setFirstName(rs.getString(2).trim());
                crazyClient.setLastName(rs.getString(3).trim());
                crazyClient.setDateOfBirth(rs.getDate(4).toLocalDate());
                crazyClient.setActual(rs.getInt(5)!=0);
                list.add(crazyClient);                
        }
        return list;
        } catch (SQLException ex) {
            Logger.getLogger(MySQLClient.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    /**Метод получения всех занятых пользовательских id
     * @return массив всех занятых пользовательских id      */
    public int[] getClientsID(){
        int [] result;
        try (Connection con = DriverManager.getConnection(prop.getProperty("url"),prop.getProperty("user"),prop.getProperty("password"))) {
            ResultSet rs = con.createStatement().executeQuery("SELECT COUNT(*) FROM Clients");
            rs.next();
            result = new int[rs.getInt(1)];
            rs = con.createStatement().executeQuery("SELECT CLIENT_ID FROM Clients");
            for (int i = 0; i < result.length; i++) {
                rs.next();
                result[i] = rs.getInt(1);
            }} catch (SQLException ex) {
            Logger.getLogger(MySQLClient.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return result;
    }
    
   /** Возвращает коллекцию клиентов, содержащую минимально-необходимую информацию 
     *  для заполнения списка клиентов, отобранную по основному запросу клиента
     * @param theme - основной запрос клиента
     * @return коллекцию клиентов. Пустую, если ничего не нашлось. NULL - если SQLException */
    public ArrayList<CrazyClient> getClientsByTheme (String theme){
        
        ArrayList<CrazyClient> list = new ArrayList<>();
        ResultSet rs;
        CrazyClient client;
        try (Connection con = DriverManager.getConnection(prop.getProperty("url"),prop.getProperty("user"),prop.getProperty("password"))) {
                        
            rs = con.createStatement().executeQuery("SELECT CLIENT_ID, FIRST_NAME, LAST_NAME FROM Clients WHERE RESPONSE_MAIN='"+theme+"'");
            while(rs.next()){
                client = new CrazyClient(rs.getInt(1));
                client.setFirstName(rs.getString(2));
                client.setLastName(rs.getString(3));
                list.add(client);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(MySQLClient.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    /**Метод добавляет клиента в БД
     * @param client новый клиент*/
    public void addClient(CrazyClient client){
        try (Connection con = DriverManager.getConnection(prop.getProperty("url"),prop.getProperty("user"),prop.getProperty("password"))) {
            String query = 
                    "INSERT INTO Clients("
                    + "CLIENT_ID, "
                    + "FIRST_NAME, "
                    + "LAST_NAME, "
                    + "RECOMENDER, "
                    + "DATE_FIRST, "
                    + "RESPONSE_1, "
                    + "RESPONSE_2, "
                    + "RESPONSE_3, "
                    + "RESPONSE_4, "
                    + "RESPONSE_5, "
                    + "CORRECTION_TARGET, "
                    + "PHONE_NUMBER, "
                    + "MEDICINE, "
                    + "DATE_LAST, "
                    + "IS_ACTUAL,"
                    + "DATE_OF_BIRTH, "
                    + "RESPONSE_MAIN) "
                    + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, client.getId());
            ps.setString(2, client.getFirstName());
            ps.setString(3, client.getLastName());
            ps.setString(4, client.getRecommender());
            ps.setDate(5, java.sql.Date.valueOf(client.getFirstAppointment()));
            ps.setString(6, client.getResponse1());
            ps.setString(7, client.getResponse2());
            ps.setString(8, client.getResponse3());
            ps.setString(9, client.getResponse4());
            ps.setString(10, client.getResponse5());
            ps.setString(11, client.getCorrectionTarget());
            ps.setString(12, client.getPhoneNumber());
            ps.setString(13, client.getMedicine());
            ps.setDate(14, java.sql.Date.valueOf(client.getLastAppointment()));
            ps.setInt(15, 1);
            ps.setDate(16, java.sql.Date.valueOf(client.getDateOfBirth()));
            ps.setString(17, client.getResponseMain());
            ps.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(new JPanel(), "ОШИБКА ПРИ ДОБАВЛЕНИИ КЛИЕНТА:\n"+ex.getMessage()+"\n"+ex.getSQLState(), "ОШИБКА!!!", JOptionPane.ERROR_MESSAGE);
        }}
           
    /**Метод редактирует данные клиента в БД
     * @param client клиент для редактирования
     * @return  true - если успешно, false - если нет*/
    public boolean editClient(CrazyClient client){
        try (Connection con = DriverManager.getConnection(prop.getProperty("url"),prop.getProperty("user"),prop.getProperty("password"))) {
            String query = 
                    "UPDATE Clients SET "
                    + "FIRST_NAME = ?, "
                    + "LAST_NAME = ?, "
                    + "RECOMENDER = ?, "
                    + "DATE_FIRST = ?, "
                    + "RESPONSE_1 = ?, "
                    + "RESPONSE_2 = ?, "
                    + "RESPONSE_3 = ?, "
                    + "RESPONSE_4 = ?, "
                    + "RESPONSE_5 = ?, "
                    + "CORRECTION_TARGET = ?, "
                    + "PHONE_NUMBER = ?, "
                    + "MEDICINE = ?, "
                    + "DATE_LAST = ?, "
                    + "IS_ACTUAL = ?, "
                    + "DATE_OF_BIRTH = ?, "
                    + "RESPONSE_MAIN = ? "
                    + "WHERE "
                    + "CLIENT_ID = ?";
             
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, client.getFirstName());
            ps.setString(2, client.getLastName());
            ps.setString(3, client.getRecommender());
            ps.setDate(4, java.sql.Date.valueOf(client.getFirstAppointment()));
            ps.setString(5, client.getResponse1());
            ps.setString(6, client.getResponse2());
            ps.setString(7, client.getResponse3());
            ps.setString(8, client.getResponse4());
            ps.setString(9, client.getResponse5());
            ps.setString(10, client.getCorrectionTarget());
            ps.setString(11, client.getPhoneNumber());
            ps.setString(12, client.getMedicine());
            ps.setDate(13, java.sql.Date.valueOf(client.getLastAppointment()));
            ps.setInt(14, client.isActual() ? 1 : 0);
            ps.setDate(15, java.sql.Date.valueOf(client.getDateOfBirth()));
            ps.setString(16, client.getResponseMain());
            ps.setInt(17, client.getId());
            return ps.executeUpdate()==1;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(new JPanel(), "ОШИБКА ПРИ РЕДАКТИРОВАНИИ КЛИЕНТА:\n"+ex.getMessage()+"\n"+ex.getSQLState(), "ОШИБКА!!!", JOptionPane.ERROR_MESSAGE);
            return false;
        }}
    
    /**Метод удаляет клиента из БД
     * @param client клиент на удаление*/
    public void removeClient(CrazyClient client){
        try (Connection con = DriverManager.getConnection(prop.getProperty("url"),prop.getProperty("user"),prop.getProperty("password"))) {
            con.createStatement().executeUpdate("DELETE FROM Clients WHERE CLIENT_ID="+client.getId());
            con.createStatement().executeUpdate("DELETE FROM Appointments WHERE CLIENT_ID="+client.getId());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(new JPanel(), "ОШИБКА ПРИ УДАЛЕНИИ КЛИЕНТА:\n"+ex.getMessage()+"\n"+ex.getSQLState(), "ОШИБКА!!!", JOptionPane.ERROR_MESSAGE);
        }}
    
    /**Метод стирает все данные в таблице clients*/
    public void wipeClientsTable(){
        try (Connection con = DriverManager.getConnection(prop.getProperty("url"),prop.getProperty("user"),prop.getProperty("password"))) {
            con.createStatement().executeUpdate("DELETE from Clients");
        } catch (SQLException ex) {
            Logger.getLogger(MySQLClient.class.getName()).log(Level.SEVERE, null, ex);
        }}
       

//---------------------------------------------------------------------------//
//-----------------------     РАБОТА С APPOINTMENTS     ---------------------//
//---------------------------------------------------------------------------//    
    
     /**Метод получения из БД первого посещения клиента
     * @param client клиент, для которого ищем первое посещение
     * @return первое посещение клиента      */
    public AppointmentDate getAppointmentDate(CrazyClient client){
         try (Connection con = DriverManager.getConnection(prop.getProperty("url"),prop.getProperty("user"),prop.getProperty("password"))) {
            ResultSet rs = con.createStatement().executeQuery(
                    "SELECT * FROM Appointments "
                  + "WHERE client_id=" + String.valueOf(client.getId()) 
                  + " AND DATE=(SELECT MIN(DATE) "
                  + "FROM Appointments "
                  + "WHERE client_id= " + String.valueOf(client.getId())+ ")" );
            AppointmentDate date;
            if(rs.next()){
                date = new AppointmentDate(rs.getInt(1), rs.getInt(2), 
                        rs.getDate(3).toLocalDate(), rs.getString(4));
                return  date;
            }
            return null;
            } catch (SQLException ex) {
            Logger.getLogger(MySQLClient.class.getName()).log(Level.SEVERE, null, ex);
            
            return null;
        }}
    
    /**Метод редактирует посещение. Изменения вносятся в строку-комментарий*/
    public void editAppointmet(AppointmentDate ad){
        try (Connection con = DriverManager.getConnection(prop.getProperty("url"),prop.getProperty("user"),prop.getProperty("password"))) {
            String query = 
                      "UPDATE Appointments SET "
                    + "PROCESS = ? "
                    + "WHERE "
                    + "APPOINTMENT_ID = ?";
             
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, ad.getAppointmentProcess());
            ps.setInt(2, ad.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(new JPanel(), "ОШИБКА ПРИ РЕДАКТИРОВАНИИ КЛИЕНТА:\n"
                    +ex.getMessage()+"\n"+ex.getSQLState(), "ОШИБКА!!!", JOptionPane.ERROR_MESSAGE);
            
        }}
    
    public AppointmentDate getAppointmentDate(int appointmentDateID){
        AppointmentDate ad = null;
        String query = 
                  "SELECT * "
                + "from Appointments "
                + "WHERE "
                + "APPOINTMENT_ID = " + String.valueOf(appointmentDateID);
        try (Connection con = DriverManager.getConnection(prop.getProperty("url"),prop.getProperty("user"),prop.getProperty("password"))) {
            ResultSet rs = con.createStatement().executeQuery(query);
            while(rs.next())
               ad = new AppointmentDate(rs.getInt(1), rs.getInt(2), 
                       rs.getDate(3).toLocalDate(), rs.getString(4));
            } catch (SQLException ex) {
            Logger.getLogger(MySQLClient.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return  ad;
    }

    /**Метод добавления в БД сведений и посещении
    * @param ad посещение*/
    public void addAppointment(AppointmentDate ad){
        try (Connection con = DriverManager.getConnection(prop.getProperty("url"),prop.getProperty("user"),prop.getProperty("password"))) {
            String querty = 
                    "INSERT INTO "
                    + "Appointments ("
                    + "APPOINTMENT_ID, "
                    + "CLIENT_ID, "
                    + "DATE, "
                    + "PROCESS )"
                    + "VALUES(?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(querty);
            ps.setInt(1, ad.getId());
            ps.setInt(2, ad.getClientId());
            ps.setDate(3, java.sql.Date.valueOf(ad.getDate()));
            ps.setString(4, ad.getAppointmentProcess());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLClient.class.getName()).log(Level.SEVERE, null, ex);
        }}
     
    /**Метод получения из БД первого посещения клиента
    * @param client клиент, для которого ищем первое посещение
    * @return первое посещение клиента      */
    public AppointmentDate getFirstAppointment(CrazyClient client){
         try (Connection con = DriverManager.getConnection(prop.getProperty("url"),prop.getProperty("user"),prop.getProperty("password"))) {
            ResultSet rs = con.createStatement().executeQuery(
                    "SELECT * FROM Appointments "
                  + "WHERE client_id=" + String.valueOf(client.getId()) 
                  + " AND DATE=(SELECT MIN(DATE) "
                  + "FROM Appointments "
                  + "WHERE client_id= " + String.valueOf(client.getId())+ ")" );
            AppointmentDate date;
            if(rs.next()){
                date = new AppointmentDate(rs.getInt(1), rs.getInt(2), 
                        rs.getDate(3).toLocalDate(), rs.getString(4));
                return  date;
            }
            return null;
            } catch (SQLException ex) {
            Logger.getLogger(MySQLClient.class.getName()).log(Level.SEVERE, null, ex);
            
            return null;
        }}
    
    /**Метод получения из БД последнего посещения клиента
     * @param client клиент, для которого ищем последнее посещение
     * @return последнее посещение клиента      */ 
    public AppointmentDate getLastAppointment(CrazyClient client){
         try (Connection con = DriverManager.getConnection(prop.getProperty("url"),prop.getProperty("user"),prop.getProperty("password"))) {
            ResultSet rs = con.createStatement().executeQuery(
                    "SELECT * FROM Appointments "
                  + "WHERE client_id=" + String.valueOf(client.getId()) 
                  + " AND DATE=(SELECT MAX(DATE) "
                  + "FROM Appointments "
                  + "WHERE client_id= " + String.valueOf(client.getId())+ ")" );
            AppointmentDate date;
            if(rs.next()){
                date = new AppointmentDate(rs.getInt(1), rs.getInt(2), 
                        rs.getDate(3).toLocalDate(), rs.getString(4));
                return  date;
            }
            return null;
            } catch (SQLException ex) {
            Logger.getLogger(MySQLClient.class.getName()).log(Level.SEVERE, null, ex);
            
            return null;
        }}
      
    /**Метод получения из БД всех посещений клиента в заданном месяце
     * 
     * @param clientId - id клиента, для которого выбираем посещения
     * @param month - номер месяца
     * @param year - номер года
     * @return коллекция посещений клиента в заданный месяц     */
    public ArrayList<AppointmentDate> getAppointmentsForMonth(int clientId, int month, int year){
        ArrayList<AppointmentDate> dates = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(prop.getProperty("url"),prop.getProperty("user"),prop.getProperty("password"))) {
            ResultSet rs = con.createStatement().executeQuery(
                    "SELECT * from Appointments WHERE client_id=" + String.valueOf(clientId) +
                            "AND MONTH(date)="+String.valueOf(month) + 
                            "AND YEAR(date)="+String.valueOf(year));
            while(rs.next())
                dates.add(new AppointmentDate(rs.getInt(1), clientId, 
                        rs.getDate(3).toLocalDate(), rs.getString(4)));
        } catch (SQLException ex) {
        Logger.getLogger(MySQLClient.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return  dates;
    }
    
       public ArrayList<AppointmentDate> getAppointmentsForClient(int clientId){
        ArrayList<AppointmentDate> dates = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(prop.getProperty("url"),prop.getProperty("user"),prop.getProperty("password"))) {
            ResultSet rs = con.createStatement().executeQuery(
                    "SELECT * from Appointments WHERE client_id= " + 
                            String.valueOf(clientId) + " ORDER BY DATE ASC");
            while(rs.next())
                dates.add(new AppointmentDate(rs.getInt(1), clientId, 
                        rs.getDate(3).toLocalDate(), rs.getString(4)));
        } catch (SQLException ex) {
        Logger.getLogger(MySQLClient.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return  dates;
    }
       
    /**метод удаляет посещение из БД
    * @param AppointmentId id посещения*/
    public void removeAppointment(int AppointmentId){
        try (Connection con = DriverManager.getConnection(prop.getProperty("url"),prop.getProperty("user"),prop.getProperty("password"))) {
            String querty = "DELETE FROM Appointments WHERE APPOINTMENT_ID = " 
                    + AppointmentId;
            con.createStatement().executeUpdate(querty);
        } catch (SQLException ex) {
            Logger.getLogger(MySQLClient.class.getName()).log(Level.SEVERE, null, ex);
        }}
    
    /**
    *
    * @return все имеющиеся в БД посещения всех клиентов
    */
    public ArrayList<AppointmentDate> getAllAppointments(){
        
        ArrayList<AppointmentDate> list = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(prop.getProperty("url"),prop.getProperty("user"),prop.getProperty("password"))) {
            ResultSet rs = con.createStatement().executeQuery("Select * from Appointments");
            while(rs.next())
                list.add(new AppointmentDate(rs.getInt(1), rs.getInt(2), 
                        rs.getDate(3).toLocalDate(), rs.getString(4)));
            return list;
        }catch (SQLException ex) {
            Logger.getLogger(MySQLClient.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }}
    
    /**Метод стирает все данные в таблице Appointnments*/
    public void wipeAppointmentsTable(){
        try (Connection con = DriverManager.getConnection(prop.getProperty("url"),prop.getProperty("user"),prop.getProperty("password"))) {
            con.createStatement().executeUpdate("DELETE from Appointments");
        } catch (SQLException ex) {
            Logger.getLogger(MySQLClient.class.getName()).log(Level.SEVERE, null, ex);
        }}
    
    //---------------------------------------------------------------------------//
    //--------------------------     РАБОТА С THEMES     ------------------------//
    //---------------------------------------------------------------------------// 
    
    public ArrayList<String> getThemes(){
        ArrayList<String> strings = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(prop.getProperty("url"),prop.getProperty("user"),prop.getProperty("password"))) {
            ResultSet rs = con.createStatement().executeQuery("SELECT * "
                    + "from Themes ORDER BY Theme ASC");
            while(rs.next())
                strings.add(rs.getString(1).trim());
        } catch (SQLException ex) {
        Logger.getLogger(MySQLClient.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return strings;
    }
       
    public void addTheme(String themeName){
        if(themeName==null || themeName.isEmpty()) return;
        try (Connection con = DriverManager.getConnection(prop.getProperty("url"),prop.getProperty("user"),prop.getProperty("password"))) {
            con.createStatement().executeUpdate("INSERT INTO Themes VALUES (\'" + themeName + "\')");
        } catch (SQLException ex) {
            Logger.getLogger(MySQLClient.class.getName()).log(Level.SEVERE, null, ex);
        JOptionPane.showMessageDialog(new JFrame(), "Такой запрос уже существует либо"
                + " длина запроса больше 50 символов", "Ошибка", JOptionPane.ERROR_MESSAGE);
    }}

    public void editTheme(String oldName, String newName){
        if(newName==null || newName.isEmpty()) return;
        if(newName.equalsIgnoreCase(oldName))  return;
        try (Connection con = DriverManager.getConnection(prop.getProperty("url"),prop.getProperty("user"),prop.getProperty("password"))) {
                con.createStatement().executeUpdate(
                        "UPDATE Themes SET " +
                        "theme = \'" + newName + "\'"+
                        "WHERE theme = \'" + oldName+"\'");
                ArrayList<CrazyClient> list = new ArrayList<>();
                ResultSet rs = con.createStatement().executeQuery(""
                        + "SELECT * FROM Clients");
                while(rs.next()){
                    CrazyClient crazyClient = new CrazyClient(rs.getInt(1));
                    crazyClient.setFirstName(rs.getString(2).trim());
                    crazyClient.setLastName(rs.getString(3).trim());
                    crazyClient.setRecommender(rs.getString(4).trim());
                    crazyClient.setFirstAppointment(rs.getDate(5).toLocalDate());
                    crazyClient.setResponse1(rs.getString(6));
                    crazyClient.setResponse2(rs.getString(7));
                    crazyClient.setResponse3(rs.getString(8));
                    crazyClient.setResponse4(rs.getString(9));
                    crazyClient.setResponse5(rs.getString(10));
                    crazyClient.setCorrectionTarget(rs.getString(11));
                    crazyClient.setPhoneNumber(rs.getString(12));
                    crazyClient.setMedicine(rs.getString(13));
                    crazyClient.setActual(rs.getInt(14)!=0);
                    crazyClient.setLastAppointment(rs.getDate(15).toLocalDate());
                    crazyClient.setDateOfBirth(rs.getDate(16).toLocalDate());
                    crazyClient.setResponseMain(rs.getString(17)==null ? "" : rs.getString(17).trim());
                    list.add(crazyClient);
                }
                
                for (CrazyClient client : list) {
                    if(client.getResponseMain().equals(oldName)){
                        client.setResponseMain(newName);
                        editClient(client);
                    }
                
            }
            } catch (SQLException ex) {
            JOptionPane.showMessageDialog(new JFrame(), "Такой запрос уже существует", 
                    "Ошибка", JOptionPane.ERROR_MESSAGE);
    }}

    public void removeTheme(String themeName){   
    try (Connection con = DriverManager.getConnection(prop.getProperty("url"),prop.getProperty("user"),prop.getProperty("password"))) {
            String querty = "DELETE FROM Themes WHERE theme = \'" + themeName+"\'";
            con.createStatement().executeUpdate(querty);
        } catch (SQLException ex) {
            Logger.getLogger(MySQLClient.class.getName()).log(Level.SEVERE, null, ex);
        }}
}
