package domhelp.com.minor;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

public class Slideshow extends AppCompatActivity {

    Button btnSkip;
    public int status=0;


    ViewPager viewPager;
    int[] img;
    private static int currentPage=0;
    private static int numPages=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slideshow);

        img=new int[]{
                R.drawable.os_cook,R.drawable.os_babysitter,R.drawable.os_gardener,R.drawable.os_gardener
        };
        viewPager=(ViewPager)findViewById(R.id.viewPager);
        ViewPagerAdapter viewPagerAdapter=new ViewPagerAdapter(this);
        viewPager.setAdapter(viewPagerAdapter);
        btnSkip=findViewById(R.id.btnSkip);
        btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Skip to new Activity:
                switch (status){
                    case 0:{
                        Intent intent=new Intent(getApplicationContext(),EnterMobileNumber.class);
                        startActivity(intent);
                    }
                    break;
                    case 1:{
                        Intent intent=new Intent(getApplicationContext(),Dashboard.class);
                        startActivity(intent);
                    }
                    break;
                    case -1:{
                        Intent intent=new Intent(getApplicationContext(),EnterUID.class);
                        startActivity(intent);
                    }
                }

                //Enter last status in Toast :
                Toast.makeText(Slideshow.this, "You're not Registered! Sign Up->", Toast.LENGTH_SHORT).show();
            }
        });
        CircleIndicator indicator=(CircleIndicator)findViewById(R.id.indicator);
        indicator.setViewPager(viewPager);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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
                    int pagecount= img.length ;
                    if(currentPage==0){
                        viewPager.setCurrentItem(pagecount-1,false);

                    }
                    else if(currentPage==pagecount-1){
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
