package iavanish.collegepal.Courses;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
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
import java.util.Collection;
import java.util.List;

import iavanish.collegepal.R;
import iavanish.collegepal.Start.DisplayProfile;
import iavanish.collegepal.Start.MultiSelectionSpinner;
import iavanish.collegepal.Start.NothingSelectedSpinnerAdapter;
import retrofit.RestAdapter;

public class CreateCourse extends Activity implements AdapterView.OnItemSelectedListener {

    private final String URL = "http://192.168.55.74:8080";
    Collection<Course> searchName, searchPreRequisites, searchInstructors, searchAdmin, searchCourse;
    private CourseClientApi courseServiceSearch = new RestAdapter.Builder()
            .setEndpoint(URL).setLogLevel(RestAdapter.LogLevel.FULL).build()
            .create(CourseClientApi.class);
    private Button mRegisterCourseButton,mClearButton;
    private EditText txtCourseId,txtCourseName, txtAdmin, txtOverview, txtPostConditions;
    private Spinner spinnerInstitution;
    MultiSelectionSpinner spinnerPreRequisites, spinnerInstructors, spinnerCourseTA;
    private ArrayList<Course> mListCourse = new ArrayList<Course>();
    String _courseID,_courseName,_admin,_overview,_postCondition,_institution,_preRequisities,_instructors, _ta;
    Boolean register;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_course);

        findAllViewsId();
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
        courseTA_list.add("Rupali Jain");
        courseTA_list.add("Ruckika Banerjee");
        courseTA_list.add("Shubham Shrivastrava");
        courseTA_list.add("Sakshi Aggarwal");
        courseTA_list.add("Shishagnee Banerjee");
        courseTA_list.add("Veronika Sharma");
        spinnerCourseTA.setItems(courseTA_list);

        mClearButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                txtCourseId.setText("");
                txtCourseName.setText("");
                txtPostConditions.setText("");
                txtOverview.setText("");
                txtPostConditions.setText("");
                spinnerInstitution.setSelection(0);
                spinnerPreRequisites.setSelection(0);
                spinnerInstructors.setSelection(0);
                spinnerCourseTA.setSelection(0);

            }
        });
        mRegisterCourseButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                getData();
                if (_courseID.length() == 0 || _courseName.length() == 0 || _admin.length() == 0 || _overview.length() == 0 || _postCondition.length() == 0 || _institution.length() == 0 || _preRequisities.length() == 0 || _instructors.length() == 0 || _ta.length() == 0) {
                    Toast.makeText(getApplicationContext(), "Complete the form correctly", Toast.LENGTH_SHORT).show();
                } else {
                    RegisterCourseTask tsk = new RegisterCourseTask();
                    tsk.execute();
                }
            }
        });

    }
    private void findAllViewsId() {
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

    }
    public void getData() {
        _courseID = txtCourseId.getText().toString();
        _courseName = txtCourseName.getText().toString();
        _postCondition=txtPostConditions.getText().toString();
        _overview=txtOverview.getText().toString();

        _preRequisities = spinnerPreRequisites.getSelectedItemsAsString();
        _instructors = spinnerInstructors.getSelectedItemsAsString();
        _ta = spinnerCourseTA.getSelectedItemsAsString();
    }
    private class RegisterCourseTask extends AsyncTask<String, Void, Boolean> {

        @Override
        protected Boolean doInBackground(String... params) {
            searchCourse = courseServiceSearch.getCourseList();

            if (searchCourse.isEmpty()) {
                return false;
            } else {

                return true;
            }
        }

        @Override
        protected void onPostExecute(Boolean b) {
            if (b) {
                if (!searchCourse.isEmpty()) {
                    if (!mListCourse.containsAll(searchCourse))
                        mListCourse.addAll(searchCourse);

                }
                int position = 0;
                while (mListCourse.size()> position) {

                    Course currentCourse = mListCourse.get(position);
                    if(_courseID.equalsIgnoreCase(currentCourse.getCourseId()) ||  _courseName.equalsIgnoreCase(currentCourse.getCourseName())){
                        Toast.makeText(getApplicationContext(), "Course is already registered, try different one!!", Toast.LENGTH_LONG).show();
                        register=true;
                        break;
                    }
                    else
                        register=false;
                    position++ ;
                }
                if(!register) {
                    Bundle bundle = new Bundle();

                    bundle.putString("CourseID", _courseID);
                    bundle.putString("CourseName", _courseName);
                    bundle.putString("Admin", _admin);
                    bundle.putString("Overview", _overview);
                    bundle.putString("postCondition", _postCondition);
                    bundle.putString("Institution", _institution);
                    bundle.putString("preRequisites", _preRequisities);
                    bundle.putString("Instructors", _instructors);
                    bundle.putString("TA", _ta);
                    Intent intent = new Intent(CreateCourse.this, DisplayCourse.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), "Jai mata Di Done", Toast.LENGTH_LONG).show();
                }
            } else
                Toast.makeText(getApplicationContext(), "kuch to gadbad hai!", Toast.LENGTH_LONG).show();

            System.out.print("Search Profile");
        }
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
