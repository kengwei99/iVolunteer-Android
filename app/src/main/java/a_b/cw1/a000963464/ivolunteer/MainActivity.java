package a_b.cw1.a000963464.ivolunteer;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;
import android.view.View;
import java.util.Calendar;
import android.widget.DatePicker;
import android.app.DatePickerDialog;
import android.widget.TimePicker;
import android.app.TimePickerDialog;
import android.util.Log;

public class MainActivity extends Activity implements View.OnClickListener{

    // Declare all the editText variable
    EditText etActivityName, etActivityLocation, etActivityDate, etActivityTime, etVolunteerName;
    // Declare OnDateSetListener and OnTimeSetListener variable
    DatePickerDialog.OnDateSetListener activityDateSetListener;
    TimePickerDialog.OnTimeSetListener activityTimeSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Once Variable has declared, now verify the button and editText by id that has already created previously
        Button buttonAdd = findViewById(R.id.buttonAdd);
        buttonAdd.setOnClickListener(MainActivity.this);

        etActivityName = (EditText) findViewById(R.id.etActivityName);
        etActivityLocation = (EditText) findViewById(R.id.etActivityLocation);
        etActivityDate = (EditText) findViewById(R.id.etActivityDate);
        etActivityTime = (EditText) findViewById(R.id.etActivityTime);
        etVolunteerName = (EditText) findViewById(R.id.etVolunteerName);

        // When user trigger this editText, it will go to the OnClick function and declare the DatePickerDialog
        etActivityDate.setOnClickListener(MainActivity.this);
        // This activityDateSetListener is call from DatePickerDialog
        activityDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                String selectdate = String.format("%02d", (month + 1)) + "-" + String.format("%02d", day) + "-" + String.valueOf(year);
                etActivityDate.setText(selectdate);
            }
        };

        // When user trigger this editText, it will go to the OnClick function and declare the TimePickerDialog
        etActivityTime.setOnClickListener(MainActivity.this);
        // This activityTimeSetListener is call from TimePickerDialog
        activityTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                etActivityTime.setText(String.format("%02d", hour) + ":" + String.format("%02d", minute));
            }
        };

        // The purpose of writing this function is to convenient user
        // Let say if user submitted typo text, user can press back button and edit without re-input the text boxes
        Bundle returnval = getIntent().getExtras();
        if(returnval == null){
            Log.d("Error Msg: ","There is nothing in this bundle");
        }else{
            etActivityName.setText(returnval.getString("tvActivityName"));
            etActivityLocation.setText(returnval.getString("tvActivityLocation"));
            etActivityDate.setText(returnval.getString("tvActivityDate"));
            etActivityTime.setText(returnval.getString("tvActivityTime"));
            etVolunteerName.setText(returnval.getString("tvVolunteerName"));
        }
    }

    // This function is for button setOnClickListener
    // For tidiness and systematic
    public void onClick(View v){

        // Trigger this function if user click on this button
        if(v.getId()==R.id.buttonAdd){
            //Validation
            if(etActivityName.getText().toString().trim().equals("")){
                etActivityName.setError("Activity Name cannot be blank!");
            }else if(etActivityDate.getText().toString().trim().equals("")){
                etActivityDate.setError("Activity Date cannot be blank!");
            }else if(etVolunteerName.getText().toString().trim().equals("")){
                etVolunteerName.setError("Volunteer Name cannot be blank!");
            }else{
                // Set the intent from MainActivity to DetailsActivity.class
                // Then get all value from editText and store inside these bundle extras.
                // startActivity means go to DetailsActivity
                Intent otheractivity = new Intent(MainActivity.this, DetailsActivity.class);
                otheractivity.putExtra("etActivityName", etActivityName.getText().toString());
                otheractivity.putExtra("etActivityLocation", etActivityLocation.getText().toString());
                otheractivity.putExtra("etActivityDate", etActivityDate.getText().toString());
                otheractivity.putExtra("etActivityTime", etActivityTime.getText().toString());
                otheractivity.putExtra("etVolunteerName", etVolunteerName.getText().toString());
                startActivity(otheractivity);
            }
        }

        // Trigger this function if user click on this editText
        if(v.getId()==R.id.etActivityDate){

            // Declare variable of year, day and month then get value from calendar
            Calendar gettoday = Calendar.getInstance();
            int year = gettoday.get(Calendar.YEAR);
            int day = gettoday.get(Calendar.DAY_OF_MONTH);
            int month = gettoday.get(Calendar.MONTH);

            // Declare a name for date picker dialog
            DatePickerDialog activitydatepicker = new DatePickerDialog(
                    MainActivity.this,
                    activityDateSetListener,
                    year, month, day);

            DatePicker dp = activitydatepicker.getDatePicker();

            Calendar pastdate = Calendar.getInstance();
            pastdate.add(Calendar.DAY_OF_MONTH,-90); // get past 90 days

            dp.setMinDate(pastdate.getTimeInMillis());// set past 3 months as minimum date

            gettoday.add(Calendar.DAY_OF_MONTH,90); // get 90 days

            dp.setMaxDate(gettoday.getTimeInMillis());// set next 3 months as maximum date

            // Set Title for datepicker dialog and show it
            activitydatepicker.setTitle(getString(R.string.datepicker_title));
            activitydatepicker.show();
        }

        // Trigger this function if user click on this editText
        if(v.getId()==R.id.etActivityTime){
            Calendar gettime = Calendar.getInstance();
            int minute = gettime.get(Calendar.MINUTE);
            int hour = gettime.get(Calendar.HOUR_OF_DAY);

            // Declare name for time picker dialog
            TimePickerDialog activitytimepicker = new TimePickerDialog(
                    MainActivity.this,
                    activityTimeSetListener,
                    hour,minute,true);

            // Set title for timepicker dialog and show it
            activitytimepicker.setTitle("Activity Time Picker");
            activitytimepicker.show();
        }
    }
}
