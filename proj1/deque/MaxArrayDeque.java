package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T>{

    private Comparator<T> comparator;

    public MaxArrayDeque(Comparator<T> c){
        comparator = c;
    }
//  TODO
    public T max(){

        return null;
    }
//    TODO
    public T max(Comparator<T> c) {

    }
}
