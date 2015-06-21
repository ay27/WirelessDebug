package com.ay27.WirelessDebug;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Button start_btn = (Button)findViewById(R.id.start_btn);

        final TextView ipView = (TextView)findViewById(R.id.ip_text);

        start_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpgradeSystemPermission.upgradeRootPermission();
                UpgradeSystemPermission.runCmd("stop adbd");
                UpgradeSystemPermission.runCmd("setprop service.adb.tcp.port 5555");
                UpgradeSystemPermission.runCmd("start adbd");
                Toast.makeText(MainActivity.this, R.string.open_ok, Toast.LENGTH_SHORT).show();
                ipView.setText(IP_Utilities.getLocalIpAddress(true).toString()+":5555");
            }
        });
        Button end_btn = (Button)findViewById(R.id.end_btn);
        end_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpgradeSystemPermission.upgradeRootPermission();
                UpgradeSystemPermission.runCmd("stop adbd");
                UpgradeSystemPermission.runCmd("setprop service.adb.tcp.port 0");
                UpgradeSystemPermission.runCmd("start adbd");
                Toast.makeText(MainActivity.this, R.string.close_ok, Toast.LENGTH_SHORT).show();
                ipView.setText("");
            }
        });

    }
}
