package iavanish.collegepal.Calendar;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.common.collect.Iterables;

import java.util.Collection;

import iavanish.collegepal.Courses.Course;
import iavanish.collegepal.Courses.CourseClientApi;
import iavanish.collegepal.Deadlines.Deadline;
import iavanish.collegepal.Deadlines.DeadlineClientApi;
import iavanish.collegepal.R;
import retrofit.RestAdapter;

public class ShowDetailsOfDate extends Activity {

    private String email;
    private String date_month_year;

    private final String URL = "http://192.168.55.74:8080";
    private CourseClientApi courseService = new RestAdapter.Builder()
            .setEndpoint(URL).setLogLevel(RestAdapter.LogLevel.FULL).build()
            .create(CourseClientApi.class);
    private DeadlineClientApi deadlineService = new RestAdapter.Builder()
            .setEndpoint(URL).setLogLevel(RestAdapter.LogLevel.FULL).build()
            .create(DeadlineClientApi.class);
    TextView txt_viewDeadline;
    String _courseName,_emailId, _courseID, _deadlineId, _deadlineDate,_deadlineType, _deadlineDetails;
    Collection<Deadline> deadline;
    Collection<Course> course;
    StringBuilder sb = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_details_of_date);

        Intent in = getIntent();

        Bundle b = in.getExtras();
        if(b!=null) {
            email = b.getString("Email");
            date_month_year = b.getString("Date");
        }

        txt_viewDeadline =(TextView) findViewById(R.id.txt_viewDeadline);

        ViewDeadlineTask tsk = new ViewDeadlineTask();
        tsk.execute();

    }

    private class ViewDeadlineTask extends AsyncTask<String, Void, Boolean> {

        @Override
        protected Boolean doInBackground(String... params) {
            Boolean ok;
            Course tempCourse;
            course = courseService.findByCourseNameIgnoreCase("Practice of Programming");
            if (!course.isEmpty()) {
                tempCourse = Iterables.getFirst(course, null);
                _courseID = tempCourse.getCourseId();
                System.out.println("CourseId"+_courseID);
            }

            Boolean appendSeparator = false;
            Deadline tempDeadline;
            deadline = deadlineService.findByCourseIdIgnoreCase(_courseID);
            if (!deadline.isEmpty()) {
                for (int i = 0; i < deadline.size(); i++) {
                    tempDeadline = Iterables.get(deadline, i);
                    _courseID = tempDeadline.getCourseId();
                    _deadlineId = tempDeadline.getDeadlineId();
                    _emailId = tempDeadline.getEmailId();
                    _deadlineDate = tempDeadline.getDate();
                    _deadlineDetails = tempDeadline.getDeadlineDetails();
                    _deadlineType = tempDeadline.getDeadlineType();
                    if (appendSeparator)
                        sb.append(",");
                    appendSeparator = true;
                    sb.append(_courseID);
                    sb.append(_deadlineId);
                    sb.append(_emailId);
                    sb.append(_deadlineDate);
                    sb.append(_deadlineDate);
                    sb.append(_deadlineType);
                    sb.append("\n");
                }
            }

            return true;
        }

        @Override
        protected void onPostExecute(Boolean b)
        {
            /*if(b) {*/
            //txt_viewDeadline.setText(txt_viewDeadline.getText()+","+_deadlineId+","+_courseID+","+_emailId+","+_deadlineDate+","+_deadlineDetails+","+_deadlineType);
            txt_viewDeadline.setText(sb);
            Toast.makeText(getApplicationContext(), "Jai mata Di Done", Toast.LENGTH_LONG).show();
            //}
            System.out.println("Display Deadline Done");
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

}
