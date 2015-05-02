package iavanish.collegepal.Courses;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.common.collect.Iterables;

import java.util.ArrayList;
import java.util.Collection;

import iavanish.collegepal.R;
import iavanish.collegepal.Start.User;
import iavanish.collegepal.Start.UserClientApi;
import retrofit.RestAdapter;

public class CourseActivity extends Activity {

    private final String URL = "http://192.168.55.74:8080";
    String str_search;
    Collection<Course> searchName, searchPreRequisites, searchInstructors, searchAdmin, searchCourse;
    private CourseClientApi courseServiceSearch = new RestAdapter.Builder()
            .setEndpoint(URL).setLogLevel(RestAdapter.LogLevel.FULL).build()
            .create(CourseClientApi.class);
    private UserClientApi userService = new RestAdapter.Builder()
            .setEndpoint(URL).setLogLevel(RestAdapter.LogLevel.FULL).build()
            .create(UserClientApi.class);
    private Button mCourseDashboardButton,mEnrollCourseButton,mRegisterCourseButton,mUpdateCourseButton,mOwnCourseButton,mAllCourseButton,mSelectCourseButton;
    private EditText txtPreferences,txtCourseId;
    private TextView txtPreferencesResult,txtOwnCourseResult,txtAllCourseResult;
    private String _preferences, _admin, _courseId;

