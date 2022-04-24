package lesson7.task1;

import com.google.errorprone.annotations.DoNotCall;
import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.generator.InRange;
import com.pholser.junit.quickcheck.generator.Size;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(JUnitQuickcheck.class)
public class TestsFuzzing {

    @Property(trials = 500)
    public void deleteMarkedFuzzingTest(
            @Size(min = 0, max = 50)List<List<@InRange(minChar = '\u0060', maxChar = '\u007E')Character>> strings
    ) {
        List<String> stringsList = new LinkedList<>();
        for (List<Character> set : strings) {
            List<Character> arr = new ArrayList<>(set);
            if (!arr.contains('\n')) {
                stringsList.add(arr.stream().map(Object::toString)
                        .collect(Collectors.joining("")));
            }
        }

        StringBuilder sb = new StringBuilder();
        StringBuilder correctSb = new StringBuilder();
        for (String string : stringsList) {
            if (!string.contains("\n")) {
                int isDeleting = getRndIntInRange(0, 1);
                if (isDeleting == 0 && !string.equals("_")) {
                    sb.append(string).append("\n");
                    correctSb.append(string).append("\n");
                } else {
                    sb.append("_").append(string).append("\n");
                }
            }
        }

        try {
            FileWriter fileWriter = new FileWriter("input/TestsFuzzing/deleteMarkedFuzzing.txt", false);
            fileWriter.write(sb.toString());
            fileWriter.flush();

            FileWriter fileWriter1 = new FileWriter("input/TestsFuzzing/correct_deleteMarkedFuzzing.txt", false);
            fileWriter1.write(correctSb.toString());
            fileWriter1.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }


        StringBuilder tempSb = new StringBuilder();
        FilesKt.deleteMarked("input/TestsFuzzing/deleteMarkedFuzzing.txt", "temp.txt");
        try (BufferedReader reader = new BufferedReader(new FileReader("temp.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                tempSb.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        correctSb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader("input/TestsFuzzing/correct_deleteMarkedFuzzing.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                correctSb.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertEquals(correctSb.toString(), tempSb.toString());

        try {
            Files.delete(Paths.get("temp.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Property(trials = 500)
    public void chooseLongestChaoticWordFuzzingTest(
            @Size(min = 0, max = 100)List<Set<@InRange(minChar = '\u005B', maxChar = '\u007E')Character>> winningWords
    ) {
        List<String> winningWordsList = new LinkedList<>();
        for (Set<Character> set : winningWords) {
            List<Character> arr = new ArrayList<>(set);
            if (!arr.contains('\n')) {
                winningWordsList.add(arr.stream().map(Object::toString)
                        .collect(Collectors.joining("")));
            }
        }

        int maxLength = 0;
        List<String> expectedList = new LinkedList<>();
        for (String word : winningWordsList) {
            if (word.length() > maxLength) {
                maxLength = word.length();
                expectedList.clear();
                expectedList.add(word);
            }
            else if (word.length() == maxLength) expectedList.add(word);
        }

        try {
            FileWriter fileWriter = new FileWriter("input/TestsFuzzing/chooseLongestChaoticWordFuzzing.txt", false);
            for (String word : winningWordsList) {
                fileWriter.write(word);
                fileWriter.write("\n");
                fileWriter.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        StringBuilder sb = new StringBuilder();
        for (String word : expectedList) {
            sb.append(word).append(", ");
        }
        if (sb.length() == 2) sb = new StringBuilder();
        if (sb.length() > 0) sb.delete(sb.length() - 2, sb.length() - 1);
        String expectedString = sb.toString();

        StringBuilder tempSb = new StringBuilder();
        FilesKt.chooseLongestChaoticWord("input/TestsFuzzing/chooseLongestChaoticWordFuzzing.txt", "temp.txt");
        try (BufferedReader reader = new BufferedReader(new FileReader("temp.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                tempSb.append(line).append(", ");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (tempSb.length() > 0) tempSb.delete(tempSb.length() - 2, tempSb.length() - 1);

        assertEquals(expectedString, tempSb.toString());

        try {
            Files.delete(Paths.get("temp.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int getRndIntInRange(int min, int max){
        return (int) (Math.random()*((max-min)+1))+min;
    }
}
