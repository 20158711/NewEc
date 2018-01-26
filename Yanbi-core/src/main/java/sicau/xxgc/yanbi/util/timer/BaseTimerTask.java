package sicau.xxgc.yanbi.util.timer;

import java.util.TimerTask;

/**
 * Created by yanbi on 2018/1/26.
 */

public class BaseTimerTask extends TimerTask {

    private ITimerListener mITimerListener=null;

    public BaseTimerTask(ITimerListener timerListener){
        this.mITimerListener=timerListener;
    }

    @Override
    public void run() {
        if (mITimerListener != null) {
            mITimerListener.onTimer();
        }
    }
}
