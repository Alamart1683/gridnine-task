package com.gridnine.testing;

import com.gridnine.testing.filter.FlightFilter;
import com.gridnine.testing.filter.FlightFilterArrivalBeforeDeparture;
import com.gridnine.testing.filter.FlightFilterBeforeCurrentTime;
import com.gridnine.testing.filter.FlightFilterTwoHoursOnGround;
import com.gridnine.testing.flight.Flight;
import com.gridnine.testing.flight.FlightBuilder;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Flight> flightList = FlightBuilder.createFlights();
        FlightFilter beforeCurrentTime = new FlightFilterBeforeCurrentTime();
        FlightFilter arrivalBeforeDeparture = new FlightFilterArrivalBeforeDeparture();
        FlightFilter twoHoursOnGround = new FlightFilterTwoHoursOnGround();
        System.out.println("Список полетов с исключенными полетами с датой отправления до настоящего времени.");
        System.out.println("Вариант через стримы:");
        printList(beforeCurrentTime.flightFilterStream(flightList));
        System.out.println("Вариант без стримов:");
        printList(beforeCurrentTime.flightFilterDefault(flightList));
        System.out.println();
        System.out.println("Список полетов с исключенными полетами с датой прибытия раньше чем дата отправления.");
        System.out.println("Вариант через стримы:");
        printList(arrivalBeforeDeparture.flightFilterStream(flightList));
        System.out.println("Вариант без стримов:");
        printList(arrivalBeforeDeparture.flightFilterDefault(flightList));
        System.out.println();
        System.out.println("Список полетов с исключенными полетами по суммарному времени на земле более двух часов.");
        System.out.println("Вариант через стримы:");
        printList(twoHoursOnGround.flightFilterStream(flightList));
        System.out.println("Вариант без стримов:");
        printList(twoHoursOnGround.flightFilterDefault(flightList));
    }

    public static void printList(List<Flight> list) {
        list.forEach(System.out::println);
    }
}