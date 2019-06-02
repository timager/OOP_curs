package server;

import java.io.Serializable;
import java.util.Random;

public class Car implements Serializable {
    public int r;
    public int g;
    public int b;

    Car(){
        Random random = new Random();
        r = random.nextInt();
        g = random.nextInt();
        b = random.nextInt();
    }
}
