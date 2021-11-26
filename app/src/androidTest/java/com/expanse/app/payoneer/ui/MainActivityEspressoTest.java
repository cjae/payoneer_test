package com.expanse.app.payoneer.ui;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.expanse.app.payoneer.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class MainActivityEspressoTest {

	@Rule
	public ActivityScenarioRule<MainActivity> activityRule =
			new ActivityScenarioRule<>(MainActivity.class);

	@Test
	public void appLaunchesSuccessfully() {
		onView(withId(R.id.container))
				.check(matches(isDisplayed()));
	}

	@Test
	public void mainFragmentIsVisible() {
		onView(withId(R.id.titleTv)).check(matches(isDisplayed()));

		onView(withId(R.id.amountLabel)).check(matches(isDisplayed()));

		onView(withId(R.id.amountTv)).check(matches(isDisplayed()));
	}
}