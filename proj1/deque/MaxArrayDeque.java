package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {

    private final Comparator<T> comparator;

    public MaxArrayDeque(Comparator<T> c) {
        super();
        comparator = c;
    }

    public T max() {
        return max(comparator);
    }

    public T max(Comparator<T> c) {
        if (isEmpty()) {
            return null;
        }
        int maxInd = 0;
        for (int currInd = 1; currInd < size(); currInd++) {
            if (c.compare(get(currInd), get(maxInd)) > 0) {
                maxInd = currInd;
            }
        }
        return get(maxInd);
    }
}
