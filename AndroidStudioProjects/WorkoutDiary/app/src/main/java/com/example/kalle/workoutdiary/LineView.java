package com.example.kalle.workoutdiary;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class LineView extends View {

    private Paint paint = new Paint();
    private PointF pointA, pointB;




    public LineView(Context context) {
        super(context);
    }

    public LineView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public LineView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        paint.setColor(BottomNaviClass.green);
        paint.setStrokeWidth(7);
        canvas.drawLine(pointA.x, pointB.x, pointA.y, pointB.y, paint);
        super.onDraw(canvas);
    }

    public void setPointA(PointF point) {
        pointA = point;
    }
    public void setPointB(PointF point) {
        pointB = point;
    }

    public void draw() {
        invalidate();
        requestLayout();
    }
}
