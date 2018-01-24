package sicau.xxgc.newfastec;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import sicau.xxgc.yanbi.delegates.YanbiDelegate;

/**
 * Created by yanbi on 2018/1/24.
 */

public class ExampleDelegate extends YanbiDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_example;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
