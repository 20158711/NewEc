package sicau.xxgc.yanbi.wxchat;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;

/**
 * Created by yanbi on 2018/1/30.
 */

public abstract class BaseWXActivity extends AppCompatActivity implements IWXAPIEventHandler{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        YanbiWeChat.getInstance().getWXAPI().handleIntent(getIntent(),this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        YanbiWeChat.getInstance().getWXAPI().handleIntent(getIntent(),this);
    }
}
