package anbermobile.netinf.adapters;

import anbermobile.netinf.R;
import anbermobile.netinf.net.NetSniffer;
import anbermobile.netinf.utils.ByteConversor;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.TextView;

public class ListAdapter extends ArrayAdapter<String>{
	
	private final Context context;
	private final String[] values;
	private PackageManager pmanager;
	private OnCheckedChangeListener checkListener;
	private ByteConversor conversor;
	

	public ListAdapter(Context context, String[] appNames, OnCheckedChangeListener listener) {
		super(context,R.layout.rowlayout,appNames);
		this.context=context;
		this.values=appNames;
		pmanager =  context.getPackageManager();
		checkListener =  listener;
		conversor = new ByteConversor();
		
	}
	
	@TargetApi(Build.VERSION_CODES.GINGERBREAD)
	@Override
	  public View getView(int position, View convertView, ViewGroup parent) {
	    LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    View rowView = inflater.inflate(R.layout.rowlayout, parent, false);
	    
	    if (position==0)
	    	rowView.setBackgroundResource(R.drawable.firstitemrowborder);
	    else if ((position==values.length-1))
	    	rowView.setBackgroundResource(R.drawable.lastitemrowborder);
	    
	    ImageView logo = (ImageView) rowView.findViewById(R.id.logo);
	    TextView name = (TextView) rowView.findViewById(R.id.appname);
	    TextView upBytes = (TextView) rowView.findViewById(R.id.upbytes);
	    TextView downBytes = (TextView) rowView.findViewById(R.id.downbytes);
	    TextView nBytes = (TextView) rowView.findViewById(R.id.nbytes);
	    CheckBox check = (CheckBox) rowView.findViewById(R.id.checkbox);
	    check.setOnCheckedChangeListener(checkListener);
	    
	    name.setText(values[position]);
	    try {
	    	
	    	long upbytes = NetSniffer.getUidTxBytes((pmanager.getApplicationInfo(values[position], PackageManager.GET_META_DATA).uid));
	    	long downbytes = NetSniffer.getUidRxBytes((pmanager.getApplicationInfo(values[position], PackageManager.GET_META_DATA).uid));
	    	
	    	upBytes.setText(conversor.bytesBinaryConvertion(upbytes));
	    	downBytes.setText(conversor.bytesBinaryConvertion(downbytes));
	    	
	    	nBytes.setText(conversor.bytesBinaryConvertion(upbytes+downbytes));
	    	
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	    
	    try {
			
			logo.setImageDrawable(pmanager.getApplicationIcon(values[position]));
			
		} catch (NameNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			logo.setImageResource(R.drawable.ic_launcher);
		}
	        

	    return rowView;
	  }


}
