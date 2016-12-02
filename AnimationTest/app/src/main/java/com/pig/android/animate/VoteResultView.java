package com.pig.android.animate;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;

/**
 * 投票结果View
 * Created by 李华 on 2015/12/28.
 */
public class VoteResultView extends View  {

    private static final int DEFAULT_DURATION = 200;

    private static final int DEFAULT_ANIM_FRAME = 50;

    private Paint mPaint;

    private Paint mPaintText;

    private Paint mBgPaint;

    private int mHeightVoteBar;

    private float mTextSize;

    private float mTitleSize;

    private String mTextStr = "0%";

    private String mTextVoteStr = "是";

    private int mDefaultWidth = 0;

    private int mDefaultTextWidth = 0;

    private float mPer = 0f;

    private float mDefaultPer = 0f;

    private int mDuration = DEFAULT_DURATION;

    private int mAnimSpeed = 0;

    private int mAnimFrameCount = DEFAULT_ANIM_FRAME;

    private int mCurrentAnimFrame = DEFAULT_ANIM_FRAME;

    private boolean mIsShowAnim = true;

    //是否打钩
    private boolean mIsShowResult = false;

    private int mVoteColor = Color.GRAY;

    private int mTextColor = Color.GRAY;

    private int mVoteBgColor = Color.GRAY;

    private int attr_voteColor = -1;

    private int attr_textColor = -1;

    public VoteResultView(Context context) {
        this(context, null);
    }

    public VoteResultView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public VoteResultView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        TypedArray t = getContext().obtainStyledAttributes(attrs, R.styleable.VoteView);

        mPaint = new Paint();
        mPaintText = new Paint();
        mBgPaint = new Paint();

        mPaint.setAntiAlias(true);
        mPaintText.setAntiAlias(true);
        mBgPaint.setAntiAlias(true);
        // TODO: 2016/1/6 锚点
//        mPaint.setTextAlign(Paint.Align.CENTER);

        mVoteColor = t.getColor(R.styleable.VoteView_voteColor, getContext().getResources().getColor(R.color.vote_color));
        mTextColor = t.getColor(R.styleable.VoteView_textColor, getContext().getResources().getColor(R.color.text_color));
        mVoteBgColor = t.getColor(R.styleable.VoteView_voteBgColor, getContext().getResources().getColor(R.color.vote_bg_color));

        float density = getResources().getDisplayMetrics().density;

        mHeightVoteBar = (int) (16*density);
        mTextSize = 16*density;
        mTitleSize = 12*density;

        mDefaultTextWidth = (int)(5*density);
        mDefaultWidth = getResources().getDisplayMetrics().widthPixels*2/3;

        mPaint.setTextSize(mTextSize);
        mPaintText.setTextSize(mTitleSize);
        mBgPaint.setTextSize(mTextSize);

//        mPaint.setStrokeWidth(3);
//        mBgPaint.setStrokeWidth(3);
        mPaint.setTypeface(Typeface.DEFAULT_BOLD);

        // 暂定10帧结束动画
        mAnimSpeed = mDuration/mAnimFrameCount;

        t.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int width;
        int height;
        Rect textBounds = new Rect();
        mPaint.getTextBounds(mTextStr, 0, mTextStr.length(), textBounds);

        if(MeasureSpec.EXACTLY != widthMode) {
            int textWidth = textBounds.width();
            width = paddingLeft + paddingRight + textWidth + mDefaultWidth;
        }else {
            width = widthSize;
        }

        if(MeasureSpec.EXACTLY != heightMode) {
            int textHeight = textBounds.height();
            height = paddingTop + paddingBottom + 3*Math.max(mHeightVoteBar, textHeight);
        }else {
            height = heightSize;
        }

        super.onMeasure(MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY),
                MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPaint.setColor(mVoteColor);
        mPaintText.setColor(mTextColor);
        mBgPaint.setColor(mVoteBgColor);

