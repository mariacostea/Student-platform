import java.util.ArrayList;
import java.util.List;

public class Student extends User{
    public Student(String firstname, String lastname){
        super(firstname, lastname);
    }

    public String toString() {
        return this.get_firstname() + " " + this.get_lastname();
    }
    Parent s_mother;
    Parent s_father;
    public void setMother(Parent mother) {
        this.s_mother = new Parent(mother.get_firstname(), mother.get_lastname());
    }
    public void setFather(Parent father) {
        this.s_father = new Parent(father.get_firstname(), father.get_lastname());
    }
    public Parent getMother(Student student) {
        return s_mother;
    }
    public Parent getFather(Student student) {
        return s_father;
    }
}