    private ArrayList<Course> mListCourse = new ArrayList<Course>();
    private ArrayList<String> courseList = new ArrayList <String>();
    private ArrayList<String> tempCourseListOffering = new ArrayList<String>();
    private ArrayList<String> tempCourseListEnrolled = new ArrayList<String>();
    private ArrayList<String> courseListEnrolled;
    private ArrayList<String> courseListOffering;
    Collection<User> user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);
        findAllViewsId();


        Intent in = getIntent();

        Bundle b = in.getExtras();
        if(b!=null) {
            _admin = b.getString("Email");
        }


        mCourseDashboardButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                      /*courseListEnrolled.clear();
                      courseListOffering.clear();*/
                      CourseEnrollmentTask tsk = new CourseEnrollmentTask();
                      tsk.execute();


                //Course Dashboard Class
            }
        });


        mRegisterCourseButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Bundle b = new Bundle();
                b.putString("Email", _admin);
                Intent intent = new Intent(v.getContext(), CreateCourse.class);
                intent.putExtras(b);
                startActivity(intent);
            }
        });

        mUpdateCourseButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                mListCourse.clear();
                courseList.clear();
                UpdateCourseTask tsk = new UpdateCourseTask();
                tsk.execute();

                /*CourseCheckTask tsk = new CourseCheckTask();
                tsk.execute();*/

            }
        });
        mOwnCourseButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                    mListCourse.clear();
                    OwnCourseTask tsk = new OwnCourseTask();
                    tsk.execute();

               //Show Own Course
            }
        });
        mAllCourseButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mListCourse.clear();
                AllCourseTask tsk = new AllCourseTask();
                tsk.execute();

                //Show All Course
            }
        });
        mSelectCourseButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(!txtPreferences.getText().toString().isEmpty()) {
                    mListCourse.clear();
                    SearchTask tsk = new SearchTask();
                    tsk.execute();
                }
            }
        });
        mEnrollCourseButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mListCourse.clear();
                courseList.clear();
                PopulateCourseTask tsk = new  PopulateCourseTask();
                tsk.execute();

            }
        });
    }
    private void findAllViewsId() {
        mCourseDashboardButton =(Button) findViewById(R.id.button_courseDashboard);
        mRegisterCourseButton = (Button) findViewById(R.id.button_newCourse);
        mUpdateCourseButton =(Button) findViewById(R.id.button_updateCourse);
        mOwnCourseButton =(Button) findViewById(R.id.button_ownCourses);
        mAllCourseButton =(Button) findViewById(R.id.button_allCourses);
        mSelectCourseButton =(Button) findViewById(R.id.button_selectCourse);
        mEnrollCourseButton =(Button) findViewById(R.id.button_enrollCourse);
        //txtCourseId =(EditText) findViewById(R.id.editText_updateCourse);
        txtPreferences =(EditText) findViewById(R.id.editText_selectCourse);
        txtPreferencesResult = (TextView) findViewById(R.id.text_selectCourses);
        txtOwnCourseResult =(TextView)findViewById(R.id.text_ownCourses);
        txtAllCourseResult =(TextView)findViewById(R.id.text_allCourses);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_course, menu);
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
    private class SearchTask extends AsyncTask<String, Void, Boolean> {

        @Override
        protected Boolean doInBackground(String... params) {
            searchPreRequisites = courseServiceSearch.findByPreRequisitesContainingIgnoreCase(txtPreferences.getText().toString());
            searchName = courseServiceSearch.findByCourseNameContainingIgnoreCase(txtPreferences.getText().toString());
            searchInstructors = courseServiceSearch.findByInstructorsContainingIgnoreCase(txtPreferences.getText().toString());
            searchCourse=courseServiceSearch.findByCourseIdIgnoreCase(txtPreferences.getText().toString());

            if (searchPreRequisites.isEmpty() && searchName.isEmpty() && searchInstructors.isEmpty() && searchCourse.isEmpty()) {
                return false;
            } else {

                return true;
            }
        }

        @Override
        protected void onPostExecute(Boolean b) {
            if (b) {
                if (!searchPreRequisites.isEmpty()) {
                    if (!mListCourse.containsAll(searchPreRequisites))
                        mListCourse.addAll(searchPreRequisites);


                }
                if(!searchCourse.isEmpty()){
                    if(!mListCourse.containsAll(searchCourse))
                        mListCourse.addAll(searchCourse);
                }


                if (!searchName.isEmpty()) {

                    if (!mListCourse.containsAll(searchName))
                        mListCourse.addAll(searchName);
                }

                if (!searchInstructors.isEmpty()) {
                    if (!mListCourse.containsAll(searchInstructors))
                        mListCourse.addAll(searchInstructors);

                }
                int position = 0;
                while (mListCourse.size()> position) {

                    Course currentCourse = mListCourse.get(position);
                    txtPreferencesResult.setText(txtPreferencesResult.getText() + "\n" + currentCourse.getCourseId() + "," + currentCourse.getCourseName());
                    position++ ;
                }

                Toast.makeText(getApplicationContext(), "Jai mata Di Done", Toast.LENGTH_LONG).show();
            } else
                Toast.makeText(getApplicationContext(), "No records found!", Toast.LENGTH_LONG).show();

            System.out.print("Search Profile");
        }

    }
    private class OwnCourseTask extends AsyncTask<String, Void, Boolean> {

        @Override
        protected Boolean doInBackground(String... params) {
            searchAdmin = courseServiceSearch.findByAdminContainingIgnoreCase(_admin);

            if (searchAdmin.isEmpty()) {
                return false;
            } else {

                return true;
            }
        }

        @Override
        protected void onPostExecute(Boolean b) {
            if (b) {
                if (!searchAdmin.isEmpty()) {
                    if (!mListCourse.containsAll(searchAdmin))
                        mListCourse.addAll(searchAdmin);

               /* for(Course iter:searchPreRequisites){
                    iter.getCourseId();
                    txtPreferencesResult.setText(txtPreferencesResult.getText());
                }*/
                }
                int position = 0;
                while (mListCourse.size()> position) {

                    Course currentCourse = mListCourse.get(position);
                    txtOwnCourseResult.setText(txtOwnCourseResult.getText() + "\n" + currentCourse.getCourseId() + "," + currentCourse.getCourseName());
                    position++ ;
                }

                Toast.makeText(getApplicationContext(), "Jai mata Di Done", Toast.LENGTH_LONG).show();
            } else
                Toast.makeText(getApplicationContext(), "No records found!", Toast.LENGTH_LONG).show();

            System.out.print("Search Profile");
        }
    }
    private class AllCourseTask extends AsyncTask<String, Void, Boolean> {

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
                    txtAllCourseResult.setText( txtAllCourseResult.getText() + "\n" + currentCourse.getCourseId() + "," + currentCourse.getCourseName());
                    position++ ;
                }


                Toast.makeText(getApplicationContext(), "Jai mata Di Done", Toast.LENGTH_LONG).show();
            } else
                Toast.makeText(getApplicationContext(), "No records found!", Toast.LENGTH_LONG).show();

            System.out.print("Search Profile");
        }
    }
    private class CourseCheckTask extends AsyncTask<String, Void, Boolean> {

        @Override
        protected Boolean doInBackground(String... params) {
            searchAdmin = courseServiceSearch.findByAdminContainingIgnoreCase(_admin);

            if (searchAdmin.isEmpty()) {
                return false;
            } else {

                return true;
            }
        }

        @Override
        protected void onPostExecute(Boolean b) {
            if (b) {
                if (!searchAdmin.isEmpty()) {
                    if (!mListCourse.containsAll(searchAdmin))
                        mListCourse.addAll(searchAdmin);

               /* for(Course iter:searchPreRequisites){
                    iter.getCourseId();
                    txtPreferencesResult.setText(txtPreferencesResult.getText());
                }*/
                }
                int position = 0;
                Boolean flag=false;
                _courseId=txtCourseId.getText().toString();
                while (mListCourse.size() > position) {

                    Course currentCourse = mListCourse.get(position);
                    if(_courseId.equalsIgnoreCase(currentCourse.getCourseId()))
                        flag=true;
                    else
                        flag=false;
                    position++;
                }
                if(flag){
                    Bundle bundle = new Bundle();
                    bundle.putString("Email", _admin);
                    bundle.putString("CourseID", _courseId);
                    Intent intent = new Intent(getApplicationContext(), UpdateCourse.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), "Jai mata Di Done", Toast.LENGTH_LONG).show();
                }
                else
                    Toast.makeText(getApplicationContext(), "No such course found!", Toast.LENGTH_LONG).show();
            } else
                Toast.makeText(getApplicationContext(), "Either you are not admin or No such course found!", Toast.LENGTH_LONG).show();

            System.out.print("Search Profile");
        }
    }
    private class PopulateCourseTask extends AsyncTask<String, Void, Boolean> {

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

                    courseList.add(currentCourse.getCourseName());
                    System.out.println("Course name" + currentCourse.getCourseName());

                    position++ ;
                }

                Toast.makeText(getApplicationContext(), "Jai mata Di Done", Toast.LENGTH_LONG).show();
                Bundle bundle = new Bundle();
                bundle.putString("Email", _admin);

                Intent intent = new Intent(getApplicationContext(), EnrollCourse.class);
                intent.putStringArrayListExtra("course_list", courseList);
                intent.putExtras(bundle);

                startActivity(intent);
            } else
                Toast.makeText(getApplicationContext(), "No records found!", Toast.LENGTH_LONG).show();

            System.out.print("Search Profile");
        }
    }
    private class UpdateCourseTask extends AsyncTask<String, Void, Boolean> {

        @Override
        protected Boolean doInBackground(String... params) {
            searchCourse = courseServiceSearch.findByAdminContainingIgnoreCase(_admin);

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

                    courseList.add(currentCourse.getCourseName());
                    System.out.println("Course name" + currentCourse.getCourseName());

                    position++ ;
                }

                Toast.makeText(getApplicationContext(), "Jai mata Di Done", Toast.LENGTH_LONG).show();
                Bundle bundle = new Bundle();
                bundle.putString("Email", _admin);
                bundle.putString("CourseID", _courseId);
                Intent intent = new Intent(getApplicationContext(), UpdateOwnCourse.class);
                intent.putStringArrayListExtra("course_list", courseList);
                intent.putExtras(bundle);

                startActivity(intent);
            } else
                Toast.makeText(getApplicationContext(), "No records found!", Toast.LENGTH_LONG).show();

            System.out.print("Search Profile");
        }
    }
    private class CourseEnrollmentTask extends AsyncTask<String, Void, Boolean>
    {

        @Override
        protected Boolean doInBackground(String... params) {
            Boolean ok;
            User tempUser;
            user = userService.findByEmailIdIgnoreCase(_admin);
            if (!user.isEmpty()) {

                tempUser = Iterables.getFirst(user, null);
                courseListEnrolled = new ArrayList <String>(tempUser.getCourseEnrolled());

                int index = 1 ;
                while (courseListEnrolled.size()> index) {
                            tempCourseListEnrolled.add(courseListEnrolled.get(index));
                            index++ ;
                }

                courseListOffering = new ArrayList <String>(tempUser.getCoursesOffering());

                 int position = 1 ;
                 while (courseListOffering.size()> position) {
                        tempCourseListOffering.add(courseListOffering.get(position));
                         position++ ;
                 }
               /* courseListEnrolled.addAll(tempUser.getCourseEnrolled());
               courseListOffering.addAll(tempUser.getCoursesOffering());*/

                return true;
            }
            else
                return false;
        }
        @Override
        protected void onPostExecute(Boolean b)
        {

            //if(b)

            Toast.makeText(getApplicationContext(), "Jai mata Di Done", Toast.LENGTH_LONG).show();
            Bundle bundle = new Bundle();
            bundle.putString("Email", _admin);

            Intent intent = new Intent(getApplicationContext(), CourseDashBoard.class);
            intent.putStringArrayListExtra("course_list_Enrolled", tempCourseListEnrolled);
            intent.putStringArrayListExtra("course_list_Offering", tempCourseListOffering);
            intent.putExtras(bundle);

            startActivity(intent);
            onBackPressed();

            System.out.println("course list done");
        }
    }
}
