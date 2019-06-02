package server;

class StatisticUtil{
    static long exponentialMedium(float medium){
        double a = Math.random();
        return Math.round((-medium)*Math.log(a));
    }

    public static int random(int min, int max)
    {
        max -= min;
        return (int) (Math.random() * ++max) + min;
    }
}
