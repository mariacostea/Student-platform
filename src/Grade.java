public class Grade implements Cloneable, Comparable<Grade>{
    private Double partialScore, examScore;
    private Student student;
    private String course; // Numele cursului

    public Grade(Double partialScore, Double examScore, Student student, String course){
        this.partialScore = partialScore;
        this.examScore = examScore;
        this.student = student;
        this.course = course;
    }

    public void setPartialScore(Double partialScore) {
        this.partialScore = partialScore;
    }

    public void setExamScore(Double examScore) {
        this.examScore = examScore;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Double getPartialScore() {
        return this.partialScore;
    }

    public Double getExamScore() {
        return this.examScore;
    }

    public String getCourse() {
        return this.course;
    }

    public Student getStudent() {
        return this.student;
    }

    public Double getTotal(){
        return this.partialScore + this.examScore;
    }

    @Override
    public String toString() {
        return "Studentul " + this.student + " partial=" + this.partialScore +  " examen=" + this.examScore + " " + this.course;
    }

    @Override
    public int compareTo(Grade o) {
        return 0;
    }

}
