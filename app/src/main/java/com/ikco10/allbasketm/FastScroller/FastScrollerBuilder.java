/*
 * Copyright 2019 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ikco10.allbasketm.FastScroller;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.util.Consumer;
import androidx.recyclerview.widget.RecyclerView;

import com.ikco10.allbasketm.R;

public class FastScrollerBuilder {

    @NonNull
    private final ViewGroup mView;

    /*
    @Nullable
    private FastScroller.ViewHelper mViewHelper;
    */

    @Nullable
    private Rect mPadding;

    private Drawable mTrackDrawable;

    private Drawable mThumbDrawable;

    private Consumer<TextView> mPopupStyle;

    /*
    @Nullable
    private FastScroller.AnimationHelper mAnimationHelper;
    */

    public FastScrollerBuilder(@NonNull ViewGroup view) {
        mView = view;
        Context context = mView.getContext();
        mTrackDrawable = AppCompatResources.getDrawable(context, R.drawable.fastscroller_track);
        mThumbDrawable = AppCompatResources.getDrawable(context, R.drawable.fastscroller_thumb);
        mPopupStyle = PopupStyles.DEFAULT;

        if (mPadding == null) {
            mPadding = new Rect();
        }

        mPadding.set(context.getResources().getDimensionPixelSize(R.dimen.fastscroller_left),
                context.getResources().getDimensionPixelSize(R.dimen.fastscroller_top),
                context.getResources().getDimensionPixelSize(R.dimen.fastscroller_right),
                context.getResources().getDimensionPixelSize(R.dimen.fastscroller_bottom));

    }

    /*
    @NonNull
    public FastScrollerBuilder setViewHelper(@Nullable FastScroller.ViewHelper viewHelper) {
        mViewHelper = viewHelper;
        return this;
    }
    */

    @NonNull
    public FastScrollerBuilder setPadding(Context context) {
        if (mPadding == null) {
            mPadding = new Rect();
        }

        mPadding.set(context.getResources().getDimensionPixelSize(R.dimen.fastscroller_left),
                context.getResources().getDimensionPixelSize(R.dimen.fastscroller_top),
                context.getResources().getDimensionPixelSize(R.dimen.fastscroller_right),
                context.getResources().getDimensionPixelSize(R.dimen.fastscroller_bottom));

        return this;
    }

    @NonNull
    public FastScrollerBuilder setPadding(@Nullable Rect padding) {
        if (padding != null) {
            if (mPadding == null) {
                mPadding = new Rect();
            }
            mPadding.set(padding);
        } else {
            mPadding = null;
        }
        return this;
    }

    @NonNull
    public FastScrollerBuilder setTrackDrawable(@NonNull Drawable trackDrawable) {
        mTrackDrawable = trackDrawable;
        return this;
    }

    /*
    @NonNull
    public FastScrollerBuilder setThumbDrawable(@NonNull Drawable thumbDrawable) {
        mThumbDrawable = thumbDrawable;
        return this;
    }
    */

    /*
    @NonNull
    public FastScrollerBuilder setPopupStyle(@NonNull Consumer<TextView> popupStyle) {
        mPopupStyle = popupStyle;
        return this;
    }
    */

    /*
    public void setAnimationHelper(@Nullable FastScroller.AnimationHelper animationHelper) {
        mAnimationHelper = animationHelper;
    }

    */

    /*
    public void disableScrollbarAutoHide() {
        DefaultAnimationHelper animationHelper = new DefaultAnimationHelper(mView);
        animationHelper.setScrollbarAutoHideEnabled(false);
        mAnimationHelper = animationHelper;
    }
    */

    @NonNull
    public FastScroller build() {
        return new FastScroller(mView, getOrCreateViewHelper(), mPadding, mTrackDrawable,
                mThumbDrawable, mPopupStyle, getOrCreateAnimationHelper());
    }

    @NonNull
    private FastScroller.ViewHelper getOrCreateViewHelper() {
        /*
        if (mViewHelper != null) {
            return mViewHelper;
        }
        */

        if (mView instanceof ViewHelperProvider) {
            return ((ViewHelperProvider) mView).getViewHelper();
        } else if (mView instanceof RecyclerView) {
            return new RecyclerViewHelper((RecyclerView) mView);
        } else if (mView instanceof ScrollView) {
            throw new UnsupportedOperationException("Please use "
                    + FastScrollScrollView.class.getSimpleName() + " instead of "
                    + ScrollView.class.getSimpleName() + "for fast scroll");
        } else {
            throw new UnsupportedOperationException(mView.getClass().getSimpleName()
                    + " is not supported for fast scroll");
        }
    }

    @NonNull
    private FastScroller.AnimationHelper getOrCreateAnimationHelper() {
        /*
        if (mAnimationHelper != null) {
            return mAnimationHelper;
        }
        */

        return new DefaultAnimationHelper(mView);
    }

    /*
    public void setmAnimationHelper(@Nullable FastScroller.AnimationHelper mAnimationHelper) {
        this.mAnimationHelper = mAnimationHelper;
    }
    */
}
