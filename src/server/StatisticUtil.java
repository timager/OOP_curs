package server;

class StatisticUtil{
    static long exponentialMedium(float medium){
        double a = Math.random();
        return Math.round((-medium)*Math.log(a));
    }
}
