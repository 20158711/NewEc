package sicau.xxgc.newfastec;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.widget.Toast;

import me.yokeyword.fragmentation.SupportActivityDelegate;
import sicau.xxgc.yanbi.activities.ProxyActivity;
import sicau.xxgc.yanbi.delegates.YanbiDelegate;
import sicau.xxgc.yanbi.launcher.LauncherDelegate;
import sicau.xxgc.yanbi.sign.ISignListener;
import sicau.xxgc.yanbi.sign.SignInDelegate;
import sicau.xxgc.yanbi.sign.SignUpDelegate;
import sicau.xxgc.yanbi.ui.launcher.ILauncherListener;
import sicau.xxgc.yanbi.ui.launcher.OnLauncherFinishTag;

public class ExampleActivity extends ProxyActivity implements
        ISignListener,
        ILauncherListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }

    @Override
    public YanbiDelegate setRootDelegate() {
        return new LauncherDelegate();
    }

    @Override
    public void onSignInSuccess() {
        Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSignUpSuccess() {
        Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLauncherFinish(OnLauncherFinishTag tag) {
        switch (tag) {
            case SIGNED:
                Toast.makeText(this, "启动结束，用户登录了", Toast.LENGTH_SHORT).show();
                startWithPop(new ExampleDelegate());
                break;
            case NOT_SIGNED:
                Toast.makeText(this, "启动结束，用户没登录", Toast.LENGTH_SHORT).show();
                //startWithPop，启动的同时把栈中的清除掉
                startWithPop(new SignInDelegate());
                break;
            default:
                break;
        }
    }
}
