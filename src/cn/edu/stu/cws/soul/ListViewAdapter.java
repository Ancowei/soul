package cn.edu.stu.cws.soul;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;

public class ListViewAdapter extends BaseAdapter {
	List<Essay> essays;
	List<LinearLayout> layouts;

	ListViewAdapter(Context c, Activity act) {
		Intent intent = act.getIntent();
		int typeID = intent.getIntExtra("catID", 1);

		DBHelper helper = new DBHelper(c, "essay.db");
		if(typeID == DBHelper.COLLECTION) {
			essays = helper.getEssayFromCollection();
			act.setTitle(DBHelper.COLLECTION_NAME);
		}
		else {
			essays = helper.getEssay(typeID);
			act.setTitle(intent.getStringExtra("catName"));
		}
		layouts = new ArrayList<LinearLayout>();
		for(int i = 0; i < essays.size(); ++i) {
			Bitmap bitmap = essays.get(i).Image;
			final Essay e = essays.get(i);
			ImageView imgView = new ImageView(act);

			imgView.setImageBitmap(bitmap);
			imgView.setLayoutParams(new LinearLayout.LayoutParams(
					0,
					LinearLayout.LayoutParams.MATCH_PARENT,
					1.0f));

			TextView textView = new TextView(act);
			textView.setLayoutParams(new LinearLayout.LayoutParams(
					0,
					LinearLayout.LayoutParams.MATCH_PARENT,
					2.0f));
			textView.setMaxLines(3);
			String content;
			content = "\n" + e.Content.replace("\n", "");
			SpannableString str = new SpannableString(e.Title + content);
			str.setSpan(new AbsoluteSizeSpan(18, true), 0, e.Title.length(),
					Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
			str.setSpan(new AbsoluteSizeSpan(15, true), e.Title.length(), str.length(),
					Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
			textView.setText(str);

			LinearLayout layout = new LinearLayout(c);
			layout.setOrientation(LinearLayout.HORIZONTAL);
			layout.addView(textView);
			layout.addView(imgView);
			layout.setLayoutParams(
					new ListView.LayoutParams(LayoutParams.MATCH_PARENT, Utils.dp2px(c, 64.0f)));
			layout.setOnClickListener(new OnClickListener(){
				public void onClick(View view) {
					Intent intent = new Intent(view.getContext(), EssayActivity.class);
					intent.putExtra("EssayID", e.ID);
					intent.putExtra("Title",e.Title);
					intent.putExtra("Content",	e.Content);
//					intent.putExtra("ImageFileName", e.ImageFileName);
					view.getContext().startActivity(intent);
				}
			}
			);
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
