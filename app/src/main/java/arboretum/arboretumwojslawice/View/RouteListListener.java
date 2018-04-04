package arboretum.arboretumwojslawice.View;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import arboretum.arboretumwojslawice.View.Adapter.CustomAdapter;

/**
 * Created by Michal on 25.03.2018.
 */

public class RouteListListener implements RecyclerView.OnItemTouchListener{

    private GestureDetector gestureDetector;
    private CustomAdapter.OnItemClickListener clickListener;

    public RouteListListener(Context context, final RecyclerView recyclerView, final CustomAdapter.OnItemClickListener clickListener) {

        this.clickListener = clickListener;
        gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener()
        {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }

//            @Override
//            public void onLongPress(MotionEvent e)
//            {
//                View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
//
//                if (child != null && clickListener != null)
//                {
//                    clickListener.onLongClick(child, recyclerView.getChildPosition(child));
//
//                }
//            }
        });
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        View child = rv.findChildViewUnder(e.getX(), e.getY());
        if (child != null && clickListener != null)
        {
            clickListener.onItemClick(rv.getChildAdapterPosition(child));
        }
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }
}
