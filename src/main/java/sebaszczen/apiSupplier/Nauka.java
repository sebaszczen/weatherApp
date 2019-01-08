package sebaszczen.apiSupplier;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Nauka {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 3, 6, 2, 5);
        list.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1>o2?1:-1;
            }
        });
    }
}
