package com.coderwjq.shop.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.Transformation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.coderwjq.shop.R;
import com.coderwjq.shop.utils.UIUtils;


/**
 * Created by coderwjq on 2017/2/11.
 */

public class ExpandTextView extends LinearLayout {

    //默认属性值
    public int defaultTextColor = Color.BLACK;
    public int defaultTextSize = 14;
    public int defaultLine = 1;
    protected TextView contentView; //文本正文
    protected ImageView expandView; //展开按钮
    //对应styleable中的属性
    protected int textColor;
    protected float textSize;
    protected int maxLine;
    protected String text;


    /**
     * 程序内实例化时调用
     *
     * @param context
     */
    public ExpandTextView(Context context) {
        super(context);
    }

    /**
     * layout文件实例化时调用,会把xml中的参数通过attrs带入到view中
     *
     * @param context
     * @param attrs
     */
    public ExpandTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
        initWithAttrs(context, attrs);
        bindListener();
    }

    /**
     * 带有主题的style信息
     *
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
    public ExpandTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init() {
        setOrientation(VERTICAL); //设置垂直布局
        setGravity(Gravity.CENTER); //中间对齐
        //初始化textView并添加
        contentView = new TextView(getContext());
        //添加行距
        contentView.setLineSpacing(1f, 1.3f);
        addView(contentView, LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        //初始化ImageView并添加
        expandView = new ImageView(getContext());
        int padding = UIUtils.dp2px(getContext(), 5);
        expandView.setPadding(padding, padding, padding, padding);
        expandView.setImageResource(R.drawable.ic_arrow_down);
        LayoutParams llp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        addView(expandView, llp);
    }

    private void initWithAttrs(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ExpandTextView);
        int textColor = a.getColor(R.styleable.ExpandTextView_textColor,
                defaultTextColor);  //取颜色值，默认defaultTextColor
        textSize = a.getDimensionPixelSize(R.styleable.ExpandTextView_textSize, defaultTextSize);//取颜字体大小，默认defaultTextSize
        maxLine = a.getInt(R.styleable.ExpandTextView_maxLine, defaultLine);//取颜显示行数，默认defaultLine
        text = a.getString(R.styleable.ExpandTextView_text);//取文本内容

        //绑定到textView   bindTextView(textColor,textSize,maxLine,text);
        bindTextView(textColor, textSize, maxLine, text);
        a.recycle();//回收释放
    }

    private void bindTextView(int textColor, float textSize, final int maxLine, String text) {
        contentView.setTextColor(textColor);
        contentView.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
        contentView.setText(text);
        contentView.setHeight(contentView.getLineHeight() * maxLine);
    }

    private void bindListener() {
        setOnClickListener(new OnClickListener() {
            boolean isExpand;

            @Override
            public void onClick(View v) {
                isExpand = !isExpand;
                contentView.clearAnimation();
                final int deltaValue;
                final int startValue = contentView.getHeight();
                int durationMillis = 350;
                if (isExpand) {
                    deltaValue = contentView.getLineHeight() * contentView.getLineCount() - startValue;
                    RotateAnimation animation = new RotateAnimation(0, 180, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                    animation.setDuration(durationMillis);
                    animation.setFillAfter(true);
                    expandView.startAnimation(animation);
                } else {
                    deltaValue = contentView.getLineHeight() * maxLine - startValue;
                    RotateAnimation animation = new RotateAnimation(180, 0, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                    animation.setDuration(durationMillis);
                    animation.setFillAfter(true);
                    expandView.startAnimation(animation);
                }
                Animation animation = new Animation() {
                    protected void applyTransformation(float interpolatedTime, Transformation t) {
                        contentView.setHeight((int) (startValue + deltaValue * interpolatedTime));
                    }

                };
                animation.setDuration(durationMillis);
                contentView.startAnimation(animation);
            }
        });
    }

    public TextView getTextView() {
        return contentView;
    }

    public void setText(CharSequence charSequence) {
        contentView.setText(charSequence);
    }

}
