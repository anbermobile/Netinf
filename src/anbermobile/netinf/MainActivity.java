package anbermobile.netinf;

import java.util.List;

import anbermobile.netinf.adapters.ListAdapter;
import anbermobile.netinf.net.NetSniffer;
import anbermobile.netinf.utils.ByteConversor;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity implements OnCheckedChangeListener,
		OnClickListener {

	private int nchecked;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);

		new NetSniffer(this.getApplicationContext());

		String[] apps = getRunningAppNames(getApplicationContext());
		ListView list = (ListView) this.findViewById(R.id.list);
		ListAdapter listadapter = new ListAdapter(this.getApplicationContext(),
				apps, this);
		list.setAdapter(listadapter);

		nchecked = 0;
		TextView totalbytes = (TextView) this.findViewById(R.id.tbytes);
		ByteConversor conversor = new ByteConversor();
		totalbytes.setText("Total: "
				+ conversor.bytesBinaryConvertion(NetSniffer.getTotalRxBytes()
						+ NetSniffer.getTotalTxBytes()));

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		// idea: if anyone if checked a Button appears at the end, but not
		// working with the first item checked

		nchecked = isChecked ? nchecked + 1 : nchecked - 1;

		Button b = (Button) this.findViewById(R.id.footer);
		b.setText(getResources().getString(R.string.task_killer) + " "
				+ String.valueOf(nchecked));

		/*
		 * if (nchecked > 0 && (b.getVisibility()==Button.GONE))
		 * b.setVisibility(Button.VISIBLE); else if (nchecked == 0 &&
		 * (b.getVisibility()==Button.VISIBLE)) b.setVisibility(Button.GONE);
		 */

	}

	public static String[] getRunningAppNames(Context context) {
		// needs to be changed

		ActivityManager amanager = (ActivityManager) context
				.getSystemService(Context.ACTIVITY_SERVICE);

		List<RunningAppProcessInfo> runningProcess = amanager
				.getRunningAppProcesses();
		String[] packages = new String[runningProcess.size()];

		int i = 0;
		String[] pcksProcess;

		for (RunningAppProcessInfo processInfo : runningProcess) {

			pcksProcess = processInfo.pkgList;
			if (pcksProcess.length >= 1)
				packages[i] = pcksProcess[0];
			else
				packages[i] = "";

			/*
			 * for (int j=0;j<pcksProcess.length;j++){
			 * 
			 * }
			 */
			i++;

		}
		return packages;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		// kill selected apps. keep selected pkg names in a list and then go
		// through the list

	}

}
