package server;

class Building {
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

    Building(long R1,long R2,double P,long T) {
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
                if(!repairShop.setCar(car)){
                    System.out.println(car+" left (repairshop is busy)\n");
                }
            }
        }
    }
}
