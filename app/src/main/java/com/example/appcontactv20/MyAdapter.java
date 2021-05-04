package com.example.appcontactv20;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.net.Inet4Address;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private static ArrayList<Contact> mContacts;
    private static Context context;

    public MyAdapter(ArrayList<Contact> mContacts, Context context) {
        this.mContacts = mContacts;
        this.context = context;
    }

    public static ArrayList<Contact> getmContacts() {
        return mContacts;
    }

    public static void setmContacts(ArrayList<Contact> mContacts) {
        MyAdapter.mContacts = mContacts;
    }

    public static Context getContext() {
        return context;
    }

    public static void setContext(Context context) {
        MyAdapter.context = context;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contact, parent,false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        holder.tvName.setText(mContacts.get(position).getName());
        holder.tvCall.setText(mContacts.get(position).getMobile());
        holder.tvSms.setText(mContacts.get(position).getMobile());
        holder.tvEmail.setText(mContacts.get(position).getEmail());

    }
    @Override
    public int getItemCount() {
        return mContacts.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public View view;
        public TextView tvName, tvCall, tvSms, tvEmail;
        private LinearLayout layoutInfor;
        public MyViewHolder(View view) {
            super(view);
            this.view = view;

            layoutInfor = this.view.findViewById(R.id.layout_infor);
            tvName = this.view.findViewById(R.id.name);
            tvName.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    if(layoutInfor.getVisibility() == View.GONE){
                        layoutInfor.setVisibility(View.VISIBLE);
                    }
                    else{
                        layoutInfor.setVisibility(View.GONE);
                    }
                }
            });
            tvCall = view.findViewById(R.id.phone);
            tvCall.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();
                    String tel = mContacts.get(pos).getMobile();
                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", tel, null));

                    context.startActivity(intent);
                }
            });
            tvSms = this.view.findViewById(R.id.sms);
            tvSms.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();
                    String addr = mContacts.get(pos).getMobile();
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms", addr,null));
                    intent.putExtra(Intent.EXTRA_PHONE_NUMBER, new String[]{addr});

                    Intent shareIntent = Intent.createChooser(intent, null);
                    context.startActivity(shareIntent);
                }
            });
            tvEmail = this.view.findViewById(R.id.email);
            tvEmail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();
                    String email = mContacts.get(getAdapterPosition()).getEmail();
                    Intent intent = new Intent(Intent.ACTION_SEND,Uri.fromParts("email", email, null));
                    intent.setType("text/html");
                    intent.putExtra(Intent.EXTRA_EMAIL, new String[]{email});

                    context.startActivity(intent);
                }
            });
        }
    }
}



