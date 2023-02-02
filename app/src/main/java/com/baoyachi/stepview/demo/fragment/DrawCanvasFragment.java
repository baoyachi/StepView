package com.baoyachi.stepview.demo.fragment;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baoyachi.stepview.demo.R;

/**
 * 日期：16/6/24 20:00
 * <p>
 * 描述：
 */
public class DrawCanvasFragment extends Fragment
{
    View mView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return new RectView(container.getContext());
    }
    public class RectView extends View
    {

        public RectView(Context context)
        {
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas)
        {

            super.onDraw(canvas);
            setBackgroundResource(R.drawable.default_bg);//设置背景色

            //--------------------------绘制矩形-----------------------------------------------------
            Paint paint = new Paint();// 定义画笔
            paint.setStyle(Paint.Style.FILL);//设置实心
            paint.setAntiAlias(true);// 消除锯齿
            paint.setColor(Color.WHITE);//设置画笔颜色
            paint.setStrokeWidth(40);// 设置paint的外框宽度
            canvas.drawRect(200, 200, 800, 220, paint);//绘制矩形
            //--------------------------绘制矩形-----------------------------------------------------


            //--------------------------绘制圆-----------------------------------------------------
            canvas.drawCircle(350, 350, 100, paint);
            //--------------------------绘制圆-----------------------------------------------------

            //--------------------------绘制虚线-----------------------------------------------------

            Paint pathPaint = new Paint();
            pathPaint.setAntiAlias(true);
            pathPaint.setColor(Color.WHITE);
            pathPaint.setStyle(Paint.Style.STROKE);
            pathPaint.setStrokeWidth(2);
            DashPathEffect mEffects = new DashPathEffect(new float[]{8, 8, 8, 8}, 1);
            Path path = new Path();
            path.moveTo(200, 600);
            path.lineTo(800, 600);
            pathPaint.setPathEffect(mEffects);
            canvas.drawPath(path, pathPaint);
            //--------------------------绘制虚线-----------------------------------------------------


        }
    }
}
