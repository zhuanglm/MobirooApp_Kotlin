package xgen.mobiroo.com.mobirooapp

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.ProgressBar
import android.widget.SimpleAdapter

import org.xml.sax.SAXException

import java.io.IOException
import java.util.ArrayList
import java.util.HashMap

import javax.xml.parsers.ParserConfigurationException

/**
 * Created by tc on 17/06/16.
 */
class CountryActivity : AppCompatActivity() {
    private lateinit var m_countries: ListView
    private lateinit var myData: ArrayList<HashMap<String, Any>>
    private val m_Countries_data: ArrayList<Country>? = null
    private lateinit var CountryListAdapter: SimpleAdapter
    private lateinit var m_waiting: ProgressBar
    private lateinit var map_cities_data: Map<String, String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country_list)

        m_waiting = findViewById(R.id.progressBar) as ProgressBar
        m_countries = findViewById(R.id.country_listView) as ListView
        myData = ArrayList()


        CountryListAdapter = SimpleAdapter(this, myData,
                android.R.layout.simple_list_item_1, arrayOf("name"),
                intArrayOf(android.R.id.text1))
        m_countries.adapter = CountryListAdapter

        m_countries.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            val mMap = CountryListAdapter.getItem(position) as Map<String, Any>
            val short_name = mMap["key"].toString()
            val full_name = mMap["name"].toString()
            val city_name = map_cities_data[short_name]
            //Log.i("Raymond click",short_name+" "+full_name);
            //Log.i("Raymond click",map_cities_data.get(short_name));

            val trans_data = Bundle()
            trans_data.putString("short", short_name)
            trans_data.putString("full", full_name)
            trans_data.putString("city", city_name)
            val intent = Intent(this@CountryActivity, DetailActivity::class.java)
            intent.putExtras(trans_data)
            startActivity(intent)
        }

        val req_country = CountryRequest()
        m_waiting.visibility = View.VISIBLE
        WebService.sendRequestAsync(req_country) { response ->
            try {
                val map_data = WebService.ParseMap(response.responseData)

                val iter = map_data.keys.iterator()

                while (iter.hasNext()) {
                    val key = iter.next().toString()
                    val hashMap = HashMap<String, Any>()
                    map_data[key]?.let { it -> hashMap.put("name", it) }
                    hashMap.put("key", key)
                    myData.add(hashMap)
                    /*Country newCountry = new Country();
                    newCountry.setShortName(key);
                    newCountry.setfullName(response.map_data.get(key));
                    m_Countries_data.add(newCountry);*/
                    //Log.i("Raymond get",key);

                }
                this@CountryActivity.runOnUiThread {
                    m_waiting.visibility = View.GONE
                    CountryListAdapter.notifyDataSetChanged()
                }
            } catch (e: ParserConfigurationException) {
                e.printStackTrace()
            } catch (e: SAXException) {
                e.printStackTrace()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }

        val req_capital = CapitalRequest()
        WebService.sendRequestAsync(req_capital) { response ->
            try {
                map_cities_data = WebService.ParseMap(response.responseData)
            } catch (e: ParserConfigurationException) {
                e.printStackTrace()
            } catch (e: SAXException) {
                e.printStackTrace()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }


    }


}
