package arobial.ta.bukukondangan;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class UpdateDeleteActivity extends AppCompatActivity {
    EditText namaEt, dit_kecamatan, dit_kelurahan, alamatEt, rpEt, literEt;
    Button tombol_ubah, tombol_hapus;
    String key, nama, kecamatan, kelurahan, alamat, rp, liter;
    Spinner statusSp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete);

        key = getIntent().getStringExtra("key");
        nama = getIntent().getStringExtra("nama");
        kecamatan = getIntent().getStringExtra("kecamatan");
        kelurahan = getIntent().getStringExtra("kelurahan");
        alamat = getIntent().getStringExtra("alamat");
        rp = getIntent().getStringExtra("rp");
        liter = getIntent().getStringExtra("liter");

        namaEt = findViewById(R.id.edit_nama);
        namaEt.setText(nama);
        dit_kecamatan = findViewById(R.id.edit_kcmt);
        dit_kecamatan.setText(kecamatan);
        dit_kelurahan = findViewById(R.id.edit_desa);
        dit_kelurahan.setText(kelurahan);
        alamatEt = findViewById(R.id.edit_alamat);
        alamatEt.setText(alamat);
        rpEt = findViewById(R.id.edit_nominal);
        rpEt.setText(rp);
        literEt = findViewById(R.id.edit_liter);
        literEt.setText(liter);
        statusSp = findViewById(R.id.sp_status);

        tombol_ubah = findViewById(R.id.btn_ubah);
        tombol_hapus = findViewById(R.id.btn_hapus);

        final List<String> status_spinner = new ArrayList<>();
        status_spinner.add(0,"- -");
        status_spinner.add("Sudah Dikembalikan");

        ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, status_spinner);
        statusSp.setAdapter(adapter);

        tombol_ubah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataKondangan dataKondangan = new DataKondangan();
                dataKondangan.setNama(namaEt.getText().toString());
                dataKondangan.setKecamatan(dit_kecamatan.getText().toString());
                dataKondangan.setKelurahan(dit_kelurahan.getText().toString());
                dataKondangan.setAlamat(alamatEt.getText().toString());
                dataKondangan.setRp(rpEt.getText().toString());
                dataKondangan.setLiter(literEt.getText().toString());
                dataKondangan.setStatus(statusSp.getSelectedItem().toString());

                new FirebaseDatabaseHelper().UpdateDataKondangan(key, dataKondangan, new FirebaseDatabaseHelper.DataStatus() {
                    @Override
                    public void DataIsLoaded(List<DataKondangan> dataKondangans, List<String> keys) {
                    }

                    @Override
                    public void DataIsInserted() {
                    }

                    @Override
                    public void DataIsUpdated() {
                        Toast.makeText(UpdateDeleteActivity.this, "Data Berhasil Diperbaharui",
                                Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void DataIsDeleted() {
                    }
                });
            }
        });

        tombol_hapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new FirebaseDatabaseHelper().DeleteDataKondangn(key, new FirebaseDatabaseHelper.DataStatus() {
                    @Override
                    public void DataIsLoaded(List<DataKondangan> dataKondangans, List<String> keys) {
                    }

                    @Override
                    public void DataIsInserted() {
                    }

                    @Override
                    public void DataIsUpdated() {
                    }

                    @Override
                    public void DataIsDeleted() {
                        Toast.makeText(UpdateDeleteActivity.this, "Data Berhasil Dihapus",
                                Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }
}

