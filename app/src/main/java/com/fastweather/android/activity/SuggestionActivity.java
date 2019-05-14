package com.fastweather.android.activity;

import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fastweather.android.R;

public class SuggestionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggestion);
        Intent intent = getIntent();
        String name = intent.getStringExtra("suggestion_name");
        String suggestionName = getSuggestionCategory(name);
        String suggestionTitle = intent.getStringExtra("suggestion_title");
        String suggestionContent = intent.getStringExtra("suggestion_content");
        int suggestionImageId = intent.getIntExtra("suggestion_image_id", 0);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolBar);
        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        ImageView suggestion_image = (ImageView) findViewById(R.id.suggestionImage);
        TextView suggestion_title = (TextView) findViewById(R.id.suggestion_title);
        TextView suggestion_content = (TextView) findViewById(R.id.suggestion_content);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        collapsingToolbarLayout.setTitle(suggestionName);
        Glide.with(this).load(suggestionImageId).into(suggestion_image);
        suggestion_title.setText(suggestionTitle);
        suggestion_content.setText(suggestionContent);
    }

    public String getSuggestionCategory(String method){
        switch (method){
            case "comfort": return "舒适度";
            case "dy": return "钓鱼";
            case "wash_car": return "洗车";
            case "gj": return "逛街";
            case "cold": return "感冒";
            case "clothes": return "穿衣";
            case "uv": return "紫外线";
            case "pj": return "啤酒";
        }
        return null;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
