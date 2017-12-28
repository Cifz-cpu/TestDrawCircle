package com.example.wangzhen.testdrawcircle;

import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CircleBarView circleBarView = findViewById(R.id.cbv);
        TextView  textView = findViewById(R.id.text_progress);

        circleBarView.setOnAnimationListener(new CircleBarView.OnAnimationListener() {
            @Override
            public String howToChangeText(float interpolatedTime, float progressNum, float maxNum) {
                DecimalFormat decimalFormat=new DecimalFormat("0.00");
                String s = decimalFormat.format(interpolatedTime * progressNum / maxNum * 100) + "%";
                return s;
            }

            @Override
            public void howTiChangeProgressColor(Paint paint, float interpolatedTime, float progressNum, float maxNum) {
                LinearGradientUtil linearGradientUtil = new LinearGradientUtil(Color.YELLOW,Color.RED);
                paint.setColor(linearGradientUtil.getColor(interpolatedTime));
            }
        });
        circleBarView.setTextView(textView);
        circleBarView.setProgressNum(100,3000);
    }
}
