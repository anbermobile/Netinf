package anbermobile.netinf.utils;

import android.app.ActivityManager.RunningAppProcessInfo;
import android.graphics.drawable.Drawable;

/**
 * 
 * Class holding information about some process
 * 
 * @author anserran
 * 
 */
public class AppInfo {
	/**
	 * Process info
	 */
	private RunningAppProcessInfo processInfo;
	
	/**
	 * App logo
	 */
	private Drawable logo;
	
	/**
	 * Bytes downloaded
	 */
	private long downloadedBytes;
	
	/**
	 * Bytes uploaded
	 */
	private long uploadedBytes;

	/**
	 * Packages downloaded
	 */
	private long downloadedPackages;

	/**
	 * Packages uploaded
	 */
	private long uploadedPackages;

	public AppInfo(RunningAppProcessInfo processInfo) {
		this.processInfo = processInfo;
	}

	public String getProcessName() {
		return processInfo.processName;
	}

	public int getPid() {
		return processInfo.pid;
	}

	public Drawable getLogo() {
		return logo;
	}

	public void setLogo(Drawable logo) {
		this.logo = logo;
	}

	public RunningAppProcessInfo getProcessInfo() {
		return processInfo;
	}

	public long getDownloadedBytes() {
		return downloadedBytes;
	}

	public long getUploadedBytes() {
		return uploadedBytes;
	}

	public long getDownloadedPackages() {
		return downloadedPackages;
	}

	public long getUploadedPackages() {
		return uploadedPackages;
	}

	public void setProcessInfo(RunningAppProcessInfo processInfo) {
		this.processInfo = processInfo;
	}

	public void setDownloadedBytes(long downloadedBytes) {
		this.downloadedBytes = downloadedBytes;
	}

	public void setUploadedBytes(long uploadedBytes) {
		this.uploadedBytes = uploadedBytes;
	}

	public void setDownloadedPackages(long downloadedPackages) {
		this.downloadedPackages = downloadedPackages;
	}

	public void setUploadedPackages(long uploadedPackages) {
		this.uploadedPackages = uploadedPackages;
	}
	
	

}
