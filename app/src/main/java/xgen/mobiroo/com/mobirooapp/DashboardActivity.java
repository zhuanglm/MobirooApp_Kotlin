package xgen.mobiroo.com.mobirooapp;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class DashboardActivity extends AppCompatActivity implements CountryDialogFragment.DialogReturn{

    private Button m_btnCountry;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initview();
    }

    public void initview(){
        m_btnCountry = (Button) findViewById(R.id.btnCountries);

        m_btnCountry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CountryDialogFragment Dialog = new CountryDialogFragment();
                Dialog.show(getFragmentManager(), "CountryDialog");
            }
        });
    }

    @Override
    public void onBtnClicked(boolean flag) {
        if(flag){
            Log.i("Raymond","true");
            Intent intent = new Intent();

            intent.setClass(DashboardActivity.this, CountryActivity.class);
            intent.putExtra("name", "raymond");
            startActivity(intent);

        }else{
            Log.i("Raymond","false");

        }
    }
}
