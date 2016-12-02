package com.pig.android.animate;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class AnimTwoActivity extends Activity {
	private ImageView imageV;
	private ImageView scaleImageV;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_anim_two);
		scaleImageV = (ImageView) findViewById(R.id.imageview_scale);
		imageV = (ImageView) findViewById(R.id.imageView1);
		
		imageV.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Util.browerImage(AnimTwoActivity.this, imageV, scaleImageV);
			}
		});
		scaleImageV.setImageBitmap(Util.getBitmap(this, R.drawable.img_01));
		scaleImageV.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Util.closeImage(AnimTwoActivity.this, imageV, scaleImageV);
			}
		});

        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) scaleImageV.getLayoutParams();
        params.height = getResources().getDisplayMetrics().widthPixels * 2/3;
        scaleImageV.setLayoutParams(params);
	}
}
