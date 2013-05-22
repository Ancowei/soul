package cn.edu.stu.cws.soul;

import java.util.List;

import android.os.Bundle;
import android.app.Activity;  
import android.util.Log;
import android.view.View;  
import android.widget.ImageView;
import android.widget.LinearLayout;  
import android.widget.ScrollView;  
import android.widget.TextView;  
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Menu;
import android.view.View.OnClickListener;


public class CategoryActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_category);
		EssayType type = new EssayType();
		Intent intent = getIntent();
		type.ID = intent.getIntExtra("catID", 1);

		LinearLayout layout = (LinearLayout)this.findViewById(R.id.svLinearLayout);
		DBHelper helper = new DBHelper(this, "essay.db");
		List<Essay> essays;
		if(type.ID == -1) {
			essays = helper.getEssayFromCollection();
			this.setTitle("我的收藏");
		}
		else {
			essays = helper.getEssay(type);
			this.setTitle(intent.getStringExtra("catName"));
		}
		Log.d("Cat", String.valueOf(essays.size()));
		for(int i = 0; i < essays.size(); ++i) {
			Bitmap bitmap = null;
			final Essay e = essays.get(i);
			ImageView imgView = new ImageView(this);
			try{
				bitmap=BitmapFactory.decodeStream(this.openFileInput(e.ImageFileName));
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
			TextView textView = new TextView(this);
			textView.setText(e.Content);
			layout.addView(imgView);
			layout.addView(textView);
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.category, menu);
		return true;
	}

}
