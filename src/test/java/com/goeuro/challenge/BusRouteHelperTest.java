package com.goeuro.challenge;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.core.env.Environment;

import java.io.File;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

/**
 * BusRouteHelperTest.
 */
@RunWith(MockitoJUnitRunner.class)
public class BusRouteHelperTest {

    public static final String TEST_DATA_FILE = "busdata";
    @Mock
    private Environment environment;

    @InjectMocks
    private BusRouteHelper helper;

    public void setup() {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(TEST_DATA_FILE).getFile());

        when(environment.getProperty(BusRouteHelper.DATA_FILE)).thenReturn(file.getAbsolutePath());
    }

    @Test
    public void routeExists() {
        setup();
        assertTrue(helper.routeExistsForStations(121, 5));
    }

    @Test
    public void routeDoesNotExistFileNotFound() {
        when(environment.getProperty(BusRouteHelper.DATA_FILE)).thenReturn("testFile");

        assertFalse(helper.routeExistsForStations(121, 5));
    }

    @Test
    public void routeDoesNotExistIncorrectDepartureStation() {
        assertFalse(helper.routeExistsForStations(0, 5));
    }

    @Test
    public void routeDoesNotExistIncorrectArrivalStation() {
        assertFalse(helper.routeExistsForStations(6, -5));
    }

    @Test
    public void routeDoesNotExistSameStation() {
        assertFalse(helper.routeExistsForStations(6, 6));
    }

}