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
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends Activity {

    Button btn_reset;
    EditText your_email;
    FirebaseAuth firebaseAuth;
    ProgressDialog LoadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        firebaseAuth = FirebaseAuth.getInstance();
        btn_reset = findViewById(R.id.btn_reset);
        your_email = findViewById(R.id.et_email);
        LoadingBar = new ProgressDialog(this);

        btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = your_email.getText().toString();

                if (email.isEmpty()){
                    your_email.setError("Tolong masukkan email anda");
                    your_email.requestFocus();
                }else {
                    firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                LoadingBar.setMessage("Mohon tunggu..");
                                LoadingBar.show();
                                Toast.makeText(ForgotPassword.this, "Silahkan cek email Anda", Toast.LENGTH_SHORT).show();
                                Intent reset = new Intent(ForgotPassword.this, LoginActivity.class);
                                startActivity(reset);
                            }else{
                                String error = task.getException().getMessage();
                                Toast.makeText(ForgotPassword.this, error, Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }
}
