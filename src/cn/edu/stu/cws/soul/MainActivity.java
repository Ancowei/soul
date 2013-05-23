package cn.edu.stu.cws.soul;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.GridView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);
		GridView gridView = (GridView) this.findViewById(R.id.gridViewMain);
		GridViewAdapter gvAdapter = new GridViewAdapter(this);
		gridView.setAdapter(gvAdapter);

		try {
			String[] imgFiles = this.getAssets().list("essay_img");
			for(int i = 0; i < imgFiles.length; ++i) {
				InputStream is = this.getAssets().open("essay_img/" + imgFiles[i]);
				FileOutputStream os =
						new FileOutputStream(this.getFileStreamPath(imgFiles[i]));
				byte[] buffer = new byte[8192];
				int count = 0;
				while ((count = is.read(buffer)) > 0) {
					os.write(buffer, 0, count);
					os.flush();
				}
				is.close();
				os.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		//FIXME: 下面的代码仅用于测试
/*
		DBHelper helper = new DBHelper(this, "essay.db");
		List<EssayType> types = helper.getEssayTypes();
		List<Essay> essayList;
		TextView text = new TextView(this);
		for(int i = 0; i < types.size(); ++i) {
			text.append(types.get(i).Name + ":\n");
			essayList = helper.getEssay(types.get(i));
			for(int j = 0; j < essayList.size(); ++j)
				text.append(essayList.get(j).Title
						+ " (" + essayList.get(j).Content + ")\n");
			text.append("\n");
		}

		//From Collection
		text.append("收藏：\n");
		essayList = helper.getEssayFromCollection();
		for(int j = 0; j < essayList.size(); ++j)
			text.append(essayList.get(j).Title
					+ " (" + essayList.get(j).Content + ")\n");
		this.setContentView(text);
*/
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
