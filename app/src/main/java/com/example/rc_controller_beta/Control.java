package com.example.rc_controller_beta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;

public class Control extends AppCompatActivity {
    ImageButton go, back, left, right, option;
    TextView net, event, server;
    private int Gea = 0, Neu = 0, Dri = 1, Rev = 2; // 중립, 전진, 후진 기어

    public void Gear(int sta){
        Gea = sta;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_control);

        go = findViewById(R.id.up_img);
        back = findViewById(R.id.down_img);
        left = findViewById(R.id.left_img);
        right = findViewById(R.id.right_img);
        option = findViewById(R.id.option_button);
        net = findViewById(R.id.net_state);
        event = findViewById(R.id.event_log);
        server = findViewById(R.id.event_server);

        option.setOnClickListener(v -> {
            Intent in = new Intent(getApplicationContext(), Option.class);
            startActivity(in);
        });

        if(Option.connect) {
            net.setText("Network: ON");

            go.setOnTouchListener((view, motionEvent) -> {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    // 버튼을 눌렀을 때
                    event.setText("LF|RF");
                    Option.c.PushMsg("LF|RF");
                    Gear(Dri);
                }else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    // 버튼에서 손을 떼었을 때
                    event.setText("LN|RN");
                    Option.c.PushMsg("LN|RN");
                    Gear(Neu);
                }
                return false;
            });

            back.setOnTouchListener((view, motionEvent) -> {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    // 버튼을 눌렀을 때
                    event.setText("LB|RB");
                    Option.c.PushMsg("LB|RB");
                    Gear(Rev);
                }else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    // 버튼에서 손을 떼었을 때
                    event.setText("LN|RN");
                    Option.c.PushMsg("LN|RN");
                    Gear(Neu);
                }
                return false;
            });

            left.setOnTouchListener((view, motionEvent) -> {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    // 버튼을 눌렀을 때
                    if(Gea == Dri){
                        event.setText("LN|RF");
                        Option.c.PushMsg("LN|RF");
                    }
                    else if(Gea == Rev){
                        event.setText("LN|RB");
                        Option.c.PushMsg("LN|RB");
                    }
                    else
                        event.setText("LN|RN");
                }else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    // 버튼에서 손을 떼었을 때
                    if(Gea == Dri){
                        event.setText("LF|RF");
                        Option.c.PushMsg("LF|RF");
                    }
                    else if(Gea == Rev){
                        event.setText("LB|RB");
                        Option.c.PushMsg("LB|RB");
                    }
                    else
                        event.setText("LN|RN");
                }
                return false;
            });

            right.setOnTouchListener((view, motionEvent) -> {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    // 버튼을 눌렀을 때
                    if(Gea == Dri){
                        event.setText("LF|RN");
                        Option.c.PushMsg("LF|RN");
                    }
                    else if(Gea == Rev){
                        event.setText("LB|RN");
                        Option.c.PushMsg("LB|RN");
                    }
                    else
                        event.setText("LN|RN");
                }else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    // 버튼에서 손을 떼었을 때
                    if(Gea == Dri){
                        event.setText("LF|RF");
                        Option.c.PushMsg("LF|RF");
                    }
                    else if(Gea == Rev){
                        event.setText("LB|RB");
                        Option.c.PushMsg("LB|RB");
                    }
                    else
                        event.setText("LN|RN");
                }
                return false;
            });
        }else{
            net.setText("Network: OFF");
        }
    }
}
