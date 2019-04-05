package sossi.techlanta.techlantademo;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import sossi.techlanta.techlantademo.model.Event;

public class EventDetailsActivity extends AppCompatActivity {

    private Event e;
    private TextView eventName;
    private TextView eventDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        e = (Event)getIntent().getSerializableExtra("event");
        eventName = findViewById(R.id.EventNameTextView);
        eventName.setText(e.name);
        eventDetails = findViewById(R.id.EventDetailTextView);
        eventDetails.setText(e.description);


    }

}
