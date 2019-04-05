package sossi.techlanta.techlantademo.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EventsManager {
    public List<Event> events;
    private EventsManager instance;
    private EventsManager() {
        events = new ArrayList<>();
        Event fakeEvent1 = new Event();
        fakeEvent1.dateEnd = new Date(2019, 4, 19, 19, 0); //7PM

        fakeEvent1.dateStart = new Date(2019, 4, 21, 21, 0); // 9PM
        fakeEvent1.name = "Charity Frozen Yogurt Fundraiser";
        fakeEvent1.description = "Mogli Yogurt has special trivia night event to raise money for Atlanta Food Pantry! Join us!";
        fakeEvent1.latitude = 33.777101;
        fakeEvent1.longitude = -84.389335;
        events.add(fakeEvent1);
    }

    public EventsManager getInstance() {
        if (instance == null) {
            instance = new EventsManager();
        }
        return instance;
    }



}
