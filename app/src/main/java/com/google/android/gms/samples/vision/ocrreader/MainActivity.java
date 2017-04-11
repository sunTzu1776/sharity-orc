/*
 * Copyright (C) The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.android.gms.samples.vision.ocrreader;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.google.android.gms.common.api.CommonStatusCodes;

import net.steamcrafted.loadtoast.LoadToast;

import java.io.IOException;
import java.util.Calendar;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Main activity demonstrating how to pass extra parameters to an activity that
 * recognizes text.
 * Jerome Sim
 */

public class MainActivity extends Activity {



    // Use a compound button so either checkbox or switch widgets work.
    private CompoundButton autoFocus;
    private CompoundButton useFlash;
    private TextView statusMessage;
    private TextView textValue;
    private String text;
    private Button mserver;
    private Button orc;
    private Button ligue;
    private Button apf;
    private Button valentin;
    private Button green;
    private String asso;
    private TextView status;
    private Button manuel;
    private Button email;
    private String mail;

    private static final int RC_OCR_CAPTURE = 9003;
    private static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        statusMessage = (TextView)findViewById(R.id.status_message);
        textValue = (TextView)findViewById(R.id.text_value);

        autoFocus = (CompoundButton) findViewById(R.id.auto_focus);
        useFlash = (CompoundButton) findViewById(R.id.use_flash);
        mserver = (Button)findViewById(R.id.server);
        orc = (Button)findViewById(R.id.read_text);
        ligue = (Button)findViewById(R.id.ligue);
        apf = (Button)findViewById(R.id.apf);
        valentin = (Button)findViewById(R.id.valentin);
        green = (Button)findViewById(R.id.green);
        green = (Button)findViewById(R.id.green);
        manuel= (Button)findViewById(R.id.manuel);
        email= (Button)findViewById(R.id.email);

        mail="";

/*
        mserver.setVisibility(View.GONE);
        ligue.setVisibility(View.GONE);
        apf.setVisibility(View.GONE);
        valentin.setVisibility(View.GONE);
        green.setVisibility(View.GONE);

*/


