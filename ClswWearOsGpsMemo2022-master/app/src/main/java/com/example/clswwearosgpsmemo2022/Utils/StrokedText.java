package com.example.clswwearosgpsmemo2022.Utils;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class StrokedText extends androidx.appcompat.widget.AppCompatTextView {
    public StrokedText(@NonNull Context context) {
        super(context);
    }

    public StrokedText(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public StrokedText(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /*
    By drawing 5 times instead of one, the class StrokedText can be used to write Stroked Text
    by adding a shadow. Drawing the shadow several time on top of itself will allow for a strong but
    thin black stroke, which is complex to achieve without this trick as there is no stroke
    property on TextView.
     */
    @Override
    public void draw(Canvas canvas) {
        for (int i = 0; i < 5; i++) {
            super.draw(canvas);
        }
    }
}
