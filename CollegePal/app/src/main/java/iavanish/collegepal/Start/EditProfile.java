package iavanish.collegepal.Start;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.common.collect.Iterables;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Vector;

import iavanish.collegepal.R;
import retrofit.RestAdapter;

/**
 * Created by Himanshu on 3/29/2015.
 */
public class EditProfile extends Activity implements OnItemSelectedListener {

    private final String URL = "http://192.168.55.74:8080";
    private UserClientApi userService = new RestAdapter.Builder()
            .setEndpoint(URL).setLogLevel(RestAdapter.LogLevel.FULL).build()
            .create(UserClientApi.class);

    private Button mRegisterButton,mClearButton;
    private EditText txtEmail,txtName;
    private Spinner spinnerCourse,spinnerBranch,spinnerInstitution;
    MultiSelectionSpinner spinnerSkills, spinnerCourseEnrolled, spinnerCourseOffering;

    String _emailID,_name,_course,_branch,_institution,_skills,_courseEnrolled,_courseOffering;
    Collection<User> user;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        findAllViewsId();
        Intent in = getIntent();

        Bundle b = in.getExtras();
        if(b!=null) {
            _emailID = b.getString("Email");
            _name = b.getString("Name");

        }

        txtEmail.setText(_emailID);
        txtName.setText(_name);

        spinnerCourse.setOnItemSelectedListener(this);

        List <String> courses = new ArrayList <String>();
        courses.add("BTech 1st year");
        courses.add("BTech 2nd year");
        courses.add("BTech 3rd year");
        courses.add("BTech 4th year");
        courses.add("MTech Ist year");
        courses.add("MTech 2nd year");
        courses.add("PhD");
        courses.add("Research Scholar");

        ArrayAdapter <String> dataAdapter_course = new ArrayAdapter <String> (this, android.R.layout.simple_spinner_item, courses);
        dataAdapter_course.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spinnerCourse.setAdapter(
                new NothingSelectedSpinnerAdapter(
                        dataAdapter_course,
                        R.layout.spinner_row_nothing_selected,
                        this));

        spinnerBranch.setOnItemSelectedListener(this);


        List <String> branch = new ArrayList <String>();
        branch.add("CSE");
        branch.add("ECE");
        branch.add("IT");

        ArrayAdapter <String> dataAdapter_branch = new ArrayAdapter <String> (this, android.R.layout.simple_spinner_item, branch);


