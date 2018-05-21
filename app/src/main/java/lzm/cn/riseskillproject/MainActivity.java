package lzm.cn.riseskillproject;

import android.content.Intent;
import android.os.Bundle;
import android.os.ParcelUuid;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import lzm.cn.riseskillproject.adapter.MainLeftMenuAdapter;
import lzm.cn.riseskillproject.fragment.ItemFragment;
import lzm.cn.riseskillproject.view.ColorTrackTextview;

public class MainActivity extends AppCompatActivity implements MainLeftMenuAdapter.ItemClickListner {

    private DrawerLayout mDrawerLayout;

    private String[] items = {"直播", "推荐", "视频", "图片", "段子", "精华"};
    private LinearLayout mIndiatorContainer; //变成通用的
    private List<ColorTrackTextview> mIndicators;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.main_drawerlayout);
        View menuView = findViewById(R.id.main_left_menu);
        menuView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.openDrawer(Gravity.LEFT);
            }
        });

        initLeftMenuView();

        mIndicators = new ArrayList<>();
        mIndiatorContainer = (LinearLayout) findViewById(R.id.indicator_view);
        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        initIndicator();
        initViewPager();
    }

    /**
     * 左侧菜单显示
     */
    private void initLeftMenuView() {

        RecyclerView leftRecyclerView = (RecyclerView) findViewById(R.id.main_left_recyclerview);
        MainLeftMenuAdapter menuAdapter = new MainLeftMenuAdapter(getBaseContext(), this);
        leftRecyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        leftRecyclerView.setAdapter(menuAdapter);
        menuAdapter.setMenuDatas(getCustomMenus());

    }

    private void initIndicator() {

        for (int i = 0; i < items.length; i++) {
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
                //1.左边位置 position
                ColorTrackTextview left = mIndicators.get(position);
                left.setDirection(ColorTrackTextview.Direction.RIGHT_TO_LEFT);
                left.setCurrentProgress(1 - positionOffset);

                try {
                    ColorTrackTextview right = mIndicators.get(position + 1);
                    right.setDirection(ColorTrackTextview.Direction.LEFT_TO_RIGHT);
                    right.setCurrentProgress(positionOffset);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onItemClick(int poisition) {
        List<String> menuListData = getCustomMenus();
        if (menuListData == null || menuListData.isEmpty()) {
            return;
        }

        mDrawerLayout.closeDrawer(Gravity.LEFT);

        Intent startIntent = null;
        String key = menuListData.get(poisition);
        if (JUNIOR_STUDY.equals(key)) {
            startIntent = new Intent(this, SimpleCustomeViewActivity.class);
            startIntent.putExtra(PAGE_TITLE, JUNIOR_STUDY);
            startActivity(startIntent);
        } else if (JUNIOR_TRAINING.equals(key)) {
            startIntent = new Intent(this, JuniorTrainingActivity.class);
            startIntent.putExtra(PAGE_TITLE, JUNIOR_TRAINING);
            startActivity(startIntent);
        } else if(KOTLIN_STUDY.equals(key)) {
            startIntent = new Intent(this, KotlinStudyActivity.class);
            startIntent.putExtra(PAGE_TITLE, KOTLIN_STUDY);
            startActivity(startIntent);
        }
    }

    public static final String PAGE_TITLE = "page_titile";
    private final String JUNIOR_STUDY = "junior study";
    private final String JUNIOR_TRAINING = "junior training";
    private final String KOTLIN_STUDY = "kotlin study";

    /**
     * 获取左侧菜单数据
     *
     * @return
     */
    private List<String> getCustomMenus() {

        List<String> menuList = new ArrayList<>();
        menuList.add(JUNIOR_STUDY);
        menuList.add(JUNIOR_TRAINING);
        menuList.add(KOTLIN_STUDY);

        return menuList;
    }
}
