package com.example.helloworld;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.widget.Toast;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    EditText edtUser,edtPass; //Biến điều khiển EditText
    Button   btnLogin, btnRegister; //Biến điều khiển Button
    static String   userNameLogined;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Khởi tạo các biến điều khiển tương ứng trong layout
        edtUser = (EditText)findViewById(R.id.edtUsername);
        edtPass = (EditText)findViewById(R.id.edtPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnRegister = (Button)findViewById(R.id.btnRegister);

        //Cài đặt sự kiện Click cho Button Login
        btnLogin.setOnClickListener(new CButtonLogin());
        //Cài đặt sự kiện Click cho Button Register
        btnRegister.setOnClickListener(new CButtonRegister());
    }

    public class CButtonLogin implements View.OnClickListener {

        @Override
        public void onClick(View v) {//Hàm sử lý sự kiện click button login
            //Nhận nội dung từ biến điều khiển
            String szUser = edtUser.getText().toString();
            String szPass = edtPass.getText().toString();

            //Kiểm tra tính hợp lệ của tài khoản, mật khẩu
            if (szUser.length() <= 3 || szPass.length() < 6){
                Toast.makeText(getApplicationContext(),"Tài khoản hoặc mật khẩu không hợp lệ!",Toast.LENGTH_SHORT).show();
                return;
            }

            //Kiểm tra nếu tài khoản, mật khẩu chính xác
            if (szUser.equalsIgnoreCase("vvdung") &&
            szPass.equalsIgnoreCase("111111")){
                //Toast.makeText(getApplicationContext(),"ACCOUNT OKIED!",Toast.LENGTH_SHORT).show();
                userNameLogined = szUser;
                Intent intent = new Intent(getApplicationContext(),UserActivity.class);
                startActivity(intent);
                return;
            }

            String szMsg = "[" + szUser + "/"  + szPass + "]";
            Toast.makeText(getApplicationContext(),szMsg,Toast.LENGTH_SHORT).show();
        }
    }

    public class CButtonRegister implements View.OnClickListener {

        @Override
        public void onClick(View v) {//Hàm sử lý sự kiện click button register
            //Toast.makeText(getApplicationContext(),"CButtonRegister::onClick...",Toast.LENGTH_SHORT).show();
            Intent i = new Intent(getApplicationContext(), RegisterActivity.class);
            startActivity(i);
        }
    }
}
