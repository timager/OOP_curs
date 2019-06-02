package client;

import java.io.Serializable;

public class Packet implements Serializable {
    public String getCommand() {
        return command;
    }

    void setCommand(String command) {
        this.command = command;
    }

    public long getR1() {
        return R1;
    }

    void setR1(long r1) {
        R1 = r1;
    }

    public long getR2() {
        return R2;
    }

    void setR2(long r2) {
        R2 = r2;
    }

    public long getT() {
        return T;
    }

    void setT(long t) {
        T = t;
    }

    public double getP() {
        return P;
    }

    void setP(double p) {
        P = p;
    }

    private String command;
    private long R1,R2,T;
    private double P;
}
