package com.pig.android.animate;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by 李华 on 2015/1/30.
 */
public class AnimOneDetailActivity extends ActionBarActivity {

    private int mY = 0;

    private int mRes = 0;

    private ImageView mImvShow;

    private RelativeLayout mRelContainer;

    private TextView mTxvShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        obtainParams();
        setContentView(R.layout.act_anim_one_detail);

        initUI();
    }

    @Override
    public void onBackPressed() {
        handleBackAction();
    }

    private void obtainParams() {
        Intent intent = getIntent();
        if(null == intent) {
            return;
        }

        mY = intent.getIntExtra("Y", 0);
        mRes = intent.getIntExtra("img", 0);
    }

    private void initUI() {
        mImvShow = (ImageView) findViewById(R.id.imv_show);
        mRelContainer = (RelativeLayout) findViewById(R.id.rel_container);
        mTxvShow = (TextView) findViewById(R.id.txv_show);

        mImvShow.setImageResource(mRes);
        int width = getResources().getDisplayMetrics().widthPixels;
        mImvShow.setLayoutParams(new RelativeLayout.LayoutParams(width, (int)(width*0.7)));


        TranslateAnimation translateAnimation = new TranslateAnimation(0, 0, mY, 0);
        translateAnimation.setDuration(300);
        translateAnimation.setStartOffset(300);
        translateAnimation.setFillAfter(true);
        translateAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
                alphaAnimation.setDuration(300);
                alphaAnimation.setFillAfter(true);
                mTxvShow.setVisibility(View.VISIBLE);
                mTxvShow.setAnimation(alphaAnimation);
                alphaAnimation.startNow();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        translateAnimation.start();
        mImvShow.startAnimation(translateAnimation);
    }

    private void handleBackAction() {
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
        alphaAnimation.setDuration(300);
        alphaAnimation.setFillAfter(true);
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mTxvShow.setVisibility(View.GONE);
                TranslateAnimation translateAnimation = new TranslateAnimation(0, 0, 0, mY);
                translateAnimation.setDuration(300);
                translateAnimation.setStartOffset(300);
                translateAnimation.setFillAfter(true);
                mImvShow.startAnimation(translateAnimation);
                translateAnimation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        finish();
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                    }
                });
                translateAnimation.startNow();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
//        mTxvShow.setAnimation(alphaAnimation);
//        alphaAnimation.startNow();
        mTxvShow.startAnimation(alphaAnimation);
    }
}
