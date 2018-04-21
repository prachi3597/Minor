package domhelp.com.minor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
//import android.widget.EditText;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
//
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Calendar;

public class EnterDetails extends AppCompatActivity {
   EditText name=(EditText)findViewById(R.id.etName);
   Spinner work=(Spinner)findViewById(R.id.spinnerWork);
   RadioButton rbHire=(RadioButton)findViewById(R.id.rbHire);
    RadioButton rbWork=(RadioButton)findViewById(R.id.rbWork);
    TextView tv;
    Spinner spinnerWork;
    FirebaseDatabase database;
    DatabaseReference ref;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_details);
        tv=(TextView)findViewById(R.id.tvWork);
        tv.setVisibility(View.GONE);
        spinnerWork=findViewById(R.id.spinnerWork);
        database= FirebaseDatabase.getInstance();
        ref=database.getReference("User");
        //date,month, year not included
//        spinnerDate=findViewById(R.id.spinnerDate);
//        spinnerMonth=findViewById(R.id.spinnerMonth);
//        spinnerYear=findViewById(R.id.spinnerYear);


        //Date
//        ArrayList<Integer> listDate= new ArrayList<>();
//        int i;
//        for(i=1;i<=31;i++){
//            listDate.add(i);
//        }
//        //Month
//        ArrayList<String> listMonth=new ArrayList<>();
//        listMonth.add("January");
//        listMonth.add("February");
//        listMonth.add("March");
//        listMonth.add("April");
//        listMonth.add("May");
//        listMonth.add("June");
//        listMonth.add("July");
//        listMonth.add("August");
//        listMonth.add("September");
//        listMonth.add("October");
//        listMonth.add("November");
//        listMonth.add("December");
//
//        //Year
//        ArrayList<Integer> listYear= new ArrayList<>();
//        Calendar calendar=Calendar.getInstance();
//        int year=calendar.get(Calendar.YEAR);
//        for(i=1900;i<year;i++){
//            listYear.add(i);
//        }

        //Work
        ArrayList<String> listWork=new ArrayList<>();
        listWork.add("Cook");
        listWork.add("Gardener");
        listWork.add("Babysitter");
        listWork.add("Maid");

        ArrayAdapter<String> adapterWork=new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item);
        spinnerWork.setAdapter(adapterWork);
        spinnerWork.setVisibility(View.GONE);
    }

    public void Hire(View view){
        TextView tv;
        tv=(TextView)findViewById(R.id.tvWork);
        tv.setText("Whom do you want to hire?");
        tv.setVisibility(View.VISIBLE);
        spinnerWork.setVisibility(View.VISIBLE);

    }
    public void Work(View view){
        TextView tv;
        tv=(TextView)findViewById(R.id.tvWork);
        tv.setText("What work do you prefer?");
        tv.setVisibility(View.VISIBLE);
        spinnerWork.setVisibility(View.VISIBLE);
    }
    public void Scan(View view){
        Intent intent=new Intent(EnterDetails.this,ScanActivity.class);
    }

}
