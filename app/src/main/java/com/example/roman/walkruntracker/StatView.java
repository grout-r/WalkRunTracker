package com.example.roman.walkruntracker;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

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
    }


    public void onDraw(Canvas canvas)
    {
        //super call
        super.onDraw(canvas);
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


}
