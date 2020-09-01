package com.ikco10.allbasketsignup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.ImageButton;

import java.lang.ref.WeakReference;

public class MainActivity extends AppCompatActivity {

    private static WeakReference<Context> context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = new WeakReference<>(getApplicationContext());

        Button register = findViewById(R.id.activity_main_loginbt);

        register.setOnClickListener(v -> {
            Handler handler = new Handler();
            handler.postDelayed(() -> {
                DialogMemberSignup dialogMemberSignup = new DialogMemberSignup();
                dialogMemberSignup.setCancelable(false);
                if (getFragmentManager() != null) {
                    dialogMemberSignup.show(getSupportFragmentManager(), "dialog_membersignup");
                }
            }, 100);
        });



    }

    public static Context getAppContext() {
        return context.get();
    }

}