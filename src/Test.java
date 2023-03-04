import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

class Test extends JFrame{
    static Catalog catalog = new Catalog();
    public static void main(String[] args) throws Exception
    {
        File file = new File(
                "C:\\Users\\Maria\\Desktop\\Tema1\\src\\test.txt");
        Scanner sc = new Scanner(file);
        List<String> strategies = new ArrayList<>();
        String read = sc.next();
        String type = "Course";
        String name = null;
        String ftname = null;
        String ltname = null;
        Teacher teacher = null;
        FullCourse fullcourse = null;
        PartialCourse partialcourse;
        String strategy = null;
        if (read.equals("courses")) {
            read = sc.next();
        }
        while(!read.equals("examScores")) {
            Collection<Assistant> assistants = new HashSet<>();
            HashMap<String,Group> groups = new HashMap<>();
            if (read.equals("name")) {
                name = sc.next();
                read = sc.next();
            }
            if (read.equals("type")) {
                type = sc.next();
                read = sc.next();
            }
            if (read.equals("strategy")) {
                strategy = sc.next();
                read = sc.next();
                strategies.add(strategy);
            }
            if (read.equals("teacher")) {
                ftname = sc.next();
                ltname = sc.next();
                teacher = new Teacher(ftname, ltname);
                read = sc.next();
            }
            if (read.equals("assistants")) {
                read = sc.next();
                while (!read.equals("groups")) {
                    ftname = read;
                    ltname = sc.next();
                    Assistant assistant = new Assistant(ftname, ltname);
                    assistants.add(assistant);
                    read = sc.next();
                }
            }
            if (read.equals("groups")) {
                String id = null;
                Assistant assistant = null;
                read = sc.next();
                while (!read.equals("name") && !read.equals("examScores")) {
                    Group group = null;
                    if (read.equals("ID")) {
                        id = sc.next();
                        read = sc.next();
                    }
                    if (read.equals("assistant")) {
                        ftname = sc.next();
                        ltname = sc.next();
                        assistant = new Assistant(ftname, ltname);
                        read = sc.next();
                    }
                    group = new Group(id, assistant);
                    if (read.equals("students")) {
                        read = sc.next();
                        while (!read.equals("ID") && !read.equals("name") && !read.equals("examScores")) {
                            ftname = read;
                            ltname = sc.next();
                            Student student = new Student(ftname, ltname);
                            group.addStudent(group, student);
                            read = sc.next();
                            if (read.equals("mother")) {
                                ftname = sc.next();
                                ltname = sc.next();
                                Parent mother = new Parent(ftname, ltname);
                                student.setMother(mother);
                                read = sc.next();
                            }
                            if (read.equals("father")) {
                                ftname = sc.next();
                                ltname = sc.next();
                                Parent father = new Parent(ftname, ltname);
                                student.setFather(father);
                                read = sc.next();
                            }
                        }

                    System.out.println(group.getID(group));
                    System.out.println(group.getStudents(group));
                    groups.put(id, group);
                    }
                }

            }
            if (type.equals("FullCourse")) {
                fullcourse = new FullCourse(name, teacher, assistants, null, groups, 0);
                catalog.addCourse(fullcourse);
                System.out.println(fullcourse.getName());
                System.out.println(fullcourse.getTeacher());
                System.out.println(fullcourse.getAssistants());
                Group ngroup = fullcourse.getgroup(groups, "312CC");
                if(ngroup!=null)
                    System.out.println(ngroup.toString());
                //fullcourse.getBestStudent(strategy);
            }
            if (type.equals("PartialCourse")) {
                partialcourse = new PartialCourse(name, teacher, assistants, null, groups, 0);
                catalog.addCourse(partialcourse);
                System.out.println(partialcourse.getName());
                System.out.println(partialcourse.getTeacher());
                System.out.println(partialcourse.getAssistants());
                //partialcourse.getBestStudent(strategy);
            }
        }
        String coursename = null;
        String fname = null;
        String lname = null;
        String tfname = null;
        String tlname = null;
        String sfname = null;
        String slname = null;
        Teacher teacher1 = new Teacher(null, null);
        Assistant assistant1 = new Assistant(null, null);
        double gr;
        Grade grade = null;
        Student student = null;
        read = sc.next();

        while(!read.equals("partialScores")) {
            ScoreVisitor scoreVisitor = new ScoreVisitor(catalog);
            if (read.equals("course")) {
                coursename = sc.next();
                read = sc.next();
            }
            if (read.equals("teacher")) {
                tfname = sc.next();
                ltname = sc.next();
                read = sc.next();
                teacher1 = new Teacher(tfname, tlname);
            }
            List<Grade> grades = new ArrayList<>();
            while (!read.equals("course") && !read.equals("partialScores")) {

                if (read.equals("student")) {
                    sfname = sc.next();
                    slname = sc.next();
                    read = sc.next();
                    student = new Student(sfname, slname);
                    Course course = catalog.returncourse(coursename);
                    ArrayList<Student> allstudents = course.getAllStudents();
                    int i;
                    for(i=0; i<allstudents.size(); i++){
                        if(allstudents.get(i).get_firstname().equals(student.get_firstname()) && allstudents.get(i).get_lastname().equals(student.get_lastname()) ){
                            student = allstudents.get(i);
                        }
                    }
                }
                if (read.equals("grade")) {
                    read = sc.next();
                    gr = Double.parseDouble(read);
                    read = sc.next();
                    grade = new Grade((double) 0, gr, student, coursename);
                    grades.add(grade);
                    scoreVisitor.addTuple(student, coursename, gr);
                    if (student.s_father != null){
                        catalog.addObserver(student.s_father);
                        catalog.notifyObservers(grade);
                        catalog.removeObserver(student.s_father);

                    }
                    if (student.s_mother != null){
                        catalog.addObserver(student.s_mother);
                        catalog.notifyObservers(grade);
                        catalog.removeObserver(student.s_mother);
                    }
                }
            }
            scoreVisitor.addexam((ArrayList)scoreVisitor.getTuples(), teacher1);
            scoreVisitor.visit(teacher1);
            Course course = catalog.returncourse(coursename);
            course.setGrades(grades);
        }
        read = sc.next();

        while(!read.equals("end")) {
            if (read.equals("course")) {
                coursename = sc.next();
                read = sc.next();

            }
            ScoreVisitor scoreVisitor = new ScoreVisitor(catalog);
            List<Grade> grades = new ArrayList<>();
            while (!read.equals("course") && !read.equals("end")) {


                if (read.equals("assistant")) {
                    tfname = sc.next();
                    ltname = sc.next();
                    read = sc.next();
                    assistant1 = new Assistant(tfname, ltname);
                }

                if (read.equals("student")) {
                    sfname = sc.next();
                    slname = sc.next();
                    read = sc.next();
                    student = new Student(sfname, slname);
                    Course course = catalog.returncourse(coursename);
                    ArrayList<Student> allstudents = course.getAllStudents();
                    int i;
                    for(i=0; i<allstudents.size(); i++){
                        if(allstudents.get(i).get_firstname().equals(student.get_firstname()) && allstudents.get(i).get_lastname().equals(student.get_lastname()) ){
                            student = allstudents.get(i);
                        }
                    }
                }
                if (read.equals("grade")) {
                    read = sc.next();
                    gr = Double.parseDouble(read);
                    read = sc.next();
                    Course course = catalog.returncourse(coursename);
                    Grade partialgrade = course.getGrade(student);
                    scoreVisitor.addTuple(student, coursename, gr);
                    if(partialgrade!=null){
                        partialgrade.setPartialScore(gr);
                    }
                    if (student.s_father != null){
                        catalog.addObserver(student.s_father);
                        catalog.notifyObservers(partialgrade);
                        catalog.removeObserver(student.s_father);
                    }
                    if (student.s_mother != null){
                        catalog.addObserver(student.s_mother);
                        catalog.notifyObservers(partialgrade);
                        catalog.removeObserver(student.s_mother);
                    }
                }
            }
            scoreVisitor.addpartial((ArrayList)scoreVisitor.getTuples(), assistant1);
            scoreVisitor.visit(assistant1);
            Course course = catalog.returncourse(coursename);
            System.out.println(course.getGrades());
        }

        ftname = sc.next();
        ltname = sc.next();
        coursename = sc.next();
        Course course = catalog.returncourse(coursename);
        String id = sc.next();
        Assistant assistant = new Assistant(ftname, ltname);
        course.addAssistant(id, assistant);
        course.addGroup(id, assistant);
        Group group = course.getgroup(course.getGroups(), id);
        System.out.println(course.getAssistants());
        System.out.println(group.toString());
        System.out.println(course.getAllStudents());
        System.out.println(course.getAllStudentGrades());
        System.out.println(course.getGraduatedStudents());
        int i;
        for(i=0; i<catalog.courseList.size(); i++)
        {
            Course c = catalog.courseList.get(i);
            String s = strategies.get(i);
            Student stud =  null;
            if(s.equals("BestExamScore")){
                stud = c.getBestStudent(new BestExamScore());
            }
            if(s.equals("BestPartialScore")){
                stud = c.getBestStudent(new BestPartialScore());
            }
            if(s.equals("BestTotalScore")){
                stud = c.getBestStudent(new BestTotalScore());
            }
            System.out.println(c.getGrades());
            System.out.println(stud);
        }

        JFrame f = new JFrame();

        JButton b = new JButton("courses");
        b.setBounds(300, 360, 100, 40);

        JTextField nameS = new JTextField(60);
        nameS.setBounds(300, 200, 100, 40);

        f.add(b);
        f.add(nameS);
        f.setSize(800, 800);
        f.setLayout(null);
        f.setVisible(true);


        b.addActionListener(new ActionListener() {


            public void actionPerformed(ActionEvent e) {
                Student copy = null;
                List<Course> courseList = new ArrayList<>();
                List<JButton> cursuri = new ArrayList<>();
                String text = ((JTextField) nameS).getText();
                int i;

                for(i=0; i<catalog.courseList.size(); i++){
                    ArrayList<Student> students = catalog.courseList.get(i).getAllStudents();
                    int j;
                    for(j=0; j<students.size(); j++){
                        if(students.get(j).toString().equals(text)){
                            copy = students.get(j);
                            f.getContentPane().setBackground(Color.blue);
                            JButton course2 = new JButton(catalog.courseList.get(i).getName());
                            courseList.add(catalog.courseList.get(i));
                            cursuri.add(course2);
                        }
                    }
                }
                int j;
                for(j=0; j<cursuri.size(); j++)
                {
                    JButton course2 = cursuri.get(j);
                    course2.setBounds(j*600, 600, 200, 70);
                    f.add(course2);
                    f.setVisible(true);
                    Course finalCopyc = courseList.get(j);
                    Student finalCopy = copy;
                    course2.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            JFrame fcourse = new JFrame();
                            fcourse.setSize(800, 800);
                            fcourse.setLayout(null);
                            String teacher = finalCopyc.getTeacher().toString();
                            Collection<Assistant> assistantList = finalCopyc.getAssistants();
                            Grade grade1 = finalCopyc.getGrade(finalCopy);
                            HashMap<String, Group> groupArrayList = finalCopyc.getGroups();
                            Assistant assistant2 = null;
                            for(Map.Entry<String, Group> set: groupArrayList.entrySet()){
                                ArrayList<Student> students = set.getValue().getStudents(set.getValue());
                                if(students.contains(finalCopy)){
                                    assistant2 = set.getValue().getAssistant(set.getValue());
                                }
                            }
                            JFormattedTextField text1 = new JFormattedTextField(" Teacher " + teacher );
                            JFormattedTextField text2 = new JFormattedTextField( "Assistants " + assistantList);
                            JFormattedTextField text3 = new JFormattedTextField( "Assistant " + assistant2);
                            JFormattedTextField text4 = new JFormattedTextField("Partial = " + grade1.getPartialScore());
                            JFormattedTextField text5 = new JFormattedTextField("Examen = " + grade1.getExamScore());
                            text1.setSize(500, 100);
                            text2.setBounds(00, 100, 500, 100);
                            text3.setBounds(00, 200, 500, 100);
                            text4.setBounds(00, 300, 500, 100);
                            text5.setBounds(00, 400, 500, 100);
                            fcourse.add(text1);
                            fcourse.add(text2);
                            fcourse.add(text3);
                            fcourse.add(text4);
                            fcourse.add(text5);
                            f.setVisible(false);
                            fcourse.setVisible(true);
                            if(fcourse.isVisible() == false){
                                f.setVisible(true);
                            }
                        }
                    });
                }
            }
        });
    }

}