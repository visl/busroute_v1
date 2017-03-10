package com.goeuro.challenge;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * BusRouteHelper - the logic for validation input and finding bus stations in a data file.
 */
@Component
public class BusRouteHelper {

    /**
     * Path to the data file.
     */
    public static final String DATA_FILE = "dataFile";

    private static final Logger logger = Logger.getLogger(BusRouteHelper.class);

    @Autowired
    private Environment environment;

    /**
     * Validates given bus station ids against data set in file provided as command
     * line arg on service startup.
     *
     * @param departureStation
     * @param arrivalStation
     * @return true if given stations belong to the bus route
     */
    public boolean routeExistsForStations(final int departureStation,
                                          final int arrivalStation) {
        if (!validInput(departureStation, arrivalStation)) {
            logger.error("Input values are incorrect " + departureStation
                    + " or " + arrivalStation);
            return false;
        }

        List<String> stations = Arrays.asList(String.valueOf(departureStation),
                String.valueOf(arrivalStation));

        final String dataFile = environment.getProperty(DATA_FILE);
        logger.debug("dataFile=" + dataFile);

        if (!StringUtils.isEmpty(dataFile)) {
            try (Stream<String> stream = Files.lines(Paths.get(dataFile))
                    .filter(BusStationPredicate.containsStations(stations))) {

                Optional<String> containsStations = stream.findFirst();
                if (containsStations.isPresent()) {
                    return true;
                }

            } catch (IOException e) {
                logger.error("Failed to parse file", e);
            }
        }

        return false;
    }

    /**
     * Checks if input values are valid integers and not the same.
     *
     * @param departureStation
     * @param arrivalStation
     * @return true if given values are correct
     */
    private boolean validInput(final int departureStation,
                               final int arrivalStation) {
        logger.debug("departureStation = " + departureStation);
        logger.debug("arrivalStation = " + arrivalStation);
        return departureStation > 0 && arrivalStation > 0
                && departureStation != arrivalStation;
    }
}
