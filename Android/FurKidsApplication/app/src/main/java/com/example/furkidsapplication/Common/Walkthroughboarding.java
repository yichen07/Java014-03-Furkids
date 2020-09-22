package com.example.furkidsapplication.Common;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.example.furkidsapplication.HelperClasses.SliderAdapter;
import com.example.furkidsapplication.HelperClasses.SliderItem;
import com.example.furkidsapplication.R;

import java.util.ArrayList;
import java.util.List;

public class Walkthroughboarding extends AppCompatActivity {
    private SliderAdapter sliderAdapter;
    private LinearLayout dots;
    private AppCompatButton nextbtn;

//    ViewPager2 viewPager;
//    LinearLayout dots;
//    SliderAdapter sliderAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_walkthroughboarding);

        dots = findViewById(R.id.dots);
        nextbtn = findViewById(R.id.nextbtn);

        setSliderItems();

        final ViewPager2 sliderViewPager = findViewById(R.id.sliderViewPager);
        sliderViewPager.setAdapter(sliderAdapter);

        setupSliderIndicators();
        setCurrentSliderIndicator(0);

        sliderViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected ( int position){
                super.onPageSelected(position);
                setCurrentSliderIndicator(position);
            }
        });
        nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick ( View view){
                if(sliderViewPager.getCurrentItem() + 1 < sliderAdapter.getItemCount()){
                   sliderViewPager.setCurrentItem(sliderViewPager.getCurrentItem() + 1);
                }else{
                    startActivity(new Intent(getApplication(),HomeActivity.class));
                    finish();
                }
            }
        });
//        第一種方法//hooks
//        viewPager = findViewById(R.id.slider);
//        dots = findViewById(R.id.dots);
//
//         第一種方法//call adapter
//        sliderAdapter = new SliderAdapter(this);
//        viewPager.setAdapter(sliderAdapter);
        }
        private void setSliderItems(){
            List<SliderItem> sliderItems = new ArrayList<>();

            SliderItem itemPetOnline = new SliderItem();
            itemPetOnline.setTitle("寵物交友");
            itemPetOnline.setDescription("以寶貝動物為主的交友功能");
            itemPetOnline.setImage(R.drawable.dogfriends);

            SliderItem itemNurOnline = new SliderItem();
            itemPetOnline.setTitle("寵物營養管理");
            itemPetOnline.setDescription("提供寵物與飼主最好的照顧平台管理功能");
            itemPetOnline.setImage(R.drawable.cat_timelinexxxhdpi);

            SliderItem itemTalkOnline = new SliderItem();
            itemPetOnline.setTitle("交流諮詢");
            itemPetOnline.setDescription("所有寵物寶貝飼主的資訊交流平台");
            itemPetOnline.setImage(R.drawable.dogxxxhdpi);

            SliderItem itemShopline = new SliderItem();
            itemPetOnline.setTitle("購物平台");
            itemPetOnline.setDescription("讓飼主省去大量時間的寵物商品購物交流");
            itemPetOnline.setImage(R.drawable.petshopxxxhdpi);

            sliderItems.add(itemPetOnline);
            sliderItems.add(itemNurOnline);
            sliderItems.add(itemTalkOnline);
            sliderItems.add(itemShopline);

            sliderAdapter = new SliderAdapter(sliderItems);
        }

        private void setupSliderIndicators(){
            ImageView[]indicators;
            indicators = new ImageView[sliderAdapter.getItemCount()];
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT
            );
            layoutParams.setMargins(8,0,0,0);
            for (int i =0;i <indicators.length;i++){
                indicators[i] = new ImageView(getApplicationContext());
                indicators[i].setImageDrawable(ContextCompat.getDrawable(
                        getApplicationContext(),
                        R.drawable.walkthroughboarding_indicator_active
                ));
                indicators[i].setLayoutParams(layoutParams);
                dots.addView(indicators[i]);
            }
        }
        private void setCurrentSliderIndicator(int index){
            int childCount = dots.getChildCount();
            for(int i = 0;i < childCount;i++){
                ImageView imageView = (ImageView)dots.getChildAt(i);
                if(i == index){
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(getApplicationContext(),
                            R.drawable.walkthroughboarding_indicator_active)
                );
            } else {
                    imageView.setImageDrawable(
                            ContextCompat.getDrawable(getApplicationContext(),
                                    R.drawable.walkthroughboarding_indicator_inactive)
                    );
                }

            if(index == sliderAdapter.getItemCount() - 1){
                nextbtn.setText("開始");
            } else {
                nextbtn.setText("跳頁");

            }
            }
        }


}