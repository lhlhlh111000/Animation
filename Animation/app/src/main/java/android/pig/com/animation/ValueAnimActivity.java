package android.pig.com.animation;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

/**
 * Created by 李华 on 2016/11/9.
 */
public class ValueAnimActivity extends AppCompatActivity implements View.OnClickListener {

    View ivAnim;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweened);

        ivAnim = findViewById(R.id.iv_anim);
        findViewById(R.id.btn_alpha).setOnClickListener(this);
        findViewById(R.id.btn_translate).setOnClickListener(this);
        findViewById(R.id.btn_scale).setOnClickListener(this);
        findViewById(R.id.btn_rotate).setOnClickListener(this);
        findViewById(R.id.iv_anim).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btn_alpha:
                startAnimAlpha();
                break;
            case R.id.btn_rotate:
                startAnimRotate();
                break;
            case R.id.btn_scale:
                startAnimScale();
                break;
            case R.id.btn_translate:
                startAnimTranslate();
                break;
            case R.id.iv_anim:
                Toast.makeText(ValueAnimActivity.this, "Hello!", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void startAnimAlpha() {
        ObjectAnimator anim = ObjectAnimator.ofFloat(ivAnim, "alpha", 0.0f, 1.0f);
        anim.setDuration(2000);
        anim.start();
    }

    private void startAnimRotate() {
        ObjectAnimator anim = ObjectAnimator.ofFloat(ivAnim, "rotation", 0f, 360f);
        anim.setDuration(1000);
        anim.start();
    }

    private void startAnimScale() {
        ObjectAnimator anim = ObjectAnimator.ofFloat(ivAnim, "scaleX", 0.0f, 1.0f);
        anim.setDuration(1000);
        anim.start();
    }

    private void startAnimTranslate() {
        ObjectAnimator anim = ObjectAnimator.ofFloat(ivAnim, "translationX", 100, 400);
        anim.setDuration(1000);
        anim.start();
    }
}
