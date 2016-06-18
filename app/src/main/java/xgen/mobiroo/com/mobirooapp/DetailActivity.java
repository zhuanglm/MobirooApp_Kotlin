package xgen.mobiroo.com.mobirooapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {
    private Button m_btnStart;
    private TextView m_tvFullName,m_tvShortName,m_tvCityName,m_tvResult;
    DoingStateReceiver m_StateReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        m_btnStart = (Button)findViewById(R.id.startcaculate_btn);
        m_tvCityName = (TextView)findViewById(R.id.cityname_tv);
        m_tvFullName  = (TextView)findViewById(R.id.fullname_tv);
        m_tvShortName  = (TextView)findViewById(R.id.shortname_tv);
        m_tvResult  = (TextView)findViewById(R.id.result_caculated_tv);

        Bundle dataPackage = getIntent().getExtras();
        String city_name = dataPackage.getString("city");
        String full_name = dataPackage.getString("full");
        String short_name = dataPackage.getString("short");

        m_tvCityName.setText(city_name);
        m_tvFullName.setText(full_name);
        m_tvShortName.setText(short_name);

        m_btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ServiceIntent = new Intent(DetailActivity.this,DoingService.class);
                DetailActivity.this.startService(ServiceIntent);
            }
        });

        BroadcastRegister();

    }

    private void BroadcastRegister(){
        IntentFilter statusIntentFilter = new IntentFilter(Constants.BROADCAST_ACTION);
        // Sets the filter's category to DEFAULT
        statusIntentFilter.addCategory(Intent.CATEGORY_DEFAULT);
        m_StateReceiver = new DoingStateReceiver();
        // Registers the DownloadStateReceiver and its intent filters
        LocalBroadcastManager.getInstance(this).registerReceiver(m_StateReceiver, statusIntentFilter);

    }

    private class DoingStateReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String data = intent.getStringExtra(Constants.BROADCAST_DATA);
            m_tvResult.setText(data);
            Log.i("Raymond receive",data);
        }
    }
}
