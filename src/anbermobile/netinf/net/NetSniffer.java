package anbermobile.netinf.net;

import android.annotation.TargetApi;
import android.content.Context;
import android.net.TrafficStats;
import android.os.Build;

public class NetSniffer {

	static Context context;

	public NetSniffer(Context c) {
		super();
		context = c;

	}

	public long getTotalBytes() {
		return TrafficStats.getTotalRxBytes() + TrafficStats.getTotalTxBytes();
	}

	public long getTotalReceivedBytes() {
		return TrafficStats.getTotalRxBytes();
	}

	public long getTotalSentBytes() {
		return TrafficStats.getTotalTxBytes();
	}

	public long getTotalPackets() {
		return TrafficStats.getTotalRxPackets()
				+ TrafficStats.getTotalTxPackets();
	}

	public long getTotalReceivedPackets() {
		return TrafficStats.getTotalRxPackets();
	}

	public long getTotalSentPackets() {
		return TrafficStats.getTotalTxPackets();
	}

	public long getMobileBytes() {
		return TrafficStats.getMobileRxBytes()
				+ TrafficStats.getMobileTxBytes();
	}

	public long getMobileReceivedBytes() {
		return TrafficStats.getMobileRxBytes();
	}

	public long getMobileSentBytes() {
		return TrafficStats.getMobileTxBytes();
	}

	public long getMobilePackets() {
		return TrafficStats.getMobileRxPackets()
				+ TrafficStats.getMobileTxPackets();
	}

	public long getMobileReceivedPackets() {
		return TrafficStats.getMobileRxPackets();
	}

	public long getMobileSentPackets() {
		return TrafficStats.getMobileTxPackets();
	}

	public long getWifiBytes() {
		return this.getWifiReceivedPackets() + this.getWifiSentPackets();
	}

	public long getWifiReceivedBytes() {
		return (TrafficStats.getTotalRxBytes() - TrafficStats
				.getMobileRxBytes());
	}

	public long getWifiSentBytes() {
		return (TrafficStats.getTotalTxBytes() - TrafficStats
				.getMobileTxBytes());
	}

	public long getWifiPackets() {
		return this.getWifiReceivedPackets() + this.getWifiSentPackets();
	}

	public long getWifiReceivedPackets() {
		return (TrafficStats.getTotalRxPackets() - TrafficStats
				.getMobileRxPackets());
	}

	public long getWifiSentPackets() {
		return (TrafficStats.getTotalTxPackets() - TrafficStats
				.getMobileTxPackets());
	}

	public long getUidReceivedBytes(int uid) {
		return TrafficStats.getUidRxBytes(uid);
	}

	public long getUidSentBytes(int uid) {
		return TrafficStats.getUidTxBytes(uid);
	}

	@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR1)
	public long getUidReceivedPackets(int uid) {
		return TrafficStats.getUidRxPackets(uid);
	}

	public long getUidSentPackets(int uid) {
		return TrafficStats.getUidTxBytes(uid);
	}

}
