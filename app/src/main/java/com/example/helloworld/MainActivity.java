package com.example.helloworld;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.widget.Toast;
import android.content.Intent;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

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

    public class CButtonLogin  implements View.OnClickListener {

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
           /* if (szUser.equalsIgnoreCase("vvdung") &&
            szPass.equalsIgnoreCase("111111")){
                //Toast.makeText(getApplicationContext(),"ACCOUNT OKIED!",Toast.LENGTH_SHORT).show();
                userNameLogined = szUser;
                Intent intent = new Intent(getApplicationContext(),UserActivity.class);
                startActivity(intent);
                return;
            }*/

            String szMsg = "[" + szUser + "/"  + szPass + "]";
            Toast.makeText(getApplicationContext(),szMsg,Toast.LENGTH_SHORT).show();

            try {
                //doGet("http://172.16.1.1:3000/users");
                String data = "{\"username\":\"" + szUser + "\",\"password\":\"" + szPass +"\"}";
                Log.d("K40",data);
                doPost("http://172.16.1.1:3000/login",data);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public class CButtonRegister implements View.OnClickListener {

        @Override
        public void onClick(View v) {//Hàm sử lý sự kiện click button register
            //Toast.makeText(getApplicationContext(),"CButtonRegister::onClick...",Toast.LENGTH_SHORT).show();
            //Intent i = new Intent(getApplicationContext(), RegisterActivity.class);
            //startActivity(i);

            try {
                doGet("http://172.16.1.1:3000/users");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    void doGet(String url) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                call.cancel();
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String myResponse = response.body().string();
                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                       //txtString.setText(myResponse);
                        Log.d("K40",myResponse);
                    }
                });
            }
        });
    }

    void doPost(String url,String json) throws IOException {
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(JSON,json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                call.cancel();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d("K40",response.body().string());
            }
        });
    }
}
