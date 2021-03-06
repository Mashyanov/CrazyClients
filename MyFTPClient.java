package CrazyClients;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPConnectionClosedException;
/**
 *
 * @author Александр Машьянов, mashyanov1987@gmail.com
 */
public final class MyFTPClient {
 
    private FileInputStream fis;
    private FTPClient client;
    private final Properties prop = new Properties();
    private static MyFTPClient myFTPClient = null;

    private MyFTPClient() {
        client = new FTPClient();
        try {
            prop.load(new FileInputStream("DBProperties.properties"));
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
            client.connect(prop.getProperty("FTPhost"));
            if(!client.isConnected()) return 11;
            client.enterLocalPassiveMode();
            boolean logged = client.login(prop.getProperty("FTPlogin"), 
                                          MySequrityManager.getInstance().getFTPPassword());
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
            client.setFileType(FTP.BINARY_FILE_TYPE);
            client.changeWorkingDirectory(prop.getProperty("FTPWorkDirectory"));
            String fileName = date.format(DateTimeFormatter.ofPattern("dd-MM-uuuu(HH-mm-ss)"))+prop.getProperty("PCIndex");
            ArrayList<String> files = new ArrayList<>();
            for (String s : (client.listNames())) {
                if(s.length() > 2) files.add(s);
            }
            if(files.size() > 9){
                files.sort((f1, f2) -> {
                    return  Integer.valueOf(f1.substring(6, 10)) - Integer.valueOf(f2.substring(6, 10))!=0 ? 
                            Integer.valueOf(f1.substring(6, 10)) - Integer.valueOf(f2.substring(6, 10)) :
                            Integer.valueOf(f1.substring(3, 5)) - Integer.valueOf(f2.substring(3, 5))!=0 ? 
                            Integer.valueOf(f1.substring(3, 5)) - Integer.valueOf(f2.substring(3, 5)) :
                            Integer.valueOf(f1.substring(0, 2)) - Integer.valueOf(f2.substring(0, 2))!=0 ? 
                            Integer.valueOf(f1.substring(0, 2)) - Integer.valueOf(f2.substring(0, 2)) :
                            Integer.valueOf(f1.substring(11, 13)) - Integer.valueOf(f2.substring(11, 13))!=0 ? 
                            Integer.valueOf(f1.substring(11, 13)) - Integer.valueOf(f2.substring(11, 13)) :
                            Integer.valueOf(f1.substring(14, 16)) - Integer.valueOf(f2.substring(14, 16))!=0 ? 
                            Integer.valueOf(f1.substring(14, 16)) - Integer.valueOf(f2.substring(14, 16)) :
                            Integer.valueOf(f1.substring(17, 19)) - Integer.valueOf(f2.substring(17, 19));
                            });
                client.deleteFile(files.get(0));
            }
            result = client.appendFile(fileName+".bak", fis);
            fis.close();
            return result;
        } catch (FTPConnectionClosedException ex){
            disconnect();
            connect();
        } catch (IOException ex) {
            System.out.println("IO error " + ex.getMessage());
        }
        return result;
    }

}
