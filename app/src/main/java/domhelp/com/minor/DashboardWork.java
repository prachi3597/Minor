package domhelp.com.minor;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class DashboardWork extends AppCompatActivity implements View.OnClickListener {


    private FirebaseAuth mAuth;

    private DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;

    @Override    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_work);

        ActionBarDrawerToggle actionBarDrawerToggle;
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.drawer_open, R.string.drawer_close);

        Toolbar toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);


        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Button mSignOutButton = findViewById(R.id.sign_out_button);
        TextView fireBaseId =  findViewById(R.id.detail);
        mAuth = FirebaseAuth.getInstance();
        if(mAuth!=null){
            fireBaseId.setText(mAuth.getCurrentUser().getPhoneNumber());
        }
        mSignOutButton.setOnClickListener(this);
    }
    public void setSupportActionBar(Toolbar toolbar) {
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }




    @Override    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sign_out_button:
                mAuth.signOut();
                Intent intent=new Intent(DashboardWork.this, PhoneAuthActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }


}

