package server;

import java.io.Serializable;

public interface Fabric extends Serializable {
    boolean setCar(Car car);
    boolean nextStage();
    Fabric getNextFabric();
    long getWorkTime();
    Car getCar();
    void clear();
}
