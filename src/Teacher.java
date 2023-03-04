public class Teacher extends User implements Element{
    public Teacher(String firstname, String lastname){
        super(firstname, lastname);
    }

    public String toString() {
        return this.get_firstname() + " " + this.get_lastname();
    }

    public void accept(Visitor visitor){
        visitor.visit(this);
    }
}
