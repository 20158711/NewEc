package sicau.xxgc.newfastec;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;

import sicau.xxgc.yanbi.activities.ProxyActivity;
import sicau.xxgc.yanbi.delegates.YanbiDelegate;
import sicau.xxgc.yanbi.launcher.LauncherDelegate;

public class ExampleActivity extends ProxyActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActionBar actionBar=getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }

    @Override
    public YanbiDelegate setRootDelegate() {
        return new LauncherDelegate();
    }
}
