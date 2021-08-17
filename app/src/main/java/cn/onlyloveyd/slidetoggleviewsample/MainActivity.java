package cn.onlyloveyd.slidetoggleviewsample;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.Locale;

import cn.onlyloveyd.slidetoggleview.SlideToggleViewTest;
import cn.onlyloveyd.slidetoggleviewsample.databinding.ActivityMainBinding;

/**
 * MainActivity
 *
 * @author onlyloveyd
 * @date 2019/1/10 09:02
 */
public class MainActivity extends AppCompatActivity {
    ActivityMainBinding mBinding;
    int oldLeft = 0;
    int width = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mBinding.slideToggleView.setSlideToggleListener(new SlideToggleViewTest.SlideToggleListener() {
            @Override
            public void onBlockPositionChanged(SlideToggleViewTest view, int left, int total,
                                               int slide) {
                String content = String.format(Locale.CHINESE, "left: %d - total: %d - slide: %d",
                        left, total, slide);
                if (mBinding.slideToggleView.getStv_type() == 1 && mBinding.slideToggleView.isStv_unlock_color_need()){
                    FrameLayout.LayoutParams layoutParams1 = (FrameLayout.LayoutParams) mBinding.slideToggleView.getmBlockBGTextView().getLayoutParams();
                    if (width == 0){
                        width = layoutParams1.width;
                    }
                    layoutParams1.width = width+left;
                    mBinding.slideToggleView.getmBlockBGTextView().setLayoutParams(layoutParams1);
                }
                mBinding.shimmerTextView.setText(content);
            }

            @Override
            public void onSlideOpen(SlideToggleViewTest view) {
                Toast.makeText(MainActivity.this, "Slide Toggle is Open",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}
