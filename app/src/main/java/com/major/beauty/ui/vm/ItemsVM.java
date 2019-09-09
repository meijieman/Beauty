package com.major.beauty.ui.vm;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

/**
 * @desc: TODO
 * @author: Major
 * @since: 2019/9/8 18:20
 */
public class ItemsVM extends ViewModel {

    public static final int ADD = 1;
    public static final int DEL = 2;

    private static MutableLiveData<Integer> mUpdate = new MutableLiveData<>();

    public MutableLiveData<Integer> getUpdate() {
        return mUpdate;
    }

}
