package lzm.cn.riseskillproject;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import lzm.cn.riseskillproject.view.QQStepView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final QQStepView stepView = (QQStepView) findViewById(R.id.step_view);
        stepView.setSetMax(4000);

        //属性动画
        ValueAnimator valueAnimator = ObjectAnimator.ofFloat(0, 3000);
        valueAnimator.setDuration(3000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float currentStep = (float) animation.getAnimatedValue();
                stepView.setCurrentStep((int) currentStep);
            }
        });
        valueAnimator.start();
    }
}
