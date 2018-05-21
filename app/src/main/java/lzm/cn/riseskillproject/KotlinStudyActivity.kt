package lzm.cn.riseskillproject

import android.os.Bundle

import lzm.cn.riseskillproject.MainActivity.PAGE_TITLE

/**
 * Created by lizhiming on 2018/2/6.
 */

class KotlinStudyActivity : BaseActivity() {

    private var mTitle: String? = null

    override fun initView(bundle: Bundle?) {
        setContentView(R.layout.activity_kotlin_study)

        mTitle = intent.extras!!.getString(PAGE_TITLE)


    }

    override fun getPageTitle(): String? {
        return mTitle
    }
}
