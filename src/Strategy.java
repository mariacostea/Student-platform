import java.util.HashMap;

public interface Strategy {
    public Student best( HashMap<Student, Grade> gradeHashMap);
}
