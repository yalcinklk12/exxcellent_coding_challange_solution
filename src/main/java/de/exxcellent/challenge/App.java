package de.exxcellent.challenge;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The entry class for your solution. This class is only aimed as starting point and not intended as baseline for your software
 * design. Read: create your own classes and packages as appropriate.
 *
 * @author Benjamin Schmid <benjamin.schmid@exxcellent.de>
 */
public final class App {
    
    /**
     * This is the main entry method of your program.
     * @param args The CLI arguments passed
     */
    public static void main(String... args) {

        // Your preparation code …
        
        String dayWithSmallestTempSpread = getResult("weather.csv", "MnT", "MxT", ",");
        System.out.printf("Day with smallest temperature spread : %s%n", dayWithSmallestTempSpread);

        String teamWithSmallestGoalSpread = getResult("football.csv", "Goals Allowed", "Goals", ",");
        System.out.printf("Team with smallest goal spread       : %s%n", teamWithSmallestGoalSpread);
    }
    
    private static List<List<String>> fetchCsv(String fileName, String separator) {
        URL url = App.class.getResource(fileName);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()))){
            List<List<String>> rows = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                List<String> row = Arrays.asList(line.split(separator));
                rows.add(row);
            }
            return rows;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
    
    private static String getResult(String fileName, String minColumn, String maxColumn, String separator) {
        List<List<String>> values = fetchCsv(fileName, separator);
        List<String> header = values.get(0);
        //header will be skipped
        values.remove(0);
        return values.stream()
                .map(value -> new ComparableRow(value,  header.indexOf(minColumn), header.indexOf(maxColumn)))
                .sorted()
                .collect(Collectors.toList())
                .get(0)
                .getName();
    }
    
}
