package cn.ucai.live.utils;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;

import com.hyphenate.chat.EMClient;
import com.hyphenate.easeui.domain.User;

import cn.ucai.live.I;
import cn.ucai.live.R;
import cn.ucai.live.ui.activity.ChangeActivity;
import cn.ucai.live.ui.activity.LoginActivity;
import cn.ucai.live.ui.activity.MainActivity;
import cn.ucai.live.ui.activity.ReChangeActivity;
import cn.ucai.live.ui.activity.RegisterActivity;


/**
 * Created by Administrator on 2017/1/10 0010.
 */

public class MFGT {
    public static void finish(Activity activity){
        activity.finish();
        activity.overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }
    public static void startActivity(Activity context, Class<?> cla){
        context.startActivity(new Intent(context,cla));
        context.overridePendingTransition(R.anim.push_right_in,R.anim.push_right_out);
    }
    public static void startActivity(Activity context, Intent intent){
        context.startActivity(intent);
        context.overridePendingTransition(R.anim.push_right_in,R.anim.push_right_out);
    }
    public static void startLoginActivity(Activity activity){
        startActivity(activity,LoginActivity.class);
    }
    public static void startRegisterActivity(Activity activity){
        startActivity(activity, RegisterActivity.class);
    }


    public static void gotoLoginActivityClear(Activity activity) {
        startActivity(activity,new Intent(activity,LoginActivity.class).
                setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK));
    }

 ;


    public static void gotoMain(Activity activity) {
        startActivity(activity, new Intent(activity,MainActivity.class).putExtra(I.BACK_MAIN_FROM_CHAT,true));
    }

    public static void gotoChangeActivity(Activity activity) {
        startActivity(activity, ChangeActivity.class);
    }

    public static void gotoReChangeActivity(Activity activity) {
        startActivity(activity, ReChangeActivity.class);
    }
}
