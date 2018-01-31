package lzm.cn.riseskillproject;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import lzm.cn.riseskillproject.fragment.ItemFragment;
import lzm.cn.riseskillproject.view.ColorTrackTextview;

public class MainActivity extends AppCompatActivity {

    private String[] items = {"直播", "推荐", "视频", "图片", "段子", "精华"};
    private LinearLayout mIndiatorContainer; //变成通用的
    private List<ColorTrackTextview> mIndicators;
    private ViewPager mViewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mIndicators = new ArrayList<>();
        mIndiatorContainer = (LinearLayout) findViewById(R.id.indicator_view);
        mViewPager = (ViewPager) findViewById(R.id.view_pager);

        initIndicator();
        initViewPager();

    }

    private void initIndicator() {

        for (int i=0; i<items.length; i++) {
            //动态添加颜色跟踪的Textview
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            params.weight = 1;
            ColorTrackTextview colorTrackTextview = new ColorTrackTextview(this);
            //设置颜色
            colorTrackTextview.setTextSize(20);
            colorTrackTextview.setText(items[i]);
            colorTrackTextview.setLayoutParams(params);
            //把新的加入LinearLayout容器
            mIndiatorContainer.addView(colorTrackTextview);
            //加入集合
            mIndicators.add(colorTrackTextview);
        }

    }


    private void initViewPager() {
        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return ItemFragment.newInstance(items[position]);
            }

            @Override
            public int getCount() {
                return items.length;
            }

        });

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

}
