package com.yb.doctors;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageReference;
import com.yb.doctors.adapters.SectorsAdapter;
import com.yb.doctors.model.Sector;

import java.util.ArrayList;
import java.util.Map;

public class MainPageActivity extends AppCompatActivity {
    ArrayList<String> imagelist;
    StorageReference root;
    /*private void collectPhoneNumbers(Map<String,Object> users) {

        ArrayList<String> phoneNumbers = new ArrayList<>();

        //iterate through each user, ignoring their UID
        for (Map.Entry<String, Object> entry : users.entrySet()){

            //Get user map
            Map singleUser = (Map) entry.getValue();
            //Get phone field and append to list
            phoneNumbers.add(singleUser.get("Name_AR").toString());
        }

        System.out.println("HI   " +phoneNumbers.toString());
    }*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        /*
        //Get datasnapshot at your "users" root node
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Home").child("specialties");
        ref.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        //Get map of users in datasnapshot
                        collectPhoneNumbers((Map<String,Object>) dataSnapshot.getValue());
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        //handle databaseError
                    }
                });

*/





















        imagelist=new ArrayList<>();
        RecyclerView recyclerView = findViewById(R.id.main_page_specialties_recycler_view);
        SectorsAdapter sectorsAdapter =new SectorsAdapter(imagelist);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainPageActivity.this,RecyclerView.HORIZONTAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        StorageReference listRef = FirebaseStorage.getInstance()
                .getReference().child("images");
        listRef.listAll().addOnSuccessListener(new OnSuccessListener<ListResult>() {
            @Override
            public void onSuccess(ListResult listResult) {
                for(StorageReference file:listResult.getItems()){
                    file.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            imagelist.add(uri.toString());
                            Log.e(" Itemvalue : " , uri.toString());
                        }
                    }).addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            recyclerView.setAdapter(sectorsAdapter);
                        }
                    });
                }
            }
        });


    }





















        /*
        ArrayList sectors = new ArrayList();

        for(int i=0 ; i < 10 ; i++)
            sectors.add(new Sector());



               RecyclerView recyclerView = findViewById(R.id.main_page_specialties_recycler_view);
                SectorsAdapter sectorsAdapter =new SectorsAdapter(sectors);
                recyclerView.setAdapter(sectorsAdapter);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainPageActivity.this,RecyclerView.HORIZONTAL,false);
                recyclerView.setLayoutManager(linearLayoutManager);



*/
    }
