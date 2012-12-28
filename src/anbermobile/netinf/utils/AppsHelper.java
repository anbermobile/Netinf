package anbermobile.netinf.utils;

import java.util.ArrayList;
import java.util.List;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.util.Log;

/**
 * 
 * Helper to query about device processes
 * 
 * @author anserran
 * 
 */
public class AppsHelper {

	private ActivityManager activityManager;

	private ConsumedDataProvider consumedDataProvider;

	private PackageManager packageManager;

	/**
	 * Apps running
	 */
	private List<AppInfo> apps;

	/**
	 * 
	 * @param context
	 *            Activity context
	 */
	public AppsHelper(Context context) {
		activityManager = (ActivityManager) context
				.getSystemService(Context.ACTIVITY_SERVICE);
		packageManager = context.getPackageManager();
		// FIXME use an actual consumed data provider
		consumedDataProvider = new ConsumedDataProvider() {

			@Override
			public int getDownloadedBytes(String processName) {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public int getUploadedBytes(String processName) {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public int getDownloadedPackages(String processName) {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public int getUploadedPackages(String processName) {
				// TODO Auto-generated method stub
				return 0;
			}

		};

		apps = new ArrayList<AppInfo>();
	}

	/**
	 * Returns a list with information about the processes running in the system
	 * 
	 * @return
	 */
	public List<AppInfo> getAppsInfo() {
		return apps;
	}

	/**
	 * Updates the apps information
	 */
	public void update() {
		apps.clear();
		for (RunningAppProcessInfo info : activityManager
				.getRunningAppProcesses()) {
			AppInfo appInfo = new AppInfo(info);
			try {
				appInfo.setLogo(packageManager
						.getApplicationIcon(info.processName));
			} catch (NameNotFoundException e) {
				Log.w("load", info.processName + " icon not found");
			}
			appInfo.setDownloadedBytes(this.consumedDataProvider
					.getDownloadedBytes(info.processName));
			appInfo.setUploadedBytes(this.consumedDataProvider
					.getUploadedBytes(info.processName));
			appInfo.setDownloadedPackages(this.consumedDataProvider
					.getDownloadedPackages(info.processName));
			appInfo.setUploadedPackages(this.consumedDataProvider
					.getDownloadedPackages(info.processName));
			apps.add(appInfo);
		}
	}
}
