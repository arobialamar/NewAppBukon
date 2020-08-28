package arobial.ta.bukukondangan;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;

public class UpdateDeleteActivity extends AppCompatActivity {

    public static final String EXTRA_DATAKONDANGAN = "extra_dakon";

    EditText namaEt, dit_kecamatan, dit_kelurahan, alamatEt, rpEt, literEt;

//    DatabaseReference getRef;
//    String getUserID;
//    FirebaseUser user;

    Button btn_rubah;
    Button btn_apus;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete);

        //Mendapatkan User ID dari akun yang terautentikasi
//        user = FirebaseAuth.getInstance().getCurrentUser();
//        assert user != null;
//        getUserID = user.getUid();
//        getRef = FirebaseDatabase.getInstance().getReference().child(getUserID).child("Data Kondangan");

        namaEt = findViewById(R.id.edit_nama);
        dit_kecamatan = findViewById(R.id.edit_kcmt);
        dit_kelurahan = findViewById(R.id.edit_desa);
        alamatEt = findViewById(R.id.edit_alamat);
        rpEt = findViewById(R.id.edit_nominal);
        literEt = findViewById(R.id.edit_liter);

        btn_rubah = findViewById(R.id.btn_ubah);
        btn_apus = findViewById(R.id.btn_hapus);

        DataKondangan dataKondangan = getIntent().getParcelableExtra(EXTRA_DATAKONDANGAN);

        namaEt.setText(dataKondangan.getNama());
        alamatEt.setText(dataKondangan.getAlamat());
        dit_kecamatan.setText(dataKondangan.getKecamatan());
        dit_kelurahan.setText(dataKondangan.getKelurahan());
        rpEt.setText(dataKondangan.getRp());
        literEt.setText(dataKondangan.getLiter());
        dit_kecamatan.setSelection(getIndex_SpinnerItem());



    }

    private int getIndex_SpinnerItem() {
        return 0;
    }

    private int getIndex_SpinnerItem(Spinner spinner, String item){
        int index = 0;
        for (int i=0; i<spinner.getCount(); i++){
            if (spinner.getItemAtPosition(i).equals(item)){
                index = 1;
                break;
            }
        }
        return index;
    }
}
