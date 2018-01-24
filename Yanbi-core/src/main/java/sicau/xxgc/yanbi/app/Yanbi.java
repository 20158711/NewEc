package sicau.xxgc.yanbi.app;

import android.content.Context;

import java.util.HashMap;

/**
 * Created by yanbi on 2018/1/23.
 */

public final class Yanbi {

    //初始化配置类并传入APPLICATION_CONTEXT
   public static Configurator init(Context context){
        getConfigurations().put(ConfigType.APPLICATION_CONTEXT.name(),context.getApplicationContext());
        return Configurator.getInstance();
    }

    //获取配置
    private static HashMap<Object,Object> getConfigurations(){
        return Configurator.getInstance().getYanbiConfigs();
    }

    //获取Context
    public static Context getApplication(){
        return (Context) getConfigurations().get(ConfigType.APPLICATION_CONTEXT.name());
    }
}
