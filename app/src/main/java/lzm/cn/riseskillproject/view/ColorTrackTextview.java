package lzm.cn.riseskillproject.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

import lzm.cn.riseskillproject.R;

/**
 * Created by lizhiming on 2018/1/31.
 */

/**
 * 字体渐变色
 */
@SuppressLint("AppCompatCustomView")
public class ColorTrackTextview extends TextView {

    private Paint mOriginPaint;
    private Paint mChangePaint;

    private float mCurrentProgress = 0.0f;

    //实现不同的朝向
    private Direction mDirection = Direction.LEFT_TO_RIGHT;

    public enum Direction {
        LEFT_TO_RIGHT, RIGHT_TO_LEFT
    }

    public ColorTrackTextview(Context context) {
        this(context, null);
    }

    public ColorTrackTextview(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ColorTrackTextview(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint(context, attrs);
    }

    private void initPaint(Context context, AttributeSet attrs) {

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ColorTrackTextview);

        int originColor = typedArray.getColor(R.styleable.ColorTrackTextview_originColor, Color.BLACK);
        int changeColor = typedArray.getColor(R.styleable.ColorTrackTextview_changeColor, Color.RED);

        mOriginPaint = getPaintByColor(originColor);
        mChangePaint = getPaintByColor(changeColor);

        typedArray.recycle();

    }

    private Paint getPaintByColor(int paintColor) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(paintColor);
        //防抖动
        paint.setDither(true);
        paint.setTextSize(getTextSize());
        return paint;
    }

    //利用clipRect的API 可以裁剪  左边用一个画笔画， 右边用另一个画笔去画，不断的去改变中间值
    @Override
    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);不用系统的，自己画

        //根据进度，把中间值算出来
        int middle = (int) (mCurrentProgress * getWidth());

        if (mDirection == Direction.LEFT_TO_RIGHT) {
            //绘制变色
            drawText(canvas, mChangePaint, 0, middle);

            drawText(canvas, mOriginPaint, middle, getWidth());
        } else {
            //绘制变色
            drawText(canvas, mChangePaint, getWidth() - middle, getWidth());

            drawText(canvas, mOriginPaint, 0, getWidth() - middle);
        }
    }

    private void drawText(Canvas canvas, Paint paint, int star, int end) {
        canvas.save();

        Rect clipRect = new Rect(star, 0, end, getHeight());
        canvas.clipRect(clipRect);

        String text = getText().toString();
        //获取字体宽度
        Rect bounds = new Rect();
        paint.getTextBounds(text, 0, text.length(), bounds);

        int x = getWidth() / 2 - bounds.width() / 2;
        //基线
        Paint.FontMetricsInt fontMetricsInt = paint.getFontMetricsInt();
        int dy = (fontMetricsInt.bottom - fontMetricsInt.top) / 2 - fontMetricsInt.bottom;
        int baseLine = getHeight() / 2 + dy;

        canvas.drawText(text, x, baseLine, paint);
        canvas.restore();
    }


    public void setDirection(Direction direction) {
        this.mDirection = direction;
    }

    public void setCurrentProgress(float currentProgress) {
        this.mCurrentProgress = currentProgress;
        invalidate();
    }

}
