package android.pig.com.animation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by 李华 on 2016/11/9.
 */
public class TweenedActivity extends AppCompatActivity implements View.OnClickListener {

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
                startAnim(R.anim.alpha);
                break;
            case R.id.btn_rotate:
                startAnim(R.anim.rotate);
                break;
            case R.id.btn_scale:
                startAnim(R.anim.scale);
                break;
            case R.id.btn_translate:
                startAnim(R.anim.translate);
                break;
            case R.id.iv_anim:
                Toast.makeText(TweenedActivity.this, "Hello!", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void startAnim(int res) {
        Animation animation = AnimationUtils.loadAnimation(this, res);
        animation.setFillAfter(true);
        ivAnim.startAnimation(animation);
    }
}
