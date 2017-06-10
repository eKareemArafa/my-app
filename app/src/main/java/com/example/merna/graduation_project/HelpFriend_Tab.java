package com.example.merna.graduation_project;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by merna on 4/22/2017.
 */
public class HelpFriend_Tab extends Activity {
     private Button eraseData;
    private Button Locate;
    private TextView Friend_name;
    private TextView Friend_num;
    private ImageView Friend_image;
    private ViewGroup parent;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.helpfriend_tab);


    //    Resources res = getResources();
  //      XmlResourceParser xrp = res.getXml(R.xml.helpfriend);
// Get Parent
//        parent = (ViewGroup) findViewById(R.id.rlHelpControl);

// Include
        //LayoutInflater inflater1 = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //View rowView = inflater1.inflate(, null);
       // parent.addView(rowView);
          Friend_name = (TextView) findViewById(R.id.NameTxt);
      Friend_num=(TextView)findViewById(R.id.NumTxt);
       /* Bundle bundle = getArguments();
        if (bundle != null){
     /*/
            String name=getIntent().getStringExtra("name");
            String num=getIntent().getStringExtra("phone");
          Friend_name.setText(name);
            Friend_num.setText(num);
     //
     //   Friend_num=(TextView)rootView.findViewById(R.id.NumTxt);
       // Friend_image=(ImageView)rootView.findViewById(R.id.HelpFriend_image);
        //Bundle bundle=getActivity().getIntent().getExtras();
       // String name=bundle.getString("name");
       // String phone=bundle.getString("phone");
       // Friend_name.setText(name);
        //Friend_num.setText(phone);
       // int img=bundle.getInt("img");
        //Friend_image.setImageResource(img);

/*

        eraseData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());

                alertDialogBuilder.setMessage("Are you ure you want to erase all data");


                alertDialogBuilder.setPositiveButton("yes",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {


                            }
                        });
                alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {

                        getActivity().finish();
                    }
                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();


            }
                                     }
        );

*/

    }
}
