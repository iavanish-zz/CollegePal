package iavanish.collegepal.Start;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import iavanish.collegepal.R;
import retrofit.RestAdapter;

/**
 * Created by Himanshu on 3/29/2015.
 */
public class CreateProfile extends Activity implements OnItemSelectedListener {
    private final String URL = "http://192.168.55.110:8080";
    private UserClientApi userService = new RestAdapter.Builder()
            .setEndpoint(URL).setLogLevel(RestAdapter.LogLevel.FULL).build()
            .create(UserClientApi.class);

    private Button mRegisterButton,mClearButton;
    private EditText txtEmail,txtName,txtGender,txtDOB;
    private Spinner spinnerCourse,spinnerBranch,spinnerInstitution;
    private RatingBar rating;
    MultiSelectionSpinner spinnerSkills;
    User  user;

    String _emailID,_name,_course,_branch,_institution,_skills,_gender,_dob;
    float _rating=0.0f;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        txtEmail = (EditText) findViewById(R.id.editText_emailID);
        txtName = (EditText) findViewById(R.id.editText_name);
        txtGender = (EditText) findViewById(R.id.editText_gender);
        txtDOB =(EditText) findViewById(R.id.editText_dob);
        spinnerCourse = (Spinner) findViewById(R.id.spinner_course);
        spinnerBranch = (Spinner) findViewById(R.id.spinner_branch);
        spinnerInstitution = (Spinner) findViewById(R.id.spinner_institution);
        spinnerSkills = (MultiSelectionSpinner) findViewById(R.id.spinner_skills);
        rating=(RatingBar)findViewById(R.id.ratingBar);
        /*rating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {

                Toast.makeText(getApplicationContext(), "Rating: " + String.valueOf(rating), Toast.LENGTH_LONG).show();
            }

        });*/
        rating.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
        rating.setFocusable(false);
        Intent in = getIntent();

        Bundle b = in.getExtras();
        if(b!=null) {
            _emailID = b.getString("Email");
            _name = b.getString("Name");

        }

        txtEmail.setText(_emailID);
        txtName.setText(_name);

        spinnerCourse.setOnItemSelectedListener(this);

        // Spinner Drop down elements
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

        mRegisterButton = (Button) findViewById(R.id.button_register);
        mClearButton = (Button) findViewById(R.id.button_clear);
        mClearButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                txtEmail.setText("");
                txtName.setText("");
                txtGender.setText("");
                txtDOB.setText("");

            }
        });
        mRegisterButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                getData();
                if (_emailID.length() == 0 || _name.length() == 0 || _course.length() == 0 || _branch.length() == 0 || _institution.length() == 0 || _skills.length() == 0 || _gender.length() == 0 || _dob.length() == 0) {
                    Toast.makeText(getApplicationContext(), "Complete the form correctly", Toast.LENGTH_SHORT).show();
                } else {

                    Bundle b = new Bundle();
                    b.putString("Email", _emailID);
                    b.putString("Name", _name);
                    b.putString("Course", _course);
                    b.putString("Branch", _branch);
                    b.putString("Institution", _institution);
                    b.putString("Skills", _skills);
                    b.putFloat("Rating", _rating);
                    b.putString("Gender", _gender);
                    b.putString("DOB", _dob);

                    Intent intent = new Intent(CreateProfile.this, DisplayProfile.class);
                    intent.putExtras(b);
                    startActivity(intent);
                }
            }
        });

    }

    public void getData() {
        _emailID = txtEmail.getText().toString();
        _name = txtName.getText().toString();
        _skills = spinnerSkills.getSelectedItemsAsString();
        _gender = txtGender.getText().toString();
        _dob = txtDOB.getText().toString();
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
        Boolean ok = userService.addUser(user);
        return ok;
    }
    @Override
    protected void onPostExecute(Boolean b)
    {
        if(b)
            Toast.makeText(getApplicationContext(),"Jai mata Di Done",Toast.LENGTH_LONG).show();
            System.out.print("registration Check");
    }
}

}
