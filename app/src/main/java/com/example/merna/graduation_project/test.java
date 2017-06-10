package com.example.merna.graduation_project;

/**
 * Created by merna on 5/26/2017.
 */

import android.app.Activity;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

public class test extends Activity {
private Button btnUnlock;
private String number;
    protected void onCreate(Bundle savedInstanceState) {
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
        window.addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
        window.addFlags(WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
        super.onCreate(savedInstanceState);
       // TelephonyManager tMgr =(TelephonyManager)mAppContext.getSystemService(Context.TELEPHONY_SERVICE);
         number ="01276446486";

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
     //   alertDialogBuilder.setView(R.layout.Lock_dialog);
        alertDialogBuilder.setPositiveButton("yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        Toast.makeText(test.this, "You clicked yes button", Toast.LENGTH_LONG).show();

                        String message = "warning : This mobile may be stolen";


/** Creating a pending intent which will be broadcasted when an sms message is successfully sent */
                        PendingIntent piSent = PendingIntent.getBroadcast(getBaseContext(), 0, new Intent("sent_msg") , 0);

/** Creating a pending intent which will be broadcasted when an sms message is successfully delivered */
                        PendingIntent piDelivered = PendingIntent.getBroadcast(getBaseContext(), 0, new Intent("delivered_msg"), 0);

/** Getting an instance of SmsManager to sent sms message from the application*/
                        SmsManager smsManager = SmsManager.getDefault();

/** Sending the Sms message to the intended party */
                        smsManager.sendTextMessage(number, null, message, piSent, piDelivered);
                    }
                });

        alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override

            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}/*
        // /   setContentView(R.layout.test);
btnUnlock=(Button)findViewById(R.id.btnGo);
        btnUnlock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             //   sendSMS(#120*01277402866#);
             //   setMobileDataEnabled(connectivityManager, enabled);
                Intent intent = new Intent("android.location.GPS_ENABLED_CHANGE");
                intent.putExtra("enabled", true);
                sendBroadcast(intent);
            }
        });
    }
  /*  public void sendSMS(String phoneNo, String msg) {
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNo, null, msg, null, null);
            Toast.makeText(getApplicationContext(), "Message Sent",
                    Toast.LENGTH_LONG).show();
        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(),ex.getMessage().toString(),
                    Toast.LENGTH_LONG).show();
            ex.printStackTrace();
        }
    }*/
  /*private void setMobileDataEnabled(Context context, boolean enabled) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
      final ConnectivityManager conman = (ConnectivityManager)  context.getSystemService(Context.CONNECTIVITY_SERVICE);
      final Class conmanClass = Class.forName(conman.getClass().getName());
      final Field connectivityManagerField = conmanClass.getDeclaredField("mService");
      connectivityManagerField.setAccessible(true);
      final Object connectivityManager = connectivityManagerField.get(conman);
      final Class connectivityManagerClass =  Class.forName(connectivityManager.getClass().getName());
      final Method setMobileDataEnabledMethod = connectivityManagerClass.getDeclaredMethod("setMobileDataEnabled", Boolean.TYPE);
      setMobileDataEnabledMethod.setAccessible(true);

      setMobileDataEnabledMethod.invoke(connectivityManager, enabled);
  }


}
*/