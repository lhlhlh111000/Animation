package android.pig.com.animation;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by 李华 on 2016/11/9.
 */
public class AnimationListActivity extends AppCompatActivity {
    AnimationDrawable animationDrawable;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_animation_list);

        final ImageView ivAnimation = (ImageView) findViewById(R.id.main_img_loading);
        findViewById(R.id.main_bt_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取 AnimationDrawable
                animationDrawable = (AnimationDrawable) ivAnimation.getDrawable();
                //开始动画
                animationDrawable.start();
            }

        });

        findViewById(R.id.main_bt_stop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取 AnimationDrawable
                animationDrawable = (AnimationDrawable) ivAnimation.getDrawable();
                //停止动画
                animationDrawable.stop();
            }

        });
    }
}
