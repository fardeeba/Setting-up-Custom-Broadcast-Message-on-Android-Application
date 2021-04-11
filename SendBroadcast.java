package com.example.broadcast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.onlinelab.R;

public class SendBroadcast extends AppCompatActivity {

    EditText etMsg;
    Button btnSend;

    MessageReceiver messageReceiver = new MessageReceiver();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_broadcast);

        etMsg = findViewById(R.id.etMsg);
        btnSend = findViewById(R.id.btnSend);

        callListener();
    }

    private void callListener() {

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String msg = etMsg.getText().toString();

                Intent intent = new Intent(SendBroadcast.this,MessageReceiver.class);
                intent.setAction("com.example.broadcast.CUSTOM_ACTION");
                intent.putExtra("msg",msg);
                sendBroadcast(intent);

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        IntentFilter intentFilter = new IntentFilter("com.example.broadcast.CUSTOM_ACTION");
        registerReceiver(messageReceiver,intentFilter);

    }

    @Override
    protected void onStop() {
        super.onStop();

        unregisterReceiver(messageReceiver);
    }
}