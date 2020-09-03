package com.ikco10.allbasketm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.Button;

import java.lang.ref.WeakReference;

public class MainActivity extends AppCompatActivity {

    private static WeakReference<Context> context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = new WeakReference<>(getApplicationContext());

        Button signupBT = findViewById(R.id.activity_main_signupbt);
        Button modifyBT = findViewById(R.id.activity_main_modifybt);

        signupBT.setOnClickListener(v -> {
            DialogMemberSignup dialogMemberSignup = new DialogMemberSignup();
            dialogMemberSignup.setCancelable(false);
            if (getFragmentManager() != null)
                dialogMemberSignup.show(getSupportFragmentManager(), "DialogMemberSignup");
        });

        modifyBT.setOnClickListener(v -> {
            DialogMemberModify dialogMemberModify = new DialogMemberModify();
            dialogMemberModify.setCancelable(false);
            if (getFragmentManager() != null)
                dialogMemberModify.show(getSupportFragmentManager(), "DialogMemberModify");
        });

    }

    public static Context getAppContext() {
        return context.get();
    }

}