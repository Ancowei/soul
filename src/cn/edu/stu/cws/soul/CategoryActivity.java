package cn.edu.stu.cws.soul;

import android.os.Bundle;
import android.app.Activity;  
import android.widget.ListView;
import android.view.Menu;


public class CategoryActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_category);
		ListViewAdapter lvAdapter = new ListViewAdapter(this, this);
		ListView listView = (ListView) this.findViewById(R.id.listView);
		listView.setAdapter(lvAdapter);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.category, menu);
		return true;
	}

}
