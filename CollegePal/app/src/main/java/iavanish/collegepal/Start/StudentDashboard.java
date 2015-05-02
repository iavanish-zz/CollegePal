package iavanish.collegepal.Start;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.plus.People;
import com.google.android.gms.plus.Plus;

import iavanish.collegepal.Calendar.CalendarActivity;
import iavanish.collegepal.Courses.CourseActivity;
import iavanish.collegepal.R;

public class StudentDashboard extends Activity implements
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener,
        ResultCallback<People.LoadPeopleResult>{

    private Button mCourseButton,mCalenderButton,mNotificationButton, mEditProfileButton, mSignOutButton;
    String _admin,_userName;

    GoogleApiClient mGoogleApiClient;
    boolean mSignInClicked;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_dashboard);

        findAllViewsId();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this).addApi(Plus.API)
                .addScope(Plus.SCOPE_PLUS_LOGIN).build();



        Intent in = getIntent();

        Bundle b = in.getExtras();
        if(b!=null) {
            _admin = b.getString("Email");
            _userName = b.getString("Name");
        }
        mCourseButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Bundle b = new Bundle();
                b.putString("Email", _admin);
                Intent intent = new Intent(v.getContext(), CourseActivity.class);
                intent.putExtras(b);
                startActivity(intent);
            }
        });

        mCalenderButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                //Calender Activity
                Bundle b = new Bundle();
                b.putString("Email", _admin);
                Intent intent = new Intent(v.getContext(), CalendarActivity.class);
                intent.putExtras(b);
                startActivity(intent);

            }
        });

        mNotificationButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                //Notification Activity

            }
        });

        mEditProfileButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Bundle b = new Bundle();
                b.putString("Email", _admin);
                b.putString("Name",_userName);
                Intent intent = new Intent(v.getContext(), EditProfile.class);
                intent.putExtras(b);
                startActivity(intent);
            }
        });
        mSignOutButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (mGoogleApiClient.isConnected()) {

                    Plus.AccountApi.clearDefaultAccount(mGoogleApiClient);
                    mGoogleApiClient.clearDefaultAccountAndReconnect();
                    mGoogleApiClient.disconnect();
                    mGoogleApiClient.connect();
                    finish();

                /*Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);*/
                }
            }
        });
    }

    private void findAllViewsId() {
        mCourseButton = (Button) findViewById(R.id.button_viewCourse);
        mCalenderButton = (Button) findViewById(R.id.button_viewCalender);
        mNotificationButton = (Button) findViewById(R.id.button_viewNotifications);
        mEditProfileButton = (Button) findViewById(R.id.button_editProfile);
        mSignOutButton = (Button) findViewById(R.id.button_signOut);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_student_dashboard, menu);
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

    @Override
    public void onConnected(Bundle bundle) {
        // TODO Auto-generated method stub
        mSignInClicked = false;

        // updateUI(true);
        Plus.PeopleApi.loadVisible(mGoogleApiClient, null).setResultCallback(
                this);
    }

    @Override
    public void onConnectionSuspended(int i) {
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    protected void onStop() {

        super.onStop();
        if (mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
    }

    @Override
    public void onResult(People.LoadPeopleResult loadPeopleResult) {

    }
}
