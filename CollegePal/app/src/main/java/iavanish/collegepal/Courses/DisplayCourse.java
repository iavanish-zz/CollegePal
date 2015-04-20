package iavanish.collegepal.Courses;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.Vector;

import iavanish.collegepal.R;
import iavanish.collegepal.Start.User;
import iavanish.collegepal.Start.UserClientApi;
import retrofit.RestAdapter;

public class DisplayCourse extends Activity {

    private final String URL = "http://192.168.55.74:8080";
    private CourseClientApi courseService = new RestAdapter.Builder()
            .setEndpoint(URL).setLogLevel(RestAdapter.LogLevel.FULL).build()
            .create(CourseClientApi.class);

    TextView txt_courseID, txt_courseName, txt_admin, txt_overview, txt_institution, txt_preRequisites, txt_postConditions, txt_instructors,txt_courseTA;
    Button button_editprofile, button_proceed;

    String _courseID,_courseName,_admin,_overview,_institution,_preRequisities,_postConditions,_instructors,_courseTA;

    Course course;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_display);

        txt_courseID = (TextView)findViewById(R.id.text_courseID);
        txt_courseName = (TextView)findViewById(R.id.text_courseName);
        txt_admin = (TextView)findViewById(R.id.text_admin);
        txt_overview = (TextView)findViewById(R.id.text_overview);
        txt_institution = (TextView)findViewById(R.id.text_institution);
        txt_preRequisites = (TextView)findViewById(R.id.text_preRequisites);
        txt_postConditions = (TextView)findViewById(R.id.text_postConditions);
        txt_instructors = (TextView)findViewById(R.id.textView_instructors);
        txt_courseTA = (TextView)findViewById(R.id.text_courseTA);

        button_editprofile =(Button) findViewById(R.id.button_editprofile);
        button_proceed =(Button) findViewById(R.id.button_proceed);

        Intent in = getIntent();
        Bundle b = in.getExtras();
        if(b!=null) {
            _courseID = b.getString("CourseID");
            _courseName = b.getString("CourseName");
            _admin = b.getString("Admin");
            _overview = b.getString("Overview");
            _institution = b.getString("Institution");
            _preRequisities = b.getString("preRequisites");
            _postConditions = b.getString("postCondition");
            _instructors = b.getString("Instructors");
            _courseTA = b.getString("TA");

            _preRequisities = _preRequisities.replace(',', '\n');
            _postConditions = _postConditions.replace(',', '\n');
            _instructors = _instructors.replace(',', '\n');
            _courseTA = _courseTA.replace(',', '\n');

            txt_courseID.setText(_courseID);
            txt_courseName.setText(_courseName);
            txt_admin.setText(_admin);
            txt_overview.setText(_overview);
            txt_institution.setText(_institution);
            txt_preRequisites.setText(_preRequisities);
            txt_postConditions.setText(_postConditions);
            txt_instructors.setText(_instructors);
            txt_courseTA.setText(_courseTA);
        }
        button_proceed.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                Vector<String> preRequisities =new Vector<String>();
                List<String> preRequisities_list = Arrays.asList(_preRequisities.split("\\s*,\\s*"));
                preRequisities.addAll(preRequisities_list);

                Vector<String> postConditions=new Vector<String>();
                List<String> postConditions_list = Arrays.asList(_postConditions.split("\\s*,\\s*"));
                postConditions.addAll(postConditions_list);

                Vector<String> instructors=new Vector<String>();
                List<String> instructors_list = Arrays.asList(_instructors.split("\\s*,\\s*"));
                instructors.addAll(instructors_list);

                Vector<String> courseTA=new Vector<String>();
                List<String> courseTA_list = Arrays.asList(_courseTA.split("\\s*,\\s*"));
                courseTA.addAll(courseTA_list);
                Vector<String> studentRegistered=new Vector<String>();
                studentRegistered.add("");

                String id = UUID.randomUUID().toString();
                course=new Course(id,_courseID,
                        _courseName,_admin,_overview,
                        _institution,
                        preRequisities,
                        postConditions ,
                        courseTA,
                        instructors,
                        studentRegistered
                        );
                UserTask tsk = new UserTask();
                tsk.execute();


            }
        });
        button_editprofile.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                onBackPressed();

            }
        });

    }
    private class UserTask extends AsyncTask<String, Void, Boolean>
    {

        @Override
        protected Boolean doInBackground(String... params) {
            Boolean ok = courseService.addCourse(course);

            if(ok!=null)
                return ok;
            return true;
        }
        @Override
        protected void onPostExecute(Boolean b)
        {
            if(b)
                Toast.makeText(getApplicationContext(), "Jai mata Di Done", Toast.LENGTH_LONG).show();
            System.out.print("test");
        }
    }
}
