package cn.ucai.live.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.hyphenate.chat.EMClient;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.ucai.live.R;
import cn.ucai.live.data.NetDao;
import cn.ucai.live.data.model.Result;
import cn.ucai.live.data.model.Wallet;
import cn.ucai.live.utils.OnCompleteListener;
import cn.ucai.live.utils.PreferenceManager;
import cn.ucai.live.utils.ResultUtils;

public class ReChangeActivity extends AppCompatActivity {

    @BindView(R.id.et_rmb)
    EditText etRmb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_re_change);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        NetDao.Rechange(this, EMClient.getInstance().getCurrentUser(), etRmb.getText().toString(), new OnCompleteListener<String>() {
            @Override
            public void onSuccess(String s) {
                if(s!=null){
                    Result result = ResultUtils.getResultFromJson(s, Wallet.class);
                    if(result!=null&&result.isRetMsg()){
                        int money=PreferenceManager.getInstance().getChange();
                        PreferenceManager.getInstance().setChange(money);
                    }
                }
            }

            @Override
            public void onError(String error) {

            }
        });
    }
}
