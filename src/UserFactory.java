public class UserFactory {
   public User getUser(String type, String fname, String lname) {
      switch (type) {
         case "Teacher":
            return new Teacher(fname, lname);
         case "Parent":
            return new Parent(fname, lname);
         case "Student":
            return new Student(fname, lname);
         case "Assistant":
            return new Assistant(fname, lname);
         default:
            return null;
      }
   }
}
