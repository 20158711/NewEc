package sicau.xxgc.newfastec;

import sicau.xxgc.yanbi.activities.ProxyActivity;
import sicau.xxgc.yanbi.delegates.YanbiDelegate;

public class ExampleActivity extends ProxyActivity {

    @Override
    public YanbiDelegate setRootDelegate() {
        return new ExampleDelegate();
    }
}
