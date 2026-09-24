public class Main {
    public static void main(String[] args) {
        Driver driver1 = new Driver("Alice", 30, 5);
        Driver driver2 = new Driver("Bob", 40, 3);

        Car car1 = new Car("2016 Toyota Prius", "ABC123", "InsureCo");
        Car car2 = new Car("2020 Honda Civic", "XYZ789", "SafeGuard");

        car1.recordAccident("2024-01-15", car2, 2000, driver1);

        System.out.println(car1);
        System.out.println(car2);
        System.out.println(driver1);
        System.out.println(driver2);

    }
}
