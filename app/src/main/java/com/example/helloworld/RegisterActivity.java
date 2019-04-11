package com.example.helloworld;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.content.Intent;
import android.widget.Button;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    TextView txtBack;
    Button btnCreate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        txtBack = (TextView)findViewById(R.id.txtBack);
        btnCreate = (Button)findViewById(R.id.btnCreateUser);

        txtBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // Finish the registration screen and return to the Login activity
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnCreate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"ButtonCreate::onClick...",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
