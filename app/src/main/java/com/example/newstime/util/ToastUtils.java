package com.example.newstime.util;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

public class ToastUtils {
//    Toast 实时显示和消失
    private ToastUtils() {

    }

    public static void showToast(Context context, String toastInfo) {
        if (null == context || TextUtils.isEmpty(toastInfo)) {
            return;
        }

        if (null == mToast) {
            mToast = Toast.makeText(context, toastInfo, Toast.LENGTH_LONG);
        } else {
            mToast.setText(toastInfo);
        }

        mToast.show();
    }

    public static void hideToast() {
        mToast.cancel();
    }

    private static Toast mToast = null;

}