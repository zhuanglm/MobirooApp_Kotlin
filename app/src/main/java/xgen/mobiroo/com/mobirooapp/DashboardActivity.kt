package xgen.mobiroo.com.mobirooapp


import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.res.Configuration
import android.content.res.Resources
import android.support.v4.content.LocalBroadcastManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView

import java.util.Locale

class DashboardActivity : AppCompatActivity(), CountryDialogFragment.DialogReturn {

    private lateinit var m_btnCountry: Button
    private lateinit var m_btn1: Button
    private lateinit var m_btn2: Button
    private lateinit var m_btnTest: ChangeColorIconWithTextView
    private lateinit var m_tvDisplay: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intent = intent
        val lang = intent.getStringExtra("lang")
        if (lang != null) {
            Log.i("language", lang)
            getCurrentLanguage(lang)
        }

        initview()

    }

    override fun onDestroy() {
        super.onDestroy()

    }

    fun initview() {
        m_btnCountry = findViewById(R.id.btnCountries) as Button
        m_btn1 = findViewById(R.id.button1) as Button
        m_btn2 = findViewById(R.id.button2) as Button
        m_btnTest = findViewById(R.id.btn_test) as ChangeColorIconWithTextView
        m_tvDisplay = findViewById(R.id.hello_tv) as TextView

        m_btnCountry.setOnClickListener {
            val Dialog = CountryDialogFragment()
            Dialog.show(fragmentManager, "CountryDialog")
        }

        m_btn1.setOnClickListener { setCurrentLanguage("en", "CA") }

        m_btn2.setOnClickListener { setCurrentLanguage("zh", "CN") }

        m_btnTest.setOnClickListener { m_btnTest.setIconAlpha(1.0f) }
    }


    fun getCurrentLanguage(ll: String) {
        val locale: Locale

        if (ll.subSequence(0, 2) == "zh") {
            locale = Locale("zh", "CN")
            Log.i("Raymond Language:", "zh")
        } else {
            locale = Locale("en", "CA")
            Log.i("Raymond Language:", "en")
        }

        val resources = resources
        val config = resources.configuration
        config.locale = locale
        val ldm = resources.displayMetrics
        resources.updateConfiguration(config, ldm)
    }

    fun setCurrentLanguage(lang: String, country: String) {
        val locale = Locale(lang, country)
        val resources = resources
        val config = resources.configuration
        config.locale = locale
        val ldm = resources.displayMetrics
        resources.updateConfiguration(config, ldm)

        val language = config.locale.language


        val intent = this.intent
        overridePendingTransition(0, 0)
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
        intent.putExtra("lang", lang)

        finish()
        overridePendingTransition(0, 0)
        try {
            Thread.sleep(1000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        startActivity(intent)

    }

    override fun onBtnClicked(flag: Boolean) {
        if (flag) {
            Log.i("Raymond", "true")
            val intent = Intent()

            //intent.setClass(this@DashboardActivity, CountryActivity::class.java)
            intent.setClass(this@DashboardActivity,CountryActivity::class.java)
            intent.putExtra("name", "raymond")
            startActivity(intent)

        } else {
            Log.i("Raymond", "false")

        }
    }
}
