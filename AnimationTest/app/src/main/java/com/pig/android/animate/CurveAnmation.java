package com.pig.android.animate;

import android.view.animation.Animation;
import android.view.animation.Transformation;

/**
 * Created by 李华 on 2015/5/11.
 */
public class CurveAnmation extends Animation {

    private int mFromXType = ABSOLUTE;
    private int mToXType = ABSOLUTE;

    private int mFromYType = ABSOLUTE;
    private int mToYType = ABSOLUTE;

    private float mFromXValue = 0.0f;
    private float mToXValue = 0.0f;

    private float mFromYValue = 0.0f;
    private float mToYValue = 0.0f;

    private float mFromXDelta;
    private float mToXDelta;
    private float mFromYDelta;
    private float mToYDelta;

    // 方程为 y = ax² + bx + c，由给定两点计算出对应的a，b
    private float mA;
    private float mB;
    private float mC;

    public CurveAnmation(float fromXDelta, float toXDelta, float fromYDelta, float toYDelta) {
        mFromXValue = fromXDelta;
        mToXValue = toXDelta;
        mFromYValue = fromYDelta;
        mToYValue = toYDelta;

        mFromXType = ABSOLUTE;
        mToXType = ABSOLUTE;
        mFromYType = ABSOLUTE;
        mToYType = ABSOLUTE;
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        float dx = mFromXDelta;
        float dy = mFromYDelta;
        if (mFromXDelta != mToXDelta) {
            dx = mFromXDelta + ((mToXDelta - mFromXDelta) * interpolatedTime);
            dy = mA*dx*dx + mB*dx + mC;
        }
//        if (mFromYDelta != mToYDelta) {
//            dy = mFromYDelta + ((mToYDelta - mFromYDelta) * interpolatedTime);
//        }
        t.getMatrix().setTranslate(dx, dy);
    }

    @Override
    public void initialize(int width, int height, int parentWidth, int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);
        mFromXDelta = resolveSize(mFromXType, mFromXValue, width, parentWidth);
        mToXDelta = resolveSize(mToXType, mToXValue, width, parentWidth);
        mFromYDelta = resolveSize(mFromYType, mFromYValue, height, parentHeight);
        mToYDelta = resolveSize(mToYType, mToYValue, height, parentHeight);

        float x1 = -mToXDelta;
        float x2 = mFromXDelta;
        float x3 = mToXDelta;
        float y1 = mToYDelta;
        float y2 = mFromYDelta;
        float y3 = mToYDelta;


        mA = (y1 * (x2 - x3) + y2 * (x3 - x1) + y3 * (x1 - x2))
                / (x1 * x1 * (x2 - x3) + x2 * x2 * (x3 - x1) + x3 * x3 * (x1 - x2));
        mB = (y1 - y2) / (x1 - x2) - mA * (x1 + x2);
        mC = y1 - (x1 * x1) * mA - x1 * mB;
    }
}
