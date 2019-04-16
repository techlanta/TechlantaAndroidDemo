package sossi.techlanta.techlantademo.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EventsManager {
    public List<Event> events;
    private static EventsManager instance;
    public Set<Event> RSVPedEvents;
    private EventsManager() {
        events = new ArrayList<>();
        RSVPedEvents = new HashSet<>();
        Event fakeEvent = new Event();
        fakeEvent.dateEnd = new Date(2019, 4, 19, 19, 0); //7PM

        fakeEvent.dateStart = new Date(2019, 4, 21, 21, 0); // 9PM
        fakeEvent.name = "Charity Frozen Yogurt Fundraiser";
        fakeEvent.description = "Mogli Yogurt has special trivia night event to raise money for Atlanta Community Food Bank! Join us!";
        fakeEvent.latitude = 33.777101;
        fakeEvent.longitude = -84.389335;
        events.add(fakeEvent);

        fakeEvent = new Event();
        fakeEvent.dateEnd = new Date(2019, 4, 19, 19, 0); //7PM

        fakeEvent.dateStart = new Date(2019, 4, 21, 21, 0); // 9PM
        fakeEvent.name = "";
        fakeEvent.description = "Mogli Yogurt has special trivia night event to raise money for Atlanta Community Food Bank! Join us!";
        fakeEvent.latitude = 33.777101;
        fakeEvent.longitude = -84.389335;
        events.add(fakeEvent);

    }

    public static EventsManager getInstance() {
        if (instance == null) {
            instance = new EventsManager();
        }
        return instance;
    }



}
