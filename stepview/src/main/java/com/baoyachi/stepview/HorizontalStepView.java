package com.baoyachi.stepview;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baoyachi.stepview.bean.StepBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 日期：16/6/22 15:47
 * <p/>
 * 描述：StepView
 */
public class HorizontalStepView extends LinearLayout implements HorizontalStepsViewIndicator.OnDrawIndicatorListener
{
    private RelativeLayout mTextContainer;
    private List<TextView> mTextViews = new ArrayList<>();

    private HorizontalStepsViewIndicator mStepsViewIndicator;
    private List<StepBean> mStepBeanList;

    private int mUnComplectedTextColor = ContextCompat.getColor(getContext(), R.color.uncompleted_text_color);//定义默认未完成文字的颜色;
    private int mComplectedTextColor = ContextCompat.getColor(getContext(), android.R.color.white);//定义默认完成文字的颜色;
    private int mTextSize = 14;//default textSize

    public HorizontalStepView(Context context)
    {
        this(context, null);
    }

    public HorizontalStepView(Context context, AttributeSet attrs)
    {
        this(context, attrs, 0);
    }

    public HorizontalStepView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init()
    {
        View rootView = LayoutInflater.from(getContext()).inflate(R.layout.widget_horizontal_stepsview, this);
        mStepsViewIndicator = (HorizontalStepsViewIndicator) rootView.findViewById(R.id.steps_indicator);
        mStepsViewIndicator.setOnDrawListener(this);
        mTextContainer = (RelativeLayout) rootView.findViewById(R.id.rl_text_container);
    }

    /**
     * 设置显示的文字
     *
     * @param stepsBeanList
     * @return
     */
    public HorizontalStepView setStepViewTexts(List<StepBean> stepsBeanList)
    {
        mStepBeanList = stepsBeanList;
        mStepsViewIndicator.setStepNum(mStepBeanList);
        createTextViews();
        return this;
    }


    /**
     * 设置未完成文字的颜色
     *
     * @param unComplectedTextColor
     * @return
     */
    public HorizontalStepView setStepViewUnComplectedTextColor(int unComplectedTextColor)
    {
        mUnComplectedTextColor = unComplectedTextColor;
        return this;
    }

    /**
     * 设置完成文字的颜色
     *
     * @param complectedTextColor
     * @return
     */
    public HorizontalStepView setStepViewComplectedTextColor(int complectedTextColor)
    {
        this.mComplectedTextColor = complectedTextColor;
        return this;
    }

    /**
     * 设置StepsViewIndicator未完成线的颜色
     *
     * @param unCompletedLineColor
     * @return
     */
    public HorizontalStepView setStepsViewIndicatorUnCompletedLineColor(int unCompletedLineColor)
    {
        mStepsViewIndicator.setUnCompletedLineColor(unCompletedLineColor);
        return this;
    }

    /**
     * 设置StepsViewIndicator完成线的颜色
     *
     * @param completedLineColor
     * @return
     */
    public HorizontalStepView setStepsViewIndicatorCompletedLineColor(int completedLineColor)
    {
        mStepsViewIndicator.setCompletedLineColor(completedLineColor);
        return this;
    }

    /**
     * 设置StepsViewIndicator默认图片
     *
     * @param defaultIcon
     */
    public HorizontalStepView setStepsViewIndicatorDefaultIcon(Drawable defaultIcon)
    {
        mStepsViewIndicator.setDefaultIcon(defaultIcon);
        return this;
    }

    /**
     * 设置StepsViewIndicator已完成图片
     *
     * @param completeIcon
     */
    public HorizontalStepView setStepsViewIndicatorCompleteIcon(Drawable completeIcon)
    {
        mStepsViewIndicator.setCompleteIcon(completeIcon);
        return this;
    }

    /**
     * 设置StepsViewIndicator正在进行中的图片
     *
     * @param attentionIcon
     */
    public HorizontalStepView setStepsViewIndicatorAttentionIcon(Drawable attentionIcon)
    {
        mStepsViewIndicator.setAttentionIcon(attentionIcon);
        return this;
    }

    /**
     * set textSize
     *
     * @param textSize
     * @return
     */
    public HorizontalStepView setTextSize(int textSize)
    {
        if(textSize > 0)
        {
            mTextSize = textSize;
        }
        return this;
    }

    @Override
    public void ondrawIndicator()
    {
        updateStepTexts();
    }

    private void createTextViews()
    {
        if (mStepBeanList != null) {
            int viewCount = mTextViews.size();
            int beanCount = mStepBeanList.size();
            int delta = viewCount - beanCount;
            if (beanCount == 0) {
                mTextContainer.removeAllViews();
                mTextViews.clear();
            } else if (delta < 0) {
                LayoutInflater inflater = LayoutInflater.from(getContext());
                for (int i = viewCount; i < beanCount; i++) {
                    TextView t = (TextView) inflater.inflate(R.layout.widget_setpsview_text_item, mTextContainer, false);
                    mTextContainer.addView(t);
                    mTextViews.add(t);
                }
            } else if (delta > 0) {
                mTextContainer.removeViews(beanCount, delta);
                mTextViews.removeAll(mTextViews.subList(beanCount, viewCount));
            }
        }
    }

    private void updateStepTexts()
    {
        List<Float> complectedXPosition = mStepsViewIndicator.getCircleCenterPointPositionList();
        if (mStepBeanList != null && complectedXPosition != null && complectedXPosition.size() > 0) {

            if (mStepBeanList.size() != mTextViews.size()) {
                return;
            }

            for (int i = 0; i < mStepBeanList.size(); i++) {
                StepBean bean = mStepBeanList.get(i);
                TextView t = mTextViews.get(i);

                t.setTextSize(TypedValue.COMPLEX_UNIT_SP, mTextSize);
                t.setText(bean.getName());

                int spec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
                t.measure(spec, spec);
                int measuredWidth = t.getMeasuredWidth();
                t.setX(complectedXPosition.get(i) - measuredWidth / 2);

                switch (bean.getState()) {
                    case StepBean.STEP_COMPLETED:
                        // fall through
                    case StepBean.STEP_UNDO:
                        t.setTypeface(null, Typeface.NORMAL);
                        t.setTextColor(mUnComplectedTextColor);
                        break;
                    case StepBean.STEP_CURRENT:
                        t.setTypeface(null, Typeface.BOLD);
                        t.setTextColor(mComplectedTextColor);
                        break;
                }
            }
            mTextContainer.requestLayout();
        }
    }
}
