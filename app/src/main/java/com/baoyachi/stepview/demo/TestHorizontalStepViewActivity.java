package com.baoyachi.stepview.demo;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;

import com.baoyachi.stepview.HorizontalStepView;
import com.baoyachi.stepview.bean.StepBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 日期：16/7/26 15:06
 * <p>
 * 描述：
 */
public class TestHorizontalStepViewActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_horizontal);
        findViewById(R.id.btn_status).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                HorizontalStepView stepView = (HorizontalStepView) findViewById(R.id.stepview);
                //-----------------------------this data is example and you can also get data from server-----------------------------
                List<StepBean> stepsBeanList = new ArrayList<>();
                StepBean stepBean0 = new StepBean("接单",1);
                StepBean stepBean1 = new StepBean("打包",1);
                StepBean stepBean2 = new StepBean("出发",0);
                StepBean stepBean3 = new StepBean("送单",-1);
                StepBean stepBean4 = new StepBean("完成",-1);
                StepBean stepBean5 = new StepBean("支付",-1);
                stepsBeanList.add(stepBean0);
                stepsBeanList.add(stepBean1);
                stepsBeanList.add(stepBean2);
                stepsBeanList.add(stepBean3);
                stepsBeanList.add(stepBean4);
                stepsBeanList.add(stepBean5);
                //-----------------------------this data is example and you can also get data from server-----------------------------

                stepView.setStepViewTexts(stepsBeanList)
                        .setTextSize(16)//set textSize
                        .setStepsViewIndicatorCompletedLineColor(ContextCompat.getColor(getBaseContext(), android.R.color.white))//设置StepsViewIndicator完成线的颜色
                        .setStepsViewIndicatorUnCompletedLineColor(ContextCompat.getColor(getBaseContext(), R.color.uncompleted_text_color))//设置StepsViewIndicator未完成线的颜色
                        .setStepViewComplectedTextColor(ContextCompat.getColor(getBaseContext(), android.R.color.white))//设置StepsView text完成线的颜色
                        .setStepViewUnComplectedTextColor(ContextCompat.getColor(getBaseContext(), R.color.uncompleted_text_color))//设置StepsView text未完成线的颜色
                        .setStepsViewIndicatorCompleteIcon(ContextCompat.getDrawable(getBaseContext(), R.drawable.complted))//设置StepsViewIndicator CompleteIcon
                        .setStepsViewIndicatorDefaultIcon(ContextCompat.getDrawable(getBaseContext(), R.drawable.default_icon))//设置StepsViewIndicator DefaultIcon
                        .setStepsViewIndicatorAttentionIcon(ContextCompat.getDrawable(getBaseContext(), R.drawable.attention));//设置StepsViewIndicator AttentionIcon
            }
        });


    }
}
