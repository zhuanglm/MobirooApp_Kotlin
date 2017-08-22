package xgen.mobiroo.com.mobirooapp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.support.v4.content.LocalBroadcastManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView

class DetailActivity : AppCompatActivity() {
    private lateinit var m_btnStart: Button
    private lateinit var m_tvFullName: TextView
    private lateinit var m_tvShortName: TextView
    private lateinit var m_tvCityName: TextView
    private lateinit var m_tvResult: TextView
    private lateinit var m_StateReceiver: DoingStateReceiver
    private lateinit var mLocalBroadcastManager: LocalBroadcastManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        m_btnStart = findViewById(R.id.startcaculate_btn) as Button
        m_tvCityName = findViewById(R.id.cityname_tv) as TextView
        m_tvFullName = findViewById(R.id.fullname_tv) as TextView
        m_tvShortName = findViewById(R.id.shortname_tv) as TextView
        m_tvResult = findViewById(R.id.result_caculated_tv) as TextView

        val dataPackage = intent.extras
        val city_name = dataPackage.getString("city")
        val full_name = dataPackage.getString("full")
        val short_name = dataPackage.getString("short")

        m_tvCityName.text = city_name
        m_tvFullName.text = full_name
        m_tvShortName.text = short_name

        m_btnStart.setOnClickListener {
            val ServiceIntent = Intent(this@DetailActivity, DoingService::class.java)
            this@DetailActivity.startService(ServiceIntent)
        }

        BroadcastRegister()

    }

    override fun onDestroy() {
        super.onDestroy()
        unRegisterLocalBroadcastReceiver()
    }

    private fun BroadcastRegister() {
        val statusIntentFilter = IntentFilter(Constants.BROADCAST_ACTION)
        // Sets the filter's category to DEFAULT
        statusIntentFilter.addCategory(Intent.CATEGORY_DEFAULT)
        m_StateReceiver = DoingStateReceiver()
        // Registers the DownloadStateReceiver and its intent filters
        mLocalBroadcastManager = LocalBroadcastManager.getInstance(this)
        mLocalBroadcastManager.registerReceiver(m_StateReceiver, statusIntentFilter)

    }

    private fun unRegisterLocalBroadcastReceiver() {
        if (mLocalBroadcastManager != null) {
            if (m_StateReceiver != null) {
                mLocalBroadcastManager.unregisterReceiver(m_StateReceiver)
            }
        }
    }

    private inner class DoingStateReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val data = intent.getStringExtra(Constants.BROADCAST_DATA)
            m_tvResult.text = data
            Log.i("Raymond receive", data)
        }
    }
}
