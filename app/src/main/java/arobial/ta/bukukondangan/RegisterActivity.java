package arobial.ta.bukukondangan;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends Activity {
    EditText emailId, password, cfm_password;
    Button BtnSignUp;
    FirebaseAuth firebaseAuth;
    ProgressDialog pgrBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        firebaseAuth = FirebaseAuth.getInstance();
        emailId = findViewById(R.id.et_email);
        password = findViewById(R.id.et_password);
        cfm_password = findViewById(R.id.et_cfm_pwd);
        BtnSignUp = findViewById(R.id.btn_signup);
        pgrBar = new ProgressDialog(this);

        BtnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BuatAkunBaru(); }
        });
    }
    private void BuatAkunBaru() {
        String email = emailId.getText().toString();
        String pwd = password.getText().toString();
        String cpwd = cfm_password.getText().toString();
        if (email.isEmpty()){
            emailId.setError("Masukkan email anda");
            emailId.requestFocus();
        }
        else if (pwd.isEmpty()){
            password.setError("Masukkan password anda");
            password.requestFocus();
        }
        else if (pwd.length() < 6){
            password.setError("Panjang password minimal 6 ");
            password.requestFocus();
        }
        else if (cpwd.isEmpty()){
            cfm_password.setError("Konfirmasi password anda");
            cfm_password.requestFocus();
        }
        else if (!pwd.equals(cpwd)){
            Toast.makeText(this, "Password anda tidak sama, silahkan cek lagi",
                    Toast.LENGTH_SHORT).show();
        }
        else if (!(email.isEmpty() && pwd.isEmpty())){
            firebaseAuth.createUserWithEmailAndPassword(email, pwd).addOnCompleteListener
                    (RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (!task.isSuccessful()){
                        Toast.makeText(RegisterActivity.this,
                                "Terjadi kesalahan, silahkan coba lagi", Toast.LENGTH_SHORT).show();
                    } else {
                        pgrBar.setMessage("Mohon tunggu..");
                        pgrBar.show();
                        startActivity(new Intent(RegisterActivity.this,
                                KategoriHajatan.class)); }
                }
            });
        }
        else {
            Toast.makeText(RegisterActivity.this, "Terjadi kesalahan",
                    Toast.LENGTH_SHORT).show(); }
    }
}
