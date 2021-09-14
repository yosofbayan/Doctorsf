package com.yb.doctors;

import android.app.Activity;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.View;

import androidx.annotation.IntRange;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewMargin extends RecyclerView.ItemDecoration {
    private final int columns;
    private int margin;
    private Activity activity;
    private DisplayMetrics displayMetrics;

    /**
     * constructor
     * @param margin desirable margin size in px between the views in the recyclerView
     * @param columns number of columns of the RecyclerView
     */
    public RecyclerViewMargin(@IntRange(from=0)int margin , @IntRange(from=0) int columns ,Activity activity) {
        this.margin = margin;
        this.columns=columns;
        this.activity=activity;
        displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

    }

    /**
     * Set different margins for the items inside the recyclerView: no top margin for the first row
     * and no left margin for the first column.
     */
    @Override
    public void getItemOffsets(Rect outRect, View view,
                               RecyclerView parent, RecyclerView.State state) {

        int position = parent.getChildLayoutPosition(view);

        if(position % columns == 0)
            outRect.right = dpToPx(margin/3);
        else if(position % columns == 1){
            outRect.right = dpToPx(margin/6);
            outRect.left = dpToPx(margin/6);
        }else{
            outRect.left = dpToPx(margin/3);
        }

        //set bottom margin to all
        outRect.bottom = dpToPx(margin/2);

    }
    private int  dpToPx(int dp){

        switch (displayMetrics.densityDpi){
            case DisplayMetrics.DENSITY_LOW:
                dp = dp * DisplayMetrics.DENSITY_LOW/160;
                break;

            case DisplayMetrics.DENSITY_HIGH:
                dp = dp * DisplayMetrics.DENSITY_HIGH/160;
                break;
            case DisplayMetrics.DENSITY_XHIGH:
                dp = dp* DisplayMetrics.DENSITY_XHIGH/160;
                break;
            case DisplayMetrics.DENSITY_XXHIGH:
                dp = dp * DisplayMetrics.DENSITY_XXHIGH/160;
                break;
            case DisplayMetrics.DENSITY_XXXHIGH:
                dp = dp * DisplayMetrics.DENSITY_XXXHIGH/160;
                break;
        }

        return  dp;

    }
}