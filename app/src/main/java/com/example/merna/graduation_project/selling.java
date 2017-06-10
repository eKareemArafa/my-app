package com.example.merna.graduation_project;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.merna.graduation_project.sql.DatabaseHelper;
import com.example.merna.graduation_project.sql.User;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import static android.R.attr.id;

/**
 * Created by merna on 4/21/2017.
 */
public class selling extends Activity {
    EditText N_id;
    EditText password;
    Button finish;
   /* String val_id;
    String val_pass;*/
   public String id;
   public String pass;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selling);
        final DatabaseHelper dbhelper = new DatabaseHelper(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(selling.this, User_profile.class);
                startActivity(intent);
            }
        });
        finish = (Button) findViewById(R.id.finish);
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                N_id = (EditText) findViewById(R.id.sell_National);
                password = (EditText) findViewById(R.id.sell_Password);
                User user = new User();
                String national=dbhelper.getID();
                Log.d("national",national);
                user = dbhelper.get_user(national);
                id = user.getId();
                pass = user.getPassword();
                Log.d("passowrd",pass);
                Log.d("national id",id);

                if ( validID(N_id)==true && validPASS(password)==true ) {

                    dbhelper.deleteUser(user);
                    Log.d("Delete","DONE");
                    Intent intent = new Intent(selling.this, SignUp.class);
                    startActivity(intent);
                }
            }
        });
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }
    public boolean validID(EditText N_id) {
        String value =N_id.getText().toString().trim();
        if (value.isEmpty() || value.equals(id)==false)  {
            N_id.setError("Enter correct National id");
            return false;
        }

        return true;
    }
    public boolean validPASS(EditText password) {
        String value =password.getText().toString().trim();
        if (value.isEmpty() || value.equals(pass)==false)  {
            N_id.setError("Enter correct password");
            return false;
        }

        return true;
    }


    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("selling Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}