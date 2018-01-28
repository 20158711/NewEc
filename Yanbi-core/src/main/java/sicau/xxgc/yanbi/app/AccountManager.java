package sicau.xxgc.yanbi.app;

import sicau.xxgc.yanbi.util.storage.YanbiPreference;

/**
 * Created by yanbi on 2018/1/27.
 */

public class AccountManager {

    private enum SignTag{
        SIGN_TAG
    }

    //可在登录状态，登录后调用
    public static void setSignState(boolean state){
        YanbiPreference.setAppFlag(SignTag.SIGN_TAG.name(),state);
    }

    private static boolean isSignIn(){
        return YanbiPreference.getAppFlag(SignTag.SIGN_TAG.name());
    }

    public static void checkAccount(IUserChecker checker){
        if (isSignIn()){
            checker.onSignIn();
        }else {
            checker.onNotSignIn();
        }
    }
}
