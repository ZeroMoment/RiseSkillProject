package lzm.cn.riseskillproject.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import lzm.cn.riseskillproject.R;

/**
 * Created by lzm on 2017/11/5.
 */

public class SimpleTextView extends View {

    private String mText;
    private int mTextSize = 16;
    private int mTextColor = Color.BLACK;

    private Paint mPaint;

    public SimpleTextView(Context context) {
        this(context, null);
    }

    public SimpleTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SimpleTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.SimpleTextView);

        mText = array.getString(R.styleable.SimpleTextView_text);
        mTextColor = array.getColor(R.styleable.SimpleTextView_textColor, mTextColor);
        mTextSize = array.getDimensionPixelSize(R.styleable.SimpleTextView_textSize, mTextSize);

        array.recycle();

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setTextSize(mTextSize);
        mPaint.setColor(mTextColor);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        /**
         * 布局的宽高都是由这个方法指定
         * 指定控件的宽高，需要测量
         */
        //获取宽高模式
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        //1.确定的值，这个时候不需要计算，给的多少就是多少
        int width = MeasureSpec.getSize(widthMeasureSpec);

        //2.给的wrap_content 需要计算
        if(widthMode == MeasureSpec.AT_MOST) {
            //计算的宽度，与字体的长度、字体的大小有关  用画笔来测量
            Rect bounds = new Rect();
            //获取文本的rect
            mPaint.getTextBounds(mText, 0, mText.length(), bounds);
            width = bounds.width();

        }

        int height = MeasureSpec.getSize(heightMeasureSpec);

        if(heightMode == MeasureSpec.AT_MOST) {
            Rect bounds = new Rect();
            mPaint.getTextBounds(mText, 0, mText.length(), bounds);
            height = bounds.height();
        }

        setMeasuredDimension(width, height);
    }
}
