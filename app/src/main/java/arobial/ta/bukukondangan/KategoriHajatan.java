package arobial.ta.bukukondangan;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class KategoriHajatan extends Activity {
    EditText kategori;
    Button selesai;
    DatabaseReference getRef;
    String getUserID;
    FirebaseUser user;
    DataKondangan addData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kategori);

        kategori = findViewById(R.id.et_kategori);
        selesai = findViewById(R.id.btn_finish);

        addData = new DataKondangan();

        //Mendapatkan User ID dari akun yang terautentikasi
        user = FirebaseAuth.getInstance().getCurrentUser();
        getUserID = user.getUid();
        getRef = FirebaseDatabase.getInstance().getReference().child(getUserID).child("Kategori Hajatan");

        selesai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnSelesai();
            }
        });

    }

    private void getValues(){
        addData.setKategori(kategori.getText().toString());
    }

    public void btnSelesai(){
        getValues();

        String isiKategori = kategori.getText().toString();

        if (isiKategori.isEmpty()){
            kategori.setError("Masukan Kategori Hajatan");
            kategori.requestFocus();
        }
        getRef.setValue(addData).addOnSuccessListener(this, new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                kategori.setText("");
                startActivity(new Intent(KategoriHajatan.this, MenuActivity.class));
            }
        });
    }
}
