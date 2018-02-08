package lzm.cn.riseskillproject;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.animation.DecelerateInterpolator;

import lzm.cn.riseskillproject.view.TrainingProgressView;

/**
 * Created by lizhiming on 2018/2/6.
 */

public class JuniorTrainingActivity extends BaseActivity {

    private String mTitle;

    @Override
    protected void initView(Bundle bundle) {
        setContentView(R.layout.activity_training_junior_view);

        mTitle = getIntent().getExtras().getString("title");

        final TrainingProgressView stepView = (TrainingProgressView) findViewById(R.id.training_progress_view);
        stepView.setSetMax(4000);

        //属性动画
        ValueAnimator valueAnimator = ObjectAnimator.ofFloat(0, 3000);
        valueAnimator.setDuration(2000);
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
}
