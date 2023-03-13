package com.example.encounterviewpager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private ItemAdapter adapter;
    private LinearLayout layoutIndicators;
    private MaterialButton fabButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        layoutIndicators = findViewById(R.id.indicator_layout);
        fabButton = findViewById(R.id.fabButton);
        setData();
    }

    private void setData(){
        List<PojoData> newList = new ArrayList<>();
        newList.add(new PojoData("Hi", R.drawable.ic_screen_image, true));
        newList.add(new PojoData("Hello", R.drawable.ic_reset_password_success_img, false));
        newList.add(new PojoData("How Are You", R.drawable.ic_notification_1, true));

        adapter = new ItemAdapter(newList);
        ViewPager2 viewPager2 = findViewById(R.id.view_pager);
        viewPager2.setUserInputEnabled(false);
        viewPager2.setAdapter(adapter);

        setupIndicator();
        setCurrentIndicator(0);
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                setCurrentIndicator(position);
            }
        });

        fabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (viewPager2.getCurrentItem() + 1 < adapter.getItemCount()){
                    viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1);
                }else {
                    Toast.makeText(getApplicationContext(), "Worked", Toast.LENGTH_SHORT).show();
                }
                Log.d(TAG, "onClick: " + viewPager2.getCurrentItem());

            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void setCurrentIndicator(int index) {
        int childCount = layoutIndicators.getChildCount();
        for (int i = 0; i < childCount; i++){
            ImageView imageView = (ImageView) layoutIndicators.getChildAt(i);
            if (i == index){
                imageView.setImageDrawable(
                        ContextCompat.getDrawable(
                                getApplicationContext(),
                                R.drawable.onboarding_indicator_active
                        ));
            }else {
                imageView.setImageDrawable(
                        ContextCompat.getDrawable(
                                getApplicationContext(),
                                R.drawable.onboarding_indicator_inactive
                        ));
            }
        }

        if (index == adapter.getItemCount() - 1){
            fabButton.setText("start");
        }else {
            fabButton.setText("next");
        }
    }

    private void setupIndicator() {
        ImageView[] indicators = new ImageView[adapter.getItemCount()];
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        );
        layoutParams.setMargins(8, 0, 8, 0);
        for (int i = 0; i < indicators.length; i++){
            indicators[i] = new ImageView(getApplicationContext());
            indicators[i].setImageDrawable(ContextCompat.getDrawable(
                    getApplicationContext(),
                    R.drawable.onboarding_indicator_inactive
            ));
            indicators[i].setLayoutParams(layoutParams);
            layoutIndicators.addView(indicators[i]);
        }
    }
}