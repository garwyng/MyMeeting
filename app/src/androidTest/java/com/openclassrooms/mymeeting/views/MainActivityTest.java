package com.openclassrooms.mymeeting.views;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.TextView;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.contrib.PickerActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.openclassrooms.mymeeting.R;
import com.openclassrooms.mymeeting.views.utils.DeleteViewAction;
import com.openclassrooms.mymeeting.views.utils.RecyclerViewItemCountAssertion;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void MyMeetingIsLaunchedTest() {
        onView(allOf(instanceOf(TextView.class), withParent(withId(R.id.toolbar))))
                .check(matches(withText("MaRéu")));
    }
    @Test
    public void recyclerViewNotEmpty() {
        ViewInteraction frameLayout = onView(
                allOf(withId(R.id.fragmentContainerView3),
                        withParent(allOf(withId(R.id.nestedScrollView), withContentDescription("liste de réu"),
                                withParent(IsInstanceOf.instanceOf(android.view.ViewGroup.class)))),
                        isDisplayed()));
        frameLayout.check(matches(isDisplayed()));

        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.recyclerview_meetings_list),
                        withParent(allOf(withId(R.id.fragmentContainerView3),
                                withParent(allOf(withId(R.id.nestedScrollView), withContentDescription("liste de réu"))))),
                        isDisplayed()));
        recyclerView.check(matches(isDisplayed()));

        onView(withId(R.id.recyclerview_meetings_list)).check(RecyclerViewItemCountAssertion.withItemCount(14));
    }
    @Test
    public void mainActivityDateFilterTest() {
        ViewInteraction overflowMenuButton = onView(
                allOf(withContentDescription("Plus d'options"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.toolbar),
                                        1),
                                0),
                        isDisplayed()));
        overflowMenuButton.perform(click());

        ViewInteraction materialTextView = onView(
                allOf(withId(androidx.core.R.id.title), withText("filtré par date"),
                        childAtPosition(
                                childAtPosition(
                                        withId(com.google.android.material.R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        materialTextView.perform(click());

        ViewInteraction materialButtonDate = onView(
                allOf(withId(R.id.button_select_date), withText("selectionné une date"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.FrameLayout")),
                                        0),
                                0),
                        isDisplayed()));
        materialButtonDate.perform(click());

        ViewInteraction appCompatImageButton = onView(
                allOf(withClassName(is("androidx.appcompat.widget.AppCompatImageButton")), withContentDescription("Mois suivant"),
                        childAtPosition(
                                allOf(withClassName(is("android.widget.DayPickerView")),
                                        childAtPosition(
                                                withClassName(is("com.android.internal.widget.DialogViewAnimator")),
                                                0)),
                                2)));
        appCompatImageButton.perform(scrollTo(), click());

        ViewInteraction datePicker = onView(
                allOf(IsInstanceOf.instanceOf(android.widget.DatePicker.class),
                        withParent(allOf(withId(android.R.id.custom),
                                withParent(IsInstanceOf.instanceOf(android.widget.FrameLayout.class)))),
                        isDisplayed()));
        datePicker.perform(PickerActions.setDate(2023,8,1));

        ViewInteraction materialButton2 = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                3)));
        materialButton2.perform(scrollTo(), click());

        ViewInteraction materialButton = onView(
                allOf(withId(R.id.button_select_time), withText("selectionné une heure"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.FrameLayout")),
                                        0),
                                1),
                        isDisplayed()));
        materialButton.perform(click());

        ViewInteraction appCompatImageButtonSet = onView(
                allOf(withClassName(is("androidx.appcompat.widget.AppCompatImageButton")), withContentDescription("Passer en mode saisie de texte pour la saisie de l'heure."),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        4),
                                0),
                        isDisplayed()));
        appCompatImageButtonSet.perform(click());

        ViewInteraction appCompatEditText = onView(
                allOf(withClassName(is("androidx.appcompat.widget.AppCompatEditText")),
                        childAtPosition(
                                allOf(withClassName(is("android.widget.RelativeLayout")),
                                        childAtPosition(
                                                withClassName(is("android.widget.TextInputTimePickerView")),
                                                1)),
                                0),
                        isDisplayed()));
        appCompatEditText.perform(replaceText("09"), closeSoftKeyboard());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withClassName(is("androidx.appcompat.widget.AppCompatEditText")),
                        childAtPosition(
                                allOf(withClassName(is("android.widget.RelativeLayout")),
                                        childAtPosition(
                                                withClassName(is("android.widget.TextInputTimePickerView")),
                                                1)),
                                3),
                        isDisplayed()));
        appCompatEditText2.perform(replaceText("00"), closeSoftKeyboard());

        ViewInteraction materialButtonOkTime = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                3)));
        materialButtonOkTime.perform(scrollTo(), click());

        ViewInteraction materialButton5 = onView(
                allOf(withId(R.id.buttonOk), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        2),
                                1),
                        isDisplayed()));
        materialButton5.perform(click());

        onView(withId(R.id.recyclerview_meetings_list)).check(RecyclerViewItemCountAssertion.withItemCount(4));
        onView(
                allOf(withId(R.id.item_meeting_hour), withText("01/08/23"), withContentDescription("day for the meeting"),
                        withParent(withParent(IsInstanceOf.instanceOf(android.widget.ScrollView.class))),
                        isDisplayed()));
        onView(
                allOf(withId(R.id.item_meeting_hour), not(withText("01/08/23")), withContentDescription("day for the meeting"),
                        withParent(withParent(IsInstanceOf.instanceOf(android.widget.ScrollView.class))),
                        not(isDisplayed())));
    }
    @Test
    public void mainActivityRoomFilterTest() {
        ViewInteraction overflowMenuButton = onView(
                allOf(withContentDescription("Plus d'options"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.toolbar),
                                        1),
                                0),
                        isDisplayed()));
        overflowMenuButton.perform(click());

        ViewInteraction materialTextView = onView(
                allOf(withId(androidx.core.R.id.title), withText("filtré par salle"),
                        childAtPosition(
                                childAtPosition(
                                        withId(com.google.android.material.R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        materialTextView.perform(click());

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.list_room),
                        childAtPosition(
                                withId(com.google.android.material.R.id.design_bottom_sheet),
                                0)));
        recyclerView.perform(actionOnItemAtPosition(0, click()));

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        onView(withId(R.id.recyclerview_meetings_list)).check(RecyclerViewItemCountAssertion.withItemCount(4));
        onView(
                allOf(withId(R.id.item_room_name), withText("Réunion 1"), withContentDescription("selection de la salle de réu"),
                        withParent(withParent(IsInstanceOf.instanceOf(android.widget.ScrollView.class))),
                        isDisplayed()));
        onView(
                allOf(withId(R.id.item_room_name), withText("Réunion 2"), withContentDescription("selection de la salle de réu"),
                        withParent(withParent(IsInstanceOf.instanceOf(android.widget.ScrollView.class))),
                        not(isDisplayed())));
        onView(
                allOf(withId(R.id.item_room_name), withText("Réunion 3"), withContentDescription("selection de la salle de réu"),
                        withParent(withParent(IsInstanceOf.instanceOf(android.widget.ScrollView.class))),
                        not(isDisplayed())));
        onView(
                allOf(withId(R.id.item_room_name), withText("Réunion 4"), withContentDescription("selection de la salle de réu"),
                        withParent(withParent(IsInstanceOf.instanceOf(android.widget.ScrollView.class))),
                        not(isDisplayed())));
        onView(
                allOf(withId(R.id.item_room_name), withText("Réunion 5"), withContentDescription("selection de la salle de réu"),
                        withParent(withParent(IsInstanceOf.instanceOf(android.widget.ScrollView.class))),
                        not(isDisplayed())));
        onView(
                allOf(withId(R.id.item_room_name), withText("Réunion 6"), withContentDescription("selection de la salle de réu"),
                        withParent(withParent(IsInstanceOf.instanceOf(android.widget.ScrollView.class))),
                        not(isDisplayed())));
        onView(
                allOf(withId(R.id.item_room_name), withText("Réunion 7"), withContentDescription("selection de la salle de réu"),
                        withParent(withParent(IsInstanceOf.instanceOf(android.widget.ScrollView.class))),
                        not(isDisplayed())));
        onView(
                allOf(withId(R.id.item_room_name), withText("Réunion 8"), withContentDescription("selection de la salle de réu"),
                        withParent(withParent(IsInstanceOf.instanceOf(android.widget.ScrollView.class))),
                        not(isDisplayed())));
        onView(
                allOf(withId(R.id.item_room_name), withText("Réunion 8"), withContentDescription("selection de la salle de réu"),
                        withParent(withParent(IsInstanceOf.instanceOf(android.widget.ScrollView.class))),
                        not(isDisplayed())));
        onView(
                allOf(withId(R.id.item_room_name), withText("Réunion 9"), withContentDescription("selection de la salle de réu"),
                        withParent(withParent(IsInstanceOf.instanceOf(android.widget.ScrollView.class))),
                        not(isDisplayed())));
        onView(
                allOf(withId(R.id.item_room_name), withText("Réunion 10"), withContentDescription("selection de la salle de réu"),
                        withParent(withParent(IsInstanceOf.instanceOf(android.widget.ScrollView.class))),
                        not(isDisplayed())));
    }

    @Test
    public void myMeetingList_deleteAction_shouldRemoveItem(){
        int startSize =15;
        onView(allOf(withId(R.id.recyclerview_meetings_list),
                isDisplayed()))
                .check(RecyclerViewItemCountAssertion.withItemCount(startSize));
        onView(allOf(withId(R.id.recyclerview_meetings_list),isDisplayed()))
                .perform(RecyclerViewActions.actionOnItemAtPosition(3,new DeleteViewAction()));
        onView(allOf(withId(R.id.recyclerview_meetings_list),isDisplayed())).check(RecyclerViewItemCountAssertion.withItemCount(startSize-1));
    }


    private static TypeSafeMatcher childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            /**
             * @param view
             * @return
             */
            @Override
            protected boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }
        };
    }

}
