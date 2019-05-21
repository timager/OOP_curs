

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
    private double P = 0.5;

    Building() {
        repairShop = new RepairShop();
        carWash = new CarWash();
        vehicleInspection = new VehicleInspection();

        repairShop.setCarWash(carWash);
        carWash.setVehicleInspection(vehicleInspection);
        vehicleInspection.setRepairShop(repairShop);

        master = new Master();
        master.setFabric(repairShop);
    }

    private void setTimeToNextCar() {
        timeToNextCar = 2;
    }


    void inputCar() {
        repairShop.nextStage();
        if (timeToNextCar > 0) {
            timeToNextCar--;
        } else {
            setTimeToNextCar();
            Car car = new Car();
            if (master.isWork()) {
                if (Math.random() > this.P) {
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
