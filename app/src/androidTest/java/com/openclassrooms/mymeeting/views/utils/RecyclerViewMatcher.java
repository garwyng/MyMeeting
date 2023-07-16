package com.openclassrooms.mymeeting.views.utils;import android.view.View;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.matcher.BoundedMatcher;

import org.hamcrest.Description;
import org.hamcrest.Matcher;

public class RecyclerViewMatcher {

    public static Matcher<View> withRecyclerView(final int recyclerViewId) {
        return new BoundedMatcher<View, RecyclerView>(RecyclerView.class) {
            @Override
            protected boolean matchesSafely(RecyclerView recyclerView) {
                return recyclerView.getId() == recyclerViewId;
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("with RecyclerView id: " + recyclerViewId);
            }
        };
    }

    public static Matcher<View> atPositionOnView(final int position, final int targetViewId) {
        return new BoundedMatcher<View, RecyclerView>(RecyclerView.class) {
            @Override
            protected boolean matchesSafely(RecyclerView recyclerView) {
                RecyclerView.ViewHolder viewHolder = recyclerView.findViewHolderForAdapterPosition(position);
                if (viewHolder == null) {
                    return false;
                }
                View targetView = viewHolder.itemView.findViewById(targetViewId);
                return targetView != null && targetViewId == targetView.getId();
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("at position: " + position + " on view with id: " + targetViewId);
            }
        };
    }
}
