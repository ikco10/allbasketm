package com.ikco10.allbasketsignup.Utils;

import android.os.SystemClock;
import android.view.View;

public abstract class OnSingleClickListener implements View.OnClickListener {
    private static final long MIN_CLICK_INTERVAL = 500;

    private long mLastClickTime;

    public abstract void onSingleClick(View v);

    @Override
    public final void onClick(View v) {

        long elapsedTime = SystemClock.elapsedRealtime() - mLastClickTime;

        if (elapsedTime <= MIN_CLICK_INTERVAL)
            return;

        mLastClickTime = SystemClock.elapsedRealtime();
        onSingleClick(v);
    }
}
