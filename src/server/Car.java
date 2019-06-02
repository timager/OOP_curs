package server;

import java.io.Serializable;
import java.util.Random;

public class Car implements Serializable {
    public int r;
    public int g;
    public int b;

    Car(){
        Random random = new Random();
        r = StatisticUtil.random(0,255);
        g = StatisticUtil.random(0,255);
        b = StatisticUtil.random(0,255);
    }

    @Override
    public String toString() {
        return "Car("+r+","+g+","+b+")";
    }
}
