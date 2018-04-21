package domhelp.com.minor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Setting time of display:
        new Timer().schedule(new TimerTask() {

            public void run() {
                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(FirebaseAuth.getInstance().getCurrentUser()!=null)
                            startActivity(new Intent(MainActivity.this, DashboardWork.class));
                        else
                            startActivity(new Intent(MainActivity.this, Slideshow.class));

                    }
                });
            }
        },1000);




    }
}
