public class Assistant extends User implements Element{
    public Assistant(String firstname, String lastname){
        super(firstname, lastname);
    }

    public String toString() {
        return this.get_firstname() + " " + this.get_lastname();
    }

    public void accept(Visitor visitor){
        visitor.visit(this);
    }
}
