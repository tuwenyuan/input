package com.twy.input.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import com.twy.input.R;
import com.twy.input.base.ConfigMsg;
import com.twy.input.utils.AscllUtils;
import com.twy.input.utils.UIUtils;

import java.util.List;

/**
 * Author by twy, Email 499216359@qq.com, Date on 2019/8/30.
 * PS: Not easy to write code, please indicate.
 */
public class MyKeyboardView extends KeyboardView {

    // 中文键盘／小写英文键盘/大写英文键盘
    private Keyboard mKeyboardCN, mKeyboardLetter, mKeyboardUpper;
    private int currentKeyboard = 0;//0中文键盘 1 小写英文键盘 2 大写英文键盘
    private Paint paint = new Paint();

    public MyKeyboardView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyKeyboardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(context);

    }

    private void init(Context context) {
        mKeyboardCN = new Keyboard(context, R.xml.default_cn_qwerty);
        mKeyboardLetter = new Keyboard(context, R.xml.default_en_s_qwerty);
        mKeyboardUpper = new Keyboard(context, R.xml.default_en_b_qwerty);
        //默认键盘
        setKeyboard(mKeyboardCN);

        setOnKeyboardActionListener(new MyOnKeyboardActionListener());
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setAntiAlias(true);
        paint.setTypeface(Typeface.DEFAULT);
        paint.setColor(ContextCompat.getColor(context, android.R.color.black));
    }

    /**
     * 绘制按键背景
     *
     * @param drawableId
     * @param canvas
     * @param key
     */
    private void drawKeyBackground(int drawableId, Canvas canvas, Keyboard.Key key) {
        Drawable npd = (Drawable) getContext().getResources().getDrawable(drawableId);
        int[] drawableState = key.getCurrentDrawableState();
        if (key.codes[0] != 0) {
            npd.setState(drawableState);
        }
        int left = key.width / 2 + key.x - UIUtils.dip2px(10);
        int top = key.height / 2 + key.y - UIUtils.dip2px(10);
        int right = key.width / 2 + key.x + UIUtils.dip2px(10);
        int bottom = key.height / 2 + key.y + UIUtils.dip2px(10);
        //绘制按键背景  加上 MyUtil.dp2px(4)
        npd.setBounds(left, top, right, bottom);
        npd.draw(canvas);
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        List<Keyboard.Key> keys = getKeyboard().getKeys();
        for (Keyboard.Key key : keys) {

            int code = key.codes[0];
            /*if(code == -114){
                drawKeyBackground1(R.mipmap.key_cn_mode_pinyin,canvas,key);
            }else */
            if (code == -8) {
                drawKeyBackground1(R.mipmap.key_enter_b, canvas, key);
            } else if (code == -3) {
                drawKeyBackground1(R.mipmap.key_del_b, canvas, key);
            } else if (code == -6) {
                drawKeyBackground2(R.mipmap.key_en, canvas, key);
            } else if (code == -7) {
                drawKeyBackground2(R.mipmap.key_cn, canvas, key);
            }else if (code == 32) {
                drawKeyBackground1(R.mipmap.key_space_b, canvas, key);
            } else if (code == -1) {
                drawKeyBackground(R.mipmap.keyboard_uppercase_active, canvas, key);
            } else if (code == -2) {
                drawKeyBackground(R.mipmap.keyboard_uppercase_select, canvas, key);
            }else {
                drawText(canvas,key);
            }


        }
    }

    private void drawKeyBackground1(int drawableId, Canvas canvas, Keyboard.Key key) {
        Drawable npd = (Drawable) getContext().getResources().getDrawable(drawableId);
        int[] drawableState = key.getCurrentDrawableState();
        if (key.codes[0] != 0) {
            npd.setState(drawableState);
        }
        //绘制按键背景  加上 MyUtil.dp2px(4)
        npd.setBounds(key.x, key.y, key.x + key.width, key.y + key.height);
        npd.draw(canvas);
    }

    private void drawKeyBackground2(int drawableId, Canvas canvas, Keyboard.Key key) {
        Drawable npd = (Drawable) getContext().getResources().getDrawable(drawableId);
        int[] drawableState = key.getCurrentDrawableState();
        if (key.codes[0] != 0) {
            npd.setState(drawableState);
        }
        int width = key.width > key.height ? key.height : key.width;
        int left = key.width / 2 + key.x - width / 2;
        int top = key.height / 2 + key.y - width / 2;
        int right = key.width / 2 + key.x + width / 2;
        int bottom = key.height / 2 + key.y + width / 2;
        //绘制按键背景  加上 MyUtil.dp2px(4)
        npd.setBounds(left, top, right, bottom);
        npd.draw(canvas);
    }

    private void drawText(Canvas canvas, Keyboard.Key key) {

        if(key.codes[0] == -5){
            paint.setTextSize(UIUtils.dip2px(12));
        }else if(key.codes[0] == -11 || key.codes[0] == -4){
            paint.setTextSize(UIUtils.dip2px(15));
        }else {
            paint.setTextSize(UIUtils.dip2px(22));
        }
        if (key.label != null) {
            Paint.FontMetricsInt fontMetricsInt = paint.getFontMetricsInt();
            int baseliney = (key.y + key.height / 2)+(fontMetricsInt.bottom-fontMetricsInt.top)/2-fontMetricsInt.bottom;
            canvas.drawText(key.label.toString(), key.x + (key.width / 2), baseliney, paint);
        }
    }


    class MyOnKeyboardActionListener implements OnKeyboardActionListener {

        @Override
        public void onPress(int primaryCode) {
            if (primaryCode == 32 || primaryCode < 0) {
                setPreviewEnabled(false);
            } else {
                setPreviewEnabled(true);
            }
            //设置某些按键不显示预览的view
            /*if (primaryCode == Keyboard.KEYCODE_SHIFT || primaryCode == Keyboard.KEYCODE_DELETE  //
                    || primaryCode == Keyboard.KEYCODE_DONE || primaryCode == DemoKeyCode.CODE_SPACE //
                    || primaryCode == DemoKeyCode.CODE_TYPE_QWERTY || primaryCode == DemoKeyCode.CODE_TYPE_NUM //
                    || primaryCode == DemoKeyCode.CODE_TYPE_SYMBOL || primaryCode == DemoKeyCode.CODE_SETTING //
                    || primaryCode == DemoKeyCode.CODE_TYPE_SWITCH_INPUT || primaryCode == Keyboard.KEYCODE_MODE_CHANGE) {
                setPreviewEnabled(false);
            } else {
            }*/

        }

        @Override
        public void onRelease(int primaryCode) {

        }

        @Override
        public void onKey(int primaryCode, int[] keyCodes) {
            switch (primaryCode) {
                case ConfigMsg.UPPER:
                    currentKeyboard = 2;
                    setKeyboard(mKeyboardUpper);
                    break;
                case ConfigMsg.EN:
                case ConfigMsg.LETTERS:
                    currentKeyboard = 1;
                    setKeyboard(mKeyboardLetter);
                    break;
                case ConfigMsg.DEL:
                    InputFrameLayout.inputFrameLayout.service.deleteSurroundingText();
                    break;
                case ConfigMsg.SYMBOLS:
                    break;
                case ConfigMsg.NUM:
                    break;
                case ConfigMsg.CN:
                    currentKeyboard = 0;
                    setKeyboard(mKeyboardCN);
                    break;
                case ConfigMsg.ENTER:
                    InputFrameLayout.inputFrameLayout.service.commitText("\n");
                    break;
                case ConfigMsg.CNDOUHAO:
                    InputFrameLayout.inputFrameLayout.service.commitText("，");
                    break;
                case ConfigMsg.CNJUHAO:
                    InputFrameLayout.inputFrameLayout.service.commitText("。");
                    break;
                case ConfigMsg.CNFENCI:
                    InputFrameLayout.inputFrameLayout.service.commitText("'");
                    break;
                default:
                    switch (currentKeyboard) {
                        case 0://中文
                            break;
                        case 1://小写英文键盘
                        case 2://大写英文键盘
                            String s = AscllUtils.ASCIIToStr(primaryCode);
                            InputFrameLayout.inputFrameLayout.service.commitText(s);
                            break;
                    }
                    break;
            }
        }

        @Override
        public void onText(CharSequence text) {

        }

        @Override
        public void swipeLeft() {

        }

        @Override
        public void swipeRight() {

        }

        @Override
        public void swipeDown() {

        }

        @Override
        public void swipeUp() {

        }
    }

}
