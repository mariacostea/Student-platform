import java.util.*;
public abstract class Course{
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    String fname, lname;
    private Teacher teacher = new Teacher(fname, lname);

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    private Collection<Assistant> assistants = new HashSet<>();

    public void setAssistants(List<Assistant> assistants) {
        this.assistants = assistants;
    }

    public Collection<Assistant> getAssistants() {
        return assistants;
    }

    private List<Grade> grades = new ArrayList<>();

    public void setGrades(List<Grade> grades) {
        this.grades = grades;
    }

    public List<Grade> getGrades() {
        return grades;
    }
    private HashMap<String,Group> groups = new HashMap<>();

    public void setGroups(HashMap<String, Group> groups) {
        this.groups = groups;
    }

    public HashMap<String, Group> getGroups() {
        return groups;
    }
    public Group getgroup(HashMap<String, Group> groups, String ID){
        return groups.get(ID);
    }
    private int credits;

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public int getCredits() {
        return credits;
    }

    public Course(String name, Teacher teacher,Collection<Assistant> assistants, List<Grade> grades, HashMap<String, Group> groups, int credits){
        this.name = name;
        this.teacher = teacher;
        this.assistants = assistants;
        this.groups = groups;
        this.grades = grades;
        this.credits = credits;
    }
    public Course(CourseBuilder builder) {
        this.name = builder.name;
        this.teacher = builder.teacher;
        this.assistants = builder.assistants;
        this.groups = builder.groups;
        this.grades = builder.grades;
        this.credits = builder.credits;
    }

    // Seteaza asistentul în grupa cu ID-ul indicat ˘
    // Daca nu exist ˘ a deja, ad ˘ auga asistentul s ˘ , i în mult, imea asistent, ilor
    public void addAssistant(String ID, Assistant assistant){
        if(assistants.contains(assistant) == false ){
            assistants.add(assistant);
        }
        Group group = new Group(ID, assistant);
        group.setAssistant(assistant);
    }
    // Adauga studentul în grupa cu ID-ul indicat ˘
    public void addStudent(String ID, Student student){
        Group g = getgroup(groups, ID);
        g.addStudent(g,student);
    }
    // Adauga grupa ˘
    public void addGroup(Group group){
        groups.put(group.getID(group), group);
    }
    // Instant, iaza o grup ˘ a s ˘ , i o adauga˘
    public void addGroup(String ID, Assistant assistant){
        Group group = new Group(ID, assistant);
        group.setAssistant(assistant);
        group.setID(ID);
        groups.put(ID, group);
    }
    // Instant, iaza o grup ˘ a s ˘ , i o adauga˘
    public void addGroup(String ID, Assistant assist, Comparator<Student> comp){
            Group group = new Group(ID, assist);
            group.setAssistant(assist);
            group.setID(ID);
            groups.put(ID, group);
    }
    // Returneaza nota unui student sau null ˘
    public Grade getGrade(Student student){
        for(int i=0; i<grades.size(); i++){
            Student stud;
            stud = grades.get(i).getStudent();
            if(stud.get_firstname().equals(student.get_firstname()) && stud.get_lastname().equals(student.get_lastname())){
                return grades.get(i);
            }
        }
        return null;
    }
    // Adauga o not ˘ a˘
    public void addGrade(Grade grade){
        grades.add(grade);
    }
    // Returneaza o list ˘ a cu tot ˘ , i student, ii
    public ArrayList<Student> getAllStudents(){
        ArrayList<Student> allstudents = new ArrayList<>();
        for(Map.Entry<String, Group> set: groups.entrySet()){
            Group g = set.getValue();
            allstudents.addAll(g.getStudents(g));
        }
        return allstudents;
    }


    // Returneaza un dict ˘ , ionar cu situat, ia student, ilor
    public HashMap<Student, Grade> getAllStudentGrades(){
        HashMap<Student, Grade> gradeHashMap = new HashMap<Student, Grade>();
        ArrayList<Student> liststudents = getAllStudents();
        int i;
        for(i=0; i<liststudents.size(); i++){
            Grade grade = getGrade(liststudents.get(i));
            gradeHashMap.put(liststudents.get(i),grade);
        }
        return gradeHashMap;
    }
    // Metoda ce o s ˘ a fie implementat ˘ a pentru a determina student ˘ , ii promovat, i
    public abstract ArrayList<Student> getGraduatedStudents();

    public Student getBestStudent(Strategy strategy){
        return strategy.best(getAllStudentGrades());
    }
    public abstract static class CourseBuilder{
        private String name;
        String fname, lname;
        private Teacher teacher = new Teacher(fname, lname);
        private Collection<Assistant> assistants;
        private List<Grade> grades;
        private HashMap<String,Group> groups;
        private int credits;
        public CourseBuilder(String name) {
            this.name = name;
        }
        public CourseBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public CourseBuilder setTeacher(Teacher teacher) {
            this.teacher = teacher;
            return this;
        }

        public CourseBuilder setAssistants(Collection<Assistant> assistants) {
            this.assistants = assistants;
            return this;
        }

        public CourseBuilder setGroup(HashMap<String,Group> groups) {
            this.groups = groups;
            return this;
        }

        public CourseBuilder setGrades(List<Grade> grades) {
            this.grades = grades;
            return this;
        }

        public CourseBuilder setCredits(List<Grade> grades) {
            this.credits = credits;
            return this;
        }
        public abstract Course build();

    }
}
