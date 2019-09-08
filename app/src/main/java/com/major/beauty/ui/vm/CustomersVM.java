package com.major.beauty.ui.vm;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

/**
 * @desc: TODO
 * @author: Major
 * @since: 2019/9/8 18:20
 */
public class CustomersVM extends ViewModel {

    public static final int ADD = 1;
    public static final int DEL = 2;

    private SingletonLiveData<Integer> mUpdate = SingletonLiveData.getInstance();

    public SingletonLiveData<Integer> getUpdate() {
        return mUpdate;
    }

    public static class SingletonLiveData<T> extends LiveData<T> {

        private SingletonLiveData() {
        }

        @Override
        public void postValue(T value) {
            super.postValue(value);
        }

        @Override
        public void setValue(T value) {
            super.setValue(value);
        }

        private static SingletonLiveData sInstance;

        public static <T> SingletonLiveData<T> getInstance() {
            if (sInstance == null) {
                sInstance = new SingletonLiveData<>();
            }
            return sInstance;
        }
    }
}
