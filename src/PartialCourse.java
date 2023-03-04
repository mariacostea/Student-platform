import java.util.*;

class PartialCourse extends Course {

    public PartialCourse(String name, Teacher teacher, Collection<Assistant> assistants, List<Grade> grades, HashMap<String, Group> groups, int credits){
        super(name, teacher, assistants, grades, groups,credits);
    }
    public PartialCourse(CourseBuilder builder) {
        super(builder);}
        public ArrayList<Student> getGraduatedStudents() {
            ArrayList<Student> promovatedstudents = new ArrayList<>();
            HashMap<Student, Grade> gradeHashMap = getAllStudentGrades();
            for (Map.Entry<Student, Grade> set : gradeHashMap.entrySet()) {
                if (set.getValue().getTotal() >= 5) {
                    Student s = set.getKey();
                    promovatedstudents.add(s);
                }
            }
            return promovatedstudents;
        }


    public static class PartialCourseBuilder extends Course.CourseBuilder{

        public PartialCourseBuilder(String name){
            super(name);
        }
        public Course build(){
            return new PartialCourse(this);
            }
        }
    }


