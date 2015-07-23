/**
 *
 */
package me.yumin.android.example.zxing;

import com.google.zxing.client.android.Intents;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

/**
 * @author yumin
 */
public class CaptureResultActivity extends Activity {

    /**
     *
     */
    private TextView tvResult;
    private TextView tvResultFormat;
    private TextView tvUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capture_result);
        initActivity();
        initData();
    }

    private void initActivity() {

        tvResult = (TextView) findViewById(R.id.tv_result);
        tvResultFormat = (TextView) findViewById(R.id.tv_result_format);
        tvUri = (TextView) findViewById(R.id.tv_uri);
    }

    private void initData() {

        Intent intent = getIntent();
        if (null != intent) {
        	String result = intent.getStringExtra(Intents.Scan.RESULT);
        	String resultFormat = intent.getStringExtra(Intents.Scan.RESULT_FORMAT);
        	String uri = intent.toUri(intent.getFlags());
        	
            tvResult.setText(result);
            tvResultFormat.setText(resultFormat);
            tvUri.setText(uri);
        }
    }
}