        orc.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, OcrCaptureActivity.class);
                intent.putExtra(OcrCaptureActivity.AutoFocus, autoFocus.isChecked());
                intent.putExtra(OcrCaptureActivity.UseFlash, useFlash.isChecked());

                startActivityForResult(intent, RC_OCR_CAPTURE);

            }
        });
        mserver.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {

                if (asso==null){

                    final LoadToast lt2 = new LoadToast(MainActivity.this);
                    lt2.setTextColor(Color.GREEN).setBackgroundColor(Color.BLACK).setProgressColor(Color.WHITE);
                    String msg = "Sélectionner assosciation";
                    lt2.setText(msg);
                    lt2.show();
                    // statusMessage.setVisibility(View.GONE);
//
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            //write your code here to be executed after 1 second
                            lt2.error();

                        }
                    }, 2000);

                }else {
                    sendToServer();
                }

            }
        });

        ligue.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {

                asso = "ligue contre le cancer";
                Toast.makeText(MainActivity.this, ""+asso+"",
                        Toast.LENGTH_LONG).show();
            }
        });

        apf.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {

                asso = "apf";
                Toast.makeText(MainActivity.this, ""+asso+"",
                        Toast.LENGTH_LONG).show();
            }
        });

        valentin.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {

                asso = "valentin Haüy";
                Toast.makeText(MainActivity.this, ""+asso+"",
                        Toast.LENGTH_LONG).show();
            }
        });


        green.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {

                asso = "association green";
                Toast.makeText(MainActivity.this, ""+asso+"",
                        Toast.LENGTH_LONG).show();
            }
        });

        manuel.setOnClickListener(new Button.OnClickListener() {

            public void onClick(View v) {

                new MaterialDialog.Builder(MainActivity.this)
                        .title("Référence ticket")
                        .inputRangeRes(2, 40, android.R.color.holo_green_light)
                        .positiveText("Donner")
                        .input(null, null, new MaterialDialog.InputCallback() {
                            @Override
                            public void onInput(MaterialDialog dialog, CharSequence input) {
                                Calendar c = Calendar.getInstance();
                                int day = c.get(Calendar.DAY_OF_MONTH);
                                int month = c.get(Calendar.MONTH);
                                int year = c.get(Calendar.YEAR);
                                int heure = c.get(Calendar.HOUR);
                                int minute = c.get(Calendar.MINUTE);

                                text = ""+day+"."+month+"."+year+" "+heure+":"+minute+" "+input.toString();
                            /*    Toast.makeText(MainActivity.this, text,
                                        Toast.LENGTH_LONG).show();*/
                                if (asso==null){

                                    final LoadToast lt2 = new LoadToast(MainActivity.this);
                                    lt2.setTextColor(Color.GREEN).setBackgroundColor(Color.BLACK).setProgressColor(Color.WHITE);
                                    String msg = "Sélectionner assosciation";
                                    lt2.setText(msg);
                                    lt2.show();
                                   // statusMessage.setVisibility(View.GONE);
//
                                    Handler handler = new Handler();
                                    handler.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            //write your code here to be executed after 1 second
                                            lt2.error();

                                        }
                                    }, 2000);

                                }else {
                                    sendToServer();
                                }
                            }

                        }).show();
            }
        });

        email.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {

                new MaterialDialog.Builder(MainActivity.this)
                        .title("Email")
                        .inputRangeRes(2, 40, android.R.color.holo_green_light)
                        .positiveText("Ok")
                        .input(null, null, new MaterialDialog.InputCallback() {
                            @Override
                            public void onInput(MaterialDialog dialog, CharSequence input2) {

                                mail = input2.toString();
                            }

                        }).show();
            }
        });
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */


    /**
     * Called when an activity you launched exits, giving you the requestCode
     * you started it with, the resultCode it returned, and any additional
     * data from it.  The <var>resultCode</var> will be
     * {@link #RESULT_CANCELED} if the activity explicitly returned that,
     * didn't return any result, or crashed during its operation.
     * <p/>
     * <p>You will receive this call immediately before onResume() when your
     * activity is re-starting.
     * <p/>
     *
     * @param requestCode The integer request code originally supplied to
     *                    startActivityForResult(), allowing you to identify who this
     *                    result came from.
     * @param resultCode  The integer result code returned by the child activity
     *                    through its setResult().
     * @param data        An Intent, which can return result data to the caller
     *                    (various data can be attached to Intent "extras").
     * @see #startActivityForResult
     * @see #createPendingResult
     * @see #setResult(int)
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == RC_OCR_CAPTURE) {
            if (resultCode == CommonStatusCodes.SUCCESS) {
                if (data != null) {
                    text = data.getStringExtra(OcrCaptureActivity.TextBlockObject);
                    statusMessage.setText(R.string.ocr_success);
                    textValue.setText(text);
                    Log.d(TAG, "Text read: " + text);
/*
                    mserver.setVisibility(View.VISIBLE);
                    ligue.setVisibility(View.VISIBLE);
                    apf.setVisibility(View.VISIBLE);
                    valentin.setVisibility(View.VISIBLE);
                    green.setVisibility(View.VISIBLE);*/


                } else {
                    statusMessage.setText(R.string.ocr_failure);
                    Log.d(TAG, "No Text captured, intent data is null");
                }
            } else {
                statusMessage.setText(String.format(getString(R.string.ocr_error),
                        CommonStatusCodes.getStatusCodeString(resultCode)));
            }
        }
        else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    public void sendToServer(){



        OkHttpClient client = new OkHttpClient();

        RequestBody formBody = new FormBody.Builder()
                .add("asso",asso)
                .add("string", text)
                .add("mail", mail)
                .build();

        Request request = new Request.Builder()
                .url("https://sharity-ref-ticket.herokuapp.com/api/extract")
                .addHeader("content-type", "application/json; q=0.5")
                .post(formBody)
                .build();
//
       final LoadToast lt = new LoadToast(MainActivity.this);
        lt.setTextColor(Color.GREEN).setBackgroundColor(Color.BLACK).setProgressColor(Color.WHITE);
        String msg = "Don effectué à "+asso+"";
        lt.setText(msg);
        lt.show();
        statusMessage.setVisibility(View.GONE);
//
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //write your code here to be executed after 1 second
                lt.success();

            }
        }, 5000);

        client.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                Toast.makeText(MainActivity.this, "sauvegarde échouée!",
                        Toast.LENGTH_LONG).show();
                // Call this if it was successful
             //   lt.success();

            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                // ... check for failure using `isSuccessful` before proceeding
               // String responseData = response.body().string();
                // Read data on the worker thread
             //  System.out.print(responseData);
/*                Toast.makeText(MainActivity.this, "sauvegarde réussie!",
                        Toast.LENGTH_LONG).show();*/
// Or this method if it failed
           //     lt.error();

            }
        });
    }
}
