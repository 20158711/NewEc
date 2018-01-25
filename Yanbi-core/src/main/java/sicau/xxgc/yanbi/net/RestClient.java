package sicau.xxgc.yanbi.net;

import android.content.Context;
import java.util.Map;
import java.util.WeakHashMap;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import sicau.xxgc.yanbi.net.callback.IError;
import sicau.xxgc.yanbi.net.callback.IFailure;
import sicau.xxgc.yanbi.net.callback.IRequest;
import sicau.xxgc.yanbi.net.callback.ISuccess;
import sicau.xxgc.yanbi.net.callback.RequestCallBacks;
import sicau.xxgc.yanbi.ui.LoaderStyle;
import sicau.xxgc.yanbi.ui.YanbiLoader;

/**
 * Created by yanbi on 2018/1/24.
 */

public class RestClient {

    private final String URL;
    private static final WeakHashMap<String, Object> PARAMS = RestCreator.getParams();
    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;
    private final RequestBody BODY;
    private final LoaderStyle LOADER_STYLE;
    private final Context CONTEXT;

    public RestClient(String url,
                      Map<String, Object> params,
                      IRequest request,
                      ISuccess success,
                      IFailure failure,
                      IError error,
                      RequestBody body,
                      Context context,
                      LoaderStyle loaderStyle) {
        this.URL = url;
        this.PARAMS.putAll(params);
        this.REQUEST = request;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
        this.BODY = body;
        this.LOADER_STYLE=loaderStyle;
        this.CONTEXT=context;
    }

    public static RestClientBuilder builder() {
        return new RestClientBuilder();
    }

    private void request(HttpMethod method) {
        final RestService service = RestCreator.getRestService();
        Call<String> call = null;
        if (REQUEST != null) {
            REQUEST.onRequestStart();
        }
        if (LOADER_STYLE != null) {
            YanbiLoader.showLoading(CONTEXT,LOADER_STYLE);
        }
        switch (method) {
            case GET:
                call = service.get(URL, PARAMS);
                break;
            case POST:
                call = service.post(URL, PARAMS);
                break;
            case PUT:
                call = service.put(URL, PARAMS);
                break;
            case DELETE:
                call = service.delete(URL, PARAMS);
                break;
            default:
                break;
        }
        if (call != null) {
            call.enqueue(getRequestCallBack());
        }
    }
    @SuppressWarnings("unchecked")
    private Callback<String> getRequestCallBack(){
        return new RequestCallBacks(
                REQUEST,
                SUCCESS,
                FAILURE,
                ERROR,
                LOADER_STYLE
        );
    }
    public final void get(){
        request(HttpMethod.GET);
    }
    public final void post(){
        request(HttpMethod.POST);
    }
    public final void put(){
        request(HttpMethod.PUT);
    }
    public final void delete(){
        request(HttpMethod.DELETE);
    }
}
