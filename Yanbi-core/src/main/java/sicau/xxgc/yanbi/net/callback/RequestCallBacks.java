package sicau.xxgc.yanbi.net.callback;

import android.os.Handler;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sicau.xxgc.yanbi.ui.LoaderStyle;
import sicau.xxgc.yanbi.ui.YanbiLoader;

/**
 * Created by yanbi on 2018/1/25.
 */

public class RequestCallBacks implements Callback{
    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;
    private final LoaderStyle LOADER_STYLE;
    private static final Handler HANDLER=new Handler();

    public RequestCallBacks(IRequest request, ISuccess success, IFailure failure, IError error,LoaderStyle style) {
        this.REQUEST = request;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
        this.LOADER_STYLE=style;
    }

    @Override
    public void onResponse(Call call, Response response) {
        if (response.isSuccessful()){
            if (call.isExecuted()){
                if (SUCCESS != null) {
                    SUCCESS.onSuccess((String) response.body());
                }
             }
        }else {
            if (ERROR != null) {
                ERROR.onError(response.code(),response.message());
            }
        }
        stopLoading();
    }

    @Override
    public void onFailure(Call call, Throwable t) {
        if (FAILURE != null) {
            FAILURE.onFailure();
        }
        if (REQUEST != null) {
            REQUEST.onRequestEnd();
        }
        stopLoading();
    }
    private void stopLoading(){
        if (LOADER_STYLE != null) {
            HANDLER.postDelayed(()->{
                YanbiLoader.stopLoading();
            },1000);
        }
    }
}
