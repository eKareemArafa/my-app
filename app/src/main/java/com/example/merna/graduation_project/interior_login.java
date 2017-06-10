package com.example.merna.graduation_project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

/**
 * Created by merna on 3/12/2017.
 */
public class interior_login extends Activity {
    ArrayAdapter<CharSequence>adapter;
    Spinner gov_no;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.interior_login);
        AppCompatButton btn_login = (AppCompatButton) findViewById(R.id.interior_login);

       // Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Sketch.ttf");
        //login_txt.setTypeface(typeface);
    //   gov_no.setOnItemSelectedListener(this);

        // Create an ArrayAdapter using the string array and a default spinner layout
      //  ArrayAdapter<int> adapter = ArrayAdapter.createFromResource(this,
        //        R.array.governrate_no, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        //gov_no.setAdapter(adapter);
//gov_no=(Spinner)findViewById(R.id.gov_no);
  //      adapter=ArrayAdapter.createFromResource(this,R.array.governrate_no,android.R.layout.simple_spinner_item);
    //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
      //  gov_no.setAdapter(adapter);
       /* gov_no.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                             @Override
                                             public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                                             }

                                             @Override
                                             public void onNothingSelected(AdapterView<?> parent) {

                                             }
                                         });*/
                btn_login.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent(interior_login.this, interior_retrieve.class);
                        startActivity(intent);

                    }
                });
}}

