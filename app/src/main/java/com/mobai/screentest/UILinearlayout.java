package com.mobai.screentest;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

public class UILinearlayout extends RelativeLayout {

    private static boolean flag = true;

    public UILinearlayout(Context context) {
        super(context);
    }

    public UILinearlayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public UILinearlayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    //现有属性和新属性的操作
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //获取所有组件的属性
        int count = this.getChildCount();
        UIUtils uiUtils = new UIUtils(getContext());
        float scaleX = uiUtils.getHorizontalScale();
        float scaleY = uiUtils.getVerticalScale();
        if (flag) {
            flag = false;
            for (int i = 0; i < count; i++) {
                View child = this.getChildAt(i);

                //获取布局参数
                LayoutParams layoutParams = (LayoutParams) child.getLayoutParams();
                //完成缩放
                layoutParams.width = (int) (layoutParams.width * scaleX);
                layoutParams.height = (int) (layoutParams.height * scaleY);
                layoutParams.topMargin = (int) (layoutParams.topMargin * scaleY);
                layoutParams.bottomMargin = (int) (layoutParams.bottomMargin * scaleY);
                layoutParams.leftMargin = (int) (layoutParams.leftMargin * scaleX);
                layoutParams.rightMargin = (int) (layoutParams.rightMargin * scaleX);
            }
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    //帮助我们把自定义的属性封装到系统的param中
    @Override
    public RelativeLayout.LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new LayoutParams(getContext(),attrs);
    }

    public static class LayoutParams extends RelativeLayout.LayoutParams {

        public LayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
        }

        public LayoutParams(int w, int h) {
            super(w, h);
        }

        public LayoutParams(ViewGroup.LayoutParams source) {
            super(source);
        }

        public LayoutParams(MarginLayoutParams source) {
            super(source);
        }

        public LayoutParams(RelativeLayout.LayoutParams source) {
            super(source);
        }
    }
}
