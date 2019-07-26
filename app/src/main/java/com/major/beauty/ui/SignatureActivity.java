package com.major.beauty.ui;

import android.view.View;

import com.github.gcacace.signaturepad.views.SignaturePad;
import com.major.beauty.R;
import com.major.beauty.base.BaseActivity;
import com.major.beauty.ui.view.SecondSurfaceView;
import com.major.beauty.ui.view.SignatureView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Desc: TODO
 * <p>
 * Author: meijie
 * PackageName: com.major.beauty.ui
 * ProjectName: Beauty
 * Date: 2019/7/26 10:05
 */
public class SignatureActivity extends BaseActivity {

    @BindView(R.id.sv_signature)
    SignatureView mSignatureView;

    @BindView(R.id.surfaceview)
    SecondSurfaceView mSecondSurfaceView;

    @BindView(R.id.signature_pad)
    SignaturePad mSignaturePad;

    @Override
    protected int getRootView() {
        return R.layout.act_signature;
    }

    @Override
    protected void init() {

    }

    @OnClick(R.id.btn_clear)
    void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_clear:
                mSignatureView.clear();

                mSecondSurfaceView.clean();

                mSignaturePad.clear();
                break;
            default:

                break;
        }
    }
}
