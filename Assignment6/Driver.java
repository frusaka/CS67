
public class Driver extends Person{
    private int licenseID;
    private int insuranceRate;

    public Driver(String name, int age, int insuranceRate){
        super(name, age);
        this.licenseID = 10000+(int)(Math.random()*90000);
        this.insuranceRate = insuranceRate;
    }

    public int getInsuranceRate(){
        return insuranceRate;
    }

    public void setInsuranceRate(int insuranceRate){
        this.insuranceRate = insuranceRate;
    }

    public String toString()
    {
        return String.format("Driver(name=%s, age=%d, licenseID=%d)", name, age, licenseID);
    }
}
