package cn.edu.stu.cws.soul;

import java.util.List;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.EditText;
import android.widget.ImageView;

public class EssayActivity extends Activity {
	private String m_essay;
	private ImageView m_ImageView;
	private EditText m_text;
	private Bitmap m_bitmap;
	private String m_title;
	private String m_content;
	private String m_ImageFileName;
	
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
		m_essay="hello";
		m_text = (EditText)findViewById(R.id.editText1);
		m_text.setText(content);
		m_text.setMovementMethod(ScrollingMovementMethod.getInstance());
		//调用display（）方法显示文章
		//m_essay="hello";
		//display(m_bitmap,m_essay);
	}
	//使用单独的方法显示图片和文字,调用时要求传递位图Bitmap和文章String
//	public void display(Bitmap bitmap,String text){
//		//根据传进来的参数获取图片
//		m_ImageView = (ImageView)findViewById(R.id.imageView1);
//		m_ImageView.setImageBitmap(bitmap);
//		m_text = (EditText)findViewById(R.id.editText1);
//		m_text.setText(text);
//		m_text.setMovementMethod(ScrollingMovementMethod.getInstance());
//		//更新内容
//		//m_text.setSelection(text.getText().length(), text.getText().length());
//	}
}

