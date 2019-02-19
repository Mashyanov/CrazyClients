
package CrazyClients;

import CrazyClients.windows.CalendarWindow;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;

/**
 *
 * @author Александр Машьянов, mashyanov1987@gmail.com
 */
public final class DateLabel extends JLabel{
    private boolean selected;
    private int appointmentId = AppointmentDate.NO_APPOINTMENT;
    private AppointmentDate ad;
    
    public DateLabel() {
        setOpaque(true);
        setHorizontalAlignment(JLabel.LEADING);
        setVerticalAlignment(JLabel.TOP);
        selected = false;
        setText(" ");
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                if(evt.getButton() == MouseEvent.BUTTON1 && isEnabled()){
                    setSelected(true);
        }}});}
    
    public void setAppointment(int appointmentID){
        this.appointmentId = appointmentID;
        if(appointmentID==-1) setBackground(new Color(240, 240, 240));
        else setBackground(new Color(-8731663));
    }

    public boolean isSelected() {
        return selected;
    }

    public int getAppointmentId() {
        return appointmentId;
    }
   
    public void setSelected(boolean aFlag){
        if(aFlag) {
            for(DateLabel label : CalendarWindow.getCalendarField())
                label.setSelected(false);
            setForeground(Color.red);
            setBorder(new LineBorder(Color.RED, 2));
            setBackground(getBackground().darker());
            
        }
        else {
            setBackground(appointmentId == -1 ? new Color(240, 240, 240) : 
                    new Color(-8731663));
            setForeground(Color.black);
            setBorder(new EtchedBorder(EtchedBorder.LOWERED));
        }
        selected = aFlag;
    }

}
