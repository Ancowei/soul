package cn.edu.stu.cws.soul;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class EssayActivity extends Activity {
	private TextView m_text;
	private Bitmap m_bitmap;
	private ImageView m_ImageView;
	
	
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
		m_ImageView = (ImageView) this.findViewById(R.id.imageView);
		m_ImageView.setImageBitmap(m_bitmap);
		m_text = (TextView)findViewById(R.id.textView);
		m_text.setText(content);
		m_text.setMovementMethod(ScrollingMovementMethod.getInstance());
		this.setTitle(title);
	}

}

