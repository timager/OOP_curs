package server;

import java.io.Serializable;
import java.util.ArrayList;

public class Building implements Serializable {
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

    void setP(double p) {
        P = p;
    }

    void setT(long t) {
        T = t;
    }

    public double P;
    public long T;

    void setWorked(boolean worked) {
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

    private double mediumT,skoT;

    private ArrayList<Long> carIntervalHistory = new ArrayList<>();


    public void clearIntervalHistory(){
        mediumT = 0;
        skoT = 0;
        carIntervalHistory.clear();
    }
    private void setTimeToNextCar() {
        timeToNextCar = StatisticUtil.exponentialMedium(this.T);
        carIntervalHistory.add(timeToNextCar);
        mediumT = StatisticUtil.medium(carIntervalHistory);
        skoT = StatisticUtil.sko(carIntervalHistory);
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

    public double getMediumT() {
        return mediumT;
    }

    public double getSkoT() {
        return skoT;
    }
}
