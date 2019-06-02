package server;


public class CarWash implements Fabric {

    private Car car;

    public long getR1() {
        return R1;
    }

    public void setR1(long r1) {
        R1 = r1;
    }

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
        return (long)(1000.0/Server.SLEEP)*StatisticUtil.exponentialMedium(this.R1);
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

    @Override
    public void clear() {
        car = null;
    }
}
