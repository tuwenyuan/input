package com.twy.input.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import com.twy.input.R;

/**
 * Author by twy, Email 499216359@qq.com, Date on 2019/8/30.
 * PS: Not easy to write code, please indicate.
 */
public class KeyBoardTitleBarPinYinAllCadidate extends FrameLayout {
    public KeyBoardTitleBarPinYinAllCadidate(Context context) {
        this(context,null);
    }

    public KeyBoardTitleBarPinYinAllCadidate(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public KeyBoardTitleBarPinYinAllCadidate(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        View view=LayoutInflater.from(getContext()).inflate(R.layout.view_title_bar_pinyin_all_cadidate,this,false);
        addView(view);
    }
}
