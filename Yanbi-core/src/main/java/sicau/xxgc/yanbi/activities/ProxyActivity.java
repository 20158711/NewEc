package sicau.xxgc.yanbi.activities;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.ContentFrameLayout;
import android.util.Log;
import me.yokeyword.fragmentation.SupportActivity;
import sicau.xxgc.yanbi.R;
import sicau.xxgc.yanbi.delegates.YanbiDelegate;

/**
 * Created by yanbi on 2018/1/24.
 */

public abstract class ProxyActivity extends SupportActivity{

    public abstract YanbiDelegate setRootDelegate();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initContainer(savedInstanceState);
    }

    private void initContainer(@Nullable Bundle savedInstanceState){
        final ContentFrameLayout container=new ContentFrameLayout(this);
        container.setId(R.id.delegate_container);
        setContentView(container);
        Log.d("###########", "initContainer: before");
        if (savedInstanceState == null) {
            Log.d("###########", "initContainer: ing");
            loadRootFragment(R.id.delegate_container,setRootDelegate());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.gc();
        System.runFinalization();
    }
}
