package com.twy.input.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import com.twy.input.R;
import com.twy.input.services.MyInputMethodService;

/**
 * Author by twy, Email 499216359@qq.com, Date on 2019/8/29.
 * PS: Not easy to write code, please indicate.
 */
public class InputFrameLayout extends FrameLayout {
    public MyInputMethodService service;
    public KeyboardTitleBarManager titleBarManagerLinearLayout;
    public KeyboardBodyManager keyboardBodyManager;
    public static InputFrameLayout inputFrameLayout;

    public InputFrameLayout(Context context) {
        this(context, null);
    }

    public InputFrameLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public InputFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init(){
        View view=LayoutInflater.from(getContext()).inflate(R.layout.view_keyboard,this,false);
        addView(view);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        titleBarManagerLinearLayout = findViewById(R.id.title_bar_manager_ll);
        keyboardBodyManager = findViewById(R.id.kbm);
        inputFrameLayout = this;
    }
}
