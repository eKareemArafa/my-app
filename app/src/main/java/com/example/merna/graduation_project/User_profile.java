package com.example.merna.graduation_project;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentUris;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.merna.graduation_project.sql.DatabaseHelper;
import com.example.merna.graduation_project.sql.User;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static android.R.attr.name;
import static com.example.merna.graduation_project.R.id.view;

public class User_profile extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private Uri contactUri;
    private String contactID;     // contacts unique ID
    private TextView contact_name;
    private TextView contact_number;
    private ImageView imageView;
    private ImageView add_image;
    private ListView contacts;
    private PhoneAdapter adapter;
    private TextView add_text;
    private List<phonebook> list = new ArrayList<phonebook>();
    private static int PICK_CONTACT = 1;
    private static final String[] phoneProjection = new String[]{ContactsContract.CommonDataKinds.Phone.DATA};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_user_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
       toolbar.setTitle("MY Friend");
/*
//        setSupportActionBar(toolbar);
      //  getActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
     //   getActionBar().setCustomView(R.layout.activity_user_profile);
       // ActionBar.LayoutParams p = new ActionBar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        //p.gravity = Gravity.CENTER;

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);

       // tabLayout.addTab(tabLayout.newTab().setText("My Friends"));
        //tabLayout.addTab(tabLayout.newTab().setText("Help Friend"));

       // tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
/*
        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        LinearLayout tabStrip = ((LinearLayout)tabLayout.getChildAt(0));
        for(int i = 0; i < tabStrip.getChildCount(); i++) {
            tabStrip.getChildAt(i).setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    return true;
                }
            });}
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


*/

        contacts = (ListView)findViewById(R.id.contacts_list);
        imageView=(ImageView)findViewById(R.id.imgAvatar);
        add_image=(ImageView)findViewById(R.id.addfriendImage);
        add_text=(TextView)findViewById(R.id.addfriendtext);
        final FloatingActionButton addfriend = (FloatingActionButton) findViewById(R.id.addfriendlist);





        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View header=navigationView.getHeaderView(0);
        TextView name=(TextView)header.findViewById(R.id.Name);
        TextView mail=(TextView)header.findViewById(R.id.mail);
        DatabaseHelper db=new DatabaseHelper(this);
        String national=db.getID();
        User user=new User();
        user=db.get_user(national);
        name.setText(user.getName());
        mail.setText(user.getGmail());



        addfriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // startActivityForResult(new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI), REQUEST_CODE_PICK_CONTACTS);
                Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);

                intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);  //should filter only contacts with phone numbers
                startActivityForResult(intent, PICK_CONTACT);

            }

        });

      contacts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                                     @TargetApi(Build.VERSION_CODES.LOLLIPOP)
                                                     @Override
                                                     public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                                                         //if (view == null) {

                                                         final String Name = ((TextView) view.findViewById(R.id.tvName)).getText().toString();

                                                         final String Phone = ((TextView) view.findViewById(R.id.tvPhone)).getText().toString();

                                                          //Toast.makeText(getActivity(), + "is selected", Toast.LENGTH_LONG).show();

                                                         // Toast.makeText(getActivity(), (position) + "is selected", Toast.LENGTH_LONG).show();
                                                         // view.setSelected(true);

                                                         final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(User_profile.this);


                                                         alertDialogBuilder.setView(R.layout.help_dialog);


                                                         alertDialogBuilder.setPositiveButton("ok",
                                                                 new DialogInterface.OnClickListener() {
                                                                     @Override
                                                                     public void onClick(DialogInterface arg0, int arg1) {
                                                                         // ViewPager viewPager = (ViewPager) getActivity().findViewById(R.id.pager);
                                                                         // viewPager.setCurrentItem(1);
                                                                         //    if (view== null){
                                                                         //     View view= getActivity().getLayoutInflater().inflate(R.layout.contact, null); // reached in case of listview bug in android which is a little chance but to make sure not to crash on NPE
                                                                         // TextView  name = (TextView) view.findViewById(R.id.tvName);
                                                                         //      String Name =name.getText();

                                                                         //CharSequence phone = ((TextView) view.findViewById(R.id.tvPhone)).getText();
                                                                         //   Intent intent = new Intent(getActivity().getBaseContext(), HelpFriend_Tab.class);
                                                                         // Bundle extra=new Bundle();
                                                                         // int img=list.get(position).getAvater();
                                                                         //   Toast.makeText(getActivity(),name + "is selected",Toast.LENGTH_LONG).show();

                                                                         // extra.putInt("img",img);
                                                                         //  extra.putString("name",Name);
                                                                         // extra.putCharSequence("phone",phone);
                                                                         //  alertDialogBuilder.putExtras(extra);
                                                                         //   startActivity(intent);

                                                                         //  intent.putExtras(extra);


                                                                         // getActivity().startActivity(intent);
                                                                      /*   FragmentTransaction ft;
                                                                         FragmentManager fm = getFragmentManager();
                                                                         ft= fm.beginTransaction();
                                                                         HelpFriend_Tab help = new HelpFriend_Tab();
                                                                         Bundle bundle = new Bundle();
                                                                         bundle.putString("name",Name);
                                                                         help.setArguments(bundle);
                                                                        ft.replace(R.id.,HelpFriend_Tab);
                                                                         ft.addToBackStack(null);
                                                                         ft.commit();

*/
                                                                         Intent intent = new Intent(User_profile.this,HelpFriend_Tab.class);

                                                                    //     Bundle bundle = new Bundle();
                                                                         intent.putExtra("name", Name);
                                                                         intent.putExtra("phone", Phone);

                                                                           startActivity(intent);

                                                                      //   Activity activity=new Activity();
                                                                    //  activity.addContentView();
                                                                 /*        new Handler().postDelayed(new Runnable() {
                                                                             @Override
                                                                             public void run() {
                                                                                 Uri gmmIntentUri = Uri.parse("geo:0,0?q=");
                                                                                 Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                                                                                 mapIntent.setPackage("com.google.android.apps.maps");
                                                                                 startActivity(mapIntent);
                                                                             }
                                                                         }, 1000);*/
                                                                     }

                                                                      /*   ViewPager viewPager = (ViewPager) getActivity().findViewById(R.id.pager);
                                                                          viewPager.setCurrentItem(1);*/

/*
                                                                         FragmentTransaction transection=getFragmentManager().beginTransaction();
                                                                         HelpFriend_Tab mfragment=new HelpFriend_Tab();
//using Bundle to send data
                                                                         Bundle bundle=new Bundle();
                                                                         bundle.putString("email",Name);
                                                                      //   bundle.putString("user_name",muser_name);
                                                                        // bundle.putString("phone",mphone_number);
                                                                        mfragment.setArguments(bundle); //data being send to SecondFragment//transection.replace(R.id., mfragment);
                                                                     transection.replace(R.id.fragment1,mfragment);
                                                                         transection.commit();
                            */


                                                                 });
                                                         alertDialogBuilder.setNegativeButton("cancle", new DialogInterface.OnClickListener() {

                                                             public void onClick(DialogInterface dialog, int which) {

                                                                 alertDialogBuilder.setCancelable(true);
                                                             }
                                                         });

                                                         AlertDialog alertDialog = alertDialogBuilder.create();
                                                         alertDialog.show();

                                                     }}
        );

      //  return rootView;

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_CONTACT && resultCode == Activity.RESULT_OK && null != data) {

            Uri contactUri = data.getData();

            retrieveContactName(contactUri);
            retrieveContactNumber(contactUri);
            add_image.setVisibility(View.INVISIBLE);
            add_text.setVisibility(View.INVISIBLE);
            contacts.setVisibility(View.VISIBLE);

            list.add(new phonebook( retrieveContactName(contactUri),retrieveContactNumber(contactUri)));
            adapter = new PhoneAdapter(this,list);
           contacts.setAdapter(adapter);

        }
    }


    private Bitmap retrieveContactPhoto() {

        Bitmap photo = null;

        try {
            InputStream inputStream = ContactsContract.Contacts.openContactPhotoInputStream(getContentResolver(),
                    ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, new Long(contactID)));

            if (inputStream != null) {
                photo = BitmapFactory.decodeStream(inputStream);

            }

            assert inputStream != null;
            inputStream.close();

        } catch (IOException e) {
            e.printStackTrace();

        }
        return photo;
    }


    private String retrieveContactNumber(Uri contactUri) {


        String ContactNumber=null;
        if (null == contactUri) return null;
        String[] phoneProjection = {ContactsContract.CommonDataKinds.Phone.DATA};
        //String[] filePathColumn = { MediaStore.Images.Media.DATA };
        Cursor cursor = getContentResolver().query(contactUri, phoneProjection, null, null, null);

        while (cursor.moveToNext()) {
            ContactNumber = cursor.getString(0);
        }
        cursor.close();
        return ContactNumber;
    }



    private String retrieveContactName(Uri contactUri) {


        String contactName = null;

        // querying contact data store
        Cursor cursor =getContentResolver().query(contactUri, null, null, null, null);

        if (cursor.moveToFirst()) {

            // DISPLAY_NAME = The display name for the contact.
            // HAS_PHONE_NUMBER =   An indicator of whether this contact has at least one phone number.

            contactName = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
        }

        cursor.close();
        return contactName;
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.Setting) {
            Intent intent = new Intent(User_profile.this, setting.class);
            startActivity(intent);
        } else if (id == R.id.Notification) {

        } else if (id == R.id.retreieve) {
            Intent intent = new Intent(User_profile.this, interior_login.class);
            startActivity(intent);
        } else if (id == R.id.selling) {
            Intent intent = new Intent(User_profile.this, selling.class);
            startActivity(intent);
        } else if (id == R.id.about) {
            Intent intent = new Intent(User_profile.this, about.class);
            startActivity(intent);
        } else if (id == R.id.help) {
            Intent intent = new Intent(User_profile.this, help.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}





