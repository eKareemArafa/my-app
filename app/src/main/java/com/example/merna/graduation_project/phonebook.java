
package com.example.merna.graduation_project;

        import android.graphics.Bitmap;
        import android.media.Image;
        import android.widget.TextView;

public class phonebook {
    private Bitmap mAvatar;
    private String mName;
    private String mPhone;


    public phonebook( String name, String phone) {
        //mAvatar = avatar;
        mName = name;
        mPhone = phone;

    }

    public phonebook( TextView contact_name, TextView contact_number) {

    }

    public void setAvatar(Bitmap avatar) {
        mAvatar = avatar;
    }

    public void setName(String name) {
        mName = name;
    }
    public String getName() {
        return mName;
    }

    public void setPhone(String phone) {
        mPhone = phone;
    }
    public String getPhone() {
        return mPhone;
    }


    public Bitmap getAvater() {
        return mAvatar;
    }

}
