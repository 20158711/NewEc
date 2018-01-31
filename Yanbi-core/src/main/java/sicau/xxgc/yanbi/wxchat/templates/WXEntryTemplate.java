package sicau.xxgc.yanbi.wxchat.templates;

import sicau.xxgc.yanbi.wxchat.BaseWXEntryActivity;
import sicau.xxgc.yanbi.wxchat.YanbiWeChat;

/**
 * Created by yanbi on 2018/1/29.
 */

public class WXEntryTemplate extends BaseWXEntryActivity {

    @Override
    protected void onSignInSuccess(String userInfo) {
        YanbiWeChat.getInstance().getSignInCallback().onSignInSuccess(userInfo);
    }
}
