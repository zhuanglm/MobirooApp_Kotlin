package xgen.mobiroo.com.mobirooapp

import android.app.IntentService
import android.content.Intent
import android.support.v4.content.LocalBroadcastManager
import android.util.Log

/**
 * Created by Raymond Zhuang on 2016-06-18.
 */
class DoingService : IntentService("intentService") {

    override fun onHandleIntent(intent: Intent?) {
        val sum = (1 .. 1000).sum()

        val localIntent = Intent(Constants.BROADCAST_ACTION)

        localIntent.putExtra(Constants.BROADCAST_DATA, "sum = "+Integer.toString(sum))

        LocalBroadcastManager.getInstance(this).sendBroadcast(localIntent)

        Log.i("Raymond send", Integer.toString(sum))
    }
}
