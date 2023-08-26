import com.gridnine.testing.filter.FlightFilter;
import com.gridnine.testing.filter.FlightFilterTwoHoursOnGround;
import com.gridnine.testing.flight.Flight;
import com.gridnine.testing.flight.Segment;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FlightFilterTwoHourOnGroundTest {
    private final List<Flight> flightList = new ArrayList<>();
    private final List<Flight> filtered = new ArrayList<>();

    @Before
    public void setFlightList() {
        // Создадим полеты
        Flight flight1 = new Flight(List.of(
                new Segment(LocalDateTime.now().minusHours(4), LocalDateTime.now().plusHours(5))
        ));

        Flight flight2 = new Flight(List.of(
                new Segment(LocalDateTime.now(), LocalDateTime.now().plusHours(4)),
                new Segment(LocalDateTime.now().plusHours(5).minusMinutes(45), LocalDateTime.now().plusHours(10))
        ));
        Flight flight3 = new Flight(List.of(
                new Segment(LocalDateTime.now().plusHours(2), LocalDateTime.now().plusHours(10)),
                new Segment(LocalDateTime.now().plusHours(10).plusMinutes(35), LocalDateTime.now().plusHours(16)),
                new Segment(LocalDateTime.now().plusHours(16).plusMinutes(35), LocalDateTime.now().plusHours(20)),
                new Segment(LocalDateTime.now().plusHours(20).plusMinutes(35), LocalDateTime.now().plusHours(23)),
                new Segment(LocalDateTime.now().plusHours(23).plusMinutes(35), LocalDateTime.now().plusHours(28))
        ));
        Flight flight4 = new Flight(List.of(
                new Segment(LocalDateTime.now().minusDays(2), LocalDateTime.now().plusDays(1)),
                new Segment(LocalDateTime.now().plusDays(1).plusHours(5), LocalDateTime.now().plusDays(2)),
                new Segment(LocalDateTime.now().plusDays(2).plusHours(6), LocalDateTime.now().plusDays(2).plusHours(10)),
                new Segment(LocalDateTime.now().plusDays(2).plusHours(12), LocalDateTime.now().plusDays(3))
        ));
        Flight flight5 = new Flight(List.of(
                new Segment(LocalDateTime.now().minusHours(3), LocalDateTime.now().plusHours(1)),
                new Segment(LocalDateTime.now().plusHours(1).plusMinutes(35), LocalDateTime.now().plusHours(8)),
                new Segment(LocalDateTime.now().plusHours(8).plusMinutes(25), LocalDateTime.now().plusHours(15))
        ));
        // Заполним список полетов
        flightList.add(flight1);
        flightList.add(flight2);
        flightList.add(flight3);
        flightList.add(flight4);
        flightList.add(flight5);
        // Заполним список отфильтрованных полетов
        filtered.add(flight1);
        filtered.add(flight2);
        filtered.add(flight5);
    }

    @Test
    public void twoHourOnGroundStreamTest() {
        FlightFilter flightFilter = new FlightFilterTwoHoursOnGround();
        Assert.assertEquals(filtered, flightFilter.flightFilterStream(flightList));
    }

    @Test
    public void twoHourOnGroundDefaultTest() {
        FlightFilter flightFilter = new FlightFilterTwoHoursOnGround();
        Assert.assertEquals(filtered, flightFilter.flightFilterDefault(flightList));
    }
}
