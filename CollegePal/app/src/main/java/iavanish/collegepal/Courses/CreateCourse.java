package iavanish.collegepal.Courses;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import iavanish.collegepal.R;
import iavanish.collegepal.Start.DisplayProfile;
import iavanish.collegepal.Start.MultiSelectionSpinner;
import iavanish.collegepal.Start.NothingSelectedSpinnerAdapter;

public class CreateCourse extends Activity implements AdapterView.OnItemSelectedListener {

    private Button mRegisterCourseButton,mClearButton;
    private EditText txtCourseId,txtCourseName, txtAdmin, txtOverview, txtPostConditions;
    private Spinner spinnerInstitution;
    MultiSelectionSpinner spinnerPreRequisites, spinnerInstructors, spinnerCourseTA;

    String _courseID,_courseName,_admin,_overview,_postCondition,_institution,_preRequisities,_instructors, _ta;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_course);

        txtCourseId = (EditText) findViewById(R.id.editText_courseId);
        txtCourseName = (EditText) findViewById(R.id.editText_courseName);
        txtAdmin = (EditText) findViewById(R.id.editText_admin);
        txtOverview = (EditText) findViewById(R.id.editText_overview);
        txtPostConditions = (EditText) findViewById(R.id.editText_postCondition);
        spinnerInstitution = (Spinner) findViewById(R.id.spinner_institution);
        spinnerPreRequisites = (MultiSelectionSpinner) findViewById(R.id.spinner_preRequisites);
        spinnerInstructors = (MultiSelectionSpinner) findViewById(R.id.spinner_instructors);
        spinnerCourseTA = (MultiSelectionSpinner) findViewById(R.id.spinner_ta);
        mRegisterCourseButton = (Button) findViewById(R.id.button_registerCourse);
        mClearButton = (Button) findViewById(R.id.button_clear);

        Intent in = getIntent();

        Bundle b = in.getExtras();
        if(b!=null) {
            _admin = b.getString("Email");
        }

        txtAdmin.setText(_admin);

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

        final List <String> preRequisites_list = new ArrayList <String>();
        preRequisites_list.add("Select Pre-Requisites");
        preRequisites_list.add("Practice of Programming");
        preRequisites_list.add("C Programming");
        preRequisites_list.add("C++ Programming");
        preRequisites_list.add("JAVA Programming");
        preRequisites_list.add("Android Programming");
        preRequisites_list.add("Data Structure and Algorithm");
        preRequisites_list.add("Machine Learning");
        preRequisites_list.add("Discrete Mathematics");
        preRequisites_list.add("Cryptography");
        preRequisites_list.add("Graph Theory");
        preRequisites_list.add("Computer Networks");
        preRequisites_list.add("Operating Systems");
        preRequisites_list.add("Verilog Programming");
        preRequisites_list.add("Python Programming");
        spinnerPreRequisites.setItems(preRequisites_list);

        final List <String> instructor_list = new ArrayList <String>();
        instructor_list.add("Alexander Fell");
        instructor_list.add("Apala Guha");
        instructor_list.add("Astrid Kiehn");
        instructor_list.add("Avanish Kumar Singh");
        instructor_list.add("Chetan Arora");
        instructor_list.add("Deependra Raghuvanshi");
        instructor_list.add("Himanshu Varshney");
        instructor_list.add("Hrishikesh B. Acharya");
        instructor_list.add("Mayank Vatsa");
        instructor_list.add("Pankaj Jalote");
        instructor_list.add("Ponnurangam Kumaraguru");
        instructor_list.add("Pushpendra Singh");
        instructor_list.add("Rahul Purandare");
        instructor_list.add("Richa Singh");
        instructor_list.add("Somitra Kr. Sanadhya");
        instructor_list.add("Sambuddho Chakravarty");
        instructor_list.add("Sandip Aine");
        instructor_list.add("Sanjit Krishnan Kaul");
        instructor_list.add("Vikram Goyal");
        instructor_list.add("Vinayak Naik");
        spinnerInstructors.setItems(instructor_list);

        final List <String> courseTA_list = new ArrayList <String>();
        courseTA_list.add("Ashish Bandil");
        courseTA_list.add("Aanchal Singh");
        courseTA_list.add("Anindya Shrivastava");
        courseTA_list.add("Anshika Agarwal");
        courseTA_list.add("Avanish Singh");
        courseTA_list.add("Ajit Pratap Singh");
        courseTA_list.add("Deependra Raghuvanshi");
        courseTA_list.add("Garima Mahajan");
        courseTA_list.add("Himanshu Varshney");
        courseTA_list.add("Joy Aneja");
        courseTA_list.add("K. Raghunath Reddy");
        courseTA_list.add("Munawar Hasan");
        courseTA_list.add("Neha Arora");
        courseTA_list.add("Niharika Gupta");
        courseTA_list.add("Priyanka Balotra");
        courseTA_list.add("Shubham Shrivastrava");
        courseTA_list.add("Sakshi Aggarwal");
        courseTA_list.add("Veronika Sharma");
        spinnerCourseTA.setItems(courseTA_list);

        mClearButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                txtCourseId.setText("");
                txtCourseName.setText("");
                txtAdmin.setText("");
                txtPostConditions.setText("");
                txtOverview.setText("");
            }
        });
        mRegisterCourseButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                getData();
                if (_courseID.length() == 0 || _courseName.length() == 0 || _admin.length() == 0 || _overview.length() == 0 || _postCondition.length() == 0 || _institution.length() == 0 || _preRequisities.length() == 0 || _instructors.length() == 0 || _ta.length() == 0) {
                    Toast.makeText(getApplicationContext(), "Complete the form correctly", Toast.LENGTH_SHORT).show();
                } else {

                    Bundle b = new Bundle();

                    b.putString("CourseID", _courseID);
                    b.putString("CourseName", _courseName);
                    b.putString("Admin", _admin);
                    b.putString("Overview", _overview);
                    b.putString("postCondition", _postCondition);
                    b.putString("Institution", _institution);
                    b.putString("preRequisites", _preRequisities);
                    b.putString("Instructors", _instructors);
                    b.putString("TA", _ta);

                    Intent intent = new Intent(CreateCourse.this, DisplayCourse.class);
                    intent.putExtras(b);
                    startActivity(intent);
                }
            }
        });

    }

    public void getData() {
        _courseID = txtCourseId.getText().toString();
        _courseName = txtCourseName.getText().toString();
        _admin=txtAdmin.getText().toString();
        _postCondition=txtPostConditions.getText().toString();
        _overview=txtOverview.getText().toString();

        _preRequisities = spinnerPreRequisites.getSelectedItemsAsString();
        _instructors = spinnerInstructors.getSelectedItemsAsString();
        _ta = spinnerCourseTA.getSelectedItemsAsString();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


        switch (parent.getId())
        {
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

}
