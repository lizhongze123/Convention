package debug;


import com.gz0101.hzwy.baselibrary.app.ContextCacheHelper;
import com.gz0101.hzwy.baselibrary.base.BaseApplication;

public class MainApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        ContextCacheHelper.getInstance().setContext(this);
    }

}