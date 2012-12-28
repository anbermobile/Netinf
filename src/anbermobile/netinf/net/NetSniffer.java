package anbermobile.netinf.net;

import android.content.Context;
import android.net.TrafficStats;

public class NetSniffer extends TrafficStats{
	
	static Context context;

	public NetSniffer(Context c){
		super();
		context=c;				
	}
	

}
