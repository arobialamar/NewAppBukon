package arobial.ta.bukukondangan;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class TambahData extends AppCompatActivity {
    Spinner kecamatan, kelurahan;
    EditText Nama, Alamat, Nominal, Liter;
    Button Tambah;
    DatabaseReference getRef;
    String getUserID;
    FirebaseUser user;
    DataKondangan addData;
    long maxid=0;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_data);

        kecamatan = findViewById(R.id.sp_kcmt);
        kelurahan = findViewById(R.id.sp_desa);
        Nama = findViewById(R.id.nama);
        Liter = findViewById(R.id.liter);
        Alamat = findViewById(R.id.lainnya);
        Nominal = findViewById(R.id.nominal);
        Tambah = findViewById(R.id.btn_tambah);

        addData = new DataKondangan();

        //Mendapatkan User ID dari akun yang terautentikasi
        user = FirebaseAuth.getInstance().getCurrentUser();
        assert user != null;
        getUserID = user.getUid();
        getRef = FirebaseDatabase.getInstance().getReference().child(getUserID).child("Data Kondangan");


        final List<String> daftar_kecamatan = new ArrayList<>();
        daftar_kecamatan.add(0,"- Pilih Kecamatan -");
        daftar_kecamatan.add("Bongas");
        daftar_kecamatan.add("Gabuswetan");
        daftar_kecamatan.add("Kroya");

        final String[] select = {"- Pilih Kelurahan -"};

        final String[] gawet =
                {"- Pilih Kelurahan -","Babakanjaya", "Drunten Kulon","Drunten Wetan",
                        "Gabuskulon","Gabuswetan","Kedokangabus","Kedungdawa","Rancahan","Rancamulya","Sekarmulya"};
        final String[] bongas =
                {"- Pilih Kelurahan -","Bongas","Cipaat","Cipedang","Kertajaya","Kertamulya","Margamulya","Plawangan","Sidamulya"};
        final String[] kroya =
                {"- Pilih Kelurahan -","Jayamulya","Kroya","Sukamelang","Sukaslamet","Sumbon","Tanjungkerta","Temiyang","Temiyangsari"};


        ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, daftar_kecamatan);
        kecamatan.setAdapter(adapter);

        kecamatan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position==0){
                    ArrayAdapter<String> adapter1;
                    adapter1 = new ArrayAdapter<>(TambahData.this,android.R.layout.simple_spinner_dropdown_item, select);
                    kelurahan.setAdapter(adapter1);
                }
                if (position==1){
                    ArrayAdapter<String> adapter2;
                    adapter2 = new ArrayAdapter<>(TambahData.this,android.R.layout.simple_spinner_dropdown_item, bongas);
                    kelurahan.setAdapter(adapter2);
                }
                if (position==2){
                    ArrayAdapter<String> adapter3;
                    adapter3 = new ArrayAdapter<>(TambahData.this,android.R.layout.simple_spinner_dropdown_item, gawet);
                    kelurahan.setAdapter(adapter3);
                }
                if (position==3){
                    ArrayAdapter<String> adapter4;
                    adapter4 = new ArrayAdapter<>(TambahData.this,android.R.layout.simple_spinner_dropdown_item, kroya);
                    kelurahan.setAdapter(adapter4);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnTambah();
            }
        });

        getRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists())
                    maxid=(dataSnapshot.getChildrenCount());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void getValues(){
        addData.setNama(Nama.getText().toString());
        addData.setKecamatan(kecamatan.getSelectedItem().toString());
        addData.setKelurahan(kelurahan.getSelectedItem().toString());
        addData.setAlamat(Alamat.getText().toString());
        addData.setRp(Nominal.getText().toString());
        addData.setLiter(Liter.getText().toString());

    }

    public void btnTambah(){
                getValues();

                String isiNama1 = Nama.getText().toString();
//                String isiCamat = kecamatan.getSelectedItem().toString();
//                String isiLurah = kelurahan.getSelectedItem().toString();
                String isiLamat = Alamat.getText().toString();
                String isiRp = Nominal.getText().toString();

                if (isiNama1.isEmpty()){
                    Nama.setError("Masukan Nama Utama");
                    Nama.requestFocus();
                }
                else if (isiLamat.isEmpty()){
                    Alamat.setError("Masukan nama");
                    Alamat.requestFocus();
                }
                else if (isiRp.isEmpty()){
                    Nominal.setError("Masukan nama");
                    Nominal.requestFocus();
                }else{
//                    String Key = getRef.child("Pengguna").child(getUserID).child("Data Uang").push().getKey();
                    getRef.child(String.valueOf(maxid+1)).setValue(addData).addOnSuccessListener(this, new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Nama.setText("");
                            kecamatan.setSelection(0);
                            kelurahan.setSelection(0);
                            Alamat.setText("");
                            Nominal.setText("");
                            Liter.setText("");
                            Snackbar.make(findViewById(R.id.btn_tambah), "Data berhasil ditambahkan", Snackbar.LENGTH_LONG).show();
                        }
                    });
                }
    }

    //menampilkan menu toolbar (logout)
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_toolbar, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.logout) {
            FirebaseAuth.getInstance().signOut();
            Intent tuLogin = new Intent(TambahData.this, LoginActivity.class);
            tuLogin.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            tuLogin.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(tuLogin);
        }
        return true;
    }


}
