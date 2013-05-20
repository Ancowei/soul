package cn.edu.stu.cws.soul;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;

public class GridViewAdapter extends BaseAdapter {
	private Context context;
	private List<EssayType> types;

	GridViewAdapter(Context c) {
		this.context = c;
		DBHelper helper = new DBHelper(this.context, "essay.db");
		types = helper.getEssayTypes();
	}

	@Override
	public int getCount() {
		return types.size() + 1;
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int pos, View arg1, ViewGroup arg2) {
		ImageButton imgButton = new ImageButton(this.context);
		imgButton.setImageResource(R.drawable.ic_launcher);
		return imgButton;
	}

}
