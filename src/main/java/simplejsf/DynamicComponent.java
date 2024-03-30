package simplejsf;

import javax.faces.bean.ManagedBean;


@ManagedBean
public class DynamicComponent {
    private Integer numberOfGuests;
    private String[] guests;

    public Integer getNumberOfGuests() {
        return numberOfGuests;
    }

    public String[] getGuests() {
        return guests;
    }

    public void setNumberOfGuests(Integer numberOfGuests) {
        this.numberOfGuests = numberOfGuests;
    }

    public void setGuests(String[] guests) {
        this.guests = guests;
    }
    
    public void submit() {
        guests = new String[numberOfGuests];
        for(int i = 0; i < numberOfGuests; i++){
            guests[i] = "name" + i;
        }
    }
}
