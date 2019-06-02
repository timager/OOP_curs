package server;

import java.util.ArrayList;

class StatisticUtil {
    static long exponentialMedium(float medium) {
        double a = Math.random();
        return Math.round((-medium) * Math.log(a));
    }

    static int random(int min, int max) {
        max -= min;
        return (int) (Math.random() * ++max) + min;
    }

    static double medium(ArrayList<Long> list) {
        final long[] sum = {0};
        list.forEach(e -> sum[0] = sum[0] + e);

        return (double) sum[0] / (double) list.size();
    }

    static double sko(ArrayList<Long> list){
        final double[] sum = {0};
        double medium = medium(list);
        list.forEach(e -> {
            sum[0] = sum[0] + Math.pow(e-medium,2);
        });
        return Math.sqrt(sum[0]/(double) list.size());
    }
}
