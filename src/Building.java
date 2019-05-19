import java.util.Random;

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

    private long timeToNextCar;
    private double P = 0.5;

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

    private void setTimeToNextCar(){
        timeToNextCar = 3;
    }


    void inputCar(){
        if(timeToNextCar>0){
            timeToNextCar--;
        }
        else{
            setTimeToNextCar();
            Car car = new Car();
            if(master.isWork()){
                if(Math.random()>this.P){
                    repairShop.setCar(car);
                }
                else{
                    System.out.println(car+" left\n");
                }
            }
            else{
                repairShop.setCar(car);
            }
        }
    }
}
