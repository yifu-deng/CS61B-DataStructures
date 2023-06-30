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
        // TODO: YOUR CODE HERE
        AList<Integer> N = new AList<>();
        AList<Integer> Ns = new AList<>();
        AList<Double> times = new AList<>();
        AList<Integer> opCounts = new AList<>();

        Stopwatch sw = new Stopwatch();
        int[] testSizes = {1000, 2000, 4000, 8000, 16000, 32000, 64000, 128000, 10000000};

        for (int size: testSizes) {
            for (int i = 0; i < size; i++) {
                N.addLast(i);
            }

            double time = sw.elapsedTime();
            int ops = size;

            Ns.addLast(size);
            times.addLast(time);
            opCounts.addLast(ops);
        }
        printTimingTable(Ns, times, opCounts);
    }
}
