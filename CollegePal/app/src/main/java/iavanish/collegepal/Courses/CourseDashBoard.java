package iavanish.collegepal.Courses;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import iavanish.collegepal.R;
import iavanish.collegepal.Start.NothingSelectedSpinnerAdapter;
import iavanish.collegepal.Start.User;
import iavanish.collegepal.Start.UserClientApi;
import retrofit.RestAdapter;

public class CourseDashBoard extends Activity implements AdapterView.OnItemSelectedListener {

    private final String URL = "http://192.168.55.74:8080";
    private CourseClientApi courseService = new RestAdapter.Builder()
            .setEndpoint(URL).setLogLevel(RestAdapter.LogLevel.FULL).build()
            .create(CourseClientApi.class);
    private UserClientApi userService = new RestAdapter.Builder()
            .setEndpoint(URL).setLogLevel(RestAdapter.LogLevel.FULL).build()
            .create(UserClientApi.class);
    private Button mViewCourseButton;
    private Spinner spinnerCourseEnrolled,spinnerCourseOffering;
    String _courseName,_admin;
    Collection<Course> course;
    Collection<User> user;
    private ArrayList<Course> mListCourse = new ArrayList<Course>();
    private ArrayList<String> courseListEnrolled = new ArrayList <String>();
    private ArrayList<String> courseListOffering = new ArrayList <String>();
    Collection<Course> searchCourse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_dash_board);
        findAllViewsId();
        Intent in = getIntent();
        courseListEnrolled = in.getStringArrayListExtra("course_list_Enrolled");
        courseListOffering = in.getStringArrayListExtra("course_list_Offering");
        Bundle b = in.getExtras();
        if(b!=null) {
            _admin = b.getString("Email");
        }
        spinnerCourseEnrolled.setOnItemSelectedListener(this);
        ArrayAdapter <String> dataAdapter_courseEnrolled= new ArrayAdapter <String> (this, android.R.layout.simple_spinner_item, courseListEnrolled);
        dataAdapter_courseEnrolled.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spinnerCourseEnrolled.setPrompt("Select Course");
        spinnerCourseEnrolled.setAdapter(
                new NothingSelectedSpinnerAdapter(
                        dataAdapter_courseEnrolled,
                        R.layout.spinner_row_nothing_selected,
                        this));

        spinnerCourseOffering.setOnItemSelectedListener(this);
        ArrayAdapter <String> dataAdapter_courseOffering= new ArrayAdapter <String> (this, android.R.layout.simple_spinner_item, courseListOffering);
        dataAdapter_courseOffering.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spinnerCourseOffering.setPrompt("Select Course");
        spinnerCourseOffering.setAdapter(
                new NothingSelectedSpinnerAdapter(
                        dataAdapter_courseOffering,
                        R.layout.spinner_row_nothing_selected,
                        this));


        mViewCourseButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (_courseName.length() == 0) {
                    Toast.makeText(getApplicationContext(), "Choose the course correctly", Toast.LENGTH_SHORT).show();
                } else {

                    Bundle bundle = new Bundle();
                    bundle.putString("Email", _admin);
                    bundle.putString("CourseName", _courseName);
                    Intent intent = new Intent(getApplicationContext(), CourseDashBoardView.class);
                    intent.putExtras(bundle);
                    startActivity(intent);

                    Toast.makeText(getApplicationContext(), "Jai Mata Di done", Toast.LENGTH_SHORT).show();

                }
            }
        });


    }

    private void findAllViewsId() {

        spinnerCourseEnrolled = (Spinner) findViewById(R.id.spinner_courseEnrolled);
        spinnerCourseOffering = (Spinner) findViewById(R.id.spinner_courseOffering);
        mViewCourseButton = (Button) findViewById(R.id.button_viewCourseDetails);

    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


        switch (parent.getId())
        {
            case R.id.spinner_courseEnrolled:
                if(parent.getItemAtPosition(position)!=null) {
                    _courseName=parent.getItemAtPosition(position).toString();
                    System.out.println("Selected Course name"+_courseName);

             }
            break;
            case R.id.spinner_courseOffering:
                if(parent.getItemAtPosition(position)!=null) {
                    _courseName=parent.getItemAtPosition(position).toString();
                    System.out.println("Selected Course name"+_courseName);

                }
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show();
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_course_dash_board, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
