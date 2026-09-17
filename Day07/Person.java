public class Person {
    private String name;
    private Date birthday;

    public Person(String name, Date birthday)
    {
        this.name = name;
        this.birthday = birthday;
    }

    public String toString()
    {
        return name+" born on "+birthday;
    }
}
