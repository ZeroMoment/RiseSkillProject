package lzm.cn.riseskillproject.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
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

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
