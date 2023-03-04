import java.util.*;

abstract class comp implements Comparator<Student>{
    public int compare(Student s1, Student s2){
        if(s1.get_lastname().compareTo(s2.get_lastname()) > 0 ){
            return 1;
        }
        else
            return -1;
    }
}
public class Group extends ArrayList<Student> {
    private ArrayList<Student> students = new ArrayList<>();
    private String ID, firstname, lastname;
    Assistant assistant = new Assistant(firstname, lastname);
    public Group(String ID, Assistant assistant, Comparator<Student> comp) {
        this.ID = ID;
        this.assistant = assistant;
        Collections.sort(students, comp);
    }
    public Group(String ID, Assistant assistant) {
        this.ID = ID;
        this.assistant = assistant;
    }

    public String getID(Group group) {
        return group.ID;
    }

    public Assistant getAssistant(Group group) {
        return group.assistant;
    }

    public void setAssistant(Assistant assistant) {
        this.assistant = assistant;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void addStudent(Group group, Student student){
        group.students.add(student);
    }

    @Override
    public String toString() {
        return "Group{" +
                "students=" + students +
                ", ID='" + ID + '\'' +
                ", assistant=" + assistant +
                '}';
    }

    public  ArrayList<Student> getStudents(Group group) {
        return group.students;
    }
}
