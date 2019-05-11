public interface Fabric {
    boolean setCar(Car car);
    boolean nextStage();
    Fabric getNextFabric();
    long getWorkTime();
}
