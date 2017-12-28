package com.example.wangzhen.testdrawcircle;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.TextView;

/**
 * Created by wangzhen on 2017/12/28.
 */

public class CircleBarView extends View {

    private Paint rPaint;
    private float sweepAngle;//圆弧经过的角度
    private Paint progressPaint;
    private Paint bgPaint;//绘制背景的画笔
    private float progressNum;
    private float maxNum;
    private float progressSweepAngle;//进度条圆弧扫过的角度
    private float startAngle;//背景圆弧的起始角度
    private CircleBarAnim circleBarAnim;

    private RectF mRectF; //绘制圆弧区域的矩形区域
    private float barWidth; //圆弧进度条宽度
    private int defaultSize; //自定义view默认的宽高

    private int progressColor;//进度条圆环的颜色
    private int bgColor;//背景圆弧颜色

    private TextView textView;
    private OnAnimationListener onAnimationListener;

    public CircleBarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    private void init(Context context, AttributeSet attrs) {

        TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.CircleBarView);

        progressColor = typedArray.getColor(R.styleable.CircleBarView_progress_color,Color.GREEN);
        bgColor = typedArray.getColor(R.styleable.CircleBarView_bg_color,Color.GRAY);
        startAngle = typedArray.getFloat(R.styleable.CircleBarView_start_angle,0);//默认为0
        sweepAngle = typedArray.getFloat(R.styleable.CircleBarView_sweep_angle,360);//默认为360
        barWidth = typedArray.getDimension(R.styleable.CircleBarView_bar_width,DpOrPxUtils.dip2px(context,10));//默认为10dp
        typedArray.recycle();//typedArray用完之后需要回收，防止内存泄漏

        defaultSize = DpOrPxUtils.dip2px(context,100);
//        barWidth = DpOrPxUtils.dip2px(context,10);
        mRectF = new RectF();

        circleBarAnim = new CircleBarAnim();

        rPaint = new Paint();
        rPaint.setStyle(Paint.Style.STROKE);
        rPaint.setColor(Color.RED);

        progressPaint = new Paint();
        progressPaint.setStyle(Paint.Style.STROKE); //只描边 不填充
        progressPaint.setColor(progressColor);
        progressPaint.setAntiAlias(true); //设置抗锯齿
        progressPaint.setStrokeWidth(barWidth);

        bgPaint = new Paint();
        bgPaint.setStyle(Paint.Style.STROKE);
        bgPaint.setColor(bgColor);
        bgPaint.setAntiAlias(true);
        bgPaint.setStrokeWidth(barWidth);

        progressNum = 0;
        maxNum  = 100;
//        startAngle  = 0;
//        sweepAngle = 360;

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int height = measureSize(defaultSize,heightMeasureSpec);
        int width = measureSize(defaultSize,widthMeasureSpec);
        int min = Math.min(width,height);
        setMeasuredDimension(min,min);

        if(min >= barWidth * 2){
            mRectF.set(barWidth/2,barWidth/2,min-barWidth/2,min-barWidth/2);
        }
    }

    /**
     * 设置显示文字的TextView
     * @param textView
     */
    public void setTextView(TextView textView) {
        this.textView = textView;
    }

    public int measureSize(int defaultSize,int measureSpec){
        int result = defaultSize;
        int specMode = View.MeasureSpec.getMode(measureSpec);
        int specSize = View.MeasureSpec.getSize(measureSpec);

        if(specMode == MeasureSpec.EXACTLY){
            result = specSize;
        }else if(specMode == MeasureSpec.AT_MOST){
            result = Math.min(result,specSize);
        }
        return result;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float x = 50;
        float y = 50;
        RectF rectF = new RectF(x,y,x+300,y+300);//建一个大小 300 * 300 的正方向区域
        /**
         * 这里角度0对应的是三点钟方向，顺时针方向递增
         */
//        canvas.drawArc(rectF,0,sweepAngle,false,progressPaint);
//        canvas.drawRect(rectF,rPaint);
        canvas.drawArc(rectF,startAngle,sweepAngle,false,bgPaint);
        canvas.drawArc(rectF,startAngle,progressSweepAngle,false,progressPaint);
    }

    public void setOnAnimationListener(OnAnimationListener onAnimationListener) {
        this.onAnimationListener = onAnimationListener;
    }

    public class CircleBarAnim extends Animation{
        public CircleBarAnim() {
        }
        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            super.applyTransformation(interpolatedTime, t);
            progressSweepAngle = interpolatedTime * sweepAngle * progressNum / maxNum;//这里计算进度条的比例
            postInvalidate();
            if(textView != null && onAnimationListener != null){
                textView.setText(onAnimationListener.howToChangeText(interpolatedTime,progressNum,maxNum));
            }
            onAnimationListener.howTiChangeProgressColor(progressPaint,interpolatedTime,progressNum,maxNum);
        }
    }

    public void setProgressNum(float pN,int time){
        circleBarAnim.setDuration(time);
        this.startAnimation(circleBarAnim);
        this.progressNum = pN;
    }

    public interface OnAnimationListener {
        /**
         * 如何处理要显示的文字内容
         * @param interpolatedTime 从0渐变成1,到1时结束动画
         * @param progressNum 进度条数值
         * @param maxNum 进度条最大值
         * @return
         */
        String howToChangeText(float interpolatedTime, float progressNum, float maxNum);

        /**
         * 如何处理进度条的颜色
         * @param paint 进度条画笔
         * @param interpolatedTime 从0渐变成1,到1时结束动画
         * @param progressNum 进度条数值
         * @param maxNum 进度条最大值
         */
        void howTiChangeProgressColor(Paint paint, float interpolatedTime, float progressNum, float maxNum);
    }

}
