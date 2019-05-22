class VehicleInspection implements Fabric {
    private Car car;
    private long R2;

    VehicleInspection(long R2){

        this.R2 = R2;
    }

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
        return StatisticUtil.exponentialMedium( this.R2);
    }
}
