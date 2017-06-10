package com.example.merna.graduation_project;

import android.app.Activity;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by merna on 3/9/2017.
 */
public class activation extends Activity {


    private boolean State;
    public int attempts=0;

    private static final int ADMIN_INTENT = 15;

    private static final String description = "Sample Administrator description";
    public static final String ACTION_PASSWORD_FAILED= "android.app.action.ACTION_PASSWORD_FAILED";
    private DevicePolicyManager mDevicePolicyManager, mDevicePolicyManager2;
    private ComponentName mComponentName,mComponentName2;
    private Button btnBack;
    private Button btnnext;
    private EditText activation_EditText;
    private int activation;
    private   boolean checked;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activation);

        mDevicePolicyManager = (DevicePolicyManager)getSystemService(
                Context.DEVICE_POLICY_SERVICE);
        mComponentName = new ComponentName(this, MyAdminReceiver.class);

        final CheckBox checkBox_terms = (CheckBox) findViewById(R.id.terms_checkbox);

        checked=checkBox_terms.isChecked();
        checkBox_terms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBox_terms.isChecked()==true) {
                    Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
                    intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, mComponentName);
                    intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION, description);
                    startActivityForResult(intent, ADMIN_INTENT);
                } else if (checkBox_terms.isChecked() == false)
                {  mDevicePolicyManager.removeActiveAdmin(mComponentName);
                    Toast.makeText(getApplicationContext(), "Admin registration removed", Toast.LENGTH_SHORT).show();

                }
            }
        });


        //Next Button
        btnnext = (Button) findViewById(R.id.next);


        //activation EditText
        activation_EditText = (EditText) findViewById(R.id.edittext_activation);
        activation = activation_EditText.getText().toString().length();

        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
 //
                if(active(activation_EditText)==true && State==true)
                {

                    Intent intent = new Intent(activation.this, stego.class);
                    startActivity(intent);
                }}
                //active(v);


        });

        //BackButton
        Button btnBack = (Button) findViewById(R.id.back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activation.this, SignUp.class);
                startActivity(intent);
            }
        });
    }




    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == ADMIN_INTENT) {
            if (resultCode == RESULT_OK) {
              State=true;

            } else {
             State=false;

                   }
            return;

        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /*
    public void active(View v) {

        //Agree terms of use CheckBox


        if (checked == true && (!activation.isEmpty())) {
            btnnext.setEnabled(true);
            Toast.makeText(this,"tmam",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(activation.this, stego.class);
            startActivity(intent);
        }

        else if (checked == false || (activation.isEmpty())) {


            btnnext.setEnabled(false);


        }

    }*/

    public boolean active(EditText activation_EditText) {
    String value =activation_EditText.getText().toString().trim();
    if (value.isEmpty())  {
        activation_EditText.setError("Enter correct activation code");
        return false;
    }

    return true;
}
}
