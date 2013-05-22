package cn.edu.stu.cws.soul;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;

public class GridViewAdapter extends BaseAdapter {
	private Context context;
	private List<EssayType> types;
	private List<ImageButton> buttons;

	GridViewAdapter(Context c) {
		this.context = c;
		DBHelper helper = new DBHelper(this.context, "essay.db");
		buttons = new ArrayList<ImageButton>();
		types = helper.getEssayTypes();
		EssayType et = new EssayType();
		et.ID = -1;
		types.add(et); //essay collection
		for(int i = 0; i < types.size(); ++i) {
			final EssayType t = types.get(i);
			ImageButton imgButton = new ImageButton(this.context);
			imgButton.setImageResource(R.drawable.ic_launcher);
			imgButton.setOnClickListener(new OnClickListener(){
				public void onClick(View view) {
					Intent intent = new Intent(view.getContext(), CategoryActivity.class);
					intent.putExtra("catID", t.ID);
					intent.putExtra("catName", t.Name);
					view.getContext().startActivity(intent);
				}
			}
			);
			buttons.add(imgButton);
		}
	}

	@Override
	public int getCount() {
		return buttons.size();
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
		return buttons.get(pos);
	}

}
