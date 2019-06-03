package com.major.beauty.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Desc: TODO
 * <p>
 * Author: meijie
 * PackageName: com.major.beauty.base
 * ProjectName: Beauty
 * Date: 2019/6/3 12:42
 */
public abstract class BaseFgt extends Fragment {

    private Unbinder mBind;
    protected FragmentActivity mActivity;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = View.inflate(getContext(), getRootView(), null);
        mBind = ButterKnife.bind(this, inflate);
        init();

        return inflate;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mActivity = getActivity();
    }


    @Override
    public void onDestroyView() {
        mBind.unbind();

        super.onDestroyView();
    }

    protected abstract int getRootView();

    protected abstract void init();
}
