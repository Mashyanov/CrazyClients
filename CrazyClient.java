package CrazyClients;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Александр Машьянов, mashyanov1987@gmail.com
 */
public class CrazyClient {
    private final int id;
    private String firstName, lastName, recommender, phoneNumber, responseMain, 
            response1, response2,response3,response4,response5, correctionTarget,
            medicine, color;
    private final ArrayList<LocalDate> appointmentsList = new ArrayList<>();
    private LocalDate firstAppointment, lastAppointment, dateOfBirth;
    private boolean actual;
        
    public CrazyClient() {
        Random random = new Random();
        boolean idIsOk = true;
        int idTemp;
        do{
            idTemp  = random.nextInt(100000);
            for (int id : MySQLClient.GetInstance().getClientsID()) {
                if(id==idTemp){
                    idIsOk=false;
                    break;
                }
            }
        } while(!idIsOk);
        this.id = idTemp;
        this.actual = true;
        this.color  = "black";
    }

    public CrazyClient(int id) {
        this.id = id;
        this.actual = true;
        this.color  = "black";
    }
    
    public String getEditedPhoneNumber(){  
        char [] tempArray = phoneNumber.toCharArray();
        StringBuilder stb = new StringBuilder("+7 (");   
        for (int i = 0; i < phoneNumber.length(); i++) {
            if(tempArray[i]==' ') return "номер телефона не задан";
            switch(i){
                case 3:
                    stb.append(") ");
                    break;
                case 6:case 8:
                    stb.append('-');
                    break;
            }
            stb.append(tempArray[i]);
        }
        return stb.toString();
    }

    public LocalDate getDateOfBirth()                 {     return dateOfBirth;         }
    public LocalDate getLastAppointment()             {     return lastAppointment;     }
    public LocalDate getFirstAppointment()            {     return firstAppointment;    }
    public ArrayList<LocalDate> getAppointmentsList() {     return appointmentsList;    }
    public int    getId()               {    return id;                }
    public String getFirstName()        {    return firstName;         }
    public String getLastName()         {    return lastName;          }
    public String getRecommender()      {    return recommender;       }
    public String getPhoneNumber()      {    return phoneNumber;       }
    public String getResponseMain()     {    return responseMain;      }
    public String getResponse1()        {    return response1;         }
    public String getResponse2()        {    return response2;         }
    public String getResponse3()        {    return response3;         }
    public String getResponse4()        {    return response4;         }
    public String getResponse5()        {    return response5;         }
    public String getCorrectionTarget() {    return correctionTarget;  }
    public String getMedicine()         {    return medicine;          }
    public boolean isActual()           {    return actual;            }
    
    public void setDateOfBirth(LocalDate dateOfBirth)           {     this.dateOfBirth = dateOfBirth;              }
    public void setLastAppointment(LocalDate lastAppointment)   {     this.lastAppointment  = lastAppointment;     }
    public void setFirstAppointment(LocalDate firstAppointment) {     this.firstAppointment = firstAppointment;    }
    public void setCorrectionTarget(String correctionTarget)    {     this.correctionTarget = correctionTarget;    }
    public void setFirstName(String firstName)     {    this.firstName    = firstName.trim();   }
    public void setLastName(String lastName)       {    this.lastName     = lastName.trim();    }
    public void setRecommender(String recommender) {    this.recommender  = recommender.trim(); }
    public void setPhoneNumber(String phoneNumber) {    this.phoneNumber  = phoneNumber;        }
    public void setResponseMain(String responseM)  {    this.responseMain = responseM;          }
    public void setResponse1(String response1)     {    this.response1    = response1;          }
    public void setResponse2(String response2)     {    this.response2    = response2;          }
    public void setResponse3(String response3)     {    this.response3    = response3;          }
    public void setResponse4(String response4)     {    this.response4    = response4;          }
    public void setResponse5(String response5)     {    this.response5    = response5;          }
    public void setMedicine(String medicine)       {    this.medicine     = medicine;           }
 
    public void setActual(boolean actual) {  
        this.actual = actual;
        if(actual) color = "black";          
        else       color = "Red";
    }
     
    @Override
    public String toString() {
        int age =LocalDate.now().minusYears(dateOfBirth.getYear()).getYear() - 1;   
        String yearsString;
        switch(age%10){
            case 1:
                yearsString = " год";
                break;
            case 2:case 3:case 4:
                yearsString = " года";
                break;
            default:
                yearsString = " лет";
                break;
            
        }
        return "<html><font color="+color+">"+ lastName + " " + firstName + ", "+  age + yearsString + "</font></html>";
    }}
