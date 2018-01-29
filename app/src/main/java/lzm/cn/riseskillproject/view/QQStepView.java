package lzm.cn.riseskillproject.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import lzm.cn.riseskillproject.R;
import lzm.cn.riseskillproject.uitl.GloabalUtils;

/**
 * Created by lizhiming on 2018/1/24.
 */

public class QQStepView extends View {

    private Paint mOuterPaint, mInnerPaint, mTextPaint;

    private int mOuterColor = Color.RED;
    private int mInnerColor = Color.BLUE;
    private int mBordWidth = 20;
    private String mStepText;
    private int mStepTextSize = 16;
    private int mStepTextColor = Color.BLACK;

    //总共的步数
    private int mStepMax;
    private int mCurrentStep; //当前的步数

    public QQStepView(Context context) {
        this(context, null);
    }

    public QQStepView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public QQStepView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.QQStepView);

        mOuterColor = array.getColor(R.styleable.QQStepView_outerColor, mOuterColor);
        mInnerColor = array.getColor(R.styleable.QQStepView_innerColor, mInnerColor);
        mBordWidth = (int) array.getDimension(R.styleable.QQStepView_bordrWidth, mBordWidth);
        mStepText = array.getString(R.styleable.QQStepView_stepText);
        mStepTextColor = array.getColor(R.styleable.QQStepView_stepTextColor, mStepTextColor);
        mStepTextSize = array.getDimensionPixelSize(R.styleable.QQStepView_stepTextSize, GloabalUtils.sp2px(getContext(), mStepTextSize));

        array.recycle();

        mOuterPaint = new Paint();
        mOuterPaint.setAntiAlias(true);
        mOuterPaint.setStrokeWidth(mBordWidth);
        mOuterPaint.setColor(mOuterColor);
        mOuterPaint.setStrokeCap(Paint.Cap.ROUND); //给画的线家个圆弧帽子，底部两头加了一个圆帽子
        mOuterPaint.setStyle(Paint.Style.STROKE);

        //内圆画笔
        mInnerPaint = new Paint();
        mInnerPaint.setAntiAlias(true);
        mInnerPaint.setStrokeWidth(mBordWidth);
        mInnerPaint.setColor(mInnerColor);
        mInnerPaint.setStrokeCap(Paint.Cap.ROUND); //给画的线家个圆弧帽子，底部两头加了一个圆帽子
        mInnerPaint.setStyle(Paint.Style.STROKE);

        mTextPaint = new Paint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setColor(mStepTextColor);
        mTextPaint.setTextSize(mStepTextSize);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        //获取宽高模式
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);



        setMeasuredDimension(width>height? height : width, width>height? height : width);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int center = getWidth()/2;
        int radius = getWidth()/2-mBordWidth/2;

        //除以2,坐标系问题?
        RectF rectF = new RectF(center-radius, center-radius, getWidth()-mBordWidth/2, getHeight()-mBordWidth/2);
        canvas.drawArc(rectF, 135, 270, false, mOuterPaint);


        //画内圆
        if(mStepMax == 0) return;
        float sweepAngle = (float) mCurrentStep/mStepMax; //进度扫描的是变化的
        canvas.drawArc(rectF, 135, sweepAngle*270, false,mInnerPaint);

        //画文字
        String stepText = mCurrentStep+"";
        Rect textBounds = new Rect();
        mTextPaint.getTextBounds(stepText, 0, stepText.length(), textBounds);
        int dx = getWidth()/2 - textBounds.width()/2;
        //text的基线
        Paint.FontMetricsInt fontMetricsInt = mTextPaint.getFontMetricsInt();
        int dy = (fontMetricsInt.bottom - fontMetricsInt.top) / 2;
        int baseLine = getHeight()/2 + dy;
        canvas.drawText(stepText, dx, baseLine, mTextPaint);
    }

    public void setSetMax(int stepMax) {
        this.mStepMax = stepMax;
    }

    public void setCurrentStep(int stepCurrent) {
        this.mCurrentStep = stepCurrent;
        //不断绘制
        invalidate();
    }
}
