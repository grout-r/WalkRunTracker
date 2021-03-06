package com.example.roman.walkruntracker;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.Collections;

public class StatView extends View {

    public StatView(Context c)
    {
        super(c);
        init();
    }

    public StatView(Context c, AttributeSet as)
    {
        super(c, as);
        init();
    }

    public StatView(Context c, AttributeSet as, int default_style)
    {
        super(c, as, default_style);
        init();
    }

    private void init()
    {
        init = false;
        data = null;
        bars = new ArrayList<>();
        barColor = new Paint(Paint.ANTI_ALIAS_FLAG);
        barColor.setColor(0xa32f3a);
    }

    public void setValues(ArrayList<Integer> data)
    {
        this.data = data;
    }

    private void initBars(Canvas canvas)
    {
            if (data.size() != 0)
            {
                init = true;
                pixelSize = canvas.getWidth() - ((canvas.getWidth() / data.size()) / 4);
                int highestValue = Collections.max(data);
                int barWidth = (pixelSize / data.size()) - (((pixelSize / data.size()) / 4) );
                for (int i = 0; i != data.size(); i++)
                {
                    int left = ( (pixelSize / data.size() ) * i) + ((pixelSize / data.size()) / 4);
                    int top = pixelSize - ((int)((float)pixelSize * 0.90) / (highestValue / data.get(i)));
                    int right = left + barWidth;
                    int bottom = pixelSize;
                    bars.add(new Rect(left, top, right, bottom));
                }
            }
    }

    public void onDraw(Canvas canvas)
    {
        //super call
        super.onDraw(canvas);
        Paint blue = new Paint(Paint.ANTI_ALIAS_FLAG);
        blue.setColor(0xFF0000FF);
        if (!init && data != null)
        {
            initBars(canvas);
        }
        for (int i = 0; i != bars.size(); i++)
        {
            canvas.drawRect(bars.get(i), blue);
        }
    }
    public boolean onTouchEvent(MotionEvent event) {

        if(event.getActionMasked() == MotionEvent.ACTION_DOWN) {
            return true;
        } else if(event.getActionMasked() == MotionEvent.ACTION_UP) {
            return true;
        } else if(event.getActionMasked() == MotionEvent.ACTION_MOVE) {
            return true;
        } else if(event.getActionMasked() == MotionEvent.ACTION_POINTER_DOWN) {
            return true;
        } else if(event.getActionMasked() == MotionEvent.ACTION_POINTER_UP) {
            return true;
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }

    int                 pixelSize;
    boolean             init;
    ArrayList<Rect>     bars;
    ArrayList<Integer>  data;
    Paint barColor;
}
