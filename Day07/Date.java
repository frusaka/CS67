public class Date {
    private int day;
    private int month;
    private int year;

    public Date(int month, int day, int year){
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public String toString()
    {
        return String.format("%02d/%02d/%d", this.month, this.day, this.year);
    }
}
