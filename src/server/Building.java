package server;

import java.io.Serializable;

public class Building implements Serializable {
    Master getMaster() {
        return master;
    }

    RepairShop getRepairShop() {
        return repairShop;
    }

    CarWash getCarWash() {
        return carWash;
    }

    VehicleInspection getVehicleInspection() {
        return vehicleInspection;
    }

    private Master master;
    private RepairShop repairShop;
    private CarWash carWash;
    private VehicleInspection vehicleInspection;

    private long timeToNextCar;
    private double P;
    private long T;

    public void setWorked(boolean worked) {
        isWorked = worked;
    }

    private boolean isWorked = false;

    Building(long R1, long R2, double P, long T) {
        this.P = P;
        this.T = T;
        repairShop = new RepairShop();
        carWash = new CarWash(R1);
        vehicleInspection = new VehicleInspection(R2);

        repairShop.setCarWash(carWash);
        carWash.setVehicleInspection(vehicleInspection);
        vehicleInspection.setRepairShop(repairShop);

        master = new Master();
        master.setFabric(repairShop);
    }

    private void setTimeToNextCar() {
        timeToNextCar = StatisticUtil.exponentialMedium(this.T);
    }


    void inputCar() {
        repairShop.nextStage();
        if (timeToNextCar > 0) {
            timeToNextCar--;
        } else {
            setTimeToNextCar();
            Car car = new Car();
            if (master.isWork()) {
                if (Math.random() <= this.P) {
                    if (!repairShop.setCar(car)) {
                        System.out.println(car + " left (repairshop is busy)\n");
                    }
                } else {
                    System.out.println(car + " left (master is busy)\n");
                }
            } else {
                if (!repairShop.setCar(car)) {
                    System.out.println(car + " left (repairshop is busy)\n");
                }
            }
        }
    }

    public boolean isWorked() {
        return isWorked;
    }

    @Override
    public String toString() {
        return "RS car: " + this.getRepairShop().getCar() + " " + "CW car: " +
                this.getCarWash().getCar() + " VI car: " +
                this.getVehicleInspection().getCar() + "\nmaster work in " +
                this.getMaster().getFabric().getClass() + " " +
                this.getMaster().getTimeLeft()/(1000/Server.SLEEP) + " c.\n";
    }
}
