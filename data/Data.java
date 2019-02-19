
package CrazyClients.data;

import java.util.ArrayList;
import javax.swing.AbstractListModel;

/**
 *
 * @author Александр Машьянов, mashyanov1987@gmail.com
 */
public class Data extends AbstractListModel<String>{
    private final ArrayList<String> list = new ArrayList<>();
    
    public void fire(){
        fireContentsChanged(this, 0, list.size());
    }
    
    @Override
    public int getSize() {
        return list.size();
    }
    public void add(String s){
        list.add(s);
    }
    @Override
    public String getElementAt(int index) {
        return list.get(index);
    }
    
    public void clear(){
        list.clear();
    }
   
    
}
