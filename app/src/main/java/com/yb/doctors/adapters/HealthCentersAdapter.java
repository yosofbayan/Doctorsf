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
import com.yb.doctors.model.FourHealthCenterObjects;

import java.util.List;


public class HealthCentersAdapter extends RecyclerView.Adapter<HealthCentersAdapter.ViewHolder> {
    private List<FourHealthCenterObjects> listOfFourHealthCenterObject;

    public HealthCentersAdapter(List<FourHealthCenterObjects> fourHealthCenterObjects){
        this.listOfFourHealthCenterObject = fourHealthCenterObjects;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        private ImageView imageView1,imageView2,imageView3,imageView4;


        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);
            imageView1 = itemView.findViewById(R.id.item_health_center_first_photo);
            imageView2 = itemView.findViewById(R.id.item_health_center_second_photo);
            imageView3 = itemView.findViewById(R.id.item_health_center_third_photo);
            imageView4 = itemView.findViewById(R.id.item_health_center_forth_photo);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();


        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View view = inflater.inflate(R.layout.item_health_center, parent, false);
        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

       // set the rest of the three ImageViews like this commented snippet
      /*  FourHealthCenterObjects fourHealthCenterObjects = listOfFourHealthCenterObject.get(position);
        if(fourHealthCenterObjects.firstHealthCenterObject!=null)
            holder.imageView1.setImageResource();*/

      //Dummy data
        holder.imageView1.setImageResource(R.drawable.photo_card1);
        holder.imageView2.setImageResource(R.drawable.photo_card1);
        holder.imageView3.setImageResource(R.drawable.photo_card1);
        holder.imageView4.setImageResource(R.drawable.photo_card1);
    }

    @Override
    public int getItemCount() {
        return listOfFourHealthCenterObject.size();
    }

}