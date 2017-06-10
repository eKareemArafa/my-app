package com.example.merna.graduation_project;

/**
 * Created by merna on 5/21/2017.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.merna.graduation_project.sql.User;
import com.example.merna.graduation_project.helper.InputValidation;
import com.example.merna.graduation_project.sql.DatabaseHelper;

public class SignUp extends AppCompatActivity implements View.OnClickListener {


    private final AppCompatActivity activity = SignUp.this;
    private LinearLayout linearLayout;
    private TextInputLayout textInputLayoutName;
    private TextInputLayout textInputLayoutEmail;
    private TextInputLayout textInputLayoutPassword;
    private TextInputLayout textInputLayoutPhoneNumber;
    private TextInputLayout textInputLayoutNationalID;
    private TextInputLayout textInputLayoutConfirmPassword;

    private TextInputEditText textInputEditTextName;
    private TextInputEditText textInputEditTextNationalID;
    private TextInputEditText textInputEditTextPhoneNumber;
    private AppCompatAutoCompleteTextView textInputEditTextEmail;
    private TextInputEditText textInputEditTextPassword;
    private TextInputEditText textInputEditTextConfirmPassword;
    private Button SignUp;
    private InputValidation inputValidation;
    private DatabaseHelper databaseHelper;
    private User user;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);


        inputValidation = new InputValidation(activity);
        databaseHelper = new DatabaseHelper(activity);
        user = new User();

        initViews();
        initListeners();
        initObjects();
    }

    /**
     * This method is to initialize views
     */
    private void initViews() {

        linearLayout=(LinearLayout)findViewById(R.id.llSignupContent);
        textInputLayoutName = (TextInputLayout) findViewById(R.id.input_layout_name);
        textInputLayoutEmail = (TextInputLayout) findViewById(R.id.input_layout_gmail);
        textInputLayoutPassword = (TextInputLayout) findViewById(R.id.input_layout_Password);
        textInputLayoutConfirmPassword = (TextInputLayout) findViewById(R.id.input_layout_confirm_password);
        textInputLayoutPhoneNumber = (TextInputLayout) findViewById(R.id.input_layout_phoneNumber);
        textInputLayoutNationalID = (TextInputLayout) findViewById(R.id.input_layout_National_ID);

        textInputEditTextName = (TextInputEditText) findViewById(R.id.input_FirstName);
        textInputEditTextEmail = (AppCompatAutoCompleteTextView) findViewById(R.id.input_Gmail);
        textInputEditTextPassword = (TextInputEditText) findViewById(R.id.input_password);
        textInputEditTextConfirmPassword = (TextInputEditText) findViewById(R.id.input_confirm_pass);
        textInputEditTextNationalID = (TextInputEditText) findViewById(R.id.input_nationalID);
        textInputEditTextPhoneNumber = (TextInputEditText) findViewById(R.id. input_phoneNumber);

        SignUp = (Button) findViewById(R.id.btnSignup);

    }

    /**
     * This method is to initialize listeners
     */
    private void initListeners() {
        SignUp.setOnClickListener(this);


    }

    /**
     * This method is to initialize objects to be used
     */
    private void initObjects() {
        inputValidation = new InputValidation(activity);
        databaseHelper = new DatabaseHelper(this);
        user = new User();

    }


    /**
     * This implemented method is to listen the click on view
     *
     * @param v
     */
 //   @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btnSignup:
               postDataToSQLite();
                break;

        }
    }

    /**
     * This method is to validate the input text fields and post data to SQLite
     */
    private void postDataToSQLite() {
        if (!inputValidation.isInputEditTextEmail(textInputEditTextEmail, textInputLayoutEmail, "Enter Vailed Gmail")) {
            return;
        } else if (!inputValidation.isInputEditTextFilled(textInputEditTextName, textInputLayoutName, getString(R.string.error_message_name))) {

            return;
        } else if (!inputValidation.isInputEditTextPhoneNumber(textInputEditTextPhoneNumber, textInputLayoutPhoneNumber, "Enter phone Number")) {

            return;
        } else if (!inputValidation.isInputEditTextNAtionalID(textInputEditTextNationalID, textInputLayoutNationalID, "Enter National ID")) {

            return;
        } else if (!inputValidation.isInputEditTextFilled(textInputEditTextPassword, textInputLayoutPassword, getString(R.string.error_message_password))) {

            return;
        } else if (!inputValidation.isInputEditTextMatches(textInputEditTextPassword, textInputEditTextConfirmPassword,
                textInputLayoutConfirmPassword, getString(R.string.error_password_match))) {

            return;
        }
        user.setName(textInputEditTextName.getText().toString().trim());

        user.setGmail(textInputEditTextEmail.getText().toString().trim());
        user.setPassword(textInputEditTextPassword.getText().toString().trim());
        user.setId(textInputEditTextNationalID.getText().toString().trim());
        user.setPhoneNumber(textInputEditTextPhoneNumber.getText().toString().trim());

        databaseHelper.addUser(user);
          // Snack Bar to show success message that record saved successfully
            Intent intent = new Intent(SignUp.this, activation.class);
            startActivity(intent);
            emptyInputEditText();
            //
        }
    /*else {


            // Snack Bar to show error message that record already exists
            Snackbar.make(linearLayout, getString(R.string.error_email_exists), Snackbar.LENGTH_LONG).show();
        }
*/


    /**
     * This method is to empty all input edit text
     */
    private void emptyInputEditText() {
        textInputEditTextName.setText(null);
        textInputEditTextEmail.setText(null);
        textInputEditTextNationalID.setText(null);
        textInputEditTextPhoneNumber.setText(null);
        textInputEditTextPassword.setText(null);
        textInputEditTextConfirmPassword.setText(null);
    }

  /*


      */
       // user.setGmail("merna.lamie@gmail.com");
   //     user.setName("merna");
       // user.setPassword("123456");
     //   user.setID(2);
       // user.setPhoneNumber("01276446486");
       // if(user !=null) {
           //

         //   String x=user.getGmail();
         //   String y=user.getName();
          //  String q=user.getPhoneNumber();
       //     String w=user.getPassword();
          //  int r=user.getID();
        //    Log.v("user",x+y+q+w+r);
          //  Toast.makeText(this,"No"+x +y,Toast.LENGTH_LONG).show();
       /*     user.setID(5);
            databaseHelper.deleteContact(user);
        }
        Log.v("Reading: ", "Reading all contacts..");
        List<Contact> contacts = databaseHelper.getAllContacts();

        for (Contact cn : contacts) {
            String log = "Id: "+cn.getID()+" ,Name: " + cn.getName() + " ,Phone: " + cn.getPhoneNumber();
            // Writing Contacts to log
            Log.v("Name: ", log);
        }
    }*/
}