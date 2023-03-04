import java.util.HashMap;
import java.util.Map;

public class  BestExamScore implements Strategy{
    public Student best( HashMap<Student, Grade> gradeHashMap){
        Student s = null;
        Grade g = new Grade(00.00, 00.00, null, null);
        for (Map.Entry<Student, Grade> set : gradeHashMap.entrySet()) {
            if(set.getValue().getExamScore() > g.getExamScore()){
                g = set.getValue();
                s = set.getKey();
            }
        }
        return s;
    }
}