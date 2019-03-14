
package CrazyClients;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Александр Машьянов, mashyanov1987@gmail.com
 */
public class MySequrityManager {
    
    private static MySequrityManager instance = null;
    private static byte[] key; 
    private Properties prop = new Properties();

    private MySequrityManager() {
        try {
            prop.load(new FileInputStream("DBProperties.properties"));
            Class.forName(prop.getProperty("driver"));
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(new JPanel(), 
                    "Не найден файл конфигурации БД DBProperties.properties\nПрограмма будет закрыта", 
                    "Критическая ошибка", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        } catch (IOException ex) {
            Logger.getLogger(MySequrityManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MySequrityManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static MySequrityManager getInstance() {
        return instance == null? new MySequrityManager() : instance;
    }
   
    public String getSQLPassword(){
        return decode(prop.getProperty("password"));
    }
    
    public String getFTPPassword(){
        return decode(prop.getProperty("FTPpassword"));
    }
    
    public final boolean checkPassword(String pswd){
        try {
            this.key = pswd.getBytes();
            String password = decode(prop.getProperty("password"));
            Connection con = DriverManager.getConnection(prop.getProperty("url"),prop.getProperty("user"), password);
            System.out.println("Connected to " + con.getCatalog());
        } catch (SQLException ex) {
             JOptionPane.showMessageDialog(new JPanel(), 
                    "Ошибка при подключении к базе данных\nСкорее всего неверный пароль!", 
                    "Критическая ошибка", JOptionPane.ERROR_MESSAGE);
            return false;
        } 
        return true;
    }
        
    private String decode(String text){
        byte[] decodedPassword = text.getBytes();
        if(key==null) System.out.println("nullkeu");
        for (int i = 0; i < decodedPassword.length; i++) {
            decodedPassword[i] = (byte) (decodedPassword[i]^key[i%key.length]);
        }
        return new String(decodedPassword);
    }
}