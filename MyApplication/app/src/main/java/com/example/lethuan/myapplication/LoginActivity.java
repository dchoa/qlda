package com.example.lethuan.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class LoginActivity extends Activity implements View.OnClickListener {

     String USER_NAME = "qlda_nhom16";
     String PASSWORD = "123456";

    Button btnLogin, btChange;
    EditText edtName, edtPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //Kiem tra: Lay thong login ra, neu no co roi, chung ta se
        //so sanh voi thong tin dang nhap xem co dung khong
        //Neu dung thi chung start luon cai Main activity


            setContentView(R.layout.activity_login);
            btnLogin = (Button) findViewById(R.id.btnLogin);
            btChange = (Button) findViewById(R.id.btChange);
            edtName = (EditText) findViewById(R.id.edtUserName);
            edtPass = (EditText) findViewById(R.id.edtPassword);
            btnLogin.setOnClickListener(this);
            btChange.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    LayoutInflater li = LayoutInflater.from(LoginActivity.this);
                    View customDialogView = li.inflate(R.layout.dialog_login, null);

                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(LoginActivity.this);
                    alertDialogBuilder.setView(customDialogView);

                    final EditText et_pass = (EditText) customDialogView.findViewById(R.id.et_pass);
                    final EditText et_newpass = (EditText) customDialogView.findViewById(R.id.et_newpass);

                    alertDialogBuilder.setCancelable(false).setPositiveButton("OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    if(et_pass.getText().toString().equalsIgnoreCase(PASSWORD)){
                                        PASSWORD = et_newpass.getText().toString();
                                        Toast.makeText(LoginActivity.this,"finish", Toast.LENGTH_SHORT).show();
                                    }

                                    else
                                        Toast.makeText(LoginActivity.this,"password wrong", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .setNegativeButton("Cancel",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            dialog.cancel();
                                        }
                                    });

                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();
                }
            });
    }

    // Hàm kiểm tra đăng nhập

    @Override
    public void onClick(View view) {
        if (edtName.length() == 0 || edtPass.length() == 0) {
            Toast.makeText(this, "Yeu cau nhap day du thong tin dang nhap",
                    Toast.LENGTH_SHORT).show();
        } else if (!edtName.getText().toString().equalsIgnoreCase(USER_NAME) ||
                !edtPass.getText().toString().equalsIgnoreCase(PASSWORD)) {
            Toast.makeText(this, "Thong tin dang nhap khong chinh xac",
                    Toast.LENGTH_SHORT).show();
        } else {
            //Danh nhap dung va login thanh cong => luu lai thong tin dang nhap
//Chuyển sang màn hình chính.
            Intent main = new Intent(this, MainActivity.class);
            startActivity(main);
            finish();
        }
    }
}