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

import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;
import com.yb.doctors.R;
import com.yb.doctors.model.Sector;
import java.util.List;
public class SectorsAdapter extends RecyclerView.Adapter<SectorsAdapter.ViewHolder> {
    private List<Sector> sectors;
    public SectorsAdapter(List<Sector> sectors){
        this.sectors = sectors;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        ImageView imageView;
        TextView textView;
        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);
            imageView = itemView.findViewById(R.id.item_specialty_photo);
            textView = itemView.findViewById(R.id.item_specialty_text);


        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();


        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View view = inflater.inflate(R.layout.item_specialty, parent, false);
        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
           holder.textView.setText(sectors.get(position).getNameAr());
        //   holder.imageView.setImageResource(R.drawable.lungs);
        String imageUrl =
                "https://firebasestorage.googleapis.com/v0/b/call-doctor-8720d.appspot.com/o/images%2F"
                +sectors.get(position).getImageKey()
                +".png?alt=media&token=016b278d-446f-4ce1-aee6-dac1b7d0cb37";
        Picasso.get()
                .load(imageUrl)
                .resize(100, 100)
                .centerCrop().networkPolicy(NetworkPolicy.OFFLINE)
                .into(holder.imageView, new Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError(Exception e) {
                        Picasso.get()
                                .load(imageUrl)
                                .resize(100, 100)
                                .centerCrop().into(holder.imageView);
                    }
                })  ;  }
    @Override
    public int getItemCount() {
        return sectors.size();
    }

}