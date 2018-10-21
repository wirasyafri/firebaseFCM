package com.ti4e.wira.platform05fcm;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.IOException;
import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;

public class ListFragment extends Fragment {

    private long idImg;
    private ListView listpiew;
    private ArrayList<String> gambarname = new ArrayList<>();
    public ArrayList<String>dataurl = new ArrayList<>();
    private FirebaseDatabase database;
    private StorageReference mStorageRef;
    private DatabaseReference mDatabase;
    public ListFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.listview, container, false);
    }

    public void setImgs(long id) {
        this.idImg = id;
    }

    public Bitmap statisImg(int draw) {
        Bitmap img = BitmapFactory.decodeResource(getActivity().getResources(), draw);
        return img;
    }

    @Override
    public void onStart() {

        super.onStart();
        View view = getView();
        if (view != null)
        {
            database = FirebaseDatabase.getInstance();
            mDatabase = database.getReference();
            mStorageRef = FirebaseStorage.getInstance().getReference("ImageSwap/");
            mStorageRef = FirebaseStorage.getInstance().getReference();
            listpiew =(ListView) view.findViewById(R.id.listview);
            final ArrayAdapter<String> Arrayadapter = new ArrayAdapter<>(view.getContext(), android.R.layout.simple_list_item_1, gambarname);
            listpiew.setAdapter(Arrayadapter);
            listpiew.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    View fragmentDetail = view.findViewById(R.id.detailingFrag);
                    if (fragmentDetail != null)
                    {
                        NabiDetailFragment deta = new NabiDetailFragment();

                        FragmentTransaction fragTrans = getFragmentManager().beginTransaction();
                        deta.setImgs(id);
                        fragTrans.replace(R.id.detailingFrag, deta);
                        fragTrans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                        fragTrans.commit();
                    }
                    else {
                        Intent nwe = new Intent(view.getContext(), DataDetail.class);
                        Bundle b = new Bundle();
                        b.putLong("id", id);
                        b.putString("url", dataurl.get((int) id));
                        nwe.putExtras(b);
                        startActivity(nwe);
                    }
                }
            });
            mDatabase.child("ImageSwap").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    gambarname.clear();
                    dataurl.clear();
                    for (DataSnapshot data : dataSnapshot.getChildren())
                    {

                        String val = data.child("/url").getValue().toString();
                        String name = data.child("/nmFile").getValue().toString();
                        gambarname.add(name);
                        dataurl.add(val);
                    }
                    Arrayadapter.notifyDataSetChanged();

                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
    }


}

