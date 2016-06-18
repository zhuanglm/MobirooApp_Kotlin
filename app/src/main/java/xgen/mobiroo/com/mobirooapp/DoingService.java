package xgen.mobiroo.com.mobirooapp;

import android.app.IntentService;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

/**
 * Created by Raymond Zhuang on 2016-06-18.
 */
public class DoingService extends IntentService {
    public DoingService(){
        super("intentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        int sum=0;
        for(int i=1;i<=1000;i++){
            sum+=i;
        }
        Intent localIntent = new Intent(Constants.BROADCAST_ACTION);

        localIntent.putExtra(Constants.BROADCAST_DATA, Integer.toString(sum));

        LocalBroadcastManager.getInstance(this).sendBroadcast(localIntent);

        Log.i("Raymond send",Integer.toString(sum));
    }
}
