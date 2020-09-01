package arobial.ta.bukukondangan;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import com.google.firebase.auth.FirebaseAuth;

public class LaporanKondangan extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laporan_kondangan);
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
            Intent tuLogin = new Intent(LaporanKondangan.this, LoginActivity.class);
            tuLogin.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            tuLogin.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(tuLogin);
        }
        return true;
    }
}
