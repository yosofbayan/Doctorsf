package com.yb.doctors.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yb.doctors.R;
import com.yb.doctors.model.Doctor;


import java.util.List;


public class TopsAdapter extends RecyclerView.Adapter<TopsAdapter.ViewHolder> {
    private List<Doctor> doctors;

    public TopsAdapter(List<Doctor> doctors){
        this.doctors = doctors;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        private ImageView imageView;
        private TextView nameTextView;
        private TextView specialtyTextView;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);
            imageView = itemView.findViewById(R.id.item_top_doctor_photo);
            nameTextView = itemView.findViewById(R.id.item_top_name);
            specialtyTextView = itemView.findViewById(R.id.item_top_speciality);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();


        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View view = inflater.inflate(R.layout.item_top_doctor, parent, false);
        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {


        holder.nameTextView.setText("Dr Mohammad Al Ahmad ");
        holder.specialtyTextView.setText("Heart Specialty");
        holder.imageView.setImageResource(R.drawable.sign_up_btn_shape);


    }

    @Override
    public int getItemCount() {
        return doctors.size();
    }

}