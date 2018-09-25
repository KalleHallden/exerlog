package com.example.kalle.workoutdiary;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.Button;
import android.widget.LinearLayout;

public class LayoutAnimationActivity extends Activity {
        LinearLayout ril1;
        Button btn;
        int initialHeight;
        int actualHeight;

        /** Called when the activity is first created. */
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setUp();
        }

        public void setUp() {
            LinearLayout total = new LinearLayout(this);

            ril1= new LinearLayout(this);

            total.addView(ril1);
            actualHeight=210;
            Ani a=new Ani();
            a.setDuration(2000);
            ril1.startAnimation(a);

            setContentView(total);
        }

        class Ani extends Animation {
            public Ani() {
            }

            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                int newHeight;

                newHeight = (int)(initialHeight * interpolatedTime);

                ril1.removeAllViews();
                btn.setWidth(100);
                btn.setHeight(300);
                btn.setText("as");
                ril1.addView(btn);
                ril1.getLayoutParams().height = newHeight;
                ril1.requestLayout();
            }

            @Override
            public void initialize(int width, int height, int parentWidth, int parentHeight) {
                super.initialize(width, height, parentWidth, parentHeight);
                initialHeight = actualHeight;
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        }
    }