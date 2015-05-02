
package iavanish.collegepal.Start;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.plus.People;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.plus.model.people.Person;
import com.google.common.collect.Iterables;

import java.io.InputStream;
import java.util.Collection;

import iavanish.collegepal.R;
import retrofit.RestAdapter;

/*
 *
 * Created by Himanshu on 3/29/2015.
 * SignUp using Google Account
 *
*/

public class SignUp extends FragmentActivity implements
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener,
        ResultCallback<People.LoadPeopleResult>, View.OnClickListener {

    private static final String TAG = "collegePal";

    private static final int STATE_DEFAULT = 0;
    private static final int STATE_SIGN_IN = 1;
    private static final int STATE_IN_PROGRESS = 2;

    private static final int RC_SIGN_IN = 0;

    private static final int DIALOG_PLAY_SERVICES_ERROR = 0;

    private static final String SAVED_PROGRESS = "sign_in_progress";

    private static final int PROFILE_PIC_SIZE = 400;

    private GoogleApiClient mGoogleApiClient;
    private int mSignInProgress;

    private PendingIntent mSignInIntent;


    private int mSignInError;
    private boolean mSignInClicked=false;

    private SignInButton mSignInButton;
    private ImageView imageProfilePic;
    private Button mSignOutButton;
    private Button mRegisterButton;
    private TextView mStatus, mEmail;

    private final String URL = "http://192.168.55.74:8080";
    private UserClientApi userService = new RestAdapter.Builder()
            .setEndpoint(URL).setLogLevel(RestAdapter.LogLevel.FULL).build()
            .create(UserClientApi.class);

    Collection<User> user;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findAllViewsId();

        mSignInButton.setOnClickListener(this);
        mSignOutButton.setOnClickListener(this);
        mRegisterButton.setOnClickListener(this);


        if (savedInstanceState != null) {
            mSignInProgress = savedInstanceState
                    .getInt(SAVED_PROGRESS, STATE_DEFAULT);
        }

        mGoogleApiClient = buildGoogleApiClient();
    }
    private void findAllViewsId() {
        mSignInButton = (SignInButton) findViewById(R.id.sign_in_button);
        mSignOutButton = (Button) findViewById(R.id.sign_out_button);
        mRegisterButton = (Button) findViewById(R.id.register_button);
        mStatus = (TextView) findViewById(R.id.sign_in_status);
        mEmail = (TextView) findViewById(R.id.circles_title);
        imageProfilePic = (ImageView) findViewById(R.id.image_profilepic);
    }
    private GoogleApiClient buildGoogleApiClient() {

        return new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(Plus.API, Plus.PlusOptions.builder().build())
                .addScope(Plus.SCOPE_PLUS_LOGIN)
                .build();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(SAVED_PROGRESS, mSignInProgress);
    }

    @Override
    public void onClick(View v) {
        if (!mGoogleApiClient.isConnecting()) {

            switch (v.getId()) {
                case R.id.sign_in_button:
                    mStatus.setText(R.string.status_signing_in);
                    resolveSignInError();
                    break;
                case R.id.sign_out_button:

                    Plus.AccountApi.clearDefaultAccount(mGoogleApiClient);
                    mGoogleApiClient.disconnect();
                    mGoogleApiClient.connect();
                    break;
                case R.id.register_button:

                    Person currentUser = Plus.PeopleApi.getCurrentPerson(mGoogleApiClient);
                    String email = Plus.AccountApi.getAccountName(mGoogleApiClient);
                    String userName = currentUser.getDisplayName();
                    Bundle b = new Bundle();
                    b.putString("Email", email);
                    b.putString("Name", userName);
                    Intent intent = new Intent(v.getContext(), CreateProfile.class);
                    intent.putExtras(b);
                    startActivity(intent);

                    break;
            }
        }
    }

    @Override
    public void onConnected(Bundle connectionHint) {
        // Reaching onConnected means we consider the user signed in.
        Log.i(TAG, "onConnected");

        try {

            updateUI(true);
            // Update the user interface to reflect that the user is signed in.

            mSignInButton.setEnabled(false);
            mSignOutButton.setEnabled(true);
            mRegisterButton.setEnabled(true);

            // Retrieve some profile information to personalize our app for the user.
            Person currentUser = Plus.PeopleApi.getCurrentPerson(mGoogleApiClient);

            mStatus.setText(String.format(
                    getResources().getString(R.string.signed_in_as),
                    currentUser.getDisplayName()));
            mEmail.setText("Email: "+Plus.AccountApi.getAccountName(mGoogleApiClient));

            String personPhotoUrl = currentUser.getImage().getUrl();
            personPhotoUrl = personPhotoUrl.substring(0,
                    personPhotoUrl.length() - 2)
                    + PROFILE_PIC_SIZE;
            new LoadProfileImage(imageProfilePic).execute(personPhotoUrl);
            // Indicate that the sign in process is complete.
            mSignInProgress = STATE_DEFAULT;
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }


    @Override
    public void onConnectionFailed(ConnectionResult result) {
        Log.i(TAG, "onConnectionFailed: ConnectionResult.getErrorCode() = "
                + result.getErrorCode());

        if (result.getErrorCode() == ConnectionResult.API_UNAVAILABLE) {
        } else if (mSignInProgress != STATE_IN_PROGRESS) {
            mSignInIntent = result.getResolution();
            mSignInError = result.getErrorCode();

            if (mSignInProgress == STATE_SIGN_IN) {
                resolveSignInError();
            }
        }

        onSignedOut();
    }
    /**
     * Updating the UI, showing/hiding buttons and profile layout
     * */
    private void updateUI(boolean isSignedIn) {
        if (isSignedIn) {
            mSignInButton.setVisibility(View.GONE);
            mSignOutButton.setVisibility(View.VISIBLE);
            mRegisterButton.setVisibility(View.VISIBLE);
            imageProfilePic.setVisibility(View.VISIBLE);
        } else {
            mSignInButton.setVisibility(View.VISIBLE);
            mSignOutButton.setVisibility(View.GONE);
            mRegisterButton.setVisibility(View.GONE);
            imageProfilePic.setVisibility(View.GONE);
        }
    }


    private void resolveSignInError() {
        if (mSignInIntent != null) {
            try {
                mSignInProgress = STATE_IN_PROGRESS;
                startIntentSenderForResult(mSignInIntent.getIntentSender(),
                        RC_SIGN_IN, null, 0, 0, 0);
            } catch (IntentSender.SendIntentException e) {
                Log.i(TAG, "Sign in intent could not be sent: "
                        + e.getLocalizedMessage());

                mSignInProgress = STATE_SIGN_IN;
                mGoogleApiClient.connect();
            }
        } else {
            showDialog(DIALOG_PLAY_SERVICES_ERROR);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        switch (requestCode) {
            case RC_SIGN_IN:
                if (resultCode == RESULT_OK) {
                    mSignInProgress = STATE_SIGN_IN;
                } else {

                    mSignInProgress = STATE_DEFAULT;
                }

                if (!mGoogleApiClient.isConnecting()) {

                    mGoogleApiClient.connect();
                }
                break;
        }
    }

    @Override
    public void onResult(People.LoadPeopleResult peopleData) {

    }

    private void onSignedOut() {
        // Update the UI to reflect that the user is signed out.
        mSignInButton.setEnabled(true);
        mSignOutButton.setEnabled(false);
        // mRevokeButton.setEnabled(false);
        mRegisterButton.setEnabled(false);
        updateUI(false);
        mStatus.setText(R.string.status_signed_out);
        mEmail.setText("");

    }

    @Override
    public void onConnectionSuspended(int cause) {

        mGoogleApiClient.connect();
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch(id) {
            case DIALOG_PLAY_SERVICES_ERROR:
                if (GooglePlayServicesUtil.isUserRecoverableError(mSignInError)) {
                    return GooglePlayServicesUtil.getErrorDialog(
                            mSignInError,
                            this,
                            RC_SIGN_IN,
                            new DialogInterface.OnCancelListener() {
                                @Override
                                public void onCancel(DialogInterface dialog) {
                                    Log.e(TAG, "Google Play services resolution cancelled");
                                    mSignInProgress = STATE_DEFAULT;
                                    mStatus.setText(R.string.status_signed_out);
                                }
                            });
                } else {
                    return new AlertDialog.Builder(this)
                            .setMessage(R.string.play_services_error)
                            .setPositiveButton(R.string.close,
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Log.e(TAG, "Google Play services error could not be "
                                                    + "resolved: " + mSignInError);
                                            mSignInProgress = STATE_DEFAULT;
                                            mStatus.setText(R.string.status_signed_out);
                                        }
                                    }).create();
                }
            default:
                return super.onCreateDialog(id);
        }
    }
    private class UserTask extends AsyncTask<String, Void, Boolean>
    {

        @Override
        protected Boolean doInBackground(String... params) {

            try {
                String email = Plus.AccountApi.getAccountName(mGoogleApiClient);
                user = userService.findByEmailIdIgnoreCase(email);
                User temp = null;
                Log.e(TAG, "user" + user);
                Log.e(TAG, "Email" + email);
                if (!user.isEmpty()) {

                    temp = Iterables.getFirst(user, null);
                    Log.e(TAG, "abc" + temp);
                    return true;
                } else {
                    Log.e(TAG, "xyz" + temp);
                    return false;
                }
            }
            catch(Exception e){
                e.printStackTrace();
            }
            return user.isEmpty();
        }
        @Override
        protected void onPostExecute(Boolean b)
        {
            if(b){
                Toast.makeText(getApplicationContext(), "You are already registered", Toast.LENGTH_LONG).show();
                mRegisterButton.setEnabled(false);
                Person currentUser = Plus.PeopleApi.getCurrentPerson(mGoogleApiClient);
                String email = Plus.AccountApi.getAccountName(mGoogleApiClient);
                String userName = currentUser.getDisplayName();
                Bundle bundle = new Bundle();
                bundle.putString("Email", email);
                bundle.putString("Name", userName);
                Intent intent = new Intent(getApplicationContext(), StudentDashboard.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
            else{
                Toast.makeText(getApplicationContext(), "You are not registered, Need to register", Toast.LENGTH_LONG).show();
            }

        }
    }

    private class LoadProfileImage extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public LoadProfileImage(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
            UserTask tsk = new UserTask();
            tsk.execute();

        }
    }
}
