package com.gridnine.testing.filter;

import com.gridnine.testing.flight.Flight;
import com.gridnine.testing.flight.Segment;

import java.util.ArrayList;
import java.util.List;

// Фильтр исключающий полеты с датой прибытия раньше, чем дата отправления
public class FlightFilterArrivalBeforeDeparture implements FlightFilter {

    // Вариант с стримами
    @Override
    public List<Flight> flightFilterStream(List<Flight> flightList) {
        List<Flight> filtered = new ArrayList<>();
        flightList.forEach(f -> {
            if (f.getSegments().stream().allMatch(s -> s.getArrivalDate().isAfter(s.getDepartureDate())))
                filtered.add(f);
        });
        return filtered;
    }

    // Вариант без стримов
    @Override
    public List<Flight> flightFilterDefault(List<Flight> flightList) {
        List<Flight> filtered = new ArrayList<>();
        for (Flight flight: flightList) {
            List<Segment> segments = flight.getSegments();
            boolean isAfter = true;
            for (Segment segment: segments) {
                if (segment.getArrivalDate().isBefore(segment.getDepartureDate())) {
                    isAfter = false;
                    break;
                }
            }
            if (isAfter) {
                filtered.add(flight);
            }
        }
        return filtered;
    }
}
