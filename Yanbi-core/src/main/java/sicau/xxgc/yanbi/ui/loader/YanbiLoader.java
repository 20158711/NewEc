package sicau.xxgc.yanbi.ui.loader;

import android.content.Context;
import android.support.v7.app.AppCompatDialog;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;

import sicau.xxgc.yanbi.R;
import sicau.xxgc.yanbi.util.DimenUtil;

/**
 * Created by yanbi on 2018/1/25.
 */

public class YanbiLoader {

    private static final int LOADER_SIZE_SCALE=8;
    private static final int LOADER_OFFSET_SCALE=10;
    private static final ArrayList<AppCompatDialog> LOADERS=new ArrayList<>();
    private static final String DEFAULT_LOADER=LoaderStyle.BallClipRotatePulseIndicator.name();
    public static void showLoading(Context context,Enum<LoaderStyle> type){
        showLoading(context,type.name());
    }

    public static void showLoading(Context context,String type){
        final AppCompatDialog dialog=new AppCompatDialog(context, R.style.dialog);
        final AVLoadingIndicatorView avLoadingIndicatorView=LoaderCreator.create(type,context);
        dialog.setContentView(avLoadingIndicatorView);

        int deviceWidth= DimenUtil.getScreenWidth();
        int deviceHeight=DimenUtil.getScreenHeight();

        final Window dialogWindow=dialog.getWindow();

        if (dialogWindow != null) {
            WindowManager.LayoutParams lp=dialogWindow.getAttributes();
            lp.width=deviceWidth/LOADER_SIZE_SCALE;
            lp.height=deviceHeight/LOADER_SIZE_SCALE+deviceHeight/LOADER_OFFSET_SCALE;
            lp.gravity= Gravity.CENTER;
        }
        LOADERS.add(dialog);
        dialog.show();
    }
    public static void showSoading(Context context){
        showLoading(context,DEFAULT_LOADER);
    }
    public static void stopLoading(){
        for (AppCompatDialog loader : LOADERS) {
            if (loader != null) {
                if (loader.isShowing()){
                    loader.cancel();
                }
            }
        }
    }
}
