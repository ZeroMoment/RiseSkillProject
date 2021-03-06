package lzm.cn.riseskillproject;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

import lzm.cn.riseskillproject.view.ColorTrackTextview;
import lzm.cn.riseskillproject.view.QQStepView;

import static lzm.cn.riseskillproject.MainActivity.PAGE_TITLE;

/**
 * Created by lizhiming on 2018/1/31.
 */

public class SimpleCustomeViewActivity extends BaseActivity {

    private String mTitle;

    private ColorTrackTextview mColorTrackTextview;

    @Override
    protected void initView(Bundle bundle) {
        setContentView(R.layout.activity_simple_customview);

        mTitle = getIntent().getExtras().getString(PAGE_TITLE);

        mColorTrackTextview = (ColorTrackTextview) findViewById(R.id.color_track_textview);

        final QQStepView stepView = (QQStepView) findViewById(R.id.step_view);
        stepView.setSetMax(4000);

        //属性动画
        ValueAnimator valueAnimator = ObjectAnimator.ofFloat(0, 3000);
        valueAnimator.setDuration(3000);
        valueAnimator.setInterpolator(new DecelerateInterpolator()); //降速
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float currentStep = (float) animation.getAnimatedValue();
                stepView.setCurrentStep((int) currentStep);
            }
        });
        valueAnimator.start();
    }

    @Override
    protected String getPageTitle() {
        return mTitle;
    }

    public void leftToRight(View v) {
        mColorTrackTextview.setDirection(ColorTrackTextview.Direction.LEFT_TO_RIGHT);
        ValueAnimator valueAnimator = ObjectAnimator.ofFloat(0, 1);
        valueAnimator.setDuration(3000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float currentProgress = (float) animation.getAnimatedValue();
                mColorTrackTextview.setCurrentProgress(currentProgress);
            }
        });
        valueAnimator.start();
    }

    public void rightToLeft(View v) {
        mColorTrackTextview.setDirection(ColorTrackTextview.Direction.RIGHT_TO_LEFT);
        ValueAnimator valueAnimator = ObjectAnimator.ofFloat(0, 1);
        valueAnimator.setDuration(3000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float currentProgress = (float) animation.getAnimatedValue();
                mColorTrackTextview.setCurrentProgress(currentProgress);
            }
        });
        valueAnimator.start();
    }

}
