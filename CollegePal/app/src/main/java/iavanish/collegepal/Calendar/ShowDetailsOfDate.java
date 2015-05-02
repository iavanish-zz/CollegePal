package iavanish.collegepal.Calendar;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.common.collect.Iterables;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import iavanish.collegepal.Courses.Course;
import iavanish.collegepal.Courses.CourseClientApi;
import iavanish.collegepal.Courses.CourseDashBoard;
import iavanish.collegepal.Deadlines.Deadline;
import iavanish.collegepal.Deadlines.DeadlineClientApi;
import iavanish.collegepal.R;
import iavanish.collegepal.Start.User;
import iavanish.collegepal.Start.UserClientApi;
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

    Collection<User> user;

    private UserClientApi userService = new RestAdapter.Builder()
            .setEndpoint(URL).setLogLevel(RestAdapter.LogLevel.FULL).build()
            .create(UserClientApi.class);

    private ArrayList<String> courseListEnrolled;

    private ArrayList<String> tempCourseListEnrolled = new ArrayList<String>();

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

        CourseEnrollmentTask tsk = new CourseEnrollmentTask();
        tsk.execute();


    }

    private class ViewDeadlineTask extends AsyncTask<String, Void, Boolean> {

        @Override
        protected Boolean doInBackground(String... params) {

            ArrayList <String> courseID = new ArrayList <String> ();

            Iterator iterator = tempCourseListEnrolled.iterator();

            while(iterator.hasNext()) {
                String temp = iterator.next().toString();
                course = courseService.findByCourseNameIgnoreCase(temp);
                if(!course.isEmpty()) {
                    Course tempCourse = Iterables.getFirst(course, null);
                    courseID.add(tempCourse.getCourseId());
                }
            }

            Boolean appendSeparator = false;
            Deadline tempDeadline;

            iterator = courseID.iterator();

            while(iterator.hasNext()) {
                String temp = iterator.next().toString();
                deadline = deadlineService.findByCourseIdIgnoreCase(temp);
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

    private class CourseEnrollmentTask extends AsyncTask<String, Void, Boolean>
    {

        @Override
        protected Boolean doInBackground(String... params) {
            Boolean ok;
            User tempUser;
            user = userService.findByEmailIdIgnoreCase(email);
            if (!user.isEmpty()) {

                tempUser = Iterables.getFirst(user, null);
                courseListEnrolled = new ArrayList <String>(tempUser.getCourseEnrolled());

                int index = 1 ;
                while (courseListEnrolled.size()> index) {
                    tempCourseListEnrolled.add(courseListEnrolled.get(index));
                    index++ ;
                }

                return true;
            }
            else
                return false;
        }
        @Override
        protected void onPostExecute(Boolean b)
        {

            Toast.makeText(getApplicationContext(), "Jai mata Di Done", Toast.LENGTH_LONG).show();

            ViewDeadlineTask tsk = new ViewDeadlineTask();
            tsk.execute();

        }
    }

}
