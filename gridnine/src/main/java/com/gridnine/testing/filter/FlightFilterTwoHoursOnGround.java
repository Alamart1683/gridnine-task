package com.gridnine.testing.filter;

import com.gridnine.testing.flight.Flight;
import com.gridnine.testing.flight.Segment;

import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

// Фильтр исключающий полеты с сумарным временем проведенным на земле более, чем два часа
public class FlightFilterTwoHoursOnGround implements FlightFilter {

    // Вариант со стримами
    @Override
    public List<Flight> flightFilterStream(List<Flight> flightList) {
        List<Flight> filtered = new ArrayList<>();
        flightList.stream().filter(f -> isLesserTwoHoursOnGround(f.getSegments())).forEach(filtered::add);
        return filtered;
    }

    // Вариант без стримов
    @Override
    public List<Flight> flightFilterDefault(List<Flight> flightList) {
        List<Flight> filtered = new ArrayList<>();
        for (Flight flight: flightList) {
            if (isLesserTwoHoursOnGround(flight.getSegments())) {
                filtered.add(flight);
            }
        }
        return filtered;
    }

    private boolean isLesserTwoHoursOnGround(List<Segment> segments) {
        long groundTime = 0;
        for (int i = 1; i < segments.size(); i++) {
            long arrivalTime = segments.get(i - 1).getArrivalDate().toInstant(ZoneOffset.UTC).getEpochSecond();
            long departureTime = segments.get(i).getDepartureDate().toInstant(ZoneOffset.UTC).getEpochSecond();
            groundTime += departureTime - arrivalTime;
        }
        return groundTime <= 3600;
    }
}
