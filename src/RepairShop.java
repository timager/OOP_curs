public class RepairShop implements Fabric {


    private Car car;

    public void setCarWash(CarWash carWash) {
        this.carWash = carWash;
    }

    private CarWash carWash;

    public Fabric getNextFabric() {
        return carWash;
    }

    public CarWash getCarWash(){
        return carWash;
    }

    @Override
    public long getWorkTime() {
        return 0;
    }

    public boolean nextStage() {
        if(carWash.getCar()==null){
            if(carWash.setCar(car)){
                car = null;
                return true;
            }
        }
        return false;
    }

    public boolean setCar(Car car) {
        if (this.car == null) {
            this.car = car;
            return true;
        }
        else{
            return false;
        }
    }
    public Car getCar() {
        return car;
    }
}
