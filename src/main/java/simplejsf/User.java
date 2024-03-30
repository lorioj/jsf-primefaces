
package simplejsf;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class User {
    
    private String name;
    
    public String getName(){
      System.out.println("getter method " + name);
      return this.name;
    }
    
    public void setName(String name){
        System.out.println("settter method " + name);
        this.name = name;
    }
    
    public void onChange(){
        System.out.println("simplejsf.User.onChange()");
    }
    
}
