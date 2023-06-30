package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeAList {
    private static void printTimingTable(AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeAListConstruction();
    }

    public static void timeAListConstruction() {
        AList<Integer> Ns = new AList<>();
        AList<Double> times = new AList<>();
        AList<Integer> opCounts = new AList<>();
//        AList<Integer> N = new AList<>();

        Stopwatch sw = new Stopwatch();
        int testLength = 1000;
        for (int i = 0; i < 15; i++) {
            if (i > 0) {
                testLength *= 2;
            }
            AList<Integer> N = new AList<>();
            for (int j = 0; j < testLength; j++) {
                N.addLast(j);
            }
            double time = sw.elapsedTime();
            int ops = testLength;

            Ns.addLast(N.size());
            times.addLast(time);
            opCounts.addLast(ops);
        }
        printTimingTable(Ns, times, opCounts);
    }
}
