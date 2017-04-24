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
            pixelSize = canvas.getWidth();
            if (data.size() != 0)
            {
                init = true;
                int higestValue = Collections.max(data);
                int barWidth = pixelSize / data.size();
                for (int i = 0; i != data.size(); i++)
                {
                    int left = barWidth * i + 70;
                    int top = barWidth / (higestValue / data.get(i));
                    int right = (barWidth * i) + barWidth;
                    int bottom = 50;
                    bars.add(new Rect(left, top, right, bottom));
                }
            }
    }

    public void onDraw(Canvas canvas)
    {
        //super call
        super.onDraw(canvas);
        if (!init && data != null)
        {
            initBars(canvas);
        }
        for (int i = 0; i != bars.size(); i++)
        {
            canvas.drawRect(bars.get(i), barColor);
        }
        canvas.drawRect(100, 100, 400, 400, barColor);
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
