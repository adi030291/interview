package com.ubs.opsit.interviews;

public class BerlinClock implements TimeConverter {

	@Override
	public String convertTime(String aTime) {

		String[] splittedTime = aTime.split(":");
		String berlinClockPattern = getTopSecondRowPattern(Integer.parseInt(splittedTime[2].trim())) + "\n"
				+ getTopHourRowPattern(Integer.parseInt(splittedTime[0].trim())) + "\n"
				+ getBottomHourRowPattern(Integer.parseInt(splittedTime[0].trim())) + "\n"
				+ getTopMinuteRowPattern(Integer.parseInt(splittedTime[1].trim())) + "\n"
				+ getBottomMinuteRowPattern(Integer.parseInt(splittedTime[1].trim()));
		return berlinClockPattern;
	}

	/**
	 * Description: create pattern for bottom minute row
	 * @param int minute
	 * @return String bottomMinutePattern 
	 */
	protected String getBottomMinuteRowPattern(int minute) {
		String bottomMinutePattern = lampsON(minute % 5, 4, true).replaceAll("R", "Y");
		return bottomMinutePattern;
	}

	/**
	 * Description: create pattern for top minute row
	 * @param int minute
	 * @return String topMinutePattern 
	 */
	protected String getTopMinuteRowPattern(int minute) {
		String topMinutePattern = lampsON(minute / 5, 11, false).replaceAll("YYY", "YYR");
		return topMinutePattern;
	}

	/**
	 * Description: create pattern for bottom hour row
	 * @param int hour
	 * @return String bottomHoursPattern 
	 */
	protected String getBottomHourRowPattern(int hours) {
		String bottomHoursPattern = lampsON(hours % 5, 4, true);
		return bottomHoursPattern;
	}

	/**
	 * Description: create pattern for top hour row
	 * @param int hours
	 * @return String topHoursPattern 
	 */
	protected String getTopHourRowPattern(int hours) {
		String topLampsPattern = lampsON(hours / 5, 4, true);
		return topLampsPattern;
	}

	/**
	 * Description: create pattern for different time variables
	 * @param int timeVariable: time(HH/MH/ss) divided by some integer
	 * @param int lampCount: count of lamps for the row
	 * @param boolean smallPattern: pattern having less number of lamp
	 * @return String topHoursPattern 
	 */
	private String lampsON(int timeVariable, int lampCount, boolean smallPattern) {
		StringBuilder output;
		if (timeVariable < 5 && smallPattern) {
			output = new StringBuilder("OOOO");
			if (timeVariable != 0) {
				for (int i = 0; i < timeVariable; i++) {
					output.setCharAt(i, 'R');
				}
			}
		} else {
			output = new StringBuilder("OOOOOOOOOOO");
			if (timeVariable != 0) {
				for (int i = 0; i < timeVariable; i++) {
					output.setCharAt(i, 'Y');
				}
			}
		}
		return output.toString();

	}
	
	/**
	 * Description: create pattern for top second row
	 * @param int second
	 * @return String Y/O 
	 */
	protected String getTopSecondRowPattern(int seconds) {
		if (seconds % 2 == 0)
			return "Y";
		else
			return "O";
	}

}
