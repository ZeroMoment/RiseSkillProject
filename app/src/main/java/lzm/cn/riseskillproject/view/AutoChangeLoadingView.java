package lzm.cn.riseskillproject.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import lzm.cn.riseskillproject.R;

/**
 * 练习进度条
 * Created by lizhiming on 2018/2/6.
 */

public class AutoChangeLoadingView extends View {

    private Paint mTrianglePaint, mSquarePaint, mCirclePaint;

    private int mTriangleColor = Color.RED;
    private int mSquareColor = Color.BLUE;
    private int mCircleColor = Color.YELLOW;

    public enum CANVAS_STYLE {
    }

    public AutoChangeLoadingView(Context context) {
        this(context, null);
    }

    public AutoChangeLoadingView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AutoChangeLoadingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.QQStepView);

        mTriangleColor = array.getColor(R.styleable.QQStepView_outerColor, mTriangleColor);
        mSquareColor = array.getColor(R.styleable.QQStepView_innerColor, mSquareColor);
        mCircleColor = array.getColor(R.styleable.QQStepView_stepTextColor, mCircleColor);

        array.recycle();

        mTrianglePaint = new Paint();
        mTrianglePaint.setAntiAlias(true);
        mTrianglePaint.setColor(mTriangleColor);
        mTrianglePaint.setStyle(Paint.Style.FILL);

        mSquarePaint = new Paint();
        mSquarePaint.setAntiAlias(true);
        mSquarePaint.setColor(mSquareColor);
        mSquarePaint.setStyle(Paint.Style.FILL);

        mCirclePaint = new Paint();
        mCirclePaint.setAntiAlias(true);
        mCirclePaint.setColor(mCircleColor);
        mSquarePaint.setStyle(Paint.Style.FILL);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        //获取宽高模式
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);


        setMeasuredDimension(width > height ? height : width, width > height ? height : width);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


    }

    public void setCurrentStep(int stepCurrent) {
        //不断绘制
        invalidate();
    }
}
