import java.util.*;

class FullCourse extends Course {

    public FullCourse(String name, Teacher teacher, Collection<Assistant> assistants, List<Grade> grades, HashMap<String, Group> groups, int credits){
        super(name, teacher, assistants, grades, groups,credits);
    }

    public FullCourse(CourseBuilder builder) {
        super(builder);}
    public ArrayList<Student> getGraduatedStudents() {
        ArrayList<Student> promovatedstudents = new ArrayList<>();
        HashMap<Student, Grade> gradeHashMap = getAllStudentGrades();
        for (Map.Entry<Student, Grade> set : gradeHashMap.entrySet()) {
            if (set.getValue().getPartialScore() >= 3 && set.getValue().getExamScore() >= 2) {
                Student s = set.getKey();
                promovatedstudents.add(s);
            }
        }
        return promovatedstudents;
    }


    public static class FullCourseBuilder extends Course.CourseBuilder{
        public FullCourseBuilder(String name){
            super(name);
        }
        public Course build(){
            return new FullCourse(this);
        }
    }
}


