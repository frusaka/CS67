import java.util.ArrayList;

public class Car {
    private String name;
    private String plateNumber;
    private String insurance;
    protected ArrayList<Accident> accidents;

    public Car(String name, String plateNumber, String insurance){
        this.name = name;
        this.plateNumber = plateNumber;
        this.insurance = insurance;
        this.accidents = new ArrayList<>();
    }

    public String getName(){
        return name;
    }

    public void recordAccident(String date, Car otherCar, int totalDamages, Driver driverAtFault)
    {
        Accident accident = new Accident(date, this, otherCar, totalDamages, driverAtFault);
        accidents.add(accident);
        otherCar.accidents.add(accident);
    }


    public String toString(){
        String out = String.format("%s with plate Number %s insured by %s", name, plateNumber, insurance);
        if (accidents.isEmpty()){
            return out;
        }
        out+="\nAccidents:\n";
        for (Accident accident: accidents){
            // System.out.println(accident);
            out+=accident+"\n";
        }
        return out;
    }
}
