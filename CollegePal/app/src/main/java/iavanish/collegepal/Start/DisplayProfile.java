package iavanish.collegepal.Start;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.Vector;

import iavanish.collegepal.R;
import retrofit.RestAdapter;

/**
 * Created by Himanshu on 4/1/2015.
 */
public class DisplayProfile extends Activity {

    private final String URL = "http://192.168.55.74:8080";
    private UserClientApi userService = new RestAdapter.Builder()
            .setEndpoint(URL).setLogLevel(RestAdapter.LogLevel.FULL).build()
            .create(UserClientApi.class);

    TextView txt_emailID, txt_name, txt_course, txt_branch, txt_institution, txt_skills, txt_courseEnrolled, txt_courseOffering;
    Button button_editprofile, button_proceed;

    String _emailID,_name,_course,_branch,_institution,_skills,_courseEnrolled,_courseOffering;

    User  user;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_display);

        txt_emailID = (TextView)findViewById(R.id.text_emailID);
        txt_name = (TextView)findViewById(R.id.text_name);
        txt_course = (TextView)findViewById(R.id.text_course);
        txt_branch = (TextView)findViewById(R.id.text_branch);
        txt_institution = (TextView)findViewById(R.id.text_institution);
        txt_skills = (TextView)findViewById(R.id.text_skills);
        txt_courseEnrolled = (TextView)findViewById(R.id.text_courseEnrolled);
        txt_courseOffering = (TextView)findViewById(R.id.text_courseOffering);
        button_editprofile =(Button) findViewById(R.id.button_editprofile);
        button_proceed =(Button) findViewById(R.id.button_proceed);

        Intent in = getIntent();
        Bundle b = in.getExtras();
        if(b!=null) {
            _emailID = b.getString("Email");
            _name = b.getString("Name");
            _course = b.getString("Course");
            _branch = b.getString("Branch");
            _institution = b.getString("Institution");
            _skills = b.getString("Skills");
            _courseEnrolled = b.getString("CourseEnrolled");
            _courseOffering = b.getString("CourseOffering");

            txt_emailID.setText(_emailID);
            txt_name.setText(_name);
            txt_course.setText(_course);
            txt_branch.setText(_branch);
            txt_institution.setText(_institution);
            txt_skills.setText(_skills);
            txt_courseEnrolled.setText(_courseEnrolled);
            txt_courseOffering.setText(_courseOffering);
        }
        button_proceed.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Vector<String> skills=new Vector<String>();
                List<String> skills_list = Arrays.asList(_skills.split("\\s*,\\s*"));
                skills.addAll(skills_list);

                Vector<String> coursesEnrolled=new Vector<String>();
                List<String> coursesEnrolled_list = Arrays.asList(_courseEnrolled.split("\\s*,\\s*"));
                coursesEnrolled.addAll(coursesEnrolled_list);

                Vector<String> coursesOffering=new Vector<String>();
                List<String> coursesOffering_list = Arrays.asList(_courseOffering.split("\\s*,\\s*"));
                coursesOffering.addAll(coursesOffering_list);

                String id = UUID.randomUUID().toString();
                user=new User(id,_emailID,
                        _name,_course,_branch,
                        _institution,
                        skills,
                        coursesEnrolled ,
                        coursesOffering);
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
            Boolean ok = userService.addUser(user);

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
