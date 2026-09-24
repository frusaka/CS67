public class Accident {
    private String date;
    private Car carA;
    private Car carB;
    private Driver driverAtFault;
    private int totalDamages;

    public Accident(String date, Car carA, Car carB, int totalDamages,  Driver driverAtFault){
        this.date = date;
        this.carA = carA;
        this.carB = carB;
        this.driverAtFault = driverAtFault;
        this.totalDamages = totalDamages;
        int sharedDamages = totalDamages/2;
        driverAtFault.setInsuranceRate(driverAtFault.getInsuranceRate() + (int)(sharedDamages*0.1));
        // System.out.printf("Accident recorded on %s between %s and %s with damages worth %d with %s at fault\n", date, carA, carB, totalDamages, driverAtFault);
    }

    @Override 
    public String toString(){
        return String.format(
            "Accident recorded on %s between %s and %s with damages worth %d with %s at fault\n",
            date,
            carA.getName(),
            carB.getName(),
            totalDamages,
            driverAtFault);
    }
}
