package sossi.techlanta.techlantademo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import sossi.techlanta.techlantademo.model.Event;
import sossi.techlanta.techlantademo.model.EventsManager;

public class EventDetailsActivity extends AppCompatActivity {

    private Event e;
    private TextView eventName;
    private TextView eventDetails;
    private DatabaseReference databaseUser;
    private FirebaseUser user;
    private Event event;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        user = FirebaseAuth.getInstance().getCurrentUser();
        databaseUser = FirebaseDatabase.getInstance().getReference();

        e = (Event)getIntent().getSerializableExtra("event");
        eventName = findViewById(R.id.EventNameTextView);
        eventName.setText(e.name);
        eventDetails = findViewById(R.id.EventDetailTextView);
        eventDetails.setText(e.description);
    }

    public void onClickRSVP(View view) {
        Toast.makeText(EventDetailsActivity.this, "RSVP Event Success!!",
                Toast.LENGTH_LONG).show();
        EventsManager.getInstance().RSVPedEvents.add(e);
        if(!(e.RSVP)) {
            DatabaseReference databaseEvent = databaseUser.child("Users/" + user.getUid() + "/Events");
            DatabaseReference newEvent = databaseEvent.push();
            String eventID = newEvent.getKey();
            e.isRSVPed(true);
            e.setEventID(eventID);
            newEvent.setValue(e);
            Log.d("Add event", "Add event success");
        }
        Intent i = new Intent(this, RSVPEventsActivity.class);
        startActivity(i);
    }


}
