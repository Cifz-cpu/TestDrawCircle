package com.example.wangzhen.testdrawcircle;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;

/**
 * Created by wangzhen on 2017/12/28.
 */

public class TestLayout extends FrameLayout {
    private View mFistPart, mSecondPart;
    private int mFistHeight, mSecondHeight;

    public TestLayout(Context context) {
        this(context, null);
    }

    public TestLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TestLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        System.out.println("----------Constructor-------------");
        mFistPart = getChildAt(0);
        mSecondPart = getChildAt(1);
        System.out.println("mFistPart:" + mFistPart + " mSecondPart:" + mSecondPart);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mFistPart = getChildAt(0);
        mSecondPart = getChildAt(1);
        mFistHeight = mFistPart.getMeasuredHeight();
        mSecondHeight = mSecondPart.getMeasuredHeight();
        System.out.println("----------onFinishInflate-------------");
        System.out.println("mFistPart:" + mFistPart + " mSecondPart:" + mSecondPart);
        System.out.println("mFistPart.getMeasuredHeight():" + mFistHeight + " mSecondPart.getMeasuredHeight():" + mSecondHeight);
        System.out.println("mFistPart.getHeight():" + mFistPart.getHeight() + " mSecondPart.getHeight():" + mSecondPart.getHeight());
        mFistPart.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onGlobalLayout() {
                System.out.println("----------addOnGlobalLayoutListener-------------");
                mFistPart.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                System.out.println("mFistPart.getMeasuredHeight():" + mFistPart.getMeasuredHeight() + "mFistPart.getHeight():" + mFistPart.getMeasuredHeight());
            }
        });
        mSecondPart.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onGlobalLayout() {
                System.out.println("----------addOnGlobalLayoutListener-------------");
                mSecondPart.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                System.out.println("mFistPart.getMeasuredHeight():" + mSecondPart.getMeasuredHeight() + "mFistPart.getHeight():" + mSecondPart.getMeasuredHeight());
            }
        });
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mFistHeight = mFistPart.getMeasuredHeight();
        mSecondHeight = mSecondPart.getMeasuredHeight();
        System.out.println("----------onSizeChanged-------------");
        System.out.println("mFistPart:" + mFistPart + " mSecondPart:" + mSecondPart);
        System.out.println("mFistPart.getMeasuredHeight():" + mFistHeight + " mSecondPart.getMeasuredHeight():" + mSecondHeight);
        System.out.println("mFistPart.getHeight():" + mFistPart.getHeight() + " mSecondPart.getHeight():" + mSecondPart.getHeight());
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mFistHeight = mFistPart.getMeasuredHeight();
        mSecondHeight = mSecondPart.getMeasuredHeight();
        System.out.println("----------onMeasure-------------");
        System.out.println("mFistPart:" + mFistPart + " mSecondPart:" + mSecondPart);
        System.out.println("mFistPart.getMeasuredHeight():" + mFistHeight + " mSecondPart.getMeasuredHeight():" + mSecondHeight);
        System.out.println("mFistPart.getHeight():" + mFistPart.getHeight() + " mSecondPart.getHeight():" + mSecondPart.getHeight());
    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
        mFistHeight = mFistPart.getMeasuredHeight();
        mSecondHeight = mSecondPart.getMeasuredHeight();
        System.out.println("----------onWindowFocusChanged-------------");
        System.out.println("mFistPart:" + mFistPart + " mSecondPart:" + mSecondPart + " hasWindowFocus:" + hasWindowFocus);
        System.out.println("mFistPart.getMeasuredHeight():" + mFistHeight + " mSecondPart.getMeasuredHeight():" + mSecondHeight);
        System.out.println("mFistPart.getHeight():" + mFistPart.getHeight() + " mSecondPart.getHeight():" + mSecondPart.getHeight());
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        mFistHeight = mFistPart.getMeasuredHeight();
        mSecondHeight = mSecondPart.getMeasuredHeight();
        System.out.println("----------onLayout-------------");
        System.out.println("mFistPart:" + mFistPart + " mSecondPart:" + mSecondPart);
        System.out.println("mFistPart.getMeasuredHeight():" + mFistHeight + " mSecondPart.getMeasuredHeight():" + mSecondHeight);
        System.out.println("mFistPart.getHeight():" + mFistPart.getHeight() + " mSecondPart.getHeight():" + mSecondPart.getHeight());
    }
}
