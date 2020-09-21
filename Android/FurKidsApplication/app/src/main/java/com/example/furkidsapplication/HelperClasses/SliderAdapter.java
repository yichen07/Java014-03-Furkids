package com.example.furkidsapplication.HelperClasses;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.furkidsapplication.R;

import java.util.List;

public class SliderAdapter extends RecyclerView.Adapter<SliderAdapter.SliderViewHolder> {
    private List<SliderItem> sliderItems;
    public SliderAdapter(List<SliderItem> sliderItems){
        this.sliderItems = sliderItems;
    }

    @NonNull
    @Override
    public SliderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SliderViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.slides_layout,parent,false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull SliderViewHolder holder, int position) {
        holder.setSliderData(sliderItems.get(position));
    }

    @Override
    public int getItemCount() {
        return sliderItems.size();
    }

    class SliderViewHolder  extends RecyclerView.ViewHolder {
        private TextView textTitle;
        private TextView textDescription;
        private ImageView imageSlider;

         SliderViewHolder(@NonNull View itemView) {
            super(itemView);
            textTitle = itemView.findViewById(R.id.textTitle);
            textDescription = itemView.findViewById(R.id.textDescription);
            imageSlider = itemView.findViewById(R.id.imageSlider);
        }

        void setSliderData(SliderItem sliderItem){
            textTitle.setText(sliderItem.getTitle());
            textDescription.setText(sliderItem.getDescription());
            imageSlider.setImageResource(sliderItem.getImage());

        }
    }
//    Context context;
//    LayoutInflater layoutInflater;
//
//    public SliderAdapter(Context context) {
//        this.context = context;
//
//    }
//
//    int images[] = {
//            R.drawable.dogfriends,
//            R.drawable.cat_timelinexxxhdpi,
//            R.drawable.corgi3xxhdpi,
//            R.drawable.petshopxxxhdpi,
//    };
//    int headings[] = {
//            R.string.first_slide_title,
//            R.string.second_slide_title,
//            R.string.third_slide_title,
//            R.string.fourth_slide_title,
//    };
//    int descriptions[] = {
//            R.string.first_slide_desc,
//            R.string.second_slide_desc,
//            R.string.third_slide_desc,
//            R.string.fourth_slide_desc,
//    };
//
//
//    @Override
//    public int getCount() {
//        return headings.length;
//    }
//
//    @Override
//    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
//        return view == (ConstraintLayout) object;
//    }
//
//    @NonNull
//    @Override
//    public Object instantiateItem(@NonNull ViewGroup container, int position) {
//        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
//        View view = layoutInflater.inflate(R.layout.slides_layout,container,false);
//
//
//        //hooks
//        ImageView imageView = view.findViewById(R.id.slider_image);
//        TextView heading = view.findViewById(R.id.slider_heading);
//        TextView desc =view.findViewById(R.id.slider_desc);
//
//        imageView.setImageResource(images[position]);
//        heading.setText(headings[position]);
//        desc.setText(descriptions[position]);
//
//        container.addView(view);
//
//        return view;
//    }
//
//    @Override
//    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
//        container.removeView((ConstraintLayout)object);
//    }
}
