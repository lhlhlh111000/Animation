package com.pig.android.animate;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;


public class MainActivityTemp extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_temp);

        findViewById(R.id.btn_anim_0).setOnClickListener(mClickListener);
        findViewById(R.id.btn_anim_1).setOnClickListener(mClickListener);
        findViewById(R.id.btn_anim_2).setOnClickListener(mClickListener);
        findViewById(R.id.btn_anim_3).setOnClickListener(mClickListener);
        findViewById(R.id.btn_anim4).setOnClickListener(mClickListener);
    }

    private View.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch(v.getId()) {
                case R.id.btn_anim_0:
                    startActivity(new Intent(MainActivityTemp.this, AnimOneActivity.class));
                    break;
                case R.id.btn_anim_1:
                    startActivity(new Intent(MainActivityTemp.this, AnimTwoActivity.class));
                    break;
                case R.id.btn_anim_2:
                    startActivity(new Intent(MainActivityTemp.this, AnimThreeActivity.class));
                    break;
                case R.id.btn_anim_3:
                    startActivity(new Intent(MainActivityTemp.this, AnimFourActivity.class));
                    break;
                case R.id.btn_anim4:
                    startActivity(new Intent(MainActivityTemp.this, VoteActivity.class));
                    break;
            }
        }
    };
}
