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

    private final String URL = "http://192.168.55.110:8080";
    private UserClientApi userService = new RestAdapter.Builder()
            .setEndpoint(URL).setLogLevel(RestAdapter.LogLevel.FULL).build()
            .create(UserClientApi.class);

    TextView txt_emailID, txt_name, txt_course, txt_branch, txt_institution, txt_skills, txt_rating, txt_gender, txt_dob;
    Button button_editprofile, button_proceed;

    String _emailID,_name,_course,_branch,_institution,_skills,_gender,_taSubject;
    float _rating=0.0f;
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
        txt_rating = (TextView)findViewById(R.id.text_rating);
        txt_gender = (TextView)findViewById(R.id.text_gender);
        txt_dob = (TextView)findViewById(R.id.text_dob);
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
            _rating = b.getFloat("Rating");
            _skills = b.getString("Skills");
            _gender = b.getString("Gender");
            _taSubject = b.getString("DOB");


            txt_emailID.setText(_emailID);
            txt_name.setText(_name);
            txt_course.setText(_course);
            txt_branch.setText(_branch);
            txt_institution.setText(_institution);
            txt_skills.setText(_skills);
            txt_rating.setText(Float.toString(_rating));
            txt_gender.setText(_gender);
            txt_dob.setText(_taSubject);
        }
        button_proceed.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Vector<String> skills=new Vector<String>();

                List<String> skills_list = Arrays.asList(_skills.split("\\s*,\\s*"));
                skills.addAll(skills_list);
                String id = UUID.randomUUID().toString();
                user=new User(id,_emailID,
                        _name,_course,_branch,
                        _institution,
                        skills,
                        _rating,
                        _gender,
                        _taSubject);
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
