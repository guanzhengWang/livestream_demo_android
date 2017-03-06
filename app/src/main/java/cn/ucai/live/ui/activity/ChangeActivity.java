package cn.ucai.live.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

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

public class ChangeActivity extends AppCompatActivity {

    @BindView(R.id.tv_change_balance)
    TextView tvChangeBalance;
    int money;
    @BindView(R.id.target_layout)
    LinearLayout targetLayout;
    View viewLoading;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change);
        ButterKnife.bind(this);
        viewLoading = LayoutInflater.from(ChangeActivity.this).inflate(R.layout.rp_loading, targetLayout,false);
        targetLayout.addView(viewLoading);
        initData();

    }

    private void initData() {
        NetDao.loadChange(this, EMClient.getInstance().getCurrentUser(), new OnCompleteListener<String>() {
            @Override
            public void onSuccess(String s) {
                if (s != null) {
                    Result result = ResultUtils.getResultFromJson(s, Wallet.class);
                    if (result != null && result.isRetMsg()) {
                        Wallet wallet = (Wallet) result.getRetData();
                        money = wallet.getBalance();
                        tvChangeBalance.setText("￥" + money);
                        PreferenceManager.getInstance().setChange(money);
                        viewLoading.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void onError(String error) {

            }
        });
    }

}
