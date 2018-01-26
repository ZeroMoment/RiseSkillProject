package lzm.cn.riseskillproject.uitl;

import android.content.Context;
import android.util.TypedValue;

/**
 * Created by lizhiming on 2018/1/26.
 */

public class GloabalUtils {
    public static int sp2px(Context context, int sp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp,
                context.getResources().getDisplayMetrics());
    }
}
