package sicau.xxgc.yanbi.wxchat;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import sicau.xxgc.yanbi.net.RestClient;

/**
 * Created by yanbi on 2018/1/30.
 */

public abstract class BaseWXEntryActivity extends BaseWXActivity{

    //用户登录成功后回调
    protected abstract void onSignInSuccess(String userInfo);

    //微信发送请求到第三方应用后的回调
    @Override
    public void onReq(BaseReq baseReq) {

    }

    //第三方应用发送请求到微信后的回调
    @Override
    public void onResp(BaseResp baseResp) {
        final String code=((SendAuth.Resp)baseResp).code;
        final StringBuilder authUrl=new StringBuilder();
        authUrl
                .append("http://api.weixin.qq.com/sns/oauth2/access_token?appid=")
                .append(YanbiWeChat.APP_ID)
                .append("&secret=")
                .append(YanbiWeChat.APP_SECRET)
                .append("&code=")
                .append(code)
                .append("&grant_type=authorization_code");
        Log.d("###", "authUrl: "+authUrl.toString());
        getAuth(authUrl.toString());
    }
    private void getAuth(String authUrl){
        RestClient
                .builder()
                .url(authUrl)
                .success((String response)->{
                    final JSONObject authObj= JSON.parseObject(response);
                    final String accessToken=authObj.getString("access_token");
                    final String openId=authObj.getString("openid");
                    final StringBuilder userInfoUrl=new StringBuilder();
                    userInfoUrl
                            .append("https://api.weixin.qq.com/sns/userinfo?access_token=")
                            .append(accessToken)
                            .append("&openid=")
                            .append(openId)
                            .append("&lang=")
                            .append("zh_CN");
                    Log.d("###", "userInfoUrl: "+userInfoUrl.toString());
                    getUserInfo(userInfoUrl.toString());
                })
                .build()
                .get();
    }

    private void getUserInfo(String userInfoUrl){
        RestClient.builder()
                .url(userInfoUrl)
                .success((String response)->{
                    onSignInSuccess(response);
                })
                .failure(()->{

                })
                .error((int code,String msg)->{

                })
                .build()
                .get();
    }
}
