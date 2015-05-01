package iavanish.collegepal.Deadlines;

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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import iavanish.collegepal.R;
import iavanish.collegepal.Start.MultiSelectionSpinner;
import iavanish.collegepal.Start.NothingSelectedSpinnerAdapter;
import retrofit.RestAdapter;

public class AddDeadline extends Activity implements AdapterView.OnItemSelectedListener{

    private final String URL = "http://192.168.55.74:8080";
    private DeadlineClientApi deadlineService = new RestAdapter.Builder()
            .setEndpoint(URL).setLogLevel(RestAdapter.LogLevel.FULL).build()
            .create(DeadlineClientApi.class);

    Button button_addDeadline, button_clear;
    private EditText txtDeadlineId,txtCourseId,txtEmailId, txtDate, txtDeadlineDetail;
    private Spinner spinnerDeadlineType;
    String  _deadlineId,_emailId, _courseID, _deadlineDate,_deadlineType, _deadlineDetails;
    Collection<Deadline> deadline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_deadline);
        findAllViewsId();
        Intent in = getIntent();
        Bundle b = in.getExtras();
        if(b!=null) {
            _courseID = b.getString("CourseId");
            _emailId = b.getString("Email");
        }

        spinnerDeadlineType.setOnItemSelectedListener(this);
        List<String> deadlineType = new ArrayList<String>();
        deadlineType.add("Public");
        deadlineType.add("Private");

        ArrayAdapter<String> dataAdapter_deadlineType= new ArrayAdapter <String> (this, android.R.layout.simple_spinner_item, deadlineType);


        dataAdapter_deadlineType.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);


        spinnerDeadlineType.setPrompt("Select Deadline Type");
        spinnerDeadlineType.setAdapter(
                new NothingSelectedSpinnerAdapter(
                        dataAdapter_deadlineType,
                        R.layout.spinner_row_nothing_selected,
                        this));







    }

    private void findAllViewsId() {
        txtDeadlineId = (EditText) findViewById(R.id.editText_deadlineID);
        txtCourseId = (EditText) findViewById(R.id.editText_courseID);
        txtEmailId = (EditText) findViewById(R.id.editText_emailID);
        txtDate = (EditText) findViewById(R.id.editText_date);
        txtDeadlineDetail = (EditText) findViewById(R.id.editText_deadlineDetails);
        spinnerDeadlineType = (Spinner) findViewById(R.id.spinner_deadlineType);
        button_addDeadline = (Button) findViewById(R.id.button_addDeadline);
        button_clear = (Button) findViewById(R.id.button_clear);
    }

    public void getData() {
        _deadlineId = txtDeadlineId.getText().toString();
        _courseID = txtCourseId.getText().toString();
        _emailId =txtEmailId.getText().toString();
        _deadlineDate=txtDate.getText().toString();
        _deadlineDetails=txtDeadlineDetail.getText().toString();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


        switch (parent.getId())
        {
            case R.id.spinner_deadlineType:
                if(parent.getItemAtPosition(position)!=null) {
                    _deadlineType=parent.getItemAtPosition(position).toString();

                }
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // TODO Auto-generated method stub

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_deadline, menu);
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
