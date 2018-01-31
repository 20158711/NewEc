package sicau.xxgc.yanbi.wxchat;

import android.app.Activity;

import com.bigkoo.convenientbanner.holder.Holder;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import sicau.xxgc.yanbi.app.ConfigKeys;
import sicau.xxgc.yanbi.app.Yanbi;
import sicau.xxgc.yanbi.wxchat.callbacks.IWeChatSignInCallback;

/**
 * Created by yanbi on 2018/1/30.
 */

public class YanbiWeChat {
    public static final String APP_ID= Yanbi.getConfiguration(ConfigKeys.WE_CHAT_APP_ID);
    public static final String APP_SECRET= Yanbi.getConfiguration(ConfigKeys.WE_CHAT_APP_SECRET);
    private final IWXAPI WXAPI;
    private IWeChatSignInCallback mSignInCallback=null;
    private static final class Holder{
        private static final YanbiWeChat INSTANCE=new YanbiWeChat();
    }
    public static YanbiWeChat getInstance(){
        return Holder.INSTANCE;
    }
    private YanbiWeChat(){
        final Activity activity=Yanbi.getConfiguration(ConfigKeys.ACTIVITY);
        WXAPI= WXAPIFactory.createWXAPI(activity,APP_ID,true);
        WXAPI.registerApp(APP_ID);
    }
    public final IWXAPI getWXAPI(){
        return WXAPI;
    }
    public final void signIn(){
        final SendAuth.Req req=new SendAuth.Req();
        req.scope="snsapi_userinfo";
        req.state="random_state";
        WXAPI.sendReq(req);
    }
    public IWeChatSignInCallback getSignInCallback(){
        return mSignInCallback;
    }
    public YanbiWeChat onSignSuccess(IWeChatSignInCallback callback){
        this.mSignInCallback=callback;
        return this;
    }
}
