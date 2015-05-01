package iavanish.collegepal.Courses;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.common.collect.Iterables;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Vector;

import iavanish.collegepal.Deadlines.Deadline;
import iavanish.collegepal.Deadlines.DeadlineActivity;
import iavanish.collegepal.R;
import retrofit.RestAdapter;

public class CourseDashBoardView extends Activity {

    private final String URL = "http://192.168.55.74:8080";
    private CourseClientApi courseService = new RestAdapter.Builder()
            .setEndpoint(URL).setLogLevel(RestAdapter.LogLevel.FULL).build()
            .create(CourseClientApi.class);

    TextView txt_courseID, txt_courseName, txt_admin, txt_overview, txt_institution, txt_preRequisites, txt_postConditions, txt_instructors,txt_courseTA,txt_studentsRegistered;
    Button button_deadline, button_resource;

    String _emailId,_courseID,_courseName,_admin,_overview,_institution;
    Collection<Course> course;
    StringBuilder _preRequisities = new StringBuilder();
    StringBuilder _postConditions = new StringBuilder();
    StringBuilder _instructors = new StringBuilder();
    StringBuilder _courseTA = new StringBuilder();
    StringBuilder _studentsRegistered = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_dash_board_view);
        findAllViewsId();
        Intent in = getIntent();
        Bundle b = in.getExtras();
        if(b!=null) {
            _courseName = b.getString("CourseName");
            _emailId = b.getString("Email");
        }

        UserTask tsk = new UserTask();
        tsk.execute();
        button_deadline.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
             //Deadline Code
                Bundle bundle = new Bundle();
                bundle.putString("Email", _admin);
                bundle.putString("CourseName", _courseName);
                Intent intent = new Intent(getApplicationContext(), DeadlineActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);

                Toast.makeText(getApplicationContext(), "Jai Mata Di done", Toast.LENGTH_SHORT).show();
            }
        });
        button_resource.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
               //Resource Code

            }
        });


    }

    private void findAllViewsId() {
        txt_courseID = (TextView)findViewById(R.id.text_courseID);
        txt_courseName = (TextView)findViewById(R.id.text_courseName);
        txt_admin = (TextView)findViewById(R.id.text_admin);
        txt_overview = (TextView)findViewById(R.id.text_overview);
        txt_institution = (TextView)findViewById(R.id.text_institution);
        txt_preRequisites = (TextView)findViewById(R.id.text_preRequisites);
        txt_postConditions = (TextView)findViewById(R.id.text_postConditions);
        txt_instructors = (TextView)findViewById(R.id.text_instructors);
        txt_courseTA = (TextView)findViewById(R.id.text_courseTA);
        txt_studentsRegistered =(TextView) findViewById(R.id.text_studentsRegistered);
        button_deadline =(Button) findViewById(R.id.button_deadline);
        button_resource =(Button) findViewById(R.id.button_resource);

    }

    private class UserTask extends AsyncTask<String, Void, Boolean> {

        @Override
        protected Boolean doInBackground(String... params) {
            Boolean ok;
            Course tempCourse;
            course = courseService.findByCourseNameIgnoreCase(_courseName);

            if (!course.isEmpty()) {
                tempCourse = Iterables.getFirst(course, null);
                _courseID = tempCourse.getCourseId();
                _courseName = tempCourse.getCourseName();
                _admin = tempCourse.getAdmin();
                _overview = tempCourse.getOverview();
                _institution = tempCourse.getInstitution();

                Vector<String> preRequisities = new Vector<String>();
                preRequisities.addAll(tempCourse.getPreRequisites());

                boolean appendSeparator = false;
                for(int y=0; y <preRequisities.size(); y++){

                    if (appendSeparator)
                        _preRequisities.append(',');
                    appendSeparator = true;

                    _preRequisities.append(preRequisities.get(y));
                }




                Vector<String> postConditions = new Vector<String>();
                postConditions.addAll(tempCourse.getPostConditions());

                appendSeparator = false;
                for(int y=0; y <postConditions.size(); y++){

                    if (appendSeparator)
                        _postConditions.append(',');
                    appendSeparator = true;
                    _postConditions.append(postConditions.get(y));
                }


                Vector<String> instructors = new Vector<String>();
                instructors.addAll(tempCourse.getInstructors());

                appendSeparator = false;
                for(int y=0; y <instructors.size(); y++){

                    if (appendSeparator)
                        _instructors.append(',');
                    appendSeparator = true;
                    _instructors.append(instructors.get(y));
                }


                Vector<String> courseTA = new Vector<String>();
                courseTA.addAll(tempCourse.getCourseTA());

                appendSeparator = false;
                for(int y=0; y <courseTA.size(); y++){

                    if (appendSeparator)
                        _courseTA.append(',');
                    appendSeparator = true;
                    _courseTA.append(courseTA.get(y));
                }

                Vector<String> studentsRegistered = new Vector<String>();
                studentsRegistered.addAll(tempCourse.getStudentRegistered());

                appendSeparator = false;
                for(int y=0; y <studentsRegistered.size(); y++){

                    if (appendSeparator)
                        _studentsRegistered.append(',');
                    appendSeparator = true;
                    _studentsRegistered.append(studentsRegistered.get(y));
                }

            }
            return true;
        }

        @Override
        protected void onPostExecute(Boolean b)
        {
            /*if(b) {*/
            txt_courseID.setText(_courseID);
            txt_courseName.setText(_courseName);
            txt_admin.setText(_admin);
            txt_overview.setText(_overview);
            txt_institution.setText(_institution);
            txt_preRequisites.setText(_preRequisities);
            txt_postConditions.setText(_postConditions);
            txt_instructors.setText(_instructors);
            txt_courseTA.setText(_courseTA);
            txt_studentsRegistered.setText(_studentsRegistered);



            Toast.makeText(getApplicationContext(), "Jai mata Di Done", Toast.LENGTH_LONG).show();
            //}
            System.out.println("Display Done");
        }
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
        getMenuInflater().inflate(R.menu.menu_course_dash_board_view, menu);
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
