package sicau.xxgc.newfastec;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

import sicau.xxgc.yanbi.app.Yanbi;
import sicau.xxgc.yanbi.database.DatabaseManager;
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
        initStetho();
        DatabaseManager.getInstance().init(this);
    }

    private void initStetho(){
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                .build()
        );
    }
}
