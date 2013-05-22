package cn.edu.stu.cws.soul;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.EditText;
import android.widget.ImageView;

public class EssayActivity extends Activity {
	private EditText m_text;
	private Bitmap m_bitmap;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_essay);
		//获取图片和文章
		Intent intent = getIntent();
		String title = intent.getStringExtra("Title");
		String content = intent.getStringExtra("Content");
		String ImageFileName= intent.getStringExtra("ImageFileName");
		try{
			m_bitmap=BitmapFactory.decodeStream(this.openFileInput(ImageFileName));
		}catch(Exception e){
			e.printStackTrace();
			
		}
        //获取完毕
		m_text = (EditText)findViewById(R.id.editText1);
		m_text.setText(content);
		m_text.setMovementMethod(ScrollingMovementMethod.getInstance());
		//m_text.setSelection(text.getText().length(), text.getText().length());
	}

}

