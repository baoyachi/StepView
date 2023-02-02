package com.baoyachi.stepview;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import androidx.core.content.ContextCompat;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * 日期：16/6/24 11:48
 * <p/>
 * 描述：
 */
public class VerticalStepView extends LinearLayout implements VerticalStepViewIndicator.OnDrawIndicatorListener
{
    private RelativeLayout mTextContainer;
    private VerticalStepViewIndicator mStepsViewIndicator;
    private List<String> mTexts;
    private int mComplectingPosition;
    private int mUnComplectedTextColor = ContextCompat.getColor(getContext(), R.color.uncompleted_text_color);//定义默认未完成文字的颜色;
    private int mComplectedTextColor = ContextCompat.getColor(getContext(), android.R.color.white);//定义默认完成文字的颜色;

    private int mTextSize = 14;//default textSize
    private List<TextView> mTextViews = new ArrayList<>();

    public VerticalStepView(Context context)
    {
        this(context, null);
    }

    public VerticalStepView(Context context, AttributeSet attrs)
    {
        this(context, attrs, 0);
    }

    public VerticalStepView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init()
    {
        View rootView = LayoutInflater.from(getContext()).inflate(R.layout.widget_vertical_stepsview, this);
        mStepsViewIndicator = (VerticalStepViewIndicator) rootView.findViewById(R.id.steps_indicator);
        mStepsViewIndicator.setOnDrawListener(this);
        mTextContainer = (RelativeLayout) rootView.findViewById(R.id.rl_text_container);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    /**
     * 设置显示的文字
     *
     * @param texts
     * @return
     */
    public VerticalStepView setStepViewTexts(List<String> texts)
    {
        mTexts = texts;
        if(texts != null){
            mStepsViewIndicator.setStepNum(mTexts.size());
        }else{
            mStepsViewIndicator.setStepNum(0);
        }
        createTextViews();
        return this;
    }

    /**
     * 设置正在进行的position
     *
     * @param complectingPosition
     * @return
     */
    public VerticalStepView setStepsViewIndicatorComplectingPosition(int complectingPosition)
    {
        mComplectingPosition = complectingPosition;
        mStepsViewIndicator.setComplectingPosition(complectingPosition);
        return this;
    }

    /**
     * 设置未完成文字的颜色
     *
     * @param unComplectedTextColor
     * @return
     */
    public VerticalStepView setStepViewUnComplectedTextColor(int unComplectedTextColor)
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
    public VerticalStepView setStepViewComplectedTextColor(int complectedTextColor)
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
    public VerticalStepView setStepsViewIndicatorUnCompletedLineColor(int unCompletedLineColor)
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
    public VerticalStepView setStepsViewIndicatorCompletedLineColor(int completedLineColor)
    {
        mStepsViewIndicator.setCompletedLineColor(completedLineColor);
        return this;
    }

    /**
     * 设置StepsViewIndicator默认图片
     *
     * @param defaultIcon
     */
    public VerticalStepView setStepsViewIndicatorDefaultIcon(Drawable defaultIcon)
    {
        mStepsViewIndicator.setDefaultIcon(defaultIcon);
        return this;
    }

    /**
     * 设置StepsViewIndicator已完成图片
     *
     * @param completeIcon
     */
    public VerticalStepView setStepsViewIndicatorCompleteIcon(Drawable completeIcon)
    {
        mStepsViewIndicator.setCompleteIcon(completeIcon);
        return this;
    }

    /**
     * 设置StepsViewIndicator正在进行中的图片
     *
     * @param attentionIcon
     */
    public VerticalStepView setStepsViewIndicatorAttentionIcon(Drawable attentionIcon)
    {
        mStepsViewIndicator.setAttentionIcon(attentionIcon);
        return this;
    }

    /**
     * is reverse draw 是否倒序画
     *
     * @param isReverSe default is true
     * @return
     */
    public VerticalStepView reverseDraw(boolean isReverSe)
    {
        this.mStepsViewIndicator.reverseDraw(isReverSe);
        return this;
    }

    /**
     * set linePadding  proportion 设置线间距的比例系数
     *
     * @param linePaddingProportion
     * @return
     */
    public VerticalStepView setLinePaddingProportion(float linePaddingProportion)
    {
        this.mStepsViewIndicator.setIndicatorLinePaddingProportion(linePaddingProportion);
        return this;
    }


    /**
     * set textSize
     *
     * @param textSize
     * @return
     */
    public VerticalStepView setTextSize(int textSize)
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
        if (mTexts != null) {
            int viewCount = mTextViews.size();
            int beanCount = mTexts.size();
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
        if(mTexts != null && complectedXPosition != null && complectedXPosition.size() > 0)
        {
            if (mTexts.size() != mTextViews.size()) {
                return;
            }

            for(int i = 0; i < mTexts.size(); i++)
            {
                TextView t = mTextViews.get(i);
                t.setTextSize(TypedValue.COMPLEX_UNIT_SP, mTextSize);
                t.setText(mTexts.get(i));

                int spec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
                t.measure(spec, spec);
                t.setY(complectedXPosition.get(i) - mStepsViewIndicator.getCircleRadius() / 2);

                if(i <= mComplectingPosition)
                {
                    t.setTypeface(null, Typeface.BOLD);
                    t.setTextColor(mComplectedTextColor);
                } else
                {
                    t.setTypeface(null, Typeface.NORMAL);
                    t.setTextColor(mUnComplectedTextColor);
                }
            }
            mTextContainer.requestLayout();
        }
    }
}
