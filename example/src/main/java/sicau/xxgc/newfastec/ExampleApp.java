package sicau.xxgc.newfastec;

import android.app.Application;

import com.joanzapata.iconify.fonts.FontAwesomeModule;

import sicau.xxgc.yanbi.app.Yanbi;
import sicau.xxgc.yanbi.ec.icon.FontEcModule;
import sicau.xxgc.yanbi.net.interceptors.DebugInterceptor;

/**
 * Created by yanbi on 2018/1/23.
 */

public class ExampleApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化配置
        Yanbi.init(this)
                .withIcon(new FontAwesomeModule())
                .withIcon(new FontEcModule())
                .withApiHost("http://127.0.0.1")
                .withInterceptor(new DebugInterceptor("index",R.raw.test))
                .configure();
    }
}
