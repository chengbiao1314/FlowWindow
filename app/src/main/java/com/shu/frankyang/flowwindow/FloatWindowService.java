package com.shu.frankyang.flowwindow;

import android.app.ActivityManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

/**
 * Created by frankyang on 15/4/24.
 */
public class FloatWindowService extends Service{

    private Timer mtimer;
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }



    @Override
    public int onStartCommand(Intent intent, int flags, int startID){

        if (mtimer == null){
            mtimer = new Timer();
            mtimer.scheduleAtFixedRate(new refreshTask(), 0,500);
        }
        return super.onStartCommand(intent,flags,startID);
    }


    class refreshTask extends TimerTask{

        @Override
        public void run() {

            //如果当前界面是桌面，悬浮框没有显示，显示悬浮框


            //如果当前界面不是桌面，悬浮框显示，悬浮框消失



            //如果当前是桌面，悬浮框显示，跟新内存数据

        }
    }


    /**
     * 得到属于桌面应用的应用程序包
     */

    private List<String> getHomes(){
        List<String> names = new ArrayList<String>();
        PackageManager pm = this.getPackageManager();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        List<ResolveInfo> resolveInfoList = pm.queryIntentActivities(intent,PackageManager.MATCH_DEFAULT_ONLY);
        for (ResolveInfo r : resolveInfoList){
            names.add(r.activityInfo.packageName);
        }
        return names;
    }


    /**
     * 判断当前是否在桌面
     */
    private Boolean isHome(){
        ActivityManager am = (ActivityManager)getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> rti = am.getRunningTasks(1);
        return getHomes().contains(rti.get(0).topActivity.getPackageName());

    }
}
