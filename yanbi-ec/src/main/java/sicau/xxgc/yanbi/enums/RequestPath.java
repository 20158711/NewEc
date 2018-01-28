package sicau.xxgc.yanbi.enums;

/**
 * Created by yanbi on 2018/1/27.
 */

public enum  RequestPath {
    USER_PROFILE("http://192.168.1.95/RestDataServer/api/user_profile");
    private String url;

    public String getUrl() {
        return url;
    }

    RequestPath(String url) {
        this.url = url;
    }
}
