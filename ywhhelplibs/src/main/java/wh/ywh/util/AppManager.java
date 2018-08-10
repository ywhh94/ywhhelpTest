package wh.ywh.util;

import android.app.Activity;
import java.util.Stack;

/**
 * Created by Administrator on 2018-08-02.
 */

public class AppManager {
    private AppManager instance;
    private Stack<Activity> activityStack;
    public AppManager getInstance(){
        if(instance == null){
            instance =  new AppManager();
        }
        return instance;
    }

    public void addActivity(Activity activity){
        if(activityStack==null){
            activityStack = new Stack<Activity>();
        }
        activityStack.add(activity);
    }
    public void finishActivity(Activity activity){
        if(activity!=null){
            activity.finish();
            activityStack.remove(activity);
            activity = null;
        }
    }
    public void finishAllActivity(){
        for(Activity activity:activityStack){
            finishActivity(activity);
        }
    }


}
