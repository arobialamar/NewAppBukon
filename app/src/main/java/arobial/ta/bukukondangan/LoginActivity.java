package arobial.ta.bukukondangan;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class LoginActivity extends Activity {

    TextView NewAccount, ForgotPwd;
    Button BtnSignIn;
    EditText emailId, password;
    FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener AuthStateListener;
    ProgressDialog LoadBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth = FirebaseAuth.getInstance();
        NewAccount = findViewById(R.id.tv_newacc);
        BtnSignIn = findViewById(R.id.btn_signin);
        emailId = findViewById(R.id.et_email);
        password = findViewById(R.id.et_password);
        ForgotPwd = findViewById(R.id.tv_forgot);
        LoadBar = new ProgressDialog(this);

        AuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser firebaseUser =  firebaseAuth.getCurrentUser();
                if (firebaseUser != null){
                    Toast.makeText(LoginActivity.this, "Anda telah login", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(LoginActivity.this, MenuActivity.class);
                    startActivity(i);
                }

            }
        };

        BtnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailId.getText().toString();
                String pwd = password.getText().toString();
                if (email.isEmpty()){
                    emailId.setError("Tolong masukkan email anda");
                    emailId.requestFocus();
                }
                else if (pwd.isEmpty()){
                    password.setError("Tolong masukkan password anda");
                    password.requestFocus();
                }
                else if (email.isEmpty() && pwd.isEmpty()){
                    Toast.makeText(LoginActivity.this, "Kolom tidak boleh kosong !", Toast.LENGTH_SHORT).show();
                }
                else if (!(email.isEmpty() && pwd.isEmpty())){
                    firebaseAuth.signInWithEmailAndPassword(email, pwd).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (! task.isSuccessful()){
                                Toast.makeText(LoginActivity.this, "Email atau password salah", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                LoadBar.setMessage("Mohon tunggu..");
                                LoadBar.show();
                                Intent ToMenu = new Intent(LoginActivity.this, MenuActivity.class);
                                startActivity(ToMenu);
                            }
                        }
                    });
                }
                else {
                    Toast.makeText(LoginActivity.this, "Terjadi kesalahan", Toast.LENGTH_SHORT).show();
                }
            }
        });

        NewAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        ForgotPwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ForgotPwd = new Intent(LoginActivity.this, ForgotPassword.class);
                startActivity(ForgotPwd);
            }
        });
    }

    @Override
    protected void onStart() {
        //Jika User Telah Masuk/Login, makan akan mengangani kajadian apakah user telah masuk
        super.onStart();
        firebaseAuth.addAuthStateListener(AuthStateListener);
    }

    @Override
    protected void onStop() {
        //Saat Aktifitas dihentikan, maka listener akan dihapus
        super.onStop();
        if(AuthStateListener != null){
            firebaseAuth.removeAuthStateListener(AuthStateListener);
        }
    }
}
