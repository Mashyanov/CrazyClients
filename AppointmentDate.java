
package CrazyClients;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Александр Машьянов, mashyanov1987@gmail.com
 */
public class AppointmentDate implements Comparable<AppointmentDate>{
    private final LocalDate date;
    private final int id, clientId;
    private final MySQLClient msqlc = MySQLClient.GetInstance();
    private String appointmentProcess;
    public final static int NO_APPOINTMENT =-1;
    

    public AppointmentDate(int clientId, LocalDate date) {
        
        this.clientId = clientId;
        this.date = date;
        Random random = new Random();
        int tempId;
        boolean idIsOk = true;
        ArrayList<AppointmentDate> dates = msqlc.getAllAppointments();
        do { 
            tempId = random.nextInt(1000000);
            for (AppointmentDate aDate : dates){
                if (aDate.getId()==tempId) {
                    idIsOk=false;
                    break;
                }
            }
        }
        while (!idIsOk);
        id = tempId;
    }

    public AppointmentDate(int id, int clientId, LocalDate date, String process) {
        this.date = date;
        this.id = id;
        this.clientId = clientId;
        this.appointmentProcess = process;
    }

    public String    getAppointmentProcess() 
                                   {   return appointmentProcess;   }
    public LocalDate getDate()     {   return date;     }
    public int       getId()       {   return id;       }
    public int       getClientId() {   return clientId; }

    public void setAppointmentProcess(String appointmentProcess) {
        this.appointmentProcess = appointmentProcess;
    }

    @Override
    public int compareTo(AppointmentDate ad) {
        return date.compareTo(ad.getDate());
    }}
