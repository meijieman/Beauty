package com.major.beauty.ui.behavior;

import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.view.View;

/**
 * Desc: 向上滑动隐藏FloatingActionButton，向下滑动显示FloatingActionButton
 * <p>
 * Author: meijie
 * PackageName: com.major.beauty.ui.behavior
 * ProjectName: Beauty
 * Date: 2019/6/4 13:53
 */
public class HideButtonBehavior extends CoordinatorLayout.Behavior {

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View child, View directTargetChild, View target, int nestedScrollAxes) {
        //滚动发生时，关心该事件。
        return true;
    }

    /**
     * 滚动开始时，监听该事件。
     * 滚动监听
     */
    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, View child, View target, int dx, int dy, int[] consumed) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed);
        if (dy < 0) {
            ViewCompat.animate(child).scaleX(1).scaleY(1).start();//显示和不显示
        } else {
            ViewCompat.animate(child).scaleX(0).scaleY(0).start();
        }
    }
}
