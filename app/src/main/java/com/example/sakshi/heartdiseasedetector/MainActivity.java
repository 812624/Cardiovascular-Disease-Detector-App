package com.example.sakshi.heartdiseasedetector;
import org.tensorflow.contrib.android.TensorFlowInferenceInterface;

import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.util.Linkify;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Gallery;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.AutoCompleteTextView;
import android.graphics.Color;
import android.view.Menu;
import android.widget.Toast;

import static android.R.id.toggle;
import static android.view.View.*;
import static com.example.sakshi.heartdiseasedetector.R.string.drawer_close;
import static com.example.sakshi.heartdiseasedetector.R.string.drawer_open;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    int num2, num3, num4, num5, num6, num9, num10, num11, num12, num14, num15, num17, num18, num19, num20, num21, num22, num23;


    // Used to load the 'native-lib' library on application startup. file:///android_asset/frozen_tfdroid.pb
    private static final String MODEL_FILE = "file:///android_asset/frozen_tfdroid.pb";
    private static final String INPUT_NODE = "x";
    private static final String OUTPUT_NODE = "softmax/preacts/preacts";

    private static final int[] INPUT_SIZE = {1,23};
    private TensorFlowInferenceInterface inferenceInterface;
    static {
        System.loadLibrary("tensorflow_inference");
    }
    static {
        System.loadLibrary("native-lib");
    }
  //  String[] sex = {"Female", "Male"};
    public static String[] sex = new String[] {"Female", "Male"};
    public static String[] cp = new String[]{"typical angina", "atypical angina", "non-anginal pain", "asymptomatic"};
    public static String[] sugar = new String[]{"<=120mg/dl", ">120mg/dl"};
    public static String[] ecg = new String[]{"Normal", "Abnormal ST-T wave, ST elev./dep. of >0.05 mV", "Showing Any left ventricular hypertrophy"};
    public static String[] exang = new String[]{"No", "Yes"};
    public static String[] slope =new String[] { "Upsloping", "Flat", "Downsloping"};
    public static String[] ca = new String[]{"0", "1", "2", "3", "4"};
    public static String[] thal = new String[] {"Normal", "Fixed defect", "Reversable defect"};

   public ActionBarDrawerToggle mToggle;
    public DrawerLayout myDraw;
    public NavigationView nv1;
    public FragmentTransaction ft1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ////
       /* Dataset1 fragment = new Dataset1();
        android.support.v4.app.FragmentTransaction fragmentTransaction =
                getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.JaiMataDi, fragment);
        fragmentTransaction.commit();
*/
        inferenceInterface = new TensorFlowInferenceInterface();
        inferenceInterface.initializeTensorFlow(getAssets(), MODEL_FILE);
        myDraw = (DrawerLayout) findViewById(R.id.mydraw);

        mToggle = new ActionBarDrawerToggle(this, myDraw,drawer_open ,drawer_close);
        myDraw.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
       // nv1 = (NavigationView) findViewById(R.id.navg1);
        //nv1.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener(){
          /*  @Override
            public boolean onNavigationItemSelected(MenuItem item)
            {
                ft1 = getSupportFragmentManager().beginTransaction();
                switch (item.getItemId())
                {
                    case R.id.my_id2:
                        ft1 = getSupportFragmentManager().beginTransaction();
                        ft1.replace(R.id.JaiMataDi, new Dataset());
                        ft1.commit();
                        myDraw.closeDrawers();
                       // getSupportActionBar().setTitle("Dataset");
                        item.setChecked(true);
                        break;
                    case R.id.my_id3:
                        ft1 = getSupportFragmentManager().beginTransaction();
                        ft1.replace(R.id.main_container, new Disclaimer());
                        ft1.commit();
                       // getSupportActionBar().setTitle("disclaimer");
                        item.setChecked(true);
                       // myDraw.closeDrawers();
                        break;
                    case R.id.my_id4:
                        ft1 = getSupportFragmentManager().beginTransaction();
                        ft1.replace(R.id.main_container, new About());
                        ft1.commit();
                      //  getSupportActionBar().setTitle("About");
                        item.setChecked(true);
                       // myDraw.closeDrawers();
                        break;
                }
                return true;
            }
        });*/


        final Spinner spinner1 = (Spinner) findViewById(R.id.spinner1);

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_dropdown_item_1line, sex);
        spinner1.setAdapter(adapter1);//setting the adapter data into the AutoCompleteTextView
        spinner1.setAdapter(adapter1);


        final Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_dropdown_item_1line, cp);
        spinner2.setAdapter(adapter2);//setting the adapter data into the AutoCompleteTextView
        spinner2.setAdapter(adapter2);

        ////////////////////////


        final Spinner spinner3 = (Spinner) findViewById(R.id.spinner3);
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_dropdown_item_1line, sugar);
        spinner3.setAdapter(adapter3);//setting the adapter data into the AutoCompleteTextView
        spinner3.setAdapter(adapter3);

        ////////////////////////


        final Spinner spinner4 = (Spinner) findViewById(R.id.spinner4);
        ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_dropdown_item_1line, ecg);
        spinner4.setAdapter(adapter4);//setting the adapter data into the AutoCompleteTextView
        spinner4.setAdapter(adapter4);

        ////////////////////////


        final Spinner spinner5 = (Spinner) findViewById(R.id.spinner5);
        ArrayAdapter<String> adapter5 = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_dropdown_item_1line, exang);
        spinner5.setAdapter(adapter5);//setting the adapter data into the AutoCompleteTextView
        spinner5.setAdapter(adapter5);

        ////////////////////////


        final Spinner spinner6 = (Spinner) findViewById(R.id.spinner6);
        ArrayAdapter<String> adapter6 = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_dropdown_item_1line, slope);
        spinner6.setAdapter(adapter6);//setting the adapter data into the AutoCompleteTextView
        spinner6.setAdapter(adapter6);

        ////////////////////////


        final Spinner spinner7 = (Spinner) findViewById(R.id.spinner7);
        ArrayAdapter<String> adapter7 = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_dropdown_item_1line, ca);
        spinner7.setAdapter(adapter7);//setting the adapter data into the AutoCompleteTextView
        spinner7.setAdapter(adapter7);

        ////////////////////////


        final Spinner spinner8 = (Spinner) findViewById(R.id.spinner8);
        ArrayAdapter<String> adapter8 = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_dropdown_item_1line, thal);
        spinner8.setAdapter(adapter8);//setting the adapter data into the AutoCompleteTextView
        spinner8.setAdapter(adapter8);

        ////////////---------------------------------
        final EditText check1 = (EditText) findViewById(R.id.textView20);
        check1.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if((check1.getText().length() < 1)||(Integer.parseInt(check1.getText().toString())) < 1||(Integer.parseInt(check1.getText().toString())  >100)){
                check1.setError("Enter Valid age");
                check1.setText("");}
            }
        });


        ////////////---------------------------------
        final EditText check2 = (EditText) findViewById(R.id.textView21);
        check2.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if((check2.getText().length() < 1)||(Float.parseFloat(check2.getText().toString())) < 1||(Float.parseFloat(check2.getText().toString())  >220)){
                    check2.setError("Enter Valid BP");
                    check2.setText("");}
            }
        });

        ////////////---------------------------------
        final EditText check3 = (EditText) findViewById(R.id.textView22);
        check3.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if((check3.getText().length() < 1)||(Float.parseFloat(check3.getText().toString())) < 1||(Float.parseFloat(check3.getText().toString())  >650)){
                    check3.setError("Enter Valid Chol");
                    check3.setText("");}
            }
        });

        ////////////---------------------------------
        final EditText check4 = (EditText) findViewById(R.id.textView23);
        check4.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if((check4.getText().length() < 1)||(Integer.parseInt(check4.getText().toString())) < 30||(Integer.parseInt(check4.getText().toString())  >250)){
                    check4.setError("Enter Valid Heart Rate");
                    check4.setText("");}
            }
        });


        ////////////---------------------------------
        final EditText check5 = (EditText) findViewById(R.id.textView24);
        check5.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if((check5.getText().length() < 1)||(Float.parseFloat(check5.getText().toString())) < 0||(Float.parseFloat(check5.getText().toString())  >10.0)){
                    check5.setError("Enter Valid Info");
                    check5.setText("");}
            }
        });

        final Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {

                final EditText editNum1 = (EditText) findViewById(R.id.textView20);
                final EditText editNum2 = (EditText) findViewById(R.id.textView21);
                final EditText editNum3 = (EditText) findViewById(R.id.textView22);
                final EditText editNum4 = (EditText) findViewById(R.id.textView23);
                final EditText editNum5 = (EditText) findViewById(R.id.textView24);

                int   num1 = Integer.parseInt(editNum1.getText().toString());
                float num7 = Float.parseFloat(editNum2.getText().toString());
                float num8 = Float.parseFloat(editNum3.getText().toString());
                float num13 = Float.parseFloat(editNum4.getText().toString());
                float num16 = Float.parseFloat(editNum5.getText().toString());
                String Sex = spinner1.getSelectedItem().toString();
                if(Sex == "Female")
                    num2 = 0;
                else
                    num2 = 1;

                String CP = spinner2.getSelectedItem().toString();
                if(CP == "typical angina") {
                    num3 = 0;
                    num4 = 0;
                    num5 = 0;
                    num6 = 1;

                }
                else if(CP == "atypical angina") {
                    num3 = 0;
                    num4 = 1;
                    num5 = 0;
                    num6 = 0;
                }
                else if(CP == "non-anginal pain") {
                    num3 = 0;
                    num4 = 0;
                    num5 = 1;
                    num6 = 0;
                }
                else if(CP == "asymptomatic") {
                    num3 = 1;
                    num4 = 0;
                    num5 = 0;
                    num6 = 0;
                }
                String BS = spinner3.getSelectedItem().toString();
                if(BS == "<=120mg/dl")
                    num9 = 0;
                else
                    num9 = 1;

                String RECG = spinner4.getSelectedItem().toString();
                if(RECG == "Normal") {
                    num10 = 0;
                    num11 = 0;
                    num12 = 1;
                }
                else if(RECG == "Abnormal ST-T wave, ST elev./dep. of >0.05 mV") {
                    num10 = 1;
                    num11 = 0;
                    num12 = 0;
                }
                else if(RECG == "Showing Any left ventricular hypertrophy")
                {
                    num10 = 0;
                    num11 = 1;
                    num12 = 0;
                }

                String Angina = spinner5.getSelectedItem().toString();
                if(Angina == "Yes") {
                    num14 = 0;
                    num15 = 1;
                }
                else if(Angina == "No") {
                    num14 =1;
                    num15 = 0;
                }
                String Slope = spinner6.getSelectedItem().toString();
                if(Slope == "Upsloping") {
                    num17 = 0;
                    num18 = 0;
                    num19 = 1;
                }
                else if(Slope == "Flat")
                {
                    num17 = 0;
                    num18 = 1;
                    num19 = 0;
                }
                else if(Slope == "Downsloping")
                {
                    num17 = 1;
                    num18 = 0;
                    num19 = 0;
                }

                String CA = spinner7.getSelectedItem().toString();
                if(CA == "0")
                    num20 = 0;
                else if(CA == "1")
                    num20 = 1;
                else if(CA == "2")
                    num20 = 2;
                else if(CA == "3")
                    num20 = 3;
                else if(CA == "4")
                    num20 = 4;

                String Thal = spinner8.getSelectedItem().toString();
                if(Thal == "Normal")
                {   num21= 0;
                    num22= 1;
                    num23= 0;
                }
                else if(Thal == "Fixed defect")
                {   num21= 1;
                    num22= 0;
                    num23= 0;
                }
                else if(Thal == "Reversable defect")
                {   num21= 0;
                    num22= 0;
                    num23= 1;
                }

                float[] inputFloats = {num1, num2, num3, num4, num5, num6, num7, num8, num9, num10, num11, num12, num13, num14, num15, num16, num17, num18, num19, num20, num21, num22, num23};
                Log.d("Yeah" , " " + num1 + " " + num2 + " " + num3 + " " + num4 +" " + num5 + " " + num6+" " + num7 + " " + num8 + " " + num9 + " " + num10 + " " + num11 +" " + num12 + " " + num13 + " " + num14 + " " + num15 + " " + num16 + " " + num17 +" " + num18 + " " + num19+" " + num20 + " "+ num21 + " " + num22+" " + num23);
                inferenceInterface.fillNodeFloat(INPUT_NODE, INPUT_SIZE, inputFloats);

                inferenceInterface.runInference(new String[] {OUTPUT_NODE});
                float[] resu = {0, 0};
                inferenceInterface.readNodeFloat(OUTPUT_NODE, resu);

               // float[] resu = {0};
               // inferenceInterface.readNodeFloat(OUTPUT_NODE, resu);
                final TextView report = (TextView) findViewById(R.id.result);
                //System.out.println(Float.toString(resu[0]));
                if(resu[0] > resu[1]) {
                    report.setText("No Disease Detected");
                }
                else {
                    report.setText("Chances of Disease");
                    report.setTextColor(Color.RED);
                }
                int f = 0;
                final TextView commonTips = (TextView) findViewById(R.id.commonTip);
                commonTips.setAutoLinkMask(Linkify.WEB_URLS);
                commonTips.setText(R.string.commonTip);

                if(num7 > 140 || num7 <80)
                {
                    final TextView r1 = (TextView) findViewById(R.id.tip1);
                    f = 1;
                    r1.setGravity(Gravity.CENTER);
                    //<a href="http://www.google.com">Go to Google</a>
                    //report1.setMovementMethod(LinkMovementMethod.getInstance());
                    r1.setText("\n" + "\u2022" + "  You have abnormal Blood Pressure maintain that" );
                    final TextView r1bp = (TextView) findViewById(R.id.tip1bp);
                    r1bp.setAutoLinkMask(Linkify.WEB_URLS);
                    r1bp.setText(R.string.r1bp);
                }

                ///////////////////////////////////////////////////////////
                if(num8 > 200 && num8 <= 239)
                {
                    final TextView r2 = (TextView) findViewById(R.id.tip2);
                    r2.setGravity(Gravity.CENTER);
                    f = 1;
                    r2.setText("\n" + "\u2022" + "  Border Line of Cholestrol"  );
                    final TextView r2chol = (TextView) findViewById(R.id.tip2chol);
                    r2chol.setAutoLinkMask(Linkify.WEB_URLS);
                    r2chol.setText(R.string.r2chol );
                }
                if(num8 > 240)
                {
                    final TextView r2 = (TextView) findViewById(R.id.tip2);
                    r2.setGravity(Gravity.CENTER);
                    f = 1;
                    r2.setText("\n" + "\u2022" + "  High Cholestrol"  );
                    final TextView r2chol = (TextView) findViewById(R.id.tip2chol);
                    r2chol.setAutoLinkMask(Linkify.WEB_URLS);
                    r2chol.setText(R.string.r2chol );
                }
                /////////////////////////////////////////////////////////

                if(num9 == 1)
                {
                    final TextView r3 = (TextView) findViewById(R.id.tip3);
                    r3.setGravity(Gravity.CENTER);
                    f = 1;
                    r3.setText("\n" + "\u2022" + "  You Need to maintain Blood Sugar" );
                    final TextView r3fbs = (TextView) findViewById(R.id.tip3fbs);
                    r3fbs.setAutoLinkMask(Linkify.WEB_URLS);
                    r3fbs.setText(R.string.r3fbs);
                }
                /////////////////////////////////////////////////////////

                if(num13 > 97)
                {
                    final TextView r4 = (TextView) findViewById(R.id.tip4);
                    r4.setGravity(Gravity.CENTER);
                    f = 1;
                    r4.setText("\n" + "\u2022" + "  High Heart Rate, needs attention"  );
                    final TextView r4hrt = (TextView) findViewById(R.id.tip4hrt);
                    r4hrt.setAutoLinkMask(Linkify.WEB_URLS);
                    r4hrt.setText(R.string.r4hrt);
                }
                /////////////////////////////////////////////////////////

                if(num15 == 1)
                {
                    final TextView r5 = (TextView) findViewById(R.id.tip5);
                    r5.setGravity(Gravity.CENTER);
                    f = 1;
                    r5.setText("\n" + "\u2022" + "  Click below for tips on Exercise induced Angina" );
                    final TextView r5eiang = (TextView) findViewById(R.id.tip5eiang);
                    r5eiang.setAutoLinkMask(Linkify.WEB_URLS);
                    r5eiang.setText(R.string.r5eiang);
                }
                /////////////////////////////////////////////////////////

                if(num22 !=  1)
                {
                    final TextView r6 = (TextView) findViewById(R.id.tip6);
                    r6.setGravity(Gravity.CENTER);
                    f = 1;
                    //r6.setText("here");
                    Log.d("Yeah" ," "+  num22);
                    r6.setText("\n" + "\u2022" + "  Thalassemia"  );
                    final TextView r6thal = (TextView) findViewById(R.id.tip6thal);
                    r6thal.setGravity(Gravity.CENTER);
                    r6thal.setAutoLinkMask(Linkify.WEB_URLS);
                    r6thal.setText("Tips to maitain Thalassemia");
                }
            }
        });
    }

   @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if(mToggle.onOptionsItemSelected(item))
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
   /* public boolean onNavigationItemSelected(MenuItem item)
    {
        ft1 = getSupportFragmentManager().beginTransaction();
        switch (item.getItemId())
        {
            case R.id.my_id2:
                Dataset1 fragment1 = new Dataset1();
                android.support.v4.app.FragmentTransaction fragmentTransaction1 =
                        getSupportFragmentManager().beginTransaction();
                fragmentTransaction1.replace(R.id.main_container, fragment1);
                fragmentTransaction1.commit();
                // getSupportActionBar().setTitle("Dataset");
                item.setChecked(true);
                break;
            case R.id.my_id3:
                Disclaimer fragment2 = new Disclaimer();
                android.support.v4.app.FragmentTransaction fragmentTransaction2 =
                        getSupportFragmentManager().beginTransaction();
                fragmentTransaction2.replace(R.id.main_container, fragment2);
                fragmentTransaction2.commit();
                item.setChecked(true);
                // myDraw.closeDrawers();
                break;
            /*case R.id.my_id4:
                ft1 = getSupportFragmentManager().beginTransaction();
                ft1.replace(R.id.main_container, new About());
                ft1.commit();
                //  getSupportActionBar().setTitle("About");
                item.setChecked(true);
                // myDraw.closeDrawers();
                break;
        }
        return true;
    }*/

    public native String stringFromJNI();
}
