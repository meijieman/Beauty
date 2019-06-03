package com.major.beauty.ui.decoration;

import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * @desc: TODO
 * @author: Major
 * @since: 2019/6/3 22:52
 */
public class SpaceDecoration extends RecyclerView.ItemDecoration {

    private int mSpace = 10;

    public SpaceDecoration() {
    }

    public SpaceDecoration(int space) {
        mSpace = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof LinearLayoutManager) {
            if (((LinearLayoutManager)layoutManager).getOrientation() == LinearLayoutManager.HORIZONTAL) {
                outRect.set(mSpace, 0, mSpace, 0);
            } else {
                outRect.set(0, mSpace, 0, mSpace);

            }
        }
    }
}
