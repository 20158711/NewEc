package sicau.xxgc.newfastec;

import android.os.Bundle;
import android.support.annotation.Nullable;
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
                .url("http://www.baidu.com")
                .loader(getContext())
                .params("","")
                .success((String response)->{
//                    Toast.makeText(_mActivity, response, Toast.LENGTH_LONG).show();
                })
                .failure(()->{

                })
                .error((int code, String msg)-> {

                })
                .build()
                .get();
    }
}
