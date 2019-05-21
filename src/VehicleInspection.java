class VehicleInspection implements Fabric {
    private Car car;

    void setRepairShop(RepairShop repairShop) {
        this.repairShop = repairShop;
    }

    private RepairShop repairShop;


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
        car = null;
        return true;
    }

    @Override
    public Fabric getNextFabric() {
        return repairShop;
    }

    @Override
    public long getWorkTime() {
        return (long) 3;
    }
}
