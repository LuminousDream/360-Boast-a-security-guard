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
import android.media.MediaPlayer;
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
import android.graphics.*;

//360装逼卫士 让你更安全装逼
public class MainActivity extends Activity implements OnClickListener
{
	String 自动更新地址左边 = "https://github.com/LuminousDream/360-Boast-a-security-guard/releases/download/TuoshenV";
	String 自动更新地址右边 = "/360.apk";
	int 体检分数=0;
	int screenWidth;
	int screenHeight;
	int mr=0;
	public long mExitTime;
	AlertDialog alertDialog1;
	public Camera camera;
	int LD;
	boolean csb=false; //是否为测试版
	int 歌曲;
	boolean 闪光灯开关;
	NotificationManager manger = null;
	AlertDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
		//在特定情况下失效 比如说是OPPO和VIVO的ROM就不行
		PowerManager powerManager = (PowerManager) getSystemService(POWER_SERVICE);
		PowerManager.WakeLock wakeLock = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK,"MyWakelockTag");
		wakeLock.acquire();
		if(isAvilible(this,"MaoMao.qihoo360.zhuangbiws"))
		{
			
		}else
		{
			new AlertDialog.Builder(this).setTitle("装逼卫士提示你:")//设置对话框标题
				.setMessage("您下载的不是正版软件\n请下载正版，谢谢。")
				.setCancelable(false)
				.setPositiveButton("确定", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialogInterface, int i) {
						System.exit(0);
					}
				}).show();	
		}
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		//this.getWindow().setFlags(0x80000000, 0x80000000);//关键代码
		DisplayMetrics dm = getResources().getDisplayMetrics();
		screenWidth = dm.widthPixels;
		screenHeight = dm.heightPixels;
		Button eco =(Button) findViewById(R.id.start);
		RelativeLayout.LayoutParams ly2 =(RelativeLayout.LayoutParams)eco.getLayoutParams();
		ly2.setMargins(0,screenHeight-250,0,5);
		eco.setLayoutParams(ly2);
		TextView opt = (TextView) findViewById(R.id.info);
		File alg = new File(getFilesDir()+"/ata.bin");
		if(!alg.exists())
		{
			mr=0;
		}else
		{
			mr = Integer.parseInt(getCertainLineOfTxt(getFilesDir()+"/ata.bin",1));
			initMediaPlayer();
		}
		
		if(!alg.exists())
		{
			eco.setVisibility(View.INVISIBLE);
			Vibrator vibrator = (Vibrator)MainActivity.this.getSystemService(MainActivity.this.VIBRATOR_SERVICE);
			vibrator.vibrate(1000);
			Toast.makeText(this,"您是第一次使用装逼卫士\n将会为您初始化安全装逼环境。",Toast.LENGTH_LONG).show();
			showBPP();
		}else
		{
			if(mr == 1)
			{
				eco.setText("开启安全装逼模式");
				Toast.makeText(this,"欢迎使用360装逼卫士\n让你更安全的装逼!",Toast.LENGTH_SHORT).show();
			}else
			{
				eco.setVisibility(View.INVISIBLE);
				Vibrator vibrator = (Vibrator)MainActivity.this.getSystemService(MainActivity.this.VIBRATOR_SERVICE);
				vibrator.vibrate(1000);
				Toast.makeText(this,"您是第一次使用装逼卫士\n将会为您初始化安全装逼环境。",Toast.LENGTH_LONG).show();
				showBPP();
			}
		}
		String gp;
		if(mr == 0)
		{
			gp="未安装";
		}else
		{
			gp="已安装";
		}
		opt.setText("奇虎360装逼卫士 椭神v3版本 \n保障你的装逼安全\n多种帅气装逼功能\n装逼被打了在主界面按下菜单\n或者进入安全装逼模式后通过悬浮窗报警\n其他功能请期待 椭神v4版本\n另一个版本 2020.1.22\n天棒版本对游戏玩家开发\n在游戏里装逼将会更安全!\n安全装逼环境:"+gp);
		//opt.setText("奇虎360装逼卫士 椭神v3内测版\n内测版本有不稳定的因素\n如有问题请到Github搜索LuminousDream评论\n安全装逼环境:"+gp);
		eco.setOnClickListener(this);
    }
	
	public void onClick(View v)  //开始按钮事件
	{
		int id = v.getId();
		switch (id)  //判断按钮ID
		{
			case R.id.start: //本次
				{
					if(mr == 1)
					{
						//斗图入口
						Button eco =(Button) findViewById(R.id.start);
						//eco.setVisibility(View.INVISIBLE);
						Toast.makeText(this,"请开始你的表演\n360装逼卫士时刻保护着你的装逼安全。",Toast.LENGTH_SHORT).show();
						Intent home = new Intent(Intent.ACTION_MAIN);
						home.addCategory(Intent.CATEGORY_HOME);
						startActivity(home);
						mr=2;
						eco.setText("开启超级装逼模式");
						showFloatView();
					}else if (mr == 0)
					{
						
					}else if(mr == 2)
					{
						
						AlertDialog.Builder builder=new AlertDialog.Builder(this);
						builder.setIcon(android.R.drawable.ic_dialog_info);//提示图标
						builder.setTitle("装逼卫士温馨提示");
						builder.setMessage("超级装逼模式将会禁用悬浮窗\n因为对手机性能有要求，所以会占用部分内存，请加入加速白名单。\n即使锁屏也会生效，装逼卫士不会干扰你在特殊地点的装逼体验\n装逼卫士提醒你，是否开启?");
						builder.setPositiveButton("是",
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialogInterface, int i) {
									//超级装逼模式启动
									dialog.dismiss();
									TextView opt = (TextView) findViewById(R.id.info);
									//opt.setText("奇虎360装逼卫士 超级装逼模式\n注意，若被暴打，请立即在主界面按两下菜单键\n开始紧急呼叫110。\n自带闪光和装逼音乐\n让你成为装逼世界上的赢家!!!");
									opt.setText("奇虎360装逼卫士 超级装逼模式\n注意，若被暴打，请立即在主界面按下菜单\n开始紧急呼叫110。\n自带闪光和装逼音乐\n让你成为装逼世界上的赢家!!!");
									FloatView.hideFloatView();
									Button eco =(Button) findViewById(R.id.start);
									mr=3;
									eco.setText("退出装逼卫士");
									mediaPlayer.start();
									//mediaPlayer.
									//eco.setVisibility(View.INVISIBLE);
									Toast.makeText(MainActivity.this,"超级装逼模式开启，请走在大街上炫耀吧!!!",Toast.LENGTH_SHORT).show();
									Intent home = new Intent(Intent.ACTION_MAIN);
									home.addCategory(Intent.CATEGORY_HOME);
									startActivity(home);
									
									new Thread(new Runnable() {

											@Override
											public void run() {
												// TODO Auto-generated method stub
												while(true)
												{
													LD=(int)(Math.random()*100);
													if(闪光灯开关)
													{
														close();
														//savebrightness(MainActivity.this,LD);
														闪光灯开关=false;
													}else
													{
														open();
														Vibrator vibrator = (Vibrator)MainActivity.this.getSystemService(MainActivity.this.VIBRATOR_SERVICE);
														vibrator.vibrate(150);
														//savebrightness(MainActivity.this,LD);
														闪光灯开关=true;
													}

													try
													{
														Thread.sleep(10);
													}catch(Exception e){}

												}
												//For循环
											}
										}).start(); //分线程，防止程序卡死。
									//语句末
								}
							});
							
							
							
						builder.setNegativeButton("否",
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialogInterface, int i) {
									dialog.dismiss();
								}
							});
							
							
							
							
						dialog=builder.create();
						dialog.show();
					}else if (mr == 3)
					{
						super.onDestroy();
						if (mediaPlayer != null) {
							mediaPlayer.stop();
							mediaPlayer.release();
						}
						System.exit(0);
					}else if(mr == 5)
					{
						//斗图入口
						Button eco =(Button) findViewById(R.id.start);
						//eco.setVisibility(View.INVISIBLE);
						Toast.makeText(this,"请开始你的表演\n360装逼卫士时刻保护着你的装逼安全。",Toast.LENGTH_SHORT).show();
						Intent home = new Intent(Intent.ACTION_MAIN);
						home.addCategory(Intent.CATEGORY_HOME);
						startActivity(home);
						mr=2;
						eco.setText("开启超级装逼模式");
						showFloatView();
					}
				}
		}
	}
	
	private boolean isAvilible( Context context, String packageName ){
        final PackageManager packageManager = context.getPackageManager();
        // 获取所有已安装程序的包信息
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);
        for ( int i = 0; i < pinfo.size(); i++ )
        {
			// 循环判断是否存在指定包名
            if(pinfo.get(i).packageName.equalsIgnoreCase(packageName)){
				return true;
			}
        }
        return false;
	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.layout.menu, menu);
        return true;
    }
	

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.item_menu1:
                Toast.makeText(MainActivity.this, "请你清除数据后重启装逼卫士，谢谢。", Toast.LENGTH_SHORT).show();
				Intent intent = new Intent();
				intent.setAction(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
				intent.setData(Uri.parse("package:" + getPackageName()));
				startActivity(intent);
				//FloatMenu.this.finish();
                break;

            case R.id.item_menu2:
                Intent intent2 = new Intent();      
				//Intent intent = new Intent(Intent.ACTION_VIEW,uri);
				intent2.setAction("android.intent.action.VIEW");    
				Uri content_url = Uri.parse("https://github.com/LuminousDream/");   
				intent2.setData(content_url);  
				startActivity(intent2);
                break;

            case R.id.item_menu3:
                Intent intent3 = new Intent(Intent.ACTION_DIAL);
				Uri data = Uri.parse("tel:110"); //自动拨打报警电话
				Toast.makeText(this,"请核对电话号码，按下拨号按钮报警!",Toast.LENGTH_LONG).show();
				intent3.setData(data);
				//FloatMenu.this.finish();
				startActivity(intent3);
                break;

            case R.id.item_menu4:
                new AlertDialog.Builder(this).setTitle("关于 360装逼卫士 椭神v3")//设置对话框标题
					.setMessage("360装逼卫士 For 椭神 Version 3 \n时时刻刻保障你的装逼安全\n有各种装逼功能\n自带自动更新功能\n若是装逼被打了可以一键报警哦\n在主界面按下菜单\n或者是在悬浮窗选择报警即可。\n© 360.com 奇虎360 京ICP备08010314号-6")
					//.setMessage("360装逼卫士 For 椭神 Version 3 Alpha \n本软件为内测版本\n有不稳定的因素\n作者GitHub首页:https://github.com/LuminousDream\n© 360.com 奇虎360 京ICP备08010314号-6")
					.setCancelable(false)
					.setPositiveButton("确定", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialogInterface, int i) 
						{
							
						}
				    }).show();
                break;

            case R.id.item_menu5:
                System.exit(0);
				break;
        }
        return true;
    }
	
	
	private void open()
	{
		try {
			camera = Camera.open();
			camera.startPreview();
			Camera.Parameters parameters = camera.getParameters();
			parameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
			camera.setParameters(parameters);
		} catch (Exception e) {
			Toast.makeText(MainActivity.this,e.toString(),Toast.LENGTH_LONG).show();
		}
	}
	
	private void close()
	{
		try {
			Camera.Parameters parameters = camera.getParameters();
			parameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
			camera.setParameters(parameters);
			camera.release();
			camera = null;
		} catch (Exception e) {
			Toast.makeText(MainActivity.this,e.toString(),Toast.LENGTH_LONG).show();
		}
	}
	
	private void copyBigDataToSD(String strOutFileName,String ascfile) throws IOException 
    {  
        File a =new File(strOutFileName);
		if(!a.exists())
		{
			InputStream myInput;  
			OutputStream myOutput = new FileOutputStream(strOutFileName);  
			myInput = this.getResources().getAssets().open(ascfile);  
			byte[] buffer = new byte[1024];  
			int length = myInput.read(buffer);
			while(length > 0)
			{
				myOutput.write(buffer, 0, length); 
				length = myInput.read(buffer);
			}

			myOutput.flush();  
			myInput.close();  
			myOutput.close();        
		}else
		{
			a.delete();
			InputStream myInput;  
			OutputStream myOutput = new FileOutputStream(strOutFileName);  
			myInput = this.getResources().getAssets().open(ascfile);  
			byte[] buffer = new byte[1024];  
			int length = myInput.read(buffer);
			while(length > 0)
			{
				myOutput.write(buffer, 0, length); 
				length = myInput.read(buffer);
			}

			myOutput.flush();  
			myInput.close();  
			myOutput.close();        
		}
    }
	
	public boolean showBPP () {//回调方法：主线程执行
	    boolean jg=false;
        final ProgressDialog dialog = ProgressDialog.show(this, "360装逼卫士", "正在准备环境....");
        //模拟做一个长时间的工作
        //长时间的工作不能再主线程做，得启动分线程完成
        new Thread() {
            public void run() {//分线程
                for(int i=0;i<100;i++) {
                    //休息一会
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                //移除dialog
                dialog.dismiss();//方法在分线程执行，但内部使用Handler实现主线程移除dialog

                //不能在分线程直接更新UI
                //显示toast
				
                runOnUiThread(new Runnable() {

						@Override
						public void run() {//在主线程执行
							pd();
						}
					});
                //runOnUiThread()在分线程执行
                /*new Thread(new Runnable() {

				 @Override
				 public void run() {
				 // TODO Auto-generated method stub

				 }
				 }).start();*/
            };
        }.start();
		return(jg);
	}
	
	
	
	public void pd()
	{
		/*
		File  a = new File("/sdcard/装逼卫士");
		if(!a.exists() && !a.isDirectory())
		{
		   a.mkdir();
		}
		try
		{
			copyBigDataToSD("/sdcard/装逼卫士/music1.mp3","music1.mp3");
			copyBigDataToSD("/sdcard/装逼卫士/music2.mp3","music2.mp3");
			copyBigDataToSD("/sdcard/装逼卫士/music3.mp3","music3.mp3");
			copyBigDataToSD("/sdcard/装逼卫士/music4.mp3","music4.mp3");
			copyBigDataToSD("/sdcard/装逼卫士/music5.mp3","music5.mp3");
			copyBigDataToSD("/sdcard/装逼卫士/safe.apk","safe.apk");
		}catch(Exception e){}
		File alg = new File("/sdcard/装逼卫士/ata.bin");
		try
		{
			Thread.sleep(1500);
			alg.createNewFile();
			Writer mmm = new OutputStreamWriter(new FileOutputStream(alg,false), "UTF-8");
			mmm.write("1");
			mmm.flush();
			mmm.close();
		}catch(Exception e){}
		*/
		try
		{
			copyBigDataToSD(getFilesDir()+"/music1.mp3","music1.mp3");
			copyBigDataToSD(getFilesDir()+"/music2.mp3","music2.mp3");
			copyBigDataToSD(getFilesDir()+"/music3.mp3","music3.mp3");
			copyBigDataToSD(getFilesDir()+"/music4.mp3","music4.mp3");
			copyBigDataToSD(getFilesDir()+"/music5.mp3","music5.mp3");
			}catch(Exception e){}
		File alg = new File(getFilesDir()+"/ata.bin");
		try
		{
			Thread.sleep(1500);
			alg.createNewFile();
			Writer mmm = new OutputStreamWriter(new FileOutputStream(alg,false), "UTF-8");
			mmm.write("1");
			mmm.flush();
			mmm.close();
		}catch(Exception e){}
		new AlertDialog.Builder(this).setTitle("装逼卫士提示你:")//设置对话框标题
			.setMessage("初始化环境完毕，请重启装逼卫士。")
			.setCancelable(false)
			.setPositiveButton("确定", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialogInterface, int i) {
					System.exit(0);
				}
			}).show();	
	}
	
	public static String getCertainLineOfTxt(String filePath, int lineNumber){
		FileReader fr = null;
		LineNumberReader reader = null;
		String txt = "";

		try{
			File file = new File(filePath);
			fr = new FileReader(file);
			reader = new LineNumberReader(fr);

			int lines = 0;

			while(txt != null){
				lines ++;

				txt = reader.readLine(); // Read a line of text.

				if(lines == lineNumber){
					//System.out.println( "txt: " + txt + " lines = " + lines );
					return txt;
				}
			}

			return txt;
		}catch(Exception e){
			e.printStackTrace();

			return txt;
		}finally{
			try{
				reader.close();
			}catch(IOException e){
				e.printStackTrace();
			}

			try{
				fr.close();
			}catch(IOException e){
				e.printStackTrace();
			}
		}
	}
	
	
	private static void copyFileUsingFileStreams(File source, File dest)
	throws IOException {   
		InputStream input = null;   
		OutputStream output = null;   
		try {
			input = new FileInputStream(source);
			output = new FileOutputStream(dest);       
			byte[] buf = new byte[1024];       
			int bytesRead;       
			while ((bytesRead = input.read(buf)) > 0) {
				output.write(buf, 0, bytesRead);
			}
		} finally {
			input.close();
			output.close();
		}
	}

	public void showFloatView(View v) {
		showFloatView();
    }
	
		/**
		 * 显示悬浮窗
		 */
		public void showFloatView() {
			FloatView.showFloatView(getApplication(), R.layout.xfc);
			FloatView.setOnClickListener(new FloatView.OnClickListener() {
				
					@Override
					public void onClick(View view) {
						try
						{
							Intent intent = new Intent(MainActivity.this,FloatMenu.class);
							startActivity(intent);
						}catch(Exception e){Toast.makeText(MainActivity.this,e.toString(),Toast.LENGTH_LONG).show();}
					}
				
				});
		}
	
		/**
		 * 隐藏悬浮窗
		 * @param v
		 */
		public void hideFloatView(View v){
			FloatView.hideFloatView();
		}
		
	private MediaPlayer mediaPlayer = new MediaPlayer();
		
	private void initMediaPlayer() {
        try {
            歌曲 = (int)(Math.random()*6);
			歌曲 = (int)(Math.random()*6);
			if(歌曲 == 6)
			{
				歌曲=5;
			}else if(歌曲 == 0)
			{
				歌曲=1;
			}
            mediaPlayer.setDataSource(getFilesDir()+"/music"+String.valueOf(歌曲)+".mp3");
			mediaPlayer.setLooping(true);
			mediaPlayer.prepare();
        } catch (IOException e) {
            Toast.makeText(this,e.toString(),Toast.LENGTH_LONG).show();
        }
    }
	
	
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if ((System.currentTimeMillis() - mExitTime) > 2000) {
				Object mHelperUtils;
				Toast.makeText(this, "再按一次退出装逼卫士", Toast.LENGTH_SHORT).show();
				mExitTime = System.currentTimeMillis();

			} else {
				FloatView.hideFloatView();
				finish();
			}
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
		
	/**
	 * 保存亮度设置状态，退出app也能保持设置状态
	 */
	
	
}
