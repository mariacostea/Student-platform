public class Notification {
    final Grade grade;
    Student s;

    String coursename;
    public Notification(Grade grade){
        this.s = grade.getStudent();
        this.coursename = grade.getCourse();
        this.grade = grade;

    }

    public void setCoursename(String coursename) {
        this.coursename = coursename;
    }

    public void setS(Student s) {
        this.s = s;
    }
    public String getCoursename() {
        return coursename;
    }
    public Student getS() {
        return s;
    }

    public String toString() {
        if(grade.getExamScore() == 00.00){
            return grade.getPartialScore().toString();
        }
        else
        {
            return grade.getExamScore().toString();
        }
    }
}


