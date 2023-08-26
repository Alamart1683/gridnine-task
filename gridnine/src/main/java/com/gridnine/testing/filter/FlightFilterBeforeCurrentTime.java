package com.gridnine.testing.filter;

import com.gridnine.testing.flight.Flight;
import com.gridnine.testing.flight.Segment;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// Фильтр исключающий полеты с сегментами до текущего момента времени
public class FlightFilterBeforeCurrentTime implements FlightFilter {
    // Вариант через стримы
    @Override
    public List<Flight> flightFilterStream(List<Flight> flightList) {
        LocalDateTime currentTime = LocalDateTime.now();
        List<Flight> filtered = new ArrayList<>();
        flightList.forEach(f -> {
            if (f.getSegments().stream().allMatch(s -> s.getDepartureDate().isAfter(currentTime)))
                filtered.add(f);
        });
        return filtered;
    }

    // Вариант без стримов
    @Override
    public List<Flight> flightFilterDefault(List<Flight> flightList) {
        LocalDateTime currentTime = LocalDateTime.now();
        List<Flight> filtered = new ArrayList<>();
        for (Flight flight : flightList) {
            boolean isAfter = true;
            for (Segment segment : flight.getSegments()) {
                if (segment.getDepartureDate().isBefore(currentTime)) {
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