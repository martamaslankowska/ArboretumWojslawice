package arboretum.arboretumwojslawice.Commons;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class DividerItemDecoration extends RecyclerView.ItemDecoration
{

    private static final int[] ATTRS = new int[]{
            android.R.attr.listDivider
    };

    private Drawable mDivider;

    private int dividerPosition;


    public DividerItemDecoration(Context context, int dividerPosition) {
        final TypedArray a = context.obtainStyledAttributes(ATTRS);
        mDivider = a.getDrawable(0);
        this.dividerPosition = dividerPosition;
        a.recycle();
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        drawVertical(c, parent);
    }

    public void drawVertical(Canvas c, RecyclerView parent) {
        final int left = parent.getPaddingLeft();
        final int right = parent.getWidth() - parent.getPaddingRight();

        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            final int top;
            final int bottom;

            // divider na dole wiersza
            if(dividerPosition == 0)
            {

                top = child.getBottom() + params.bottomMargin;
                bottom = top + mDivider.getIntrinsicHeight()+2;
            }
            // divider na górze wiersza
            else
            {

                top = child.getTop();
                bottom = top + mDivider.getIntrinsicHeight()+2;
            }

            mDivider.setBounds(left, top, right, bottom);
            mDivider.setColorFilter(new PorterDuffColorFilter(0xFF000000,PorterDuff.Mode.MULTIPLY));
            mDivider.draw(c);
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.set(0, 0, 0, mDivider.getIntrinsicHeight());
    }
}