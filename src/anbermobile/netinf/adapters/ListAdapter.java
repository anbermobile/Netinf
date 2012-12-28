package anbermobile.netinf.adapters;

import java.util.List;

import anbermobile.netinf.R;
import anbermobile.netinf.utils.AppInfo;
import anbermobile.netinf.utils.ByteConversor;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.TextView;

public class ListAdapter extends ArrayAdapter<AppInfo> {

	private final Context context;
	private final List<AppInfo> values;
	private OnCheckedChangeListener checkListener;
	private ByteConversor conversor;

	public ListAdapter(Context context, List<AppInfo> apps,
			OnCheckedChangeListener listener) {
		super(context, R.layout.rowlayout, apps);
		this.context = context;
		this.values = apps;
		checkListener = listener;
		conversor = new ByteConversor();

	}

	@TargetApi(Build.VERSION_CODES.GINGERBREAD)
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.rowlayout, parent, false);

		if (position == 0)
			rowView.setBackgroundResource(R.drawable.firstitemrowborder);
		else if ((position == values.size() - 1))
			rowView.setBackgroundResource(R.drawable.lastitemrowborder);

		ImageView logo = (ImageView) rowView.findViewById(R.id.logo);
		TextView name = (TextView) rowView.findViewById(R.id.appname);
		TextView upBytes = (TextView) rowView.findViewById(R.id.upbytes);
		TextView downBytes = (TextView) rowView.findViewById(R.id.downbytes);
		TextView nBytes = (TextView) rowView.findViewById(R.id.nbytes);
		CheckBox check = (CheckBox) rowView.findViewById(R.id.checkbox);
		check.setOnCheckedChangeListener(checkListener);

		AppInfo app = values.get(position);

		name.setText(app.getProcessName());
		upBytes.setText(conversor.bytesBinaryConvertion(app.getUploadedBytes()));
		downBytes.setText(conversor.bytesBinaryConvertion(app
				.getDownloadedBytes()));
		nBytes.setText(conversor.bytesBinaryConvertion(app.getUploadedBytes()
				+ app.getDownloadedBytes()));
		
		if (app.getLogo() == null) {
			logo.setImageResource(R.drawable.ic_launcher);
		} else {
			logo.setImageDrawable(app.getLogo());
		}

		return rowView;
	}
}
