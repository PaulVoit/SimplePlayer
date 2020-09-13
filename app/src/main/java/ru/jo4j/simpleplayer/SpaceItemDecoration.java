package ru.jo4j.simpleplayer;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SpaceItemDecoration extends RecyclerView.ItemDecoration {
    private int space;

    public SpaceItemDecoration(int space)
    {
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state)
    {
        outRect.bottom = space;
        outRect.left = space;
        outRect.right = space;
    }
}
