package com.example.merna.graduation_project;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentUris;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by merna on 4/22/2017.
 */
public class MyFriend_Tab extends Fragment {
    ListView listView_contacts;
   // private static final String TAG = MyFriend_Tab.class.getSimpleName();
    //private static final int REQUEST_CODE_PICK_CONTACTS = 1;
   private Uri  contactUri;
    private String contactID;     // contacts unique ID
    private TextView contact_name;
    private TextView contact_number;
    private ImageView imageView;
    private ImageView add_image;
    private ListView contacts;
    private PhoneAdapter adapter;
    private List<phonebook> list = new ArrayList<phonebook>();
    private static int PICK_CONTACT = 1;
    private static final String[] phoneProjection = new String[]{ContactsContract.CommonDataKinds.Phone.DATA};


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.myfriend_tab, container, false);
        listView_contacts = (ListView) rootView.findViewById(R.id.contacts_list);
        imageView=(ImageView)rootView.findViewById(R.id.imgAvatar);

        FloatingActionButton addfriend = (FloatingActionButton) rootView.findViewById(R.id.addfriendlist);

        addfriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               // startActivityForResult(new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI), REQUEST_CODE_PICK_CONTACTS);
                Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);

                intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);  //should filter only contacts with phone numbers
                startActivityForResult(intent, PICK_CONTACT);
            }

        });

        listView_contacts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                                     @TargetApi(Build.VERSION_CODES.LOLLIPOP)
                                                     @Override
                                                     public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                                                         //if (view == null) {

                                                        final String Name = ((TextView) view.findViewById(R.id.tvName)).getText().toString();

                                                        final String Phone = ((TextView) view.findViewById(R.id.tvPhone)).getText().toString();

                                                         Toast.makeText(getActivity(), Name + Phone+ "is selected", Toast.LENGTH_LONG).show();

                                                         final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());


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
                                                                         HelpFriend_Tab mapFragment = new HelpFriend_Tab();
                                                                         Bundle bundle = new Bundle();
                                                                         bundle.putString("name", Name);
                                                                         bundle.putString("phone", Phone);
                                                                         // bundle.putString("board_id", board_id);
                                                                    /*     mapFragment.setArguments(bundle);
                                                                         FragmentManager manger = getFragmentManager();
                                                                         manger.beginTransaction().replace(R.id.fragment1, mapFragment).commit();
                                                                         new Handler().postDelayed(new Runnable() {
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

                                                         return rootView;

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_CONTACT && resultCode == Activity.RESULT_OK && null != data) {

             Uri contactUri = data.getData();

             retrieveContactName(contactUri);
            retrieveContactNumber(contactUri);

            list.add(new phonebook( retrieveContactName(contactUri),retrieveContactNumber(contactUri)));
            adapter = new PhoneAdapter(this.getActivity(),list);
            listView_contacts.setAdapter(adapter);

        }
    }


    private Bitmap retrieveContactPhoto() {

        Bitmap photo = null;

        try {
            InputStream inputStream = ContactsContract.Contacts.openContactPhotoInputStream(getActivity().getContentResolver(),
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
        Cursor cursor = getActivity().getContentResolver().query(contactUri, phoneProjection, null, null, null);

        while (cursor.moveToNext()) {
            ContactNumber = cursor.getString(0);
        }
        cursor.close();
return ContactNumber;
    }



    private String retrieveContactName(Uri contactUri) {


        String contactName = null;

        // querying contact data store
        Cursor cursor =getActivity().getContentResolver().query(contactUri, null, null, null, null);

        if (cursor.moveToFirst()) {

            // DISPLAY_NAME = The display name for the contact.
            // HAS_PHONE_NUMBER =   An indicator of whether this contact has at least one phone number.

            contactName = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
        }

        cursor.close();
        return contactName;
    }
}
