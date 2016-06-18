package xgen.mobiroo.com.mobirooapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by tc on 17/06/16.
 */
public class CountryActivity extends AppCompatActivity {
    private ListView m_countries;
    private ArrayList<HashMap<String, Object>> myData;
    private ArrayList<Country> m_Countries_data = null;
    private SimpleAdapter CountryListAdapter;
    private ProgressBar m_waiting;
    private Map<String, String> map_cities_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_list);

        m_waiting = (ProgressBar)findViewById(R.id.progressBar);
        m_countries = (ListView)findViewById(R.id.country_listView);
        myData = new ArrayList<HashMap<String, Object>>();


        CountryListAdapter = new SimpleAdapter(this, myData,
                android.R.layout.simple_list_item_1, new String[]{"name"},
                new int[]{android.R.id.text1});
        m_countries.setAdapter(CountryListAdapter);

        m_countries.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Map<String, Object> mMap = (Map<String, Object>) CountryListAdapter.getItem(position);
                String short_name = mMap.get("key").toString();
                String full_name = mMap.get("name").toString();
                String city_name = map_cities_data.get(short_name);
                //Log.i("Raymond click",short_name+" "+full_name);
                //Log.i("Raymond click",map_cities_data.get(short_name));

                Bundle trans_data = new Bundle();
                trans_data.putString("short",short_name);
                trans_data.putString("full",full_name);
                trans_data.putString("city",city_name);
                Intent intent = new Intent(CountryActivity.this,DetailActivity.class);
                intent.putExtras(trans_data);
                startActivity(intent);

            }
        });

        CountryRequest req_country = new CountryRequest();
        m_waiting.setVisibility(View.VISIBLE);
        WebService.sendRequestAsync(req_country, new WebService.QueryCallback<CountryResponse>() {
            @Override
            public void response(CountryResponse response) {

                Iterator it = response.map_data.keySet().iterator();

                while (it.hasNext()) {
                    String key = it.next().toString();
                    HashMap<String, Object> hashMap = new HashMap<String, Object>();
                    hashMap.put("name",response.map_data.get(key));
                    hashMap.put("key",key);
                    myData.add(hashMap);
                    /*Country newCountry = new Country();
                    newCountry.setShortName(key);
                    newCountry.setfullName(response.map_data.get(key));
                    m_Countries_data.add(newCountry);*/
                    //Log.i("Raymond get",key);

                }
                CountryActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        m_waiting.setVisibility(View.GONE);
                        CountryListAdapter.notifyDataSetChanged();
                    }
                });
            }
        });

        CapitalRequest req_capital = new CapitalRequest();
        WebService.sendRequestAsync(req_capital, new WebService.QueryCallback<CapitalResponse>() {
            @Override
            public void response(CapitalResponse response) {
                map_cities_data = response.map_data;
            }
        });


    }


}
