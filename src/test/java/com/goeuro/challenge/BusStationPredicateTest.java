package com.goeuro.challenge;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * BusStationPredicateTest.
 */
public class BusStationPredicateTest {

    @Test
    public void containsStations() {
        List<String> stations = Arrays.asList(String.valueOf(12),
                String.valueOf(155));

        String busStationLine = "11 121 114 148 169 12 16 155";

        assertTrue(BusStationPredicate.containsStations(stations).test(busStationLine));
    }

    @Test
    public void containsStationsWhichHasSameIdAsBusNumber() {
        List<String> stations = Arrays.asList(String.valueOf(11),
                String.valueOf(155));

        String busStationLine = "11 11 121 114 148 169 12 16 155";

        assertTrue(BusStationPredicate.containsStations(stations).test(busStationLine));
    }

    @Test
    public void doesnotContainStations() {
        List<String> stations = Arrays.asList(String.valueOf(122),
                String.valueOf(155));

        String busStationLine = "11 121 114 148 169 12 16 155";

        assertFalse(BusStationPredicate.containsStations(stations).test(busStationLine));
    }

    @Test
    public void doesnotContainStationsWhenIdMatchesWithBusNumberOnly() {
        List<String> stations = Arrays.asList(String.valueOf(11),
                String.valueOf(155));

        String busStationLine = "11 121 114 148 169 12 16 155";

        assertFalse(BusStationPredicate.containsStations(stations).test(busStationLine));
    }

}
