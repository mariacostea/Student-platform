import java.util.ArrayList;
import java.util.List;

public class Catalog implements Subject{
    private static Catalog catalog;

    public static Catalog getInstance(){
        if(catalog == null)
            catalog = new Catalog();
        return catalog;
    }
    List<Course> courseList = new ArrayList<Course>();
    public void addCourse(Course course) {
        courseList.add(course);
    }
    public void removeCourse(Course course) {
        courseList.remove(course);
    }

    public Course returncourse(String name){
        int i;
        for(i=0; i<courseList.size(); i++){
            Course course = courseList.get(i);
            if(course.getName().equals(name)){
                return course;
            }
        }
        return null;
    }

    private List<Observer> observers = new ArrayList<>();

    public void addObserver(Observer o) {
        observers.add(o);
    }

    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    public void notifyObservers(Grade grade) {
        Student s = grade.getStudent();
        Notification n = new Notification(grade);
        for(Observer o: observers) {
            if(s.s_father!= null)
                if(s.s_father.equals(o))
                    o.update(n);
            if(s.s_mother!= null)
                if(s.s_mother.equals(o))
                    o.update(n);
        }
    }

    public List<Observer> getObservers() {
        return observers;
    }
}
