package debug;


import com.gz0101.hzwy.baselibrary.app.ContextCacheHelper;
import com.gz0101.hzwy.baselibrary.base.BaseApplication;
import com.gz0101.hzwy.city.picker.style.citylist.utils.CityListLoader;

public class MainApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        ContextCacheHelper.getInstance().setContext(this);
        CityListLoader.getInstance().loadCityData(this);
        CityListLoader.getInstance().loadProData(this);
    }
}