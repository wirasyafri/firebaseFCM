package com.ti4e.wira.platform05fcm;

        import android.content.Intent;
        import android.support.annotation.NonNull;
        import android.support.annotation.Nullable;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.AdapterView;
        import android.widget.ArrayAdapter;
        import android.widget.ListView;
        import android.widget.Toast;

        import com.google.firebase.database.ChildEventListener;
        import com.google.firebase.database.DataSnapshot;
        import com.google.firebase.database.DatabaseError;
        import com.google.firebase.database.DatabaseReference;
        import com.google.firebase.database.FirebaseDatabase;
        import com.google.firebase.database.ValueEventListener;
        import com.google.firebase.storage.FirebaseStorage;
        import com.google.firebase.storage.StorageReference;

        import java.util.ArrayList;
        import java.util.HashMap;
        import java.util.List;
        import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private ListView listpiew;
    private ArrayList<String> gambarname = new ArrayList<>();
    public ArrayList<String>dataurl = new ArrayList<>();
    private FirebaseDatabase database;
    private StorageReference mStorageRef;
    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview);
        database = FirebaseDatabase.getInstance();
        mDatabase = database.getReference();
        mStorageRef = FirebaseStorage.getInstance().getReference("ImageSwap/");
        mStorageRef = FirebaseStorage.getInstance().getReference();
        listpiew =(ListView) findViewById(R.id.listview);
        final ArrayAdapter<String> Arrayadapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,gambarname);
        listpiew.setAdapter(Arrayadapter);
        listpiew.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent nwe = new Intent(view.getContext(),DataDetail.class);
                Bundle b = new Bundle();
                b.putLong("id",id);
                b.putString("url",dataurl.get((int) id));
                nwe.putExtras(b);
                startActivity(nwe);
            }
        });
        mDatabase.child("ImageSwap").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

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
