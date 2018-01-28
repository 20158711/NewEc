package sicau.xxgc.yanbi.sign;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.OnClick;
import sicau.xxgc.yanbi.delegates.YanbiDelegate;
import sicau.xxgc.yanbi.ec.R;
import sicau.xxgc.yanbi.ec.R2;
import sicau.xxgc.yanbi.enums.RequestPath;
import sicau.xxgc.yanbi.net.RestClient;

/**
 * Created by yanbi on 2018/1/27.
 */

public class SignInDelegate extends YanbiDelegate {

    @BindView(R2.id.edit_sign_in_email)
    TextInputEditText mEmail=null;
    @BindView(R2.id.edit_sign_in_password)
    TextInputEditText mPassword=null;

    private ISignListener mISignListener=null;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if(activity instanceof ISignListener){
            mISignListener= (ISignListener) activity;
        }
    }

    @OnClick(R2.id.btn_sign_in)
    void onClickSignIn(){
        if (checkForm()) {
            RestClient.builder()
                    .url(RequestPath.USER_PROFILE.getUrl())
                    .params("email", mEmail.getText().toString())
                    .params("password", mPassword.getText().toString())
                    .success((String response) -> {
                        Toast.makeText(_mActivity, response, Toast.LENGTH_SHORT).show();
                        Log.d("###", "onClickSignUp: " + response);
                        SignHandler.onSignIn(response,mISignListener);
                    })
                    .build()
                    .post();
            Toast.makeText(_mActivity, "验证通过", Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R2.id.icon_sign_in_wechat)
    void onClickWeChat(){
        //TODO
        //微信登录
    }

    @OnClick(R2.id.tv_link_sign_up)
    void onClickLink(){
        start(new SignUpDelegate());
    }

    private boolean checkForm(){
        final String email=mEmail.getText().toString();
        final String password=mPassword.getText().toString();
        boolean isPass=true;
        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            mEmail.setError("错误的邮箱格式");
            isPass=false;
        }else {
            mEmail.setError(null);
        }
        if (password.isEmpty() || password.length()<6){
            mPassword.setError("请填写至少6位数密码");
            isPass=false;
        }else {
            mPassword.setError(null);
        }
        return isPass;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_in;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
