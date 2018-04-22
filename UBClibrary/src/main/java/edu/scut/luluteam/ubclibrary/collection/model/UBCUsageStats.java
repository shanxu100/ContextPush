package edu.scut.luluteam.ubclibrary.collection.model;

import android.Manifest;
import android.app.AppOpsManager;
import android.app.usage.UsageEvents;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.util.Log;

import java.util.Calendar;
import java.util.List;

import edu.scut.luluteam.ubclibrary.utils.CheckPermissionUtil;

/**
 * @author Guan
 * @date Created on 2018/4/20
 */
public class UBCUsageStats {

    private static final String TAG = "UBCUsageStats";
    private UsageStatsManager usm;
    private Context context;

    private UsageStatsManager getUsageStatsManager(Context context) {
        UsageStatsManager usm = (UsageStatsManager) context.getSystemService(Context.USAGE_STATS_SERVICE);
        return usm;
    }

    public UBCUsageStats(Context context) {
        this.context = context;
        usm = getUsageStatsManager(context);
    }

    public void appStats() {
        //检查权限
        if (!CheckPermissionUtil.checkSystemAppPermission(context, AppOpsManager.OPSTR_GET_USAGE_STATS)) {
            return;
        }
        Calendar calendar = Calendar.getInstance();
        long endTime = calendar.getTimeInMillis();
        calendar.add(Calendar.YEAR, -1);
        long startTime = calendar.getTimeInMillis();

        Log.i(TAG, "Range start:" + startTime);
        Log.i(TAG, "Range end:" + endTime);

        List<UsageStats> usageStatsList = usm.queryUsageStats(
                UsageStatsManager.INTERVAL_DAILY,
                startTime,
                endTime);
        printUsageStats(usageStatsList);

// 或者
        UsageEvents uEvents = usm.queryEvents(startTime, endTime);
        Log.i(TAG, "print UsageEvent: ");

        while (uEvents.hasNextEvent()) {
            UsageEvents.Event e = new UsageEvents.Event();
            uEvents.getNextEvent(e);
            if (e != null) {
                Log.i(TAG, "Event: " + e.getPackageName() + "\t" + e.getTimeStamp());
            }
        }

    }

    private void printUsageStats(List<UsageStats> usageStatsList) {
        StringBuilder sb = new StringBuilder();
        sb.append("printUsageStats:\n");
        for (UsageStats u : usageStatsList) {
            sb.append("Pkg: " + u.getPackageName() + "\t" + "ForegroundTime: "
                    + u.getTotalTimeInForeground() + "\n");
        }
        Log.i(TAG, sb.toString());
    }

}
