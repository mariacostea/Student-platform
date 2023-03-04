import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Parent extends User implements Observer{
    public Parent(String firstname, String lastname){
        super(firstname, lastname);
    }

    public String toString() {
        return this.get_firstname() + " " + this.get_lastname();
    }

    public void update(Notification notification) {
        System.out.println("Doamna/Domnule " + this.toString() + " va anuntam ca " + notification.getS() +" a primit nota "+ notification.toString() + " la cursul " + notification.getCoursename());
    }

}
