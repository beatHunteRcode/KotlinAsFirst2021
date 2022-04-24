package lesson5.task1;

import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.generator.InRange;
import com.pholser.junit.quickcheck.generator.Size;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import com.sun.xml.internal.bind.v2.TODO;
import org.junit.runner.RunWith;

import java.util.*;

import kotlin.Pair;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(JUnitQuickcheck.class)
public class TestsFuzzing {

    @Property(trials = 500)
    public void averageStockPriceFuzzingTest(String company, List<@InRange(min = "0")Double> prices) {
        List<Pair<String, Double>> list = new LinkedList<>();
        double sum = 0.0;
        for (Double price : prices) {
            sum += price;
            Pair<String, Double> pair = new Pair<String, Double>(company, price);
            list.add(pair);
        }
        double averagePrice = prices.size() > 0 ? sum / prices.size() : 0.0;
        Map<String, Double> expectedMap = new HashMap<String, Double>() {{
            put(company, averagePrice);
        }};
        if (averagePrice > 0) {
            assertEquals(expectedMap, MapKt.averageStockPrice(list));
        }
        else {
            assertEquals(new HashMap<String, Double>(), MapKt.averageStockPrice(list));
        }
    }

    @Property(trials = 500)
    public void findSumOfTwoFuzzingTest(@Size(min = 0, max = 1000)List<Integer> list,
                                        Integer numb1,
                                        Integer numb2
                                        )
    {
        int sum = numb1 + numb2;
        int pos1 = 0;
        int pos2 = 0;
        list.remove(numb1);
        list.remove(numb2);
        if (list.size() > 0) {
            pos1 = getRndIntInRange(0, list.size() / 2 - 1);
            pos2 = getRndIntInRange(list.size() / 2, list.size() - 1);
            list.add(pos1, numb1);
            list.add(pos2, numb2);
        }
        if (list.size() > 0) {
            assertEquals(new Pair<Integer, Integer>(pos1, pos2), MapKt.findSumOfTwo(list, sum));
        }
        else {
            assertEquals(new Pair<Integer, Integer>(-1, -1), MapKt.findSumOfTwo(list, sum));
        }
    }

//    @Property(trials = 500)
//    public void bagPackingFuzzingTest(
//            List<String> itemsList,
//            List<@InRange(min = "100", max = "1000")Integer> itemsWeightsList,
//            List<@InRange(min = "500", max = "5000")Integer> itemsCostsList,
//            @InRange(min = "0", max = "5000") int capacity
//    ) {
//        int minimalSize = min(itemsList.size(), itemsWeightsList.size(), itemsCostsList.size());
//
//        Map<String, Pair<Integer, Integer>> testingMap = new HashMap<>();
//        for (int i = 0; i < minimalSize; i++) {
//            testingMap.put(itemsList.get(i), new Pair<>(itemsWeightsList.get(i), itemsCostsList.get(i)));
//        }
//
//
//        if (minimalSize > 0) {
//
//        }
//        else {
//            assertEquals(new HashSet<String>(), MapKt.bagPacking(testingMap, capacity));
//        }
//    }

    public static int min(int a, int b, int c) {
        return Math.min(Math.min(a, b), c);
    }

    private int getRndIntInRange(int min, int max){
        return (int) (Math.random()*((max-min)+1))+min;
    }
}
