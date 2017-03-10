package com.goeuro.challenge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * BusStationPredicate - rules for finding bus stations.
 */
public class BusStationPredicate {

    /**
     * Validates String on which it is called whether it contains provided stations.
     *
     * @param stations list of stations that should be present in string
     * @return true if given stations are both present
     */
    public static Predicate<String> containsStations(final List<String> stations) {
        return p -> convertIntoStationList(p).containsAll(stations);
    }

    /**
     * Converts String line into list by space regex and removes first element.
     *
     * @param line String to convert
     * @return List<String>
     */
    private static List<String> convertIntoStationList(final String line) {
        List<String> listOfStations = new ArrayList<String>();
        listOfStations.addAll(Arrays.asList(line.split(" ")));
        listOfStations.remove(0);
        return listOfStations;
    }
}
