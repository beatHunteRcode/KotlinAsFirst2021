package lesson6.task1;

import com.pholser.junit.quickcheck.From;
import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.generator.InRange;
import com.pholser.junit.quickcheck.generator.Size;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(JUnitQuickcheck.class)
public class TestsFuzzing {

    @Property(trials = 500)
    public void plusMinusFuzzingTest(
            @Size(min = 0, max = 50)
                    List<@InRange(min = "0", max = "5000")Integer> listOfNumbers
    ) {
        int sum = 0;
        StringBuilder sb = new StringBuilder();
        if (listOfNumbers.size() > 0) {
            sum = listOfNumbers.get(0);
            sb.append(sum);
            for (int i = 1; i < listOfNumbers.size(); i++) {
                int number = listOfNumbers.get(i);
                int sign = getRndIntInRange(0, 1);
                if (sign == 0) {
                    sum += number;
                    sb.append(" + ");
                    sb.append(number);
                } else if (sign == 1) {
                    sum -= number;
                    sb.append(" - ");
                    sb.append(number);
                }
            }
        }

        if (listOfNumbers.size() > 0) {
            assertEquals(sum, ParseKt.plusMinus(sb.toString()));
        }
        else {
            Assertions.assertThrows(IllegalArgumentException.class, () -> ParseKt.plusMinus(sb.toString()));
        }
    }

    @Property(trials = 500)
    public void bestLongJumpFuzzingTest(
            @Size(min = 0, max = 20) List<@InRange(min = "0", max = "5000")Integer> successfullJumpsList,
            @InRange(min = "0", max = "20")Integer numberOfMisses,
            @InRange(min = "0", max = "20")Integer numberOfFails
    ) {

        int bestJump = successfullJumpsList.size() > 0 ? Collections.max(successfullJumpsList) : 0;
        List<String> attemptsList = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        for (Integer jump : successfullJumpsList) attemptsList.add(jump.toString());
        for (int i = 0; i < numberOfMisses; i++)  attemptsList.add("-");
        for (int i = 0; i < numberOfFails; i++)  attemptsList.add("%");
        Collections.shuffle(attemptsList);
        for (String attempt : attemptsList) {
            sb.append(attempt).append(" ");
        }

        if (successfullJumpsList.size() > 0) {
            assertEquals(bestJump, ParseKt.bestLongJump(sb.toString().trim()));
        }
        else {
            assertEquals(-1, ParseKt.bestLongJump(sb.toString().trim()));
        }
    }

    private int getRndIntInRange(int min, int max){
        return (int) (Math.random()*((max-min)+1))+min;
    }

}
