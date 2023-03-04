import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ScoreVisitor implements Visitor{
    HashMap<Teacher, ArrayList<Tuple>> examScores = new HashMap<>();
    HashMap<Assistant, ArrayList<Tuple>> partialScores = new HashMap<>();

    List<Tuple> tuples = new ArrayList<>();
    Catalog catalog = Catalog.getInstance();

    public void addTuple(Student student, String coursename, Double grade){
        Tuple tuple = new Tuple<>(student, coursename, grade);
        tuples.add(tuple);

    }

    public List<Tuple> getTuples() {
        return tuples;
    }

    public ScoreVisitor(HashMap<Teacher, ArrayList<Tuple>> examScores, HashMap<Assistant, ArrayList<Tuple>> partialScores, Catalog catalog ){
        this.examScores = examScores;
        this.partialScores = partialScores;
        this.catalog = catalog;
    }

    public ScoreVisitor(Catalog catalog){
        this.catalog = catalog;
    }

    public void addexam(ArrayList<Tuple> tuples1, Teacher teacher) {
            examScores.put(teacher, tuples1);
        }

    public void addpartial(ArrayList<Tuple> tuples1, Assistant assistant) {
        partialScores.put(assistant, tuples1);
    }

    public void visit(Teacher teacher){
        ArrayList<Tuple> tuples1 = examScores.get(teacher);
        int i;
        for(i=0; i<tuples1.size(); i++){
            Grade grade = new Grade(00.00 , (Double)tuples1.get(i).getGrade(), (Student) tuples1.get(i).getStudent(), (String) tuples1.get(i).getCoursename());
            catalog.notifyObservers(grade);
        }
    }

    public void visit(Assistant assistant) {
        ArrayList<Tuple> tuples1 = partialScores.get(assistant);
        int i;
        for (i = 0; i < tuples1.size(); i++) {
            Grade grade = new Grade((Double) tuples1.get(i).getGrade(), 00.00, (Student) tuples1.get(i).getStudent(), (String) tuples1.get(i).getCoursename());
            catalog.notifyObservers(grade);
        }
    }
    private class Tuple<Student, String, Double>{
        Student student;
        String coursename;
        Double grade;
        private Tuple(Student student, String coursename, Double grade){
            this.student = student;
            this.coursename = coursename;
            this.grade = grade;
        }

        public void setCoursename(String coursename) {
            this.coursename = coursename;
        }

        public void setStudent(Student student) {
            this.student = student;
        }

        public void setGrade(Double grade) {
            this.grade = grade;
        }

        public Student getStudent() {
            return student;
        }

        public Double getGrade() {
            return grade;
        }

        public String getCoursename() {
            return coursename;
        }
    }
}
