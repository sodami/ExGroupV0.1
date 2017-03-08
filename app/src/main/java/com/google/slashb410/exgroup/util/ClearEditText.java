package com.google.slashb410.exgroup.util;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.google.slashb410.exgroup.R;

/**
 * Created by Tacademy on 2017-03-08.
 */

public class ClearEditText extends AppCompatEditText implements TextWatcher, View.OnTouchListener, View.OnFocusChangeListener {

    Drawable clearDrawable;
    OnTouchListener onTouchListener;
    OnFocusChangeListener onFocusChangeListener;


    @Override
    public void setOnFocusChangeListener(OnFocusChangeListener onFocusChangeListener) {
        this.onFocusChangeListener = onFocusChangeListener;
    }

    @Override
    public void setOnTouchListener(OnTouchListener onTouchListener) {
        this.onTouchListener = onTouchListener;
    }

    public ClearEditText(Context context) {

        super(context);
        init();
    }

    public ClearEditText(Context context, AttributeSet attrs) {

        super(context, attrs);
        init();
    }

    public ClearEditText(Context context, AttributeSet attrs, int defStyleAttr) {

        super(context, attrs, defStyleAttr);
        init();
    }

    public void init() {
        clearDrawable = getResources().getDrawable(R.drawable.clear_pink);
//        DrawableCompat.setTintList(clearDrawable, getHintTextColors());
        clearDrawable.setBounds(0, 0, clearDrawable.getIntrinsicWidth()/4, clearDrawable.getIntrinsicHeight()/4);

        setClearIconVisible(false);

        addTextChangedListener(this);

        super.setOnTouchListener(this);
        super.setOnFocusChangeListener(this);
    }

    private void setClearIconVisible(boolean b) {
        clearDrawable.setVisible(b, false);
        setCompoundDrawables(null, null, b ? clearDrawable : null, null);


    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (isFocused()) {
            setClearIconVisible(s.length() > 0);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int x = (int) event.getX();

        if (clearDrawable.isVisible() && x > getWidth() - getPaddingRight() - clearDrawable.getIntrinsicWidth()) {
            if (event.getAction() == MotionEvent.ACTION_UP) {
                setError(null);
                setText(null);
            }

            return true;
        }

        if(onTouchListener!=null){
            return onTouchListener.onTouch(v, event);
        }else{
            return false;
        }
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {

        if(hasFocus){
            setClearIconVisible(getText().length()>0);
        }else{
            setClearIconVisible(false);
        }

        if(onFocusChangeListener !=null){
            onFocusChangeListener.onFocusChange(v, hasFocus);
        }
    }
}
