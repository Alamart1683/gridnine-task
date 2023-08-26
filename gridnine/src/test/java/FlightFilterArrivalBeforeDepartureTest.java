import com.gridnine.testing.filter.FlightFilter;
import com.gridnine.testing.filter.FlightFilterArrivalBeforeDeparture;
import com.gridnine.testing.flight.Flight;
import com.gridnine.testing.flight.Segment;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FlightFilterArrivalBeforeDepartureTest {
    private final List<Flight> flightList = new ArrayList<>();
    private final List<Flight> filtered = new ArrayList<>();

    @Before
    public void setFlightList() {
        // Создадим полеты
        Flight flight1 = new Flight(List.of(
                new Segment(LocalDateTime.now().minusDays(3), LocalDateTime.now().minusDays(2)),
                new Segment(LocalDateTime.now(), LocalDateTime.now().minusHours(12))
        ));
        Flight flight2 = new Flight(List.of(
                new Segment(LocalDateTime.now().minusHours(5), LocalDateTime.now().plusHours(2)),
                new Segment(LocalDateTime.now().plusHours(3), LocalDateTime.now().plusHours(11))
        ));
        Flight flight3 = new Flight(List.of(
                new Segment(LocalDateTime.now().plusMinutes(60), LocalDateTime.now().minusMinutes(60))
        ));
        Flight flight4 = new Flight(List.of(
                new Segment(LocalDateTime.now().plusHours(1), LocalDateTime.now().plusHours(10)),
                new Segment(LocalDateTime.now().plusHours(11), LocalDateTime.now().plusHours(19)),
                new Segment(LocalDateTime.now().plusHours(20), LocalDateTime.now().plusDays(1))
        ));
        Flight flight5 = new Flight(List.of(
                new Segment(LocalDateTime.now().plusMinutes(48), LocalDateTime.now().minusHours(3)),
                new Segment(LocalDateTime.now().plusHours(3).plusMinutes(40), LocalDateTime.now().plusHours(8))
        ));
        Flight flight6 = new Flight(List.of(
                new Segment(LocalDateTime.now().plusMinutes(48), LocalDateTime.now().plusHours(3)),
                new Segment(LocalDateTime.now().plusHours(3).plusMinutes(40), LocalDateTime.now().minusHours(8))
        ));
        // Заполним список полетов
        flightList.add(flight1);
        flightList.add(flight2);
        flightList.add(flight3);
        flightList.add(flight4);
        flightList.add(flight5);
        flightList.add(flight6);
        // Заполним список отфильтрованных полетов
        filtered.add(flight2);
        filtered.add(flight4);
    }

    @Test
    public void arrivalBeforeDepartureStreamTest() {
        FlightFilter flightFilter = new FlightFilterArrivalBeforeDeparture();
        Assert.assertEquals(filtered, flightFilter.flightFilterStream(flightList));
    }

    @Test
    public void arrivalBeforeDepartureDefaultTest() {
        FlightFilter flightFilter = new FlightFilterArrivalBeforeDeparture();
        Assert.assertEquals(filtered, flightFilter.flightFilterDefault(flightList));
    }
}
