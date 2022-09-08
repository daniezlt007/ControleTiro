package com.dev.controletiro.interfaces;

import android.view.View;

public interface OnListClickInteractionListener {

    void onItemClick(View view, int position, boolean isLongClick);
    boolean onItemLongClick(View view, int position,boolean isLongClick);

}
