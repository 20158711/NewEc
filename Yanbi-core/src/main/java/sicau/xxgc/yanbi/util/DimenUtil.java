package sicau.xxgc.yanbi.util;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import sicau.xxgc.yanbi.app.Yanbi;

/**
 * Created by yanbi on 2018/1/25.
 */

public class DimenUtil {

    public static DisplayMetrics getDm(){
        final Resources resources= Yanbi.getApplicationContext().getResources();
        final DisplayMetrics dm=resources.getDisplayMetrics();
        return dm;
    }

    public static int getScreenWidth(){
        return getDm().widthPixels;
    }

    public static int getScreenHeight(){
        return getDm().heightPixels;
    }
}
