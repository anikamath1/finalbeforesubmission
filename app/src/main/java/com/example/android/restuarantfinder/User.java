package com.example.android.restuarantfinder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class User extends RecyclerView.Adapter<User.ViewHolder> {
    static ArrayList<String> name;
    ArrayList<ArrayList> list;
    User(ArrayList<String> name, ArrayList<ArrayList> list)
    {
        this.name=name;
        this.list=list;
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
       // TextView obj;
       // obj=holder.itemView.findViewById(R.id.manager_username);
        //TextView username=obj.findViewById(R.id.manager_username);
        holder.username.setText(name.get(position).toString());
        RecyclerView section;
        RecyclerView.Adapter adapter;
        section=holder.itemView.findViewById(R.id.card_view);
        adapter=new UserList2(list.get(position));


        //Log.d("HIIII","yuhoo");
        section.setAdapter(adapter);



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public User.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.manager_rowbill, parent, false);
        return new User.ViewHolder(view);
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView username;
       public RecyclerView obj;


        public ViewHolder(View itemView) {
            super(itemView);
            username=itemView.findViewById(R.id.manager_username);

            //obj=itemView.findViewById(R.id.card_view);


        }
    }
}
