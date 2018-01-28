package sicau.xxgc.yanbi.sign;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import sicau.xxgc.yanbi.app.AccountManager;
import sicau.xxgc.yanbi.database.DatabaseManager;
import sicau.xxgc.yanbi.database.UserProfile;

/**
 * Created by yanbi on 2018/1/27.
 */

public class SignHandler {

    public static void onSignIn(String response,ISignListener signListener){
        final JSONObject profileJson= JSON.parseObject(response).getJSONObject("data");
        final long userId=profileJson.getLong("userId");
        final String name=profileJson.getString("name");
        final String avatar=profileJson.getString("avatar");
        final String gender=profileJson.getString("gender");
        final String address=profileJson.getString("address");

        final UserProfile profile=new UserProfile(userId,name,avatar,gender,address);
        DatabaseManager.getInstance().getDao().insert(profile);

        //已经注册并登录成功
        AccountManager.setSignState(true);
        signListener.onSignInSuccess();

    }

    public static void onSignUp(String response,ISignListener signListener){
        final JSONObject profileJson= JSON.parseObject(response).getJSONObject("data");
        final long userId=profileJson.getLong("userId");
        final String name=profileJson.getString("name");
        final String avatar=profileJson.getString("avatar");
        final String gender=profileJson.getString("gender");
        final String address=profileJson.getString("address");

        final UserProfile profile=new UserProfile(userId,name,avatar,gender,address);
        DatabaseManager.getInstance().getDao().insert(profile);

        //已经注册并登录成功
        AccountManager.setSignState(true);
        signListener.onSignUpSuccess();

    }

}
