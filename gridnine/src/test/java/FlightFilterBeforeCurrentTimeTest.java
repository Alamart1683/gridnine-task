import com.gridnine.testing.filter.FlightFilter;
import com.gridnine.testing.filter.FlightFilterBeforeCurrentTime;
import com.gridnine.testing.flight.Flight;
import com.gridnine.testing.flight.Segment;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FlightFilterBeforeCurrentTimeTest {
    private final List<Flight> flightList = new ArrayList<>();
    private final List<Flight> filtered = new ArrayList<>();

    @Before
    public void setFlightList() {
        // Создадим полеты
        Flight flight1 = new Flight(List.of(
                new Segment(LocalDateTime.now().minusDays(2), LocalDateTime.now().minusDays(1)),
                new Segment(LocalDateTime.now(), LocalDateTime.now().plusHours(12)),
                new Segment(LocalDateTime.now().plusHours(13), LocalDateTime.now().minusDays(2))
        ));
        Flight flight2 = new Flight(List.of(
                new Segment(LocalDateTime.now().minusHours(2), LocalDateTime.now().plusHours(4)),
                new Segment(LocalDateTime.now().plusHours(5), LocalDateTime.now().plusHours(7))
        ));
        Flight flight3 = new Flight(List.of(
                new Segment(LocalDateTime.now().plusMinutes(30), LocalDateTime.now().plusHours(3).minusMinutes(24))
        ));
        Flight flight4 = new Flight(List.of(
                new Segment(LocalDateTime.now().plusHours(12), LocalDateTime.now().plusHours(17)),
                new Segment(LocalDateTime.now().minusMinutes(15), LocalDateTime.now().plusHours(22)),
                new Segment(LocalDateTime.now().plusHours(23), LocalDateTime.now().plusHours(32))
        ));
        Flight flight5 = new Flight(List.of(
                new Segment(LocalDateTime.now().plusMinutes(48), LocalDateTime.now().plusHours(3)),
                new Segment(LocalDateTime.now().plusHours(3).plusMinutes(40), LocalDateTime.now().plusHours(8))
        ));
        // Заполним список полетов
        flightList.add(flight1);
        flightList.add(flight2);
        flightList.add(flight3);
        flightList.add(flight4);
        flightList.add(flight5);
        // Заполним список отфильтрованных полетов
        filtered.add(flight3);
        filtered.add(flight5);
    }

    @Test
    public void beforeCurrentTimeStreamTest() {
        FlightFilter flightFilter = new FlightFilterBeforeCurrentTime();
        Assert.assertEquals(filtered, flightFilter.flightFilterStream(flightList));
    }

    @Test
    public void beforeCurrentTimeDefaultTest() {
        FlightFilter flightFilter = new FlightFilterBeforeCurrentTime();
        Assert.assertEquals(filtered, flightFilter.flightFilterDefault(flightList));
    }

}
