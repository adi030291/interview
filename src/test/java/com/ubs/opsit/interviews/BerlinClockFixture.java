package com.ubs.opsit.interviews;

import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.Test;

import junit.framework.Assert;

import static com.ubs.opsit.interviews.support.BehaviouralTestEmbedder.aBehaviouralTestRunner;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Acceptance test class that uses the JBehave (Gerkin) syntax for writing
 * stories. You should not need to edit this class to complete the exercise,
 * this is your definition of done.
 */
public class BerlinClockFixture {

	private TimeConverter berlinClock;
	private String theTime;

	@Test
	public void berlinClockAcceptanceTests() throws Exception {
		aBehaviouralTestRunner().usingStepsFrom(this).withStory("berlin-clock.story").run();
	}

	@When("the time is $time")
	public void whenTheTimeIs(String time) {
		theTime = time;
	}

	@Then("the clock should look like $")
	public void thenTheClockShouldLookLike(String theExpectedBerlinClockOutput) {
		berlinClock = new BerlinClock();
		String[] theExpectedBerlinClockArray = theExpectedBerlinClockOutput.split("\n");
		String[] theberlinClockArray = berlinClock.convertTime(theTime).split("\n");
		for (int index = 0; index < theExpectedBerlinClockArray.length; index++) {
			assertThat(theExpectedBerlinClockArray[index].trim().toString()).isEqualTo(theberlinClockArray[index].trim().toString());

		}
		// assertThat(berlinClock.convertTime(theTime)).isEqualTo(theExpectedBerlinClockOutput);
	}

	// Yellow lamp should toggle every 2 seconds
	@Test
	public void yellowLampToggle() {
		berlinClock = new BerlinClock();
		Assert.assertEquals("O", ((BerlinClock) berlinClock).getTopSecondRowPattern(55));
		Assert.assertEquals("Y", ((BerlinClock) berlinClock).getTopSecondRowPattern(2));
	}

	// Top hour lamp test : red light for every 5 hour
	@Test
	public void topHourLampCheck() {
		berlinClock = new BerlinClock();
		Assert.assertEquals("RRRR", ((BerlinClock) berlinClock).getTopHourRowPattern(22));
		Assert.assertEquals("RRRO", ((BerlinClock) berlinClock).getTopHourRowPattern(17));
		Assert.assertEquals("RROO", ((BerlinClock) berlinClock).getTopHourRowPattern(12));
		Assert.assertEquals("ROOO", ((BerlinClock) berlinClock).getTopHourRowPattern(7));
		Assert.assertEquals("OOOO", ((BerlinClock) berlinClock).getTopHourRowPattern(4));
	}

	// Bottom hour lamp test : red light for every 1-4 hour
	@Test
	public void bottomHourLampCheck() {
		berlinClock = new BerlinClock();
		Assert.assertEquals("RRRR", ((BerlinClock) berlinClock).getBottomHourRowPattern(22));
		Assert.assertEquals("RRRO", ((BerlinClock) berlinClock).getBottomHourRowPattern(17));
		Assert.assertEquals("RROO", ((BerlinClock) berlinClock).getBottomHourRowPattern(12));
		Assert.assertEquals("ROOO", ((BerlinClock) berlinClock).getBottomHourRowPattern(7));
		Assert.assertEquals("OOOO", ((BerlinClock) berlinClock).getBottomHourRowPattern(4));
	}

	// Top minute lamp test
	@Test
	public void topMiuteLampCheck() {
		berlinClock = new BerlinClock();
		Assert.assertEquals("OOOOOOOOOOO", ((BerlinClock) berlinClock).getTopMinuteRowPattern(0));
		Assert.assertEquals("YYROOOOOOOO", ((BerlinClock) berlinClock).getTopMinuteRowPattern(17));
		Assert.assertEquals("YYRYYRYYRYY", ((BerlinClock) berlinClock).getTopMinuteRowPattern(59));
	}

	// Bottom minute lamp test: red light for every 1-4 hour
	@Test
	public void bottomMiuteLampCheck() {
		berlinClock = new BerlinClock();
		Assert.assertEquals("RRRR", ((BerlinClock) berlinClock).getBottomMinuteRowPattern(12));
		Assert.assertEquals("RRRO", ((BerlinClock) berlinClock).getBottomMinuteRowPattern(45));
		Assert.assertEquals("RROO", ((BerlinClock) berlinClock).getBottomMinuteRowPattern(00));
		Assert.assertEquals("ROOO", ((BerlinClock) berlinClock).getBottomMinuteRowPattern(44));
		Assert.assertEquals("OOOO", ((BerlinClock) berlinClock).getBottomMinuteRowPattern(1));
	}

}
