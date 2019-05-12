public class Building {
    public Master getMaster() {
        return master;
    }

    public RepairShop getRepairShop() {
        return repairShop;
    }

    public CarWash getCarWash() {
        return carWash;
    }

    public VehicleInspection getVehicleInspection() {
        return vehicleInspection;
    }

    private Master master;
    private RepairShop repairShop;
    private CarWash carWash;
    private VehicleInspection vehicleInspection;

    Building(){
        repairShop = new RepairShop();
        carWash = new CarWash();
        vehicleInspection = new VehicleInspection();

        repairShop.setCarWash(carWash);
        carWash.setVehicleInspection(vehicleInspection);
        vehicleInspection.setRepairShop(repairShop);

        master = new Master();
        master.setFabric(repairShop);
    }
}
