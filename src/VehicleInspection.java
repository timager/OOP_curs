class VehicleInspection implements Fabric {
    private Car car;
    private RepairShop repairShop;

    VehicleInspection() {
        this.repairShop = new RepairShop();
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
        car = null;
        return true;
    }

    @Override
    public Fabric getNextFabric() {
        return repairShop;
    }

    @Override
    public long getWorkTime() {
        return (long) 3000;
    }
}
