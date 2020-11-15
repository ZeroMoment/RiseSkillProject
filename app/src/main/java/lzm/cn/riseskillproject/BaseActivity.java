package lzm.cn.riseskillproject;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Created by lizhiming on 2018/2/1.
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView(savedInstanceState);

        setTitle();
    }

    private void setTitle() {
        View backView = findViewById(R.id.common_title_backview);
        if (backView != null) {
            backView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }

        TextView commonTitleTv = (TextView) findViewById(R.id.common_title_tv);
        if (commonTitleTv != null) {
            commonTitleTv.setText(getPageTitle());
        }
    }

    protected abstract void initView(Bundle bundle);

    protected abstract String getPageTitle();
}
