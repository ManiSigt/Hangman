package com.example.manisigurdsson.hangman;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.inputmethod.InputConnection;
import android.widget.Button;
import android.widget.LinearLayout;
import android.view.View;

public class MyKeyboard extends LinearLayout implements View.OnClickListener {

    private Button button1, button2, button3, button4, button5, button6, button7, button8, button9, button10, button11,
            button12, button13, button14, button15, button16, button17, button18, button19, button20, button21, button22,
            button23, button24, button25, button26, button27, button28, button29, button30, button31, button32, button33,
            button34, button35, button36;

    private SparseArray<String> keyValues = new SparseArray<>();
    private InputConnection inputConnection;

    public MyKeyboard (Context context) {
        this (context, null, 0);
    }

    public MyKeyboard (Context context, AttributeSet attrs) {
        this (context, attrs, 0);
    }

    public MyKeyboard (Context context, AttributeSet attrs, int defStyleAttr) {
        super (context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        LayoutInflater.from(context).inflate(R.layout.keyboard, this, true);
        button1 = findViewById(R.id.btn_a);
        button1.setOnClickListener(this);
        button2 = findViewById(R.id.btn_aa);
        button2.setOnClickListener(this);
        button3 = findViewById(R.id.btn_b);
        button3.setOnClickListener(this);
        button4 = findViewById(R.id.btn_c);
        button4.setOnClickListener(this);
        button5 = findViewById(R.id.btn_d);
        button5.setOnClickListener(this);
        button6 = findViewById(R.id.btn_dd);
        button6.setOnClickListener(this);
        button7 = findViewById(R.id.btn_e);
        button7.setOnClickListener(this);
        button8 = findViewById(R.id.btn_ee);
        button8.setOnClickListener(this);
        button9 = findViewById(R.id.btn_f);
        button9.setOnClickListener(this);
        button10 = findViewById(R.id.btn_g);
        button10.setOnClickListener(this);
        button11 = findViewById(R.id.btn_h);
        button11.setOnClickListener(this);
        button12 = findViewById(R.id.btn_i);
        button12.setOnClickListener(this);
        button13 = findViewById(R.id.btn_ii);
        button13.setOnClickListener(this);
        button14 = findViewById(R.id.btn_j);
        button14.setOnClickListener(this);
        button15 = findViewById(R.id.btn_k);
        button15.setOnClickListener(this);
        button16 = findViewById(R.id.btn_l);
        button16.setOnClickListener(this);
        button17 = findViewById(R.id.btn_m);
        button17.setOnClickListener(this);
        button18 = findViewById(R.id.btn_n);
        button18.setOnClickListener(this);
        button19 = findViewById(R.id.btn_o);
        button19.setOnClickListener(this);
        button20 = findViewById(R.id.btn_oo);
        button20.setOnClickListener(this);
        button21 = findViewById(R.id.btn_p);
        button21.setOnClickListener(this);
        button22 = findViewById(R.id.btn_q);
        button22.setOnClickListener(this);
        button23 = findViewById(R.id.btn_r);
        button23.setOnClickListener(this);
        button24 = findViewById(R.id.btn_s);
        button24.setOnClickListener(this);
        button25 = findViewById(R.id.btn_t);
        button25.setOnClickListener(this);
        button26 = findViewById(R.id.btn_u);
        button26.setOnClickListener(this);
        button27 = findViewById(R.id.btn_uu);
        button27.setOnClickListener(this);
        button28 = findViewById(R.id.btn_v);
        button28.setOnClickListener(this);
        button29 = findViewById(R.id.btn_w);
        button29.setOnClickListener(this);
        button30 = findViewById(R.id.btn_x);
        button30.setOnClickListener(this);
        button31 = findViewById(R.id.btn_y);
        button31.setOnClickListener(this);
        button32 = findViewById(R.id.btn_yy);
        button32.setOnClickListener(this);
        button33 = findViewById(R.id.btn_z);
        button33.setOnClickListener(this);
        button34 = findViewById(R.id.btn_th);
        button34.setOnClickListener(this);
        button35 = findViewById(R.id.btn_ae);
        button35.setOnClickListener(this);
        button36 = findViewById(R.id.btn_ooo);
        button36.setOnClickListener(this);

        keyValues.put(R.id.btn_a, "a");
        keyValues.put(R.id.btn_aa, "á");
        keyValues.put(R.id.btn_b, "b");
        keyValues.put(R.id.btn_c, "c");
        keyValues.put(R.id.btn_d, "d");
        keyValues.put(R.id.btn_dd, "ð");
        keyValues.put(R.id.btn_e, "e");
        keyValues.put(R.id.btn_ee, "é");
        keyValues.put(R.id.btn_f, "f");
        keyValues.put(R.id.btn_g, "g");
        keyValues.put(R.id.btn_h, "h");
        keyValues.put(R.id.btn_i, "i");
        keyValues.put(R.id.btn_ii, "í");
        keyValues.put(R.id.btn_j, "j");
        keyValues.put(R.id.btn_k, "k");
        keyValues.put(R.id.btn_l, "l");
        keyValues.put(R.id.btn_m, "m");
        keyValues.put(R.id.btn_n, "n");
        keyValues.put(R.id.btn_o, "ó");
        keyValues.put(R.id.btn_oo, "ó");
        keyValues.put(R.id.btn_p, "p");
        keyValues.put(R.id.btn_q, "q");
        keyValues.put(R.id.btn_r, "r");
        keyValues.put(R.id.btn_s, "s");
        keyValues.put(R.id.btn_t, "t");
        keyValues.put(R.id.btn_u, "u");
        keyValues.put(R.id.btn_uu, "ú");
        keyValues.put(R.id.btn_v, "v");
        keyValues.put(R.id.btn_w, "w");
        keyValues.put(R.id.btn_x, "x");
        keyValues.put(R.id.btn_y, "y");
        keyValues.put(R.id.btn_yy, "ý");
        keyValues.put(R.id.btn_z, "z");
        keyValues.put(R.id.btn_th, "þ");
        keyValues.put(R.id.btn_ae, "æ");
        keyValues.put(R.id.btn_ooo, "ö");
    }

    @Override
    public void onClick(View view) {
        if (inputConnection == null)
            return;

        String value = keyValues.get(view.getId());
        view.setBackgroundColor(2);
        inputConnection.commitText(value, 1);
        Log.d("KKKEEYYY===VALLLLUUEEE", value);
    }

    public void setInputConnection(InputConnection ic) {
        inputConnection = ic;
    }
}
