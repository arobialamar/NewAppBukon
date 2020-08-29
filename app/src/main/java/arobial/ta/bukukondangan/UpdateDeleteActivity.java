package arobial.ta.bukukondangan;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UpdateDeleteActivity extends AppCompatActivity {
    public static final String EXTRA_DATAKONDANGAN = "extra_dakon";
    EditText namaEt, dit_kecamatan, dit_kelurahan, alamatEt, rpEt, literEt;
    DatabaseReference getRef;
    String getUserID;
    FirebaseUser user;

    long maxid=0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete);

//        Mendapatkan User ID dari akun yang terautentikasi
        user = FirebaseAuth.getInstance().getCurrentUser();
        assert user != null;
        getUserID = user.getUid();
        getRef = FirebaseDatabase.getInstance().getReference().child(getUserID).child("Data Kondangan");

        namaEt = findViewById(R.id.edit_nama);
        dit_kecamatan = findViewById(R.id.edit_kcmt);
        dit_kelurahan = findViewById(R.id.edit_desa);
        alamatEt = findViewById(R.id.edit_alamat);
        rpEt = findViewById(R.id.edit_nominal);
        literEt = findViewById(R.id.edit_liter);

        DataKondangan dataKondangan = getIntent().getParcelableExtra(EXTRA_DATAKONDANGAN);

        namaEt.setText(dataKondangan.getNama());
        alamatEt.setText(dataKondangan.getAlamat());
        dit_kecamatan.setText(dataKondangan.getKecamatan());
        dit_kelurahan.setText(dataKondangan.getKelurahan());
        rpEt.setText(dataKondangan.getRp());
        literEt.setText(dataKondangan.getLiter());

        getRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                if (snapshot.exists())
//                    maxid=(snapshot.getChildrenCount());
                for (DataSnapshot ds: snapshot.getChildren()) {
//                    DaKonList.add(ds.getValue(DataKondangan.class));
                    Log.d("myTag" ,ds.getKey());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void btnUbah_click(View view) {
        getRef.child("3").child("nama").setValue(namaEt.getText().toString());
//        getRef.child(String.valueOf(maxid+1)).child("nama").setValue(namaEt.getText().toString());
    }

    public void btnHapus_Click(View view) {
        getRef.child("3").removeValue();
//        getRef.child(String.valueOf(maxid+1)).removeValue();
    }
}