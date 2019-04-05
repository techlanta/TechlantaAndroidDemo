package sossi.techlanta.techlantademo;

import sossi.techlanta.techlantademo.model.Event;

public class MarkerData {
    public Event e;
    public boolean clickedOnce;

    public MarkerData(Event e) {
        this.e = e;
        clickedOnce = false;
    }
}
