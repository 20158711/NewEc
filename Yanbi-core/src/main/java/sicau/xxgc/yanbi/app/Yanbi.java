package sicau.xxgc.yanbi.app;

import android.content.Context;
import android.os.Handler;

/**
 * Created by yanbi on 2018/1/23.
 */

public final class Yanbi {

    //初始化配置类并传入APPLICATION_CONTEXT
    public static Configurator init(Context context) {
        Configurator.getInstance()
                .getYanbiConfigs()
                .put(ConfigKeys.APPLICATION_CONTEXT, context.getApplicationContext());
        return Configurator.getInstance();
    }

    public static Configurator getConfigurator(){
       return Configurator.getInstance();
    }

    public static <T> T getConfiguration(Object key) {
        return getConfigurator().getConfiguration(key);
    }

    public static Context getApplicationContext() {
        return getConfiguration(ConfigKeys.APPLICATION_CONTEXT);
    }

    public static Handler getHandler() {
        return getConfiguration(ConfigKeys.HANDLER);
    }
}
