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

public class welcome extends Activity {

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            //界面转载
            Intent intent = new Intent(welcome.this,MainActivity.class);
            startActivities(new Intent[]{intent});  //start跳转
            finish();//结束欢迎界面活动
        }
    };

    protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.welcome);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);
        ActionBar actionBar = getActionBar();     //取消标题头actionbar
        if (actionBar != null) {
            actionBar.hide();
        }
        //延迟发送信息2000Ms即2秒
        handler.sendMessageDelayed(Message.obtain(), 2000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }
}
