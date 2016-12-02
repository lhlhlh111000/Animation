package com.pig.android.animate;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by 李华 on 2015/5/13.
 */
public class AnimFourActivity extends ActionBarActivity {

    private int mDownloadCount = 0;

    private ImageView mImvDownload;

    private ListView mLsvTest;

    private boolean mIsAnim = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.act_anim_four);
        mImvDownload = (ImageView) findViewById(R.id.imv_download);
        initListView();
    }

    private void initListView() {
        mLsvTest = (ListView) findViewById(R.id.lsv_test2);

        mLsvTest.setAdapter(new TestAdapter());
    }

    private void showDownloadCount() {
        TextView txvCount = (TextView) findViewById(R.id.txv_download_count);

        if(mDownloadCount == 0) {
            txvCount.setVisibility(View.GONE);
        }else {
            txvCount.setText("" + mDownloadCount);
            txvCount.setVisibility(View.VISIBLE);
        }
    }

    private void startDownloadAnim(View viewTarget) {
        // 位置信息获取
        int[] initLocation = new int[2];
        viewTarget.getLocationInWindow(initLocation);
        int[] toLocation = new int[2];
        mImvDownload.getLocationInWindow(toLocation);
        // 获取动画View
        final View viewFrom = getMoveView(viewTarget, initLocation);
        // 位移动画
        CurveAnmation curveAnmation = new CurveAnmation(
                0, toLocation[0] - initLocation[0],
                0, toLocation[1] - initLocation[1]);
        // 缩放动画
        float xScale = (((float) mImvDownload.getWidth()/(float) viewTarget.getWidth()));
        float yScale = (((float) mImvDownload.getHeight()/(float) viewTarget.getHeight()));
        ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, xScale, 1.0f, yScale,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);

        AnimationSet set = new AnimationSet(false);
        set.addAnimation(scaleAnimation);
        set.addAnimation(curveAnmation);
        set.setDuration(800);
        set.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                // 显示下载数
                showDownloadCount();
                // 移除已添加View
                removeView(viewFrom);
                mIsAnim = false;
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        viewFrom.startAnimation(set);
    }

    private View getMoveView(View view, int[] initLocation) {
        ImageView imvView = new ImageView(this);
        imvView.setImageResource(R.drawable.ic_launcher);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(view.getWidth(), view.getHeight());
        params.leftMargin = initLocation[0];
        params.topMargin = initLocation[1];
        imvView.setLayoutParams(params);

        ((ViewGroup) getWindow().getDecorView()).addView(imvView);

        return imvView;
    }

    private void removeView(View toMove) {
        ((ViewGroup) getWindow().getDecorView()).removeView(toMove);
    }

    class TestAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return 100;
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = getLayoutInflater().inflate(R.layout.item_anim_four, null);

            final ImageView imvIcon = (ImageView) convertView
                    .findViewById(R.id.imv_anim_item_icon);

            convertView.findViewById(R.id.btn_download)
                    .setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(mIsAnim) {
                                return;
                            }

                            mIsAnim = true;
                            mDownloadCount++;
                            startDownloadAnim(imvIcon);
                        }
                    });

            return convertView;
        }
    }
}
