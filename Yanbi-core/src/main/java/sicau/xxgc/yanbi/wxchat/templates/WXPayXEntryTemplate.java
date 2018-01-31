package sicau.xxgc.yanbi.wxchat.templates;

import sicau.xxgc.yanbi.wxchat.BaseWXEntryActivity;

/**
 * Created by yanbi on 2018/1/29.
 */

public class WXPayXEntryTemplate extends BaseWXEntryActivity {

    @Override
    protected void onResume() {
        super.onResume();
        finish();
        overridePendingTransition(0,0);
    }

    @Override
    protected void onSignInSuccess(String userInfo) {

    }
}