        dataAdapter_branch.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);


        spinnerBranch.setPrompt("Select Branch");
        spinnerBranch.setAdapter(
                new NothingSelectedSpinnerAdapter(
                        dataAdapter_branch,
                        R.layout.spinner_row_nothing_selected,
                        this));


        spinnerInstitution.setOnItemSelectedListener(this);


        List <String> institution = new ArrayList <String>();
        institution.add("IIIT Delhi");
        institution.add("IIT Delhi");
        institution.add("DTU");
        institution.add("NSIT");

        ArrayAdapter <String> dataAdapter_institution= new ArrayAdapter <String> (this, android.R.layout.simple_spinner_item, institution);


        dataAdapter_institution.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);


        spinnerInstitution.setPrompt("Select Institution");
        spinnerInstitution.setAdapter(
                new NothingSelectedSpinnerAdapter(
                        dataAdapter_institution,
                        R.layout.spinner_row_nothing_selected,
                        this));

        final List <String> skills = new ArrayList <String>();
        skills.add("C Programming");
        skills.add("C++ Programming");
        skills.add("JAVA Programming");
        skills.add("Android Programming");
        skills.add("Cloud Computing");
        skills.add("Machine Learning");
        skills.add("Data Analytics");
        skills.add("Cryptography");
        skills.add("Bio Informatics");
        skills.add("TOC");
        skills.add("Operating Systems");
        skills.add("Robotics");
        skills.add("Power Electronics");
        spinnerSkills.setItems(skills);

        final List <String> courseEnrolled = new ArrayList <String>();
        courseEnrolled.add("Practice of Programming");
        courseEnrolled.add("Pattern Recognition");
        courseEnrolled.add("Data Structure and Algorithm");
        courseEnrolled.add("Mobile Computing");
        courseEnrolled.add("Programming Cloud Services");
        courseEnrolled.add("Machine Learning");
        courseEnrolled.add("Data Mining");
        courseEnrolled.add("Applied Cryptography");
        courseEnrolled.add("Smart Coding");
        courseEnrolled.add("Learning Hadoop");
        courseEnrolled.add("Verilog Programming");
        courseEnrolled.add("Learning Robotics");
        courseEnrolled.add("Wireless Networking");
        spinnerCourseEnrolled.setItems(courseEnrolled);

        final List <String> courseOffering = new ArrayList <String>();
        courseOffering.add("Practice of Programming");
        courseOffering.add("Data Structure and Algorithm");
        courseOffering.add("Pattern Recognition");
        courseOffering.add("Mobile Computing");
        courseOffering.add("Programming Cloud Services");
        courseOffering.add("Machine Learning");
        courseOffering.add("Data Mining");
        courseOffering.add("Applied Cryptography");
        courseOffering.add("Smart Coding");
        courseOffering.add("Learning Hadoop");
        courseOffering.add("Verilog Programming");
        courseOffering.add("Learning Robotics");
        courseOffering.add("Wireless Networking");
        spinnerCourseOffering.setItems(courseOffering);

        mClearButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                spinnerCourse.setSelection(0);
                spinnerBranch.setSelection(0);
                spinnerInstitution.setSelection(0);
                spinnerSkills.setSelection(0);
                spinnerCourseEnrolled.setSelection(0);
                spinnerCourseOffering.setSelection(0);

            }
        });
        mRegisterButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                getData();
                if (_emailID.length() == 0 || _name.length() == 0 || _course.length() == 0 || _branch.length() == 0 || _institution.length() == 0 || _skills.length() == 0) {
                    Toast.makeText(getApplicationContext(), "Complete the form correctly", Toast.LENGTH_SHORT).show();
                } else {

                    UserTask tsk = new UserTask();
                    tsk.execute();
                }
            }
        });
    }
    private void findAllViewsId() {

        txtEmail = (EditText) findViewById(R.id.editText_emailID);
        txtName = (EditText) findViewById(R.id.editText_name);
        spinnerCourse = (Spinner) findViewById(R.id.spinner_course);
        spinnerBranch = (Spinner) findViewById(R.id.spinner_branch);
        spinnerInstitution = (Spinner) findViewById(R.id.spinner_institution);
        spinnerSkills = (MultiSelectionSpinner) findViewById(R.id.spinner_preRequisites);
        spinnerCourseEnrolled = (MultiSelectionSpinner) findViewById(R.id.spinner_courseEnrolled);
        spinnerCourseOffering = (MultiSelectionSpinner) findViewById(R.id.spinner_courseOffering);
        mRegisterButton = (Button) findViewById(R.id.button_register);
        mClearButton = (Button) findViewById(R.id.button_clear);

    }
    public void getData() {
        _emailID = txtEmail.getText().toString();
        _name = txtName.getText().toString();
        _skills = spinnerSkills.getSelectedItemsAsString();
        /*_courseEnrolled = spinnerCourseEnrolled.getSelectedItemsAsString();
        _courseOffering = spinnerCourseOffering.getSelectedItemsAsString();*/
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


        switch (parent.getId())
        {
            case R.id.spinner_course:
                if(parent.getItemAtPosition(position)!=null) {
                    _course=parent.getItemAtPosition(position).toString();

                }
                break;

            case R.id.spinner_branch:
                if(parent.getItemAtPosition(position)!=null) {
                    _branch=parent.getItemAtPosition(position).toString();

                }
                break;
            case R.id.spinner_institution:
                if(parent.getItemAtPosition(position)!=null) {
                    _institution=parent.getItemAtPosition(position).toString();

                }
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // TODO Auto-generated method stub

    }

    private class UserTask extends AsyncTask<String, Void, Boolean>
    {

        @Override
        protected Boolean doInBackground(String... params) {
            Boolean ok;
            User tempUser;
            User newUser;
            user = userService.findByEmailIdIgnoreCase(_emailID);
            if (!user.isEmpty()) {
                tempUser = Iterables.getFirst(user, null);
                Vector<String> skills=new Vector<String>();
                List<String> skills_list = Arrays.asList(_skills.split("\\s*,\\s*"));
                skills.addAll(skills_list);

                /*Vector<String> coursesEnrolled=new Vector<String>();
                List<String> coursesEnrolled_list = Arrays.asList(_courseEnrolled.split("\\s*,\\s*"));
                coursesEnrolled.addAll(coursesEnrolled_list);

                Vector<String> coursesOffering=new Vector<String>();
                List<String> coursesOffering_list = Arrays.asList(_courseOffering.split("\\s*,\\s*"));
                coursesOffering.addAll(coursesOffering_list);*/

                newUser = new User(tempUser.getStr(), tempUser.getStr(),
                        _emailID, _name,_course,_branch,
                        _institution,
                        skills,
                        tempUser.getCourseEnrolled() ,
                        tempUser.getCoursesOffering());
                ok = userService.addUser(newUser);
                return ok;
            }
            else
                return false;
        }
        @Override
        protected void onPostExecute(Boolean b)
        {
           /* if(b) {*/
                Toast.makeText(getApplicationContext(), "Jai mata Di Done", Toast.LENGTH_LONG).show();
                onBackPressed();
            //}
            System.out.println("Update done");
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
