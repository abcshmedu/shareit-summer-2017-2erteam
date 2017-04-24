package datenzugriffsschicht;

public class Copy {
    private Medium medium;
    private String owner;
    Copy(String owner, Medium toCopy){
        medium = toCopy;
        this.owner=owner;
    }
    public Medium getMedium(){
        return medium;
    }
    public String getUserName(){
        return owner;
    }
}
