package com.example.merna.graduation_project;

/**
 * Created by merna on 5/26/2017.
 */


import android.app.admin.DeviceAdminReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

public class MyAdminReceiver extends DeviceAdminReceiver {




      private String KEY_ATTEMPTS_NO="attempts_no";
    //private static final String KEY_ATTEMPTS_NO = "";

    private static final int LIMIT = 3;

    @Override
    public void onPasswordFailed(Context context, Intent intent) {
       // Log.v("TAG","Failed login");
        Toast.makeText(context,"Failed login", Toast.LENGTH_LONG).show();
    SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);

        int attempts = sharedPrefs.getInt(KEY_ATTEMPTS_NO, 0) + 1;



         if (attempts == LIMIT) {
            // Reset the counter
          sharedPrefs.edit().putInt(KEY_ATTEMPTS_NO, 0).apply();

            // Launch your activity
           Intent i = new Intent(context, test.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
             i.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                     + WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD|
                     + WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED|
                     + WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
             context.startActivity(i);


        }

        else {
            // Save the new attempts number
            sharedPrefs.edit().putInt(KEY_ATTEMPTS_NO, attempts).apply();
        }



    }





    @Override
    public void onPasswordSucceeded(Context context, Intent intent) {
        // Reset number of attempts
       /* SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        sharedPrefs.edit().putInt(KEY_ATTEMPTS_NO, 0).apply();*/
        Log.v("TAG","succeed login");
    }







/*
    @Override
    public void onReceive(Context arg0, Intent arg1) {

        // TODO Auto-generated method stub


        DevicePolicyManager mgr = (DevicePolicyManager) arg0.getSystemService(Context.DEVICE_POLICY_SERVICE);
        int no = mgr.getCurrentFailedPasswordAttempts();

       Toast.makeText(arg0, "wrong", Toast.LENGTH_LONG).show();

        /*Intent i = new Intent();
        i.setClassName("com.bew.locksmith","com.bew.locksmith.test");
        arg0.startActivity(i);

        //String action = arg1.getAction();


    }



*/
}