package com.example.merna.graduation_project;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.merna.graduation_project.helper.InputValidation;
import com.example.merna.graduation_project.sql.DatabaseHelper;
import com.example.merna.graduation_project.sql.User;

import static android.R.attr.id;
import static android.R.attr.viewportHeight;

/**
 * Created by merna on 3/12/2017.
 */
public class setting extends Activity {
    private EditText GMail;
    private EditText password;
    public String mail;
    public  String pass;
    private ViewGroup parent;
    private ViewGroup parent1;
    private   View rowView;
    DatabaseHelper db=new DatabaseHelper(this);
    InputValidation valid=new InputValidation(this);
    private LinearLayout parentLinearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(setting.this, User_profile.class);
                startActivity(intent);

                parentLinearLayout = (LinearLayout) findViewById(R.id.parent_linear_layout);

                TextView change_password = (TextView) findViewById(R.id.tvchangepassword);
                // change_password.startAnimation(AnimationUtils.loadAnimation( ,android.R.anim.fade_in));
                TextView change_gmail = (TextView) findViewById(R.id.tvchangeGmail);
            }

        });
    }


    public void click(View v) {
//        final LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
// Get Parent
        parent = (ViewGroup) findViewById(R.id.password);
// Include
        LayoutInflater inflater1 = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater1.inflate(R.layout.changepassword, null);
        parent.addView(rowView);
    }


    public void G_save(View v) {
        User user =new User();
        mail= db.getGmail();
        String id =db.getID();
        Log.d("stored mail",mail);
        GMail= (EditText) findViewById(R.id.currentGmail);
        if(validGmail(GMail)==true){
            EditText new_mail=(EditText) findViewById(R.id.eidtGmail);
            String NewMail= new_mail.getText().toString();
            user.setGmail(NewMail);
            user.setId(id);
            db.updateGmail(user);
            db.getinfo(user);
            parent1.removeView((View) v.getParent());
        }
    }


    public void gmail(View v) {
// Get Parent
        parent1 = (ViewGroup) findViewById(R.id.rlGmail);
// Include
        LayoutInflater inflater1 = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater1.inflate(R.layout.changepgmail, null);
        parent1.addView(rowView);
    }


    public void save(View v) {
        User user=new User();
        pass=db.getPass();
        String id=db.getID();
        Log.d("stored password",pass);
        password=(EditText)findViewById(R.id.currentpass);
        if (validPASS(password)==true) {
            EditText newpass=(EditText)findViewById(R.id.eidtpass);
            String pass=newpass.getText().toString();
            EditText confpass=(EditText)findViewById(R.id.Rpass);
            String conf=confpass.getText().toString();
            if (conf.equals(pass)==false){
                confpass.setError("Enter corrcet password");
                confpass.setText(null);
            }else {
                user.setPassword(conf);
                user.setId(id);
                db.updatePass(user);
                db.getinfo(user);
                parent.removeView((View) v.getParent());
            }

        }
    }


    public boolean validGmail(EditText GMail) {
        String value =GMail.getText().toString().trim();
        if (value.isEmpty() || value.equals(mail)==false)  {
            GMail.setError("Enter correct Gmail");
            return false;
        }

        return true;
    }


    public boolean validPASS(EditText password) {
        String value =password.getText().toString().trim();
        if (value.isEmpty() || value.equals(pass)==false)  {
            password.setError("Enter correct password");
            return false;
        }

        return true;
    }
}