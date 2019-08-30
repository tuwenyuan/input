package com.twy.input.services;

import android.inputmethodservice.InputMethodService;
import android.view.View;
import android.view.inputmethod.InputConnection;
import com.twy.input.R;
import com.twy.input.view.InputFrameLayout;

/**
 * Author by twy, Email 499216359@qq.com, Date on 2019/8/29.
 * PS: Not easy to write code, please indicate.
 */
public class MyInputMethodService extends InputMethodService {

    private InputConnection ic;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public View onCreateInputView() {
        ic = getCurrentInputConnection();
        InputFrameLayout layout = (InputFrameLayout)getLayoutInflater().inflate(R.layout.view_keyboard1, null, false);
        layout.service = this;
        return layout;
    }

    @Override
    public View onCreateCandidatesView() {

        return super.onCreateCandidatesView();
    }

    /**
     * 关闭键盘
     */
    public void closeKeyBorad(){
        hideWindow();
    }

    /**
     * 输入
     * @param text
     */
    public void commitText(String text){
        ic.commitText(text,1);
    }

    /**
     * 删除
     */
    public void deleteSurroundingText(){
        CharSequence textBeforeCursor = ic.getTextBeforeCursor(2, 1);
        if(textBeforeCursor!=null){
            String s=textBeforeCursor.toString();
            ic.deleteSurroundingText(1,0);
        }
    }

}
