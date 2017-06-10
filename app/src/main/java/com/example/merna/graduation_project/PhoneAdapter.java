package com.example.merna.graduation_project;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class PhoneAdapter extends BaseAdapter {
    private Context mContext;
    private List<phonebook> mListPhoneBook;

    public PhoneAdapter(Context context, List<phonebook> list) {
        mContext = context;
        mListPhoneBook =list;

    }

    @Override
    public int getCount() {
        return mListPhoneBook.size();
    }

    @Override
    public Object getItem(int pos) {
        return mListPhoneBook.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return pos;
    }

    @Override
    public View getView(int pos, View convertView, ViewGroup parent) {
        // get selected entry
        phonebook entry = mListPhoneBook.get(pos);

        // inflating list view layout if null
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(R.layout.contact, null);

        }

        // set avatar
    /*  ImageView ivAvatar = (ImageView)convertView.findViewById(R.id.imgAvatar);
        ivAvatar.setImageBitmap(entry.getAvater());
*/
        // set name
        TextView tvName = (TextView)convertView.findViewById(R.id.tvName);
        tvName.setText(entry.getName());

        // set phone
        TextView tvPhone = (TextView)convertView.findViewById(R.id.tvPhone);
        tvPhone.setText(entry.getPhone());


        return convertView;
}

}