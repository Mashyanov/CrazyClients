
package CrazyClients;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
/**
 *
 * @author Александр Машьянов, mashyanov1987@gmail.com
 */
public class MyFTPClient {
 
private FileInputStream fis;
private FTPClient client;

//АКТУАЛЬНЫЙ СЕРВЕР

private final String FTPlogin = ":)";
private final String FTPpassword = ":)";
private final String FTPhost = ":)";
private static MyFTPClient myFTPClient = null;

    private MyFTPClient() {
        client = new FTPClient();
    try {
       
        Runtime p = Runtime.getRuntime();
        p.exec("netsh advfirewall set global StatefulFTP disable");
    } catch (IOException ex) {
        Logger.getLogger(MyFTPClient.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
    
    public static MyFTPClient getInstance(){
    if(myFTPClient == null) myFTPClient = new MyFTPClient();
    return myFTPClient;
 }

    public int connect(){
        try {
            
            System.out.println("Подключаемся к FTP-серверу...");
            client.connect(FTPhost);
            if(!client.isConnected()) return 11;
            client.enterLocalPassiveMode();
            boolean logged = client.login(FTPlogin, FTPpassword);
            if(logged) {
                System.out.println("Успешное подключение!");
            return 0;
        }
    } catch (IOException ex) {
        JOptionPane.showMessageDialog(new JPanel(), "Не удалось подключиться к серверу.\n"
                + "Проверьте ваше интернет-соединение", 
                  "Ошибка соединения", JOptionPane.ERROR_MESSAGE);
        return 1111;
    }
    
    JOptionPane.showMessageDialog(new JPanel(), "Не удалось подключиться к серверу\n"
                + "Проверьте интернет-соединение", "Ошибка соединения", JOptionPane.ERROR_MESSAGE);
    System.out.println("Отключено...");
    return 22;
}
    public void disconnect(){
    try {
        client.disconnect();
    } catch (IOException ex) {
        Logger.getLogger(MyFTPClient.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
public boolean uploadDBBackUp() {
boolean result = false; 
    LocalDateTime date = LocalDateTime.now();
    File dir = new File("backup/cc.bak");
    
    try {
        fis = new FileInputStream(dir);
        client.changeWorkingDirectory(":)");
        client.setFileType(FTP.BINARY_FILE_TYPE);
        String fileName = date.format(DateTimeFormatter.ofPattern("dd-MM-uuuu(HH-mm-ss)"));
        String files[] = client.listNames();
        System.out.println(files.length);
        if(files.length > 9)
            client.deleteFile(files[2]);
        result = client.appendFile(fileName+".bak", fis);
        fis.close();
        System.out.println(date.toString());
        System.out.println(result);
        return result;
    } catch (IOException ex) {
        System.out.println("IO error " + ex.getMessage());
    }
    return result;
}


}
