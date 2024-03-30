
package simplejsf;

import java.util.ArrayList;
import java.util.List;


public class DataViewBean {
    
    private List<Car> cars;
     
    private Car selectedCar;
     
//    @ManagedProperty("#{carService}")
//    private CarService service;
//     
//    @PostConstruct
//    public void init() {
//        cars = service.createCars(48);
//    }
 
    public List<Car> getCars() {
        List<Car> cars = new ArrayList<>();
        Car c = new Car();
        c.setColor("blue");
        cars.add(c);
        return cars;
    }
 
//    public void setService(CarService service) {
//        this.service = service;
//    }
 
    public Car getSelectedCar() {
        return selectedCar;
    }
 
    public void setSelectedCar(Car selectedCar) {
        this.selectedCar = selectedCar;
    }
    
}