        if(0 != mCurrentAnimFrame) {
            int width = getMeasuredWidth();
            int height = getMeasuredHeight();

            Rect textBounds = new Rect();
//            mPaint.getTextBounds(mTextStr, 0, mTextStr.length(), textBounds);
            //设为固定值，保证对齐
            mPaint.getTextBounds("100%", 0, "100%".length(), textBounds);
            int textWidth = textBounds.width();
            int textHeight = textBounds.height();

            float density = getResources().getDisplayMetrics().density;

            // 百分比文字
            int textBottom = (height + textHeight)/2;

            canvas.drawText(mTextStr, getPaddingLeft(), textBottom, mPaint);

            // 进度条
            int left = getPaddingLeft() + textWidth +(int)(6*density);
            int top = (height - mHeightVoteBar)/2;
            int right = left + width - left;
            int bottom = (height + mHeightVoteBar)/2;
            RectF rectFVote  = new RectF(left, top, right, bottom);

            int ry = (bottom - top)/2;
            int rx = (right - left)/ 2;
            int  r = Math.min(ry, rx);
            //灰色进度条
            canvas.drawRoundRect(rectFVote, r, r, mBgPaint);

            right = left + (int) ((width - left)*mPer*getInterpolation(mCurrentAnimFrame));
            rectFVote = new RectF(left, top, right, bottom);
            rx = (right - left)/ 2;
            r = Math.min(ry, rx);
            //主色进度条
            canvas.drawRoundRect(rectFVote, r, r, mPaint);
            //选项名字

            //空
            mPaintText.setStyle(Paint.Style.FILL);
            mPaintText.setStrokeWidth(1);
            canvas.drawText(mTextVoteStr, getPaddingLeft() + 9 * mDefaultTextWidth, bottom + (int) (15 * density), mPaintText);

            //打钩
            if (mIsShowResult) {
                mPaintText.getTextBounds(mTextVoteStr, 0, mTextVoteStr.length(), textBounds);
                Path path = new Path();
                path.moveTo(getPaddingLeft() + 10 * mDefaultTextWidth + textBounds.width(), bottom + (int) (9 * density));
                path.lineTo(getPaddingLeft() + 11 * mDefaultTextWidth + textBounds.width(), bottom + (int) (15 * density));
                path.lineTo(getPaddingLeft() + 13 * mDefaultTextWidth + textBounds.width() + (int) (1 * density), bottom + (int) (6 * density));

                // 设置线宽
                mPaintText.setStyle(Paint.Style.STROKE);
                mPaintText.setStrokeWidth(3);
                canvas.drawPath(path, mPaintText);
            }
        }


        if(mIsShowAnim) {
            // 直接显示结果，默认
            if(mCurrentAnimFrame != mAnimFrameCount) {
                mCurrentAnimFrame = mAnimFrameCount;
                postInvalidate();
            }
        }else {
            // 动画显示
            if(mCurrentAnimFrame < mAnimFrameCount) {
                mCurrentAnimFrame ++;

            }
            // 文字动画
            if (0 <= mPer && mPer <= 1 && mPer > mDefaultPer) {
                mDefaultPer = mDefaultPer + 0.01f;
                mTextStr = ((int) (mDefaultPer*100)) + "%";
            }
            postInvalidateDelayed(mAnimSpeed);
        }
    }

    /**
     * 插值器获取
     * @return
     */
    private float getInterpolation(int frame) {
        float input = (float)frame/(float)mAnimFrameCount;
        float result = 1.0f - (1.0f - input) * (1.0f - input);
        return result;
    }

    /**
     * 进场动画
     */
    public void preEnterAnim() {
        mCurrentAnimFrame = 0;
        mIsShowAnim = false;
        postInvalidateDelayed(mAnimSpeed);
    }

    public void showResult() {
        mIsShowAnim = true;
        postInvalidate();
    }

    /**
     * 设置结果比例
     * @param per
     */
    public void setPer(float per) {
        if(per < 0f || per > 1.0f) {
           return;
        }
        mDefaultPer = 0f;
        mPer = per;
        mTextStr = ((int) (per*100)) + "%";
        requestLayout();
    }

    /**
     *  设置投票颜色
     * @param color
     */
    public void setVoteColor (int color) {
        this.mVoteColor = color;
        invalidate();
    }

    /**
     *  设置投票背景颜色
     * @param color
     */
    public void setVoteBgColor (int color) {
        this.mVoteBgColor = color;
        invalidate();
    }

    /**
     *  设置投票颜色
     * @param color
     */
    public void setTextColor (int color) {
        this.mTextColor = color;
        invalidate();
    }

    /**
     *  设置投票选项名字
     * @param str
     */
    public void setTextStr (String str) {
        this.mTextVoteStr = str;
        invalidate();
    }


    public void setShowResult(boolean mIsShowResult) {
        this.mIsShowResult = mIsShowResult;
        invalidate();
    }
}
