package a_b.cw1.a000963464.ivolunteer;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;
import android.view.View;

public class DetailsActivity extends Activity implements View.OnClickListener{

    // Declare all the editText variable
    TextView tvActivityName, tvActivityLocation, tvActivityDate, tvActivityTime, tvVolunteerName;
    String ActivityName, ActivityLocation, ActivityDate, ActivityTime, VolunteerName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        // Once Variable has declared, now verify the button and editText by id that has already created previously
        Button buttonBack = findViewById(R.id.btn_back);
        buttonBack.setOnClickListener(DetailsActivity.this);

        tvActivityName = findViewById(R.id.tvActivityName);
        tvActivityLocation = findViewById(R.id.tvActivityLocation);
        tvActivityDate = findViewById(R.id.tvActivityDate);
        tvActivityTime = findViewById(R.id.tvActivityTime);
        tvVolunteerName = findViewById(R.id.tvVolunteerName);

        // The purpose of writing this function is to convenient user
        // Let say if user submitted typo text, user can press back button and edit without re-input the text boxes
        Bundle getval = getIntent().getExtras();
        if(getval == null){
            return;
        }else{
            // Set the val to these variable from the intent bundle extra values.
            ActivityName = getval.getString("etActivityName");
            ActivityLocation = getval.getString("etActivityLocation");
            ActivityDate = getval.getString("etActivityDate");
            ActivityTime = getval.getString("etActivityTime");
            VolunteerName = getval.getString("etVolunteerName");

            // Set the val to these text view from the string variables
            tvActivityName.setText("Activity Name: " + String.valueOf(ActivityName));
            tvActivityLocation.setText("Activity Location: " + ActivityLocation);
            tvActivityDate.setText("Activity Date: " + ActivityDate);
            tvActivityTime.setText("Activity Time: " + ActivityTime);
            tvVolunteerName.setText("Volunteer Name: " + VolunteerName);
        }

    }

    // This function is for button setOnClickListener
    // For tidiness and systematic
    public void onClick(View v){
        if(v.getId()==R.id.btn_back){

            Intent backactivity = new Intent(DetailsActivity.this, MainActivity.class);
            backactivity.putExtra("tvActivityName", ActivityName);
            backactivity.putExtra("tvActivityLocation", ActivityLocation);
            backactivity.putExtra("tvActivityDate", ActivityDate);
            backactivity.putExtra("tvActivityTime", ActivityTime);
            backactivity.putExtra("tvVolunteerName", VolunteerName);
            startActivity(backactivity);
        }
    }
}
