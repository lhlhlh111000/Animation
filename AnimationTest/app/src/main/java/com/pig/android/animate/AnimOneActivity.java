package com.pig.android.animate;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;


public class AnimOneActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_anim_one);

        initUI();
    }

    private void initUI() {
        ListView lsvTest = (ListView) findViewById(R.id.lsv_test);

        lsvTest.setAdapter(new ImageAdapter());
    }

    class ImageAdapter extends BaseAdapter {

        private int mWidth = 0;

        private Integer[] mImages = new Integer[] {
                R.drawable.img_01,
                R.drawable.img_02,
                R.drawable.img_03,
                R.drawable.img_04,
                R.drawable.img_05,
                R.drawable.img_06,
                R.drawable.img_07,
                R.drawable.img_08
        };

        public ImageAdapter() {
            mWidth = getResources().getDisplayMetrics().widthPixels;
        }

        @Override
        public int getCount() {
            return mImages.length;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public Object getItem(int position) {
            return mImages[position];
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imvShow = new ImageView(AnimOneActivity.this);
            imvShow.setLayoutParams(new AbsListView.LayoutParams(mWidth, (int)(mWidth*0.7)));
            imvShow.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imvShow.setImageResource((int) getItem(position));

            bindViewClickEvent(imvShow,  position);

            return imvShow;
        }

        public void bindViewClickEvent(View view, final int position) {
            if(null == view) {
                return;
            }

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    // 状态栏高度
                    Rect frame = new Rect();
                    getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
                    int statusBarHeight = frame.top;

                    // ActionBar高度
                    int actionBarHeight = getSupportActionBar().getHeight();

                    // 图片在屏幕中的位置信息
                    int[] location = new int[2];
                    v.getLocationInWindow(location);

                    // 跳转页面，并传递信息
                    Intent intent = new Intent(AnimOneActivity.this, AnimOneDetailActivity.class);
                    intent.putExtra("X", location[0]);
                    intent.putExtra("Y", location[1] - statusBarHeight - actionBarHeight);
                    intent.putExtra("img", (int) getItem(position));
                    AnimOneActivity.this.startActivity(intent);
                }
            });
        }
    }
}
