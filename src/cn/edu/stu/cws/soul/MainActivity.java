package cn.edu.stu.cws.soul;

import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_main);
		DBHelper helper = new DBHelper(this, "essay.db");
		List<EssayType> types = helper.getEssayTypes();
		TextView text = new TextView(this);
		for(int i = 0; i < types.size(); ++i) {
			text.append(types.get(i).Name);
			text.append("\n");
		}
		this.setContentView(text);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
