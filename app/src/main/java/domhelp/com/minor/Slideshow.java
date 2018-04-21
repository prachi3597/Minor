package domhelp.com.minor;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.PhoneAuthProvider;

import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;


public class Slideshow extends AppCompatActivity {
    private static int currentPage=0;
    private static int numPages=0;
    ViewPager viewPager;
    int[] img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {



        Button btnSkip;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slideshow);

        img=new int[]{
                R.drawable.os_cook,R.drawable.os_babysitter,R.drawable.os_gardener,R.drawable.os_gardener};
        viewPager=findViewById(R.id.viewPager);
        ViewPagerAdapter viewPagerAdapter=new ViewPagerAdapter(this);
        viewPager.setAdapter(viewPagerAdapter);
        btnSkip=findViewById(R.id.btnSkip);
        btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Skip to new Activity:

                Intent intent=new Intent(getApplicationContext(),PhoneAuthActivity.class);
                startActivity(intent);
            }
        });
        CircleIndicator indicator=findViewById(R.id.indicator);
        indicator.setViewPager(viewPager);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position){
            currentPage=position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if(state==ViewPager.SCROLL_STATE_IDLE){
                    int page_count= img.length ;
                    if(currentPage==0){
                        viewPager.setCurrentItem(page_count-1,false);

                    }
                    else if(currentPage==page_count-1){
                        viewPager.setCurrentItem(0,false);
                    }
                }

            }
        });
        //slide show with timer
        final Handler handler=new Handler();
        final Runnable update=new Runnable() {
            @Override
            public void run() {
               if(currentPage==numPages){

                   currentPage=0;
               }

               viewPager.setCurrentItem(currentPage++,true);
            }

        };
        //swiping:
        Timer swipe=new Timer();
        swipe.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(update);
            }
        },1000,1000);

        

    }


}
