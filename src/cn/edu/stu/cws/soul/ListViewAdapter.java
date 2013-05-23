package cn.edu.stu.cws.soul;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ListViewAdapter extends BaseAdapter {
	List<Essay> essays;
	List<LinearLayout> layouts;

	ListViewAdapter(Context c, Activity act) {
		EssayType type = new EssayType();
		Intent intent = act.getIntent();
		type.ID = intent.getIntExtra("catID", 1);

		DBHelper helper = new DBHelper(c, "essay.db");
		if(type.ID == -1) {
			essays = helper.getEssayFromCollection();
			act.setTitle("我的收藏");
		}
		else {
			essays = helper.getEssay(type);
			act.setTitle(intent.getStringExtra("catName"));
		}
		layouts = new ArrayList<LinearLayout>();
		for(int i = 0; i < essays.size(); ++i) {
			Bitmap bitmap = null;
			final Essay e = essays.get(i);
			ImageView imgView = new ImageView(act);
			try{
				bitmap=BitmapFactory.decodeStream(act.openFileInput(e.ImageFileName));
			}catch(Exception e1){
				e1.printStackTrace();
			}
			imgView.setImageBitmap(bitmap);
			imgView.setOnClickListener(new OnClickListener(){
				public void onClick(View view) {
					Intent intent = new Intent(view.getContext(), EssayActivity.class);
					intent.putExtra("Title",e.Title);
					intent.putExtra("Content",	e.Content);
					intent.putExtra("ImageFileName", e.ImageFileName);
					view.getContext().startActivity(intent);
				}
			}
			);
			imgView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, 128));
			TextView textView = new TextView(act);
			int pos = 25;
			if(e.Content.length() <= pos)
				textView.setText(e.Content);
			else
				textView.setText(e.Content.substring(0, pos) + "...");
			LinearLayout layout = new LinearLayout(c);
			layout.setOrientation(LinearLayout.VERTICAL);
			layout.addView(imgView);
			layout.addView(textView);
			layouts.add(layout);
		}
	}

	@Override
	public int getCount() {
		return layouts.size();
	}

	@Override
	public Object getItem(int arg0) {
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		return 0;
	}

	@Override
	public View getView(int i, View arg1, ViewGroup arg2) {
		return layouts.get(i);
	}

}
