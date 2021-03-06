package sicau.xxgc.yanbi.app;

import android.app.Activity;

import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;

import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.Interceptor;

/**
 * Created by yanbi on 2018/1/23.
 */

public class Configurator {
    //使用WeakHashMap存放配置（键值对）
    private static final HashMap<Object,Object> YANBI_CONFIGS=new HashMap<>();
    //用于存放字体图标
    private static final ArrayList<IconFontDescriptor> ICONS=new ArrayList<>();
    private static final ArrayList<Interceptor> INTERCEPTORS=new ArrayList<>();
    //使用静态内部类实现单例
    private static class Holder{
        private static final Configurator INSTANCE=new Configurator();
    }

    //返回实例
    public static Configurator getInstance(){
        return Holder.INSTANCE;
    }

    //获取配置
    final HashMap<Object,Object> getYanbiConfigs(){
        return YANBI_CONFIGS;
    }

    //私有化构造方法并将CONFIG_READY置为false
    private Configurator(){
        YANBI_CONFIGS.put(ConfigKeys.CONFIG_READY.name(),false);
    }

    //配置完成，将CONFIG_READY置为true
    public final void configure(){
        initIcons();
        YANBI_CONFIGS.put(ConfigKeys.CONFIG_READY.name(),true);
    }

    //配置API_HOST
    public final Configurator withApiHost(String host){
        YANBI_CONFIGS.put(ConfigKeys.API_HOST,host);
        return this;
    }

    //初始化字体图标
    private void initIcons(){
        if (ICONS.size()>0){
            final Iconify.IconifyInitializer initializer=Iconify.with(ICONS.get(0));
            for (int i = 0; i < ICONS.size(); i++) {
                initializer.with(ICONS.get(i));
            }
        }
    }

    //配置自定义字体
    public final Configurator withIcon(IconFontDescriptor descriptor){
        ICONS.add(descriptor);
        return this;
    }

    public final Configurator withInterceptor(Interceptor interceptor){
        INTERCEPTORS.add(interceptor);
        YANBI_CONFIGS.put(ConfigKeys.INTERCEPTOR,INTERCEPTORS);
        return this;
    }

    public final Configurator withInterceptors(ArrayList<Interceptor> interceptors){
        INTERCEPTORS.addAll(interceptors);
        YANBI_CONFIGS.put(ConfigKeys.INTERCEPTOR,INTERCEPTORS);
        return this;
    }

    public final Configurator withWeChatAppId(String appId){
        YANBI_CONFIGS.put(ConfigKeys.WE_CHAT_APP_ID,appId);
        return this;
    }

    public final Configurator withWeChatAppSecret(String appSecret){
        YANBI_CONFIGS.put(ConfigKeys.WE_CHAT_APP_SECRET,appSecret);
        return this;
    }

    public final Configurator withActivity(Activity activity){
        YANBI_CONFIGS.put(ConfigKeys.ACTIVITY,activity);
        return this;
    }

    //检查配置是否完成
    private void checkConfiguration(){
        final boolean isReady= (boolean) YANBI_CONFIGS.get(ConfigKeys.CONFIG_READY.name());
        if (!isReady){
            //配置不完整,抛出异常
            throw new RuntimeException("Configuration is not ready,call configure");
        }
    }

    //单独获取某项配置
    @SuppressWarnings("unchecked")
    final <T> T getConfiguration(Object key) {
        checkConfiguration();
        final Object value = YANBI_CONFIGS.get(key);
        if (value == null) {
            throw new NullPointerException(key.toString() + " IS NULL");
        }
        return (T) YANBI_CONFIGS.get(key);
    }

}
