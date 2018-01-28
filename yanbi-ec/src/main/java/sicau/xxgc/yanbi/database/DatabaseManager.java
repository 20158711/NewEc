package sicau.xxgc.yanbi.database;

import android.content.Context;

import org.greenrobot.greendao.database.Database;

/**
 * Created by yanbi on 2018/1/27.
 */

public class DatabaseManager {

    private DaoSession mDaoSession=null;
    private UserProfileDao mDao=null;

    private DatabaseManager(){}

    public DatabaseManager init(Context context){
        initDao(context);
        return this;
    }

    private static final class Holder{
        private static final DatabaseManager INSTANCE=new DatabaseManager();
    }

    public static DatabaseManager getInstance(){
        return Holder.INSTANCE;
    }

    private void initDao(Context context){
        final ReleaseOpenHelper helper=new ReleaseOpenHelper(context,"fast_ec.db");
        final Database db=helper.getWritableDb();
        mDaoSession=new DaoMaster(db).newSession();
        mDao=mDaoSession.getUserProfileDao();
    }

    public final UserProfileDao getDao(){
        return mDao;
    }

}
