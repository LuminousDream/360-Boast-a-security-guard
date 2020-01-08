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
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.os.Build;
//import android.support.annotation.Dimension;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
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
import android.widget.ImageView;
import android.content.pm.*;
import android.test.suitebuilder.annotation.*;
import java.security.*;
import android.text.style.*;
import android.net.*;
import android.view.*;
import org.apache.http.cookie.*;

/**
 * Created by cool on 2016/8/30.
 */
public class FloatView extends Activity
{
    private static Context mContext;
    private static WindowManager mWindowManager;
    private static WindowManager.LayoutParams wmParams;
    private static View mView;
    private static boolean isShow = false;//悬浮框是否已经显示
	
    private static OnClickListener mListener;//view的点击回调listener
	
	
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.xfc);
	}
	
    public static void setOnClickListener(OnClickListener listener){
        mListener = listener;
    }

    interface OnClickListener{
        void onClick(View view);
    }

    /**
     * 显示悬浮框
     */
    public static void showFloatView(Context context,int layoutId){
        mContext = context;

        if(isShow){
            return;
        }

        mWindowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        wmParams = new WindowManager.LayoutParams();
        wmParams.type = WindowManager.LayoutParams.TYPE_PHONE;
        wmParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        wmParams.gravity = Gravity.CENTER;
        wmParams.format = PixelFormat.RGBA_8888;
        wmParams.x = context.getResources().getDisplayMetrics().widthPixels;
        wmParams.y = 0;

        wmParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        wmParams.height = WindowManager.LayoutParams.WRAP_CONTENT;

        mView = LayoutInflater.from(context).inflate(layoutId, null);
        mWindowManager.addView(mView, wmParams);


        mView.setOnTouchListener(new View.OnTouchListener() {
				float downX = 0;
				float downY = 0;
				int oddOffsetX = 0;
				int oddOffsetY = 0;
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					switch(event.getAction()){
						case MotionEvent.ACTION_DOWN:
							downX =  event.getX();
							downY =  event.getY();
							oddOffsetX = wmParams.x;
							oddOffsetY = wmParams.y;
							break;
						case MotionEvent.ACTION_MOVE:
							float moveX = event.getX();
							float moveY =  event.getY();
							//不除以3，拖动的view抖动的有点厉害
							wmParams.x += (moveX - downX)/3;
							wmParams.y += (moveY - downY)/3;
							if(mView != null){
								mWindowManager.updateViewLayout(mView,wmParams);
							}

							break;
						case MotionEvent.ACTION_UP:
							int newOffsetX = wmParams.x;
							int newOffsetY = wmParams.y;
							if(Math.abs(newOffsetX - oddOffsetX) <=20 && Math.abs(newOffsetY - oddOffsetY) <=20){
								if(mListener != null){
									mListener.onClick(mView);
								}
							}
							break;
					}
					return true;
				}

			});

        isShow = true;
    }

    /**
     * 隐藏悬浮窗
     */
    public static void hideFloatView(){
        if(mWindowManager != null && isShow){
            mWindowManager.removeView(mView);
            isShow = false;
        }
    }
}
