package com.major.beauty.base;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

/**
 * @desc: TODO
 * @author: Major
 * @since: 2019/4/3 22:45
 */
public class BaseViewModel<S, F> extends AndroidViewModel {

    protected MutableLiveData<S> mSuccess = new MutableLiveData<>();
    protected MutableLiveData<F> mFailure = new MutableLiveData<>();
    protected MutableLiveData<Boolean> mLoading = new MutableLiveData<>();

    public BaseViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<Boolean> onLoading() {
        return mLoading;
    }

    public MutableLiveData<S> onSuccess(){
        return mSuccess;
    }

    public MutableLiveData<F> onFailure(){
        return mFailure;
    }
}
