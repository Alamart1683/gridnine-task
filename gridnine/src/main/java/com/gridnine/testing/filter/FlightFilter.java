package com.gridnine.testing.filter;

import com.gridnine.testing.flight.Flight;

import java.util.List;

public interface FlightFilter {
    List<Flight> flightFilterStream(List<Flight> flightList);

    List<Flight> flightFilterDefault(List<Flight> flightList);
}
