package com.example.merna.graduation_project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by merna on 5/19/2017.
 */

public class Main extends Activity{
    RelativeLayout relativeLayout;
    Animation translate;
    TextView signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);


relativeLayout =(RelativeLayout)findViewById(R.id.activity_main);
        signup = (TextView)findViewById(R.id.tvSignup);
        // load the animation
        translate = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slid_up);
        Animation clockwise= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_right_to_left);
        signup.startAnimation(clockwise);





        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            //    showSignupForm();
               relativeLayout.startAnimation(translate);
                Intent intent=new Intent(Main.this,SignUp.class);
                startActivity(intent);

            }
        });
    }

    public void onAnimationEnd(Animation animation) {
        // Take any action after completing the animation

        // check for fade in animation
        if (animation == translate) {
            Toast.makeText(getApplicationContext(), "Animation Stopped",
                    Toast.LENGTH_SHORT).show();
        }

    }


    public void onAnimationRepeat(Animation animation) {
        // TODO Auto-generated method stub

    }


    public void onAnimationStart(Animation animation) {
        // TODO Auto-generated method stub

    }

}
