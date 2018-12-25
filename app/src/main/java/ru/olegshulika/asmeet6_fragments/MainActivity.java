package ru.olegshulika.asmeet6_fragments;

import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import java.util.List;

public class MainActivity extends AppCompatActivity implements Fragment2_Button.OnF2ButtonPressedListener {
    private static final String TAG = "MainActivity";
    private LocalBroadcastManager localBroadcastManager;
    private LocalBroadcastReceiver localBroadcstReceiver;

    private class LocalBroadcastReceiver extends BroadcastReceiver {
        LocalBroadcastReceiver(){super();}
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            Log.d(TAG,"onReceive "+action);
            if(TextService.KEY_BROADCAST.equals(action)) {
                String sysTime = "time="+intent.getLongExtra(TextService.KEY_TIME, 0);
                Fragment1_EditText fragment1 = (Fragment1_EditText)getSupportFragmentManager().findFragmentById(R.id.fragment_1);
                fragment1.setEditTextValue(sysTime);
                Fragment2_Button fragment2 = (Fragment2_Button)getSupportFragmentManager().findFragmentById(R.id.fragment_2);
                fragment2.setTextValue(sysTime);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, " onCreate");
        setContentView(R.layout.activity_main);
        localBroadcastManager = LocalBroadcastManager.getInstance(this);
        localBroadcstReceiver = new LocalBroadcastReceiver();

    }

    @Override
    protected void onStart() {
        Log.d(TAG, " onStart");
        super.onStart();
        startService(TextService.newIntent(MainActivity.this, Command.START));
    }

    @Override
    protected void onResume() {
        Log.d(TAG, " onResume");
        super.onResume();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(TextService.KEY_BROADCAST);
        localBroadcastManager.registerReceiver(localBroadcstReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        Log.d(TAG, " onPause");
        super.onPause();
        localBroadcastManager.unregisterReceiver(localBroadcstReceiver);

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, " onStop");
        startService(TextService.newIntent(MainActivity.this, Command.STOP));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, " onDestroy");
    }

    @Override
    public void OnF2ButtonPressed() {
        Log.d(TAG, " OnF2ButtonPressed");

    }
}
