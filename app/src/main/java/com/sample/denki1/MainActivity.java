package com.sample.denki1;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.IntegerRes;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;


public class MainActivity extends Activity implements TextWatcher {
    EditText shohidenryoku, tanka, hour, day, min;
    //Button button1;
    TextView kekka;
    static protected SharedPreferences sharedPreferences;
    static protected SharedPreferences.Editor editor;
    static protected float key_tanka,key_hour,key_day,key_min,key_shohidenryoku;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        kekka = (TextView) findViewById(R.id.kekka);
        tanka = (EditText) findViewById(R.id.tanka);
        hour = (EditText) findViewById(R.id.hour);
        day = (EditText) findViewById(R.id.day);
        min = (EditText) findViewById(R.id.min);
        shohidenryoku = (EditText) findViewById(R.id.shohidenryoku);

        //button1 = (Button) findViewById(R.id.button1);
        //button1.setOnClickListener(this);

        tanka.addTextChangedListener(this);
        hour.addTextChangedListener(this);
        day.addTextChangedListener(this);
        min.addTextChangedListener(this);
        shohidenryoku.addTextChangedListener(this);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = sharedPreferences.edit();
        key_tanka = sharedPreferences.getFloat("key_tanka",19.43f );
        key_hour = sharedPreferences.getFloat("key_hour",24 );
        key_day = sharedPreferences.getFloat("key_day",30 );
        key_min = sharedPreferences.getFloat("key_min",0 );
        key_shohidenryoku = sharedPreferences.getFloat("key_shohidenryoku",100);

        if(key_tanka==(int)key_tanka){
            tanka.setText(String.format("%d", (int) key_tanka));
        }else{
            tanka.setText(String.format("%s",key_tanka));
        }
        if(key_hour==(int)key_hour){
            hour.setText(String.format("%d", (int) key_hour));
        }else{
            hour.setText(String.format("%s",key_hour));
        }
        if(key_day==(int)key_day){
            day.setText(String.format("%d", (int) key_day));
        }else{
            day.setText(String.format("%s",key_day));
        }

        if(key_min==(int)key_min){
            min.setText(String.format("%d", (int) key_min));
        }else{
            min.setText(String.format("%s",key_min));
        }
        if(key_shohidenryoku==(int)key_shohidenryoku){
            shohidenryoku.setText(String.format("%d", (int)key_shohidenryoku));
        }else{
            shohidenryoku.setText(String.format("%s",key_shohidenryoku));
        }
//aaaaaaaaaaaaaaaaaaaaaaaaaaa
        MobileAds.initialize(getApplicationContext(), "");
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        /*tanka.setOnEditorActionListener(editfinish);
        hour.setOnEditorActionListener(editfinish);
        day.setOnEditorActionListener(editfinish);
        min.setOnEditorActionListener(editfinish);
        shohidenryoku.setOnEditorActionListener(editfinish);
        */

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    @Override
    public void afterTextChanged(Editable s) {
        keisan();
    }

    //@Override
    //public void onClick(View v) {
    //    keisan();
    //}

    public void keisan() {
        try {
            SpannableStringBuilder sp1 = (SpannableStringBuilder) shohidenryoku.getText();
            float shohidenryoku = Float.parseFloat(sp1.toString());
            SpannableStringBuilder sp2 = (SpannableStringBuilder) tanka.getText();
            float tanka = Float.parseFloat(sp2.toString());
            SpannableStringBuilder sp3 = (SpannableStringBuilder) hour.getText();
            float hour = Float.parseFloat(sp3.toString());
            SpannableStringBuilder sp4 = (SpannableStringBuilder) day.getText();
            float day = Float.parseFloat(sp4.toString());
            SpannableStringBuilder sp5 = (SpannableStringBuilder) min.getText();
            float min = Float.parseFloat(sp5.toString());
            float min_fix = Float.parseFloat(sp5.toString()) / 60;
            kekka.setText(String.valueOf((int) (shohidenryoku * day * (hour + min_fix) * tanka / 1000)));

            editor.putFloat("key_tanka",tanka);
            editor.putFloat("key_hour",hour);
            editor.putFloat("key_day",day);
            editor.putFloat("key_min",min);
            editor.putFloat("key_shohidenryoku",shohidenryoku);
            editor.apply();

        } catch (Exception e) {
            kekka.setText("----");
        }
    }
    /*
    TextView.OnEditorActionListener editfinish = new TextView.OnEditorActionListener() {
        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                v.setFocusable(false);
                v.setFocusableInTouchMode(true);
                keisan();
            }
            return false;
        }
    };
    */


}