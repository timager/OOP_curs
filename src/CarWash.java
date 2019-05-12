class CarWash implements Fabric {

    private Car car;

    public void setVehicleInspection(VehicleInspection vehicleInspection) {
        this.vehicleInspection = vehicleInspection;
    }

    private VehicleInspection vehicleInspection;

    public Fabric getNextFabric() {
        return vehicleInspection;
    }

    @Override
    public long getWorkTime() {
        return 5000;
    }

    public boolean setCar(Car car) {
        if (this.car == null) {
            this.car = car;
            return true;
        } else return false;
    }

    Car getCar() {
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
