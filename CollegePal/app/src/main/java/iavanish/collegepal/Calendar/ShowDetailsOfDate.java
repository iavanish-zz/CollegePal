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
import iavanish.collegepal.Deadlines.Deadline;
import iavanish.collegepal.Deadlines.DeadlineClientApi;
import iavanish.collegepal.R;
import iavanish.collegepal.Start.User;
import iavanish.collegepal.Start.UserClientApi;
import retrofit.RestAdapter;

public class ShowDetailsOfDate extends Activity {

    private String email;
    private String date_month_year;
    private int date;

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

    ArrayList <RepresentableDeadline> representableDeadlines;

    private UserClientApi userService = new RestAdapter.Builder()
            .setEndpoint(URL).setLogLevel(RestAdapter.LogLevel.FULL).build()
            .create(UserClientApi.class);

    private ArrayList <String> courseListEnrolled;

    private ArrayList <String> tempCourseListEnrolled = new ArrayList <String> ();

    ArrayList <String> courseID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_details_of_date);

        Intent in = getIntent();

        Bundle b = in.getExtras();
        if(b!=null) {
            email = b.getString("Email");
            date_month_year = b.getString("Date");
            date = Integer.parseInt(date_month_year.substring(0, date_month_year.indexOf('-')));
        }

        txt_viewDeadline =(TextView) findViewById(R.id.txt_viewDeadline);

        CourseEnrollmentTask tsk = new CourseEnrollmentTask();
        tsk.execute();

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

            Toast.makeText(getApplicationContext(), String.valueOf(tempCourseListEnrolled.size()), Toast.LENGTH_LONG).show();

            ViewDeadlineTask tsk = new ViewDeadlineTask();
            tsk.execute();

        }
    }

    private class ViewDeadlineTask extends AsyncTask<String, Void, Boolean> {

        @Override
        protected Boolean doInBackground(String... params) {

            courseID = new ArrayList <String> ();

            Iterator iterator = tempCourseListEnrolled.iterator();

            while(iterator.hasNext()) {

                String temp = iterator.next().toString();
                course = courseService.findByCourseNameIgnoreCase(temp);

                if(!course.isEmpty()) {
                    Course tempCourse = Iterables.getFirst(course, null);
                    courseID.add(tempCourse.getCourseId());
                }

            }

            return true;
        }

        @Override
        protected void onPostExecute(Boolean b)
        {
            /*if(b) {*/
            //txt_viewDeadline.setText(txt_viewDeadline.getText()+","+_deadlineId+","+_courseID+","+_emailId+","+_deadlineDate+","+_deadlineDetails+","+_deadlineType);


            Toast.makeText(getApplicationContext(), "hello" + String.valueOf(courseID.size()), Toast.LENGTH_LONG).show();

            ViewDeadlineTask2 tsk = new ViewDeadlineTask2();
            tsk.execute();

            System.out.println("Display Deadline Done");
        }
    }

    private class ViewDeadlineTask2 extends AsyncTask<String, Void, Boolean> {

        @Override
        protected Boolean doInBackground(String... params) {

            Deadline tempDeadline;

            Iterator iterator = courseID.iterator();

            representableDeadlines = new ArrayList <RepresentableDeadline> ();

            while(iterator.hasNext()) {

                String temp = iterator.next().toString();
                deadline = deadlineService.findByCourseIdIgnoreCase(temp);

                if (!deadline.isEmpty()) {

                    for (int i = 0; i < deadline.size(); i++) {

                        tempDeadline = Iterables.get(deadline, i);
                        _courseID = tempDeadline.getCourseId();
                        _deadlineId = tempDeadline.getDeadlineId();
                        _deadlineDate = tempDeadline.getDate();
                        _deadlineDetails = tempDeadline.getDeadlineDetails();
                        _deadlineType = tempDeadline.getDeadlineType();

                        RepresentableDeadline tempD = new RepresentableDeadline();
                        tempD.courseID = _courseID;
                        tempD.deadlineId = _deadlineId;
                        tempD.deadlineDate = _deadlineDate;
                        tempD.deadlineDetails = _deadlineDetails;
                        tempD.deadlineType = _deadlineType;
                        tempD.percentage = 0;
                        representableDeadlines.add(tempD);

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

            Toast.makeText(getApplicationContext(), String.valueOf(representableDeadlines.size()), Toast.LENGTH_LONG).show();

            Demo demo = new Demo();
            ArrayList <RepresentableDeadline> privateSchedule = demo.schedulePrivateDeadlines(representableDeadlines, date);
            ArrayList <RepresentableDeadline> publicSchedule = demo.schedulePublicDeadlines(representableDeadlines, date);

            Iterator <RepresentableDeadline> iterator = privateSchedule.iterator();
            while(iterator.hasNext()) {
                RepresentableDeadline temp = iterator.next();
                if(temp.deadlineType.equalsIgnoreCase("private") && temp.percentage > 0) {
                    sb.append(temp.deadlineId);
                    sb.append("\n");
                    sb.append(temp.courseID);
                    sb.append("\n");
                    sb.append(temp.deadlineDate);
                    sb.append("\n");
                    sb.append(temp.deadlineDetails);
                    sb.append("\n");
                    sb.append(temp.deadlineType);
                    sb.append("\n");
                    sb.append("You should complete ");
                    sb.append(String.valueOf(temp.percentage));
                    sb.append("% of this deadline on ");
                    sb.append(date_month_year);
                    sb.append("\n");
                    sb.append("\n");
                    sb.append("\n");
                }
            }

            iterator = publicSchedule.iterator();
            while(iterator.hasNext()) {
                RepresentableDeadline temp = iterator.next();
                if(temp.deadlineType.equalsIgnoreCase("public") && temp.percentage > 0) {
                    sb.append(temp.deadlineId);
                    sb.append("\n");
                    sb.append(temp.courseID);
                    sb.append("\n");
                    sb.append(temp.deadlineDate);
                    sb.append("\n");
                    sb.append(temp.deadlineDetails);
                    sb.append("\n");
                    sb.append(temp.deadlineType);
                    sb.append("\n");
                    sb.append("You should complete ");
                    sb.append(String.valueOf(temp.percentage));
                    sb.append("% of this deadline on ");
                    sb.append(date_month_year);
                    sb.append("\n");
                    sb.append("\n");
                    sb.append("\n");
                }
            }

            txt_viewDeadline.setText(sb);
            Toast.makeText(getApplicationContext(), "Jai mata Di Done", Toast.LENGTH_LONG).show();
            //}
            System.out.println("Display Deadline Done");
        }
    }

}
