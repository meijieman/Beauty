package com.major.beauty.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewStub;
import android.widget.LinearLayout;

import com.major.beauty.R;
import com.major.beauty.bean.SmartItem;

/**
 * Desc: TODO
 * <p>
 * Author: meijie
 * PackageName: com.major.beauty.ui.view
 * ProjectName: Beauty
 * Date: 2019/8/15 16:59
 */
public class SmartItemView extends LinearLayout {

    private SmartItem mItem;

    public SmartItemView(Context context) {
        super(context);
    }

    public SmartItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        addView(View.inflate(getContext(), R.layout.view_smart_item, null));
    }

    public void setType(int type) {
        mItem = new SmartItem(SmartItem.ITEM_DEFAULT);
        mItem.setType(type);

    }

    public void setItem(SmartItem item) {
        mItem = item;

        reload();
    }

    // 根据 type 装载数据
    private void reload() {
        if (mItem == null) {
            return;
        }

        switch (mItem.getType()) {
            case SmartItem.ITEM_EDIT: {
                ViewStub stub = findViewById(R.id.vs_edit);
                stub.inflate();
                break;

            }
            case SmartItem.ITEM_TIMMER: {
                ViewStub stub = findViewById(R.id.vs_timer);
                stub.inflate();
                break;

            }
            case SmartItem.ITEM_SPINNER: {
                ViewStub stub = findViewById(R.id.vs_spinner);
                stub.inflate();
                break;

            }
            default:

                break;
        }
    }
}
