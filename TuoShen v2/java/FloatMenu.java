package MaoMao.qihoo360.zhuangbiws;
import android.app.*;
import android.os.*;
import java.*;
import javax.*;
import java.net.*;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import android.app.*;
import android.os.*;
import java.net.*;
import java.text.*;
import java.util.ArrayList;
import java.util.List;
import java.*;
import java.io.*;
import org.apache.http.client.*;
import org.*;
import java.util.Date;
import java.util.Calendar;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import java.sql.*;
import android.view.View.OnClickListener;
import android.widget.*;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.storage.OnObbStateChangeListener;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.content.*;
import android.widget.Toast;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Notification;
import android.app.Notification.Builder;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.util.*;
import android.content.*;
import android.*;
import android.hardware.Camera;
import android.hardware.*;
import android.view.View;
import android.widget.Toast;
import android.content.pm.*;
import android.test.suitebuilder.annotation.*;
import java.security.*;
import android.text.style.*;
import android.net.*;
import android.view.*;

public class FloatMenu extends Activity
{
AlertDialog alertDialog1;
MainActivity ap = new MainActivity();
private final static int REQUEST_CODE = 100;
	FloatView a = new FloatView();
	MainActivity b = new MainActivity();
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		// TODO: Implement this method
		super.onCreate(savedInstanceState);
		setContentView(R.layout.xmenu);
		//a.hideFloatView();
		final String[] items = {"打开主界面","安装安全卫士","快打110", "返回桌面", "关于本软件", "退出装逼卫士"};
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
        alertBuilder.setTitle("悬浮窗菜单");
        alertBuilder.setItems(items, new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialogInterface, int i) {
					if(items[i] == "退出装逼卫士")
					{
						System.exit(0);
					}else if(items[i] == "返回桌面")
					{
						//TODO:对于返回的操作
						try
						{
							FloatMenu.this.finish();
							Intent home = new Intent(Intent.ACTION_MAIN);
							home.addCategory(Intent.CATEGORY_HOME);
							startActivity(home);
						}catch(Exception e){Toast.makeText(FloatMenu.this,e.toString(),Toast.LENGTH_LONG).show();}
					}else if(items[i] == "快打110")
					{
						Intent intent = new Intent(Intent.ACTION_DIAL);
						Uri data = Uri.parse("tel:110"); //自动拨打报警电话
					    Toast.makeText(FloatMenu.this,"请核对电话号码，按下拨号按钮报警!",Toast.LENGTH_LONG).show();
						intent.setData(data);
						FloatMenu.this.finish();
						startActivity(intent);
					}else if(items[i] == "关于本软件")
					{
						new AlertDialog.Builder(FloatMenu.this).setTitle("关于 360装逼卫士 椭神v2")//设置对话框标题
							.setMessage("360装逼卫士 For 椭神 Version 2 \n时时刻刻保障你的装逼安全\n有各种装逼功能\n若是装逼被打了可以一键报警哦\n在主界面按两下菜单键\n或者是在悬浮窗选择报警即可。\n© 360.com 奇虎360 京ICP备08010314号-6")
							.setCancelable(false)
							.setPositiveButton("确定", new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialogInterface, int i) {
									FloatMenu.this.finish();
									Intent home = new Intent(Intent.ACTION_MAIN);
									home.addCategory(Intent.CATEGORY_HOME);
									startActivity(home);
								}
							}).show();	
					}else if(items[i] == "打开主界面")
					{
						alertDialog1.dismiss();
						finish();
					}
					else if(items[i] == "安装安全卫士")
					{
						String str = "/sdcard/safe.apk";
						String fileName = str;
						Intent intent = new Intent(Intent.ACTION_VIEW);
						intent.setDataAndType(Uri.fromFile(new File(fileName)),"application/vnd.android.package-archive");
						startActivity(intent);
						FloatMenu.this.finish();
					}
					alertDialog1.dismiss();
				}
			});
			alertBuilder.setCancelable(false);
        alertDialog1 = alertBuilder.create();
        alertDialog1.show();
	}
	
	public static void openApk(Uri uri, Context context) {
		Intent intent = new Intent();
		intent.setAction(Intent.ACTION_VIEW);
		if (Build.VERSION.SDK_INT >= 24) {
			intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
			intent.setDataAndType(uri, "application/vnd.android.package-archive");
		} else {
			intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		}
		intent.setDataAndType(uri, "application/vnd.android.package-archive");
		context.startActivity(intent);
	}
	
}
