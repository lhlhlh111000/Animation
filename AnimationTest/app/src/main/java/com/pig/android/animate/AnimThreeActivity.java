package com.pig.android.animate;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by 李华 on 2015/5/11.
 */
public class AnimThreeActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.act_anim_three);

        final Button btnShow = (Button) findViewById(R.id.btn_anim_3);

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CurveAnmation curveAnmation = new CurveAnmation(0, 300, 0, -300);
                curveAnmation.setDuration(800);
                curveAnmation.setFillAfter(false);
//                btnShow.setAnimation(curveAnmation);
//                curveAnmation.startNow();
                btnShow.startAnimation(curveAnmation);
            }
        });
    }
}
