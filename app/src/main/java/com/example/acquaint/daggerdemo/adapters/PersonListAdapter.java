package com.example.acquaint.daggerdemo.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.acquaint.daggerdemo.R;
import com.example.acquaint.daggerdemo.interfaces.ActivityContext;
import com.example.acquaint.daggerdemo.model.User;

import java.util.List;

public class PersonListAdapter extends RecyclerView.Adapter<PersonListAdapter.ViewHolder>{

    private List<User> listofUser;
    private Context mContext;

    public PersonListAdapter(@ActivityContext Context context, List<User> listofUser) {
        mContext = context;
        this.listofUser = listofUser;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.row_user,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        User user = listofUser.get(holder.getAdapterPosition());
        holder.txtName.setText(user.getName());
        holder.txtSurname.setText(user.getSurname());
        holder.txtPhone.setText(user.getPhone());

        if(holder.getAdapterPosition()%2==0){
            holder.itemView.setBackgroundColor(mContext.getResources().getColor(android.R.color.holo_blue_light));
        }else {
            holder.itemView.setBackgroundColor(mContext.getResources().getColor(android.R.color.holo_orange_light));
        }
    }

    @Override
    public int getItemCount() {
        return listofUser.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtName;
        TextView txtSurname;
        TextView txtPhone;

        public ViewHolder(View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txt_name);
            txtSurname = itemView.findViewById(R.id.txt_surname);
            txtPhone = itemView.findViewById(R.id.txt_phone);
        }
    }
}
