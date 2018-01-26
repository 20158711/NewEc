package sicau.xxgc.newfastec;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import sicau.xxgc.yanbi.delegates.YanbiDelegate;
import sicau.xxgc.yanbi.net.RestClient;
import sicau.xxgc.yanbi.net.callback.IError;
import sicau.xxgc.yanbi.net.callback.IFailure;
import sicau.xxgc.yanbi.net.callback.ISuccess;

/**
 * Created by yanbi on 2018/1/24.
 */

public class ExampleDelegate extends YanbiDelegate {

    private static final String TAG="#############";
    @Override
    public Object setLayout() {
        return R.layout.delegate_example;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        testRestClient();
    }
    private void testRestClient(){
        RestClient.builder()
                .url("http://127.0.0.1/index")
                .loader(getContext())
                .success((String response)->{
                    Log.d(TAG, response);
                    Toast.makeText(_mActivity, response, Toast.LENGTH_SHORT).show();
                })
                .failure(()->{
                    Log.d(TAG, "testRestClient: failure");
                })
                .error((int code, String msg)-> {
                    Log.d(TAG, "testRestClient: error");
                })
                .build()
                .get();
    }
}
