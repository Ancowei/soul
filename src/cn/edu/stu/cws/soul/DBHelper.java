package cn.edu.stu.cws.soul;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DBHelper {
	String dbName;
	Context context;

	public DBHelper(Context context, String name) {
		this.dbName = name;
		this.context = context;
		this.copyDatabase();
	}

	/* 获取分类列表 */
	public List<EssayType> getEssayTypes() {
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery("select ID, Name, MusicFileName "
				+ "from EssayType order by ID", null);
		List<EssayType> essayTypes = new ArrayList<EssayType>();
		EssayType t = null;
		while(cursor.moveToNext())
		{
			t = new EssayType();
			t.ID = cursor.getInt(0);
			t.Name = cursor.getString(1);
			t.MusicFileName = cursor.getString(2);
			essayTypes.add(t);
		}
		db.close();
		return essayTypes;
	}

	/* 获取某个分类的所有文章 */
	public List<Essay> getEssay(EssayType essayType) {
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery("select ID, Title, Content, ImageFileName "
				+ "from Essay where EssayTypeID = ?",
				new String[]{String.valueOf(essayType.ID)});
		List<Essay> essayList = new ArrayList<Essay>();
		while(cursor.moveToNext()) {
			Essay e = new Essay();
			e.ID = cursor.getInt(0);
			e.Title = cursor.getString(1);
			e.Content = cursor.getString(2);
			e.ImageFileName = cursor.getString(3);
			e.essayType = essayType;
			essayList.add(e);
		}
		return essayList;
	}

	public List<Essay> getEssayFromCollection() {
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery("select Essay.ID, Title, Content, ImageFileName "
				+ "from Collection, Essay where Collection.EssayID = Essay.ID "
				+ "order by Collection.ID", null);
		List<Essay> essayList = new ArrayList<Essay>();
		while(cursor.moveToNext()) {
			Essay e = new Essay();
			e.ID = cursor.getInt(0);
			e.Title = cursor.getString(1);
			e.Content = cursor.getString(2);
			e.ImageFileName = cursor.getString(3);
			e.essayType = null; //TODO: 获取文章类型
			essayList.add(e);
		}
		return essayList;
	}

	/* 判断指定的文章是否已经被收藏 */
	public boolean isInCollection(Essay e) {
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery("select * from Collection where EssayID = ?",
				new String[]{String.valueOf(e.ID)});
		return cursor.getCount() != 0;
	}

	/* 收藏指定的文章 */
	public void addToCollection(Essay e) {
		if(isInCollection(e))
			return;
		SQLiteDatabase db = this.getWritableDatabase();
		db.execSQL("insert into Collection(EssayID) values(?)", new Object[]{e.ID});
	}

	private SQLiteDatabase getWritableDatabase()
	{
		File dbFile = this.context.getDatabasePath(dbName);
		return SQLiteDatabase.openDatabase(dbFile.getAbsolutePath(), null,
				SQLiteDatabase.NO_LOCALIZED_COLLATORS | SQLiteDatabase.OPEN_READWRITE);
	}

	private void copyDatabase() {
		File dbFile = this.context.getDatabasePath(dbName);
		Log.d("DBHelper", dbFile.getAbsolutePath());
		Log.d("DBHelper", String.valueOf(dbFile.exists()));
		//数据库不存在则拷贝
		if(!dbFile.exists()){
			File dbDir = new File(dbFile.getParent());
			if (!dbDir.exists())// 判断数据库文件夹是否存在，不存在则新建
				dbDir.mkdir();

			FileOutputStream os = null;
			try {
				os = new FileOutputStream(dbFile.getAbsolutePath());
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

			InputStream is = null;
			try {
				is = context.getAssets().open(dbName);
			} catch (IOException e) {
				e.printStackTrace();
			}

			byte[] buffer = new byte[8192];
			int count = 0;
			try {
				while ((count = is.read(buffer)) > 0) {
					os.write(buffer, 0, count);
					os.flush();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

			try {
				is.close();
				os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
