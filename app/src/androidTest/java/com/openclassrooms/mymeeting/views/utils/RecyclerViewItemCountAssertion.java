package com.openclassrooms.mymeeting.views.utils;

import static androidx.test.espresso.matcher.ViewMatchers.assertThat;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.ViewAssertion;

import org.hamcrest.Matcher;
import org.hamcrest.core.Is;

public class RecyclerViewItemCountAssertion implements ViewAssertion {
    private final Matcher<Integer> matcher;

    private RecyclerViewItemCountAssertion(Matcher<Integer> matcher) {
        this.matcher = matcher;
    }

    /**
     * @param expectedCount
     * @return boolean
     */
    public static RecyclerViewItemCountAssertion withItemCount(int expectedCount) {
        return withItemCount(Is.is(expectedCount));

    }

    /**
     * @param matcher
     * @return matcher
     */
    public static RecyclerViewItemCountAssertion withItemCount(Matcher<Integer> matcher) {
        return new RecyclerViewItemCountAssertion(matcher);
    }

    /**
     * @param view                 the view, if one was found during the view interaction or null if it was not (which
     *                             may be an acceptable option for an assertion)
     * @param noViewFoundException an exception detailing why the view could not be found or null if
     *                             the view was found
     */
    @Override
    public void check(View view, NoMatchingViewException noViewFoundException) {
        if (noViewFoundException != null) {
            throw noViewFoundException;
    }
        RecyclerView recyclerView = (RecyclerView) view;
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        assertThat(adapter.getItemCount(), matcher);
    }
}
