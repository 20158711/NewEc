package sicau.xxgc.yanbi.launcher;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.Toast;

import java.text.MessageFormat;
import java.util.Timer;

import butterknife.BindView;
import butterknife.OnClick;
import sicau.xxgc.yanbi.delegates.YanbiDelegate;
import sicau.xxgc.yanbi.ec.R2;
import sicau.xxgc.yanbi.ui.launcher.ScrollLauncherTag;
import sicau.xxgc.yanbi.util.storage.YanbiPreference;
import sicau.xxgc.yanbi.util.timer.BaseTimerTask;
import sicau.xxgc.yanbi.util.timer.ITimerListener;

/**
 * Created by yanbi on 2018/1/26.
 */

public class LauncherDelegate extends YanbiDelegate implements ITimerListener{

    @BindView(R2.id.tv_launcher_timer)
    AppCompatTextView mTvTimer=null;
    private Timer mTimer=null;
    private int mCount=5;

    @Override
    public Object setLayout() {
        return sicau.xxgc.yanbi.ec.R.layout.delegate_launcher;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initTimer();
    }

    //判断是否显示滑动启动页
    private void checkIsShowScroll(){
        if (!YanbiPreference.getAppFlag(ScrollLauncherTag.HAS_FIRST_LAUNCHER_APP.name())){
            start(new LauncherScrollDelegate(),SINGLETASK);
        }else {
            //TODO
            //检查登录
            Toast.makeText(_mActivity, "check login", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onTimer() {
        getProxyActivity().runOnUiThread(()->{
            if (mTvTimer != null) {
                mTvTimer.setText(MessageFormat.format("跳过{0}",mCount--));
                if (mCount<0){
                    if (mTimer != null) {
                        mTimer.cancel();
                        mTimer=null;
                        checkIsShowScroll();
                    }
                }
            }
        });
    }

    @OnClick(R2.id.tv_launcher_timer)
    void onClickTimerView(){
        if (mTimer != null) {
            mTimer.cancel();
            mTimer=null;
            checkIsShowScroll();
        }
    }

    private void initTimer(){
        mTimer=new Timer();
        final BaseTimerTask task=new BaseTimerTask(this);
        mTimer.schedule(task,0,1000);
    }
}
