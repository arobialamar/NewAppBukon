package arobial.ta.bukukondangan;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MenuActivity extends AppCompatActivity {
    LinearLayout DataUang, DataBeras, LihatData, LapKondangan;
    TextView tampil_kategori;
    DatabaseReference getRef;
    String getUserID;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        DataUang = findViewById(R.id.ll_dtUang);
        DataBeras = findViewById(R.id.ll_dtBeras);
        LihatData = findViewById(R.id.ll_liatData);
        LapKondangan = findViewById(R.id.ll_laporan);
        tampil_kategori = findViewById(R.id.kategori_hajatan);

        //Mendapatkan User ID dari akun yang terautentikasi
        user = FirebaseAuth.getInstance().getCurrentUser();
        getUserID = user.getUid();
        getRef = FirebaseDatabase.getInstance().getReference();

        DataUang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toLogin = new Intent(MenuActivity.this, TambahData.class);
                startActivity(toLogin);
            }
        });

//        DataBeras.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent toLogin = new Intent(MenuActivity.this, TambahDataBeras.class);
//                startActivity(toLogin);
//            }
//        });

        LihatData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toLogin = new Intent(MenuActivity.this, LihatDataKondangan.class);
                startActivity(toLogin);
            }
        });

        LapKondangan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toLogin = new Intent(MenuActivity.this, LaporanKondangan.class);
                startActivity(toLogin);
            }
        });

        getRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String kategori_hjt = dataSnapshot.child(getUserID).child("Kategori Hajatan").child("kategori").getValue(String.class);

                tampil_kategori.setText(kategori_hjt);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_toolbar, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
            if (item.getItemId() == R.id.logout) {
                FirebaseAuth.getInstance().signOut();
                Intent tuLogin = new Intent(MenuActivity.this, LoginActivity.class);
                tuLogin.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                tuLogin.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(tuLogin);
            }

        return true;
    }
}
