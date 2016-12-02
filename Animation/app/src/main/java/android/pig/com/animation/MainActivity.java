package android.pig.com.animation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_animation_list).setOnClickListener(this);
        findViewById(R.id.btn_animation_tweened).setOnClickListener(this);
        findViewById(R.id.btn_animation_value).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch(v.getId()) {
            case R.id.btn_animation_list:
                intent = new Intent(MainActivity.this, AnimationListActivity.class);
                break;
            case R.id.btn_animation_tweened:
                intent = new Intent(MainActivity.this, TweenedActivity.class);
                break;
            case R.id.btn_animation_value:
                intent = new Intent(MainActivity.this, ValueAnimActivity.class);
                break;
        }

        startActivity(intent);
    }
}
