package server;


class CarWash implements Fabric {

    private Car car;
    private long R1;

    CarWash(long R1) {
        this.R1 = R1;
    }

    void setVehicleInspection(VehicleInspection vehicleInspection) {
        this.vehicleInspection = vehicleInspection;
    }

    private VehicleInspection vehicleInspection;

    public Fabric getNextFabric() {
        return vehicleInspection;
    }

    @Override
    public long getWorkTime() {
        return StatisticUtil.exponentialMedium(this.R1);
    }

    public boolean setCar(Car car) {
        if (this.car == null) {
            this.car = car;
            return true;
        } else return false;
    }

    public Car getCar() {
        return car;
    }


    public boolean nextStage() {
        if (vehicleInspection.getCar() == null) {
            if (vehicleInspection.setCar(car)) {
                car = null;
                return true;
            }
        }
        return false;
    }
}
