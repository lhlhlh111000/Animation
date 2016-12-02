package com.pig.android.animate;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

/**
 * Created by 李华 on 2016/11/10.
 */
public class VoteActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_vote);

        final VoteResultView vote = (VoteResultView) findViewById(R.id.vote);
        findViewById(R.id.btn_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vote.setPer(0.5f);
                vote.preEnterAnim();
            }
        });
    }
}
