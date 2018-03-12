package io.github.ziginsider.epam_laba_2;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.support.design.widget.Snackbar;

/**
 * @author Alex Kisel
 * @since 2018-03-09
 */
public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_PERMISSION = 1;
    private static final String PERMISSION_NAME = "io.github.ziginsider.module_2.PERMISSION";
    private static final String MODULE_TWO_ACTION = "io.github.ziginsider.module_2.ACTION";

    private View mainLayout;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainLayout = findViewById(R.id.main_layout);
        button = findViewById(R.id.button);
        initRequestPermissionButton();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_PERMISSION: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    launchModuleTwoActivity();
                } else {
                    Toast.makeText(this, R.string.permission_denied, Toast.LENGTH_SHORT).show();
                }
                break;
            }
            default: {
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
                break;
            }
        }
    }

    private void initRequestPermissionButton() {
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(MainActivity.this, PERMISSION_NAME)
                        != PackageManager.PERMISSION_GRANTED) {
                    requestLaunchActivityPermission();
                } else {
                    launchModuleTwoActivity();
                }
            }
        });
    }

    private void requestLaunchActivityPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, PERMISSION_NAME)) {
            Snackbar.make(mainLayout, R.string.permission_launch_rationale,
                    Snackbar.LENGTH_INDEFINITE)
                    .setAction(R.string.ok, new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            ActivityCompat.requestPermissions(MainActivity.this,
                                    new String[]{PERMISSION_NAME},
                                    REQUEST_PERMISSION);
                        }
                    })
                    .show();
        } else {
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{PERMISSION_NAME},
                    REQUEST_PERMISSION);
        }

    }

    private void launchModuleTwoActivity() {
        Intent intent = new Intent(MODULE_TWO_ACTION);
        startActivity(intent);
    }
}
