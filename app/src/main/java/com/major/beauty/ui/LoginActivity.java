package com.major.beauty.ui;

import com.airbnb.lottie.LottieAnimationView;
import com.major.beauty.R;
import com.major.beauty.base.BaseActivity;

import butterknife.BindView;

/**
 * Desc: TODO
 * <p>
 * Author: meijie
 * PackageName: com.major.beauty.ui
 * ProjectName: Beauty
 * Date: 2019/6/5 9:19
 */
public class LoginActivity extends BaseActivity {


    @BindView(R.id.animation_view)
    LottieAnimationView mLottie;

    @Override
    protected int getRootView() {
        return R.layout.act_login;
    }

    @Override
    protected void init() {

        mLottie.playAnimation();

    }
}
