package com.example.eventbrite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {
    Persoana persoana;
    Button button;
    EditText nume, email,phone, dataNastere;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        persoana = new Persoana();
        button = findViewById(R.id.btn_Save);
        nume = findViewById(R.id.et_Name);
        email = findViewById(R.id.et_Email);
        phone = findViewById(R.id.et_Phone);
        dataNastere = findViewById(R.id.et_DateBirth);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isValid()) {
                    persoana.setNume(nume.getText().toString());
                    persoana.setPhone(phone.getText().toString());
                    persoana.setEmail(email.getText().toString());
                    persoana.setDataNastere(dataNastere.getText().toString());

                    Bundle bundle = new Bundle();
                    bundle.putParcelable("persoanaParsata", persoana);
                    Intent intent = new Intent();
                    intent.putExtra("persoanaBundle", bundle);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });

    }

    private Boolean isValid() {
        if (nume.getText().toString().isEmpty()) {
            Toast.makeText(RegisterActivity.this, "Campul pentru nume nu este completat", Toast.LENGTH_LONG).show();
            return false;
        }
        if (phone.getText().toString().isEmpty()) {
            Toast.makeText(RegisterActivity.this, "Campul pentru telefon nu este completat", Toast.LENGTH_LONG).show();
            return false;
        }
        if (email.getText().toString().isEmpty()) {
            Toast.makeText(RegisterActivity.this, "Campul pentru email nu este completat", Toast.LENGTH_LONG).show();
            return false;
        }
        if (!isEmailValid(email.getText().toString())) {
            Toast.makeText(RegisterActivity.this, "Email-ul nu respecta formatul", Toast.LENGTH_LONG).show();
            return false;
        }
        if (dataNastere.getText().toString().isEmpty()) {
            Toast.makeText(RegisterActivity.this, "Campul pentru data nastere nu este completat", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    public boolean isEmailValid(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}