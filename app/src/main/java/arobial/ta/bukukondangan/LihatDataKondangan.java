package arobial.ta.bukukondangan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class LihatDataKondangan extends AppCompatActivity {

    ListView ll;
//    RecyclerView recyclerView;
    ArrayAdapter<String> adapter;
    DatabaseReference dataRef;
    FirebaseUser user;

    List<String> itemlist;

    String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_data);
        ll = findViewById(R.id.lView);
        user = FirebaseAuth.getInstance().getCurrentUser();
        assert user != null;
        uid = user.getUid();
        itemlist = new ArrayList<>();
        dataRef = FirebaseDatabase.getInstance().getReference();

        dataRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                itemlist.clear();

                for (int i = 0 ; i<2000 ; i++) {
//                int i=0;
//                String nama_anda  ;
//                String alamat_anda;
//                String hobi_anda;
//                String status ;
////              while (nama_anda = null){

                    String nama1 = dataSnapshot.child(uid).child("Data Kondangan").child(String.valueOf(i+1)).child("nama1").getValue(String.class);
//                    String kecamadan = dataSnapshot.child(uid).child("Data Uang").child(String.valueOf(i)).child("kecamatan").getValue(String.class);
//                    String keluradan = dataSnapshot.child(uid).child("Data Uang").child(String.valueOf(i)).child("kelurahan").getValue(String.class);
                    String kecamatan = dataSnapshot.child(uid).child("Data Kondangan").child(String.valueOf(i+1)).child("kecamatan").getValue(String.class);
                    String uang = dataSnapshot.child(uid).child("Data Kondangan").child(String.valueOf(i+1)).child("rp").getValue(String.class);
                    String liter = dataSnapshot.child(uid).child("Data Kondangan").child(String.valueOf(i+1)).child("liter").getValue(String.class);

//                Toast.makeText(new LihatDataKondangan(),"Nama: "+nama_anda).show();
                    if (nama1==null){
                        break;
                    }
//                    System.out.println("Nama " + nama_anda);
                    itemlist.add("Nama : " + nama1);
//                    itemlist.add("Kecamatan : " + kecamadan);
//                    itemlist.add("Kelurahan : " + keluradan);
                    itemlist.add("Kecamatan : " + kecamatan);
                    itemlist.add("Uang : " + uang);
                    itemlist.add("Beras : " + liter + " Liter");
//                    i++;

                }

//                new RecyclerView_Config().setConfig(recyclerView, LihatDataKondangan.this);
                adapter = new ArrayAdapter<>(LihatDataKondangan.this, android.R.layout.simple_list_item_1, itemlist);
                ll.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


}
