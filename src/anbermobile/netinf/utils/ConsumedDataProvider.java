package anbermobile.netinf.utils;

public interface ConsumedDataProvider {

	int getDownloadedBytes(String processName);
	
	int getUploadedBytes(String processName);
	
	int getDownloadedPackages(String processName);
	
	int getUploadedPackages(String processName);
	
}
