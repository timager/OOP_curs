public class Main {
    private static void draw(Building building){
        System.out.print("RS car: "+building.getRepairShop().getCar()+" ");
        System.out.print("CW car: "+building.getCarWash().getCar()+" ");
        System.out.println("VI car: "+building.getVehicleInspection().getCar());

        System.out.println("master work in "+building.getMaster().getFabric().getClass()+" "+ building.getMaster().getTimeLeft()+" c.\n");
    }

    public static void main(String[] args) throws InterruptedException {
        Building building = new Building();
        while (true) {
            draw(building);

            building.inputCar();
            building.getMaster().work();
            Thread.sleep(1000);
        }
    }
}
