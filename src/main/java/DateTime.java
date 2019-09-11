/**
 * Class that converts date and time into an easy-to-read format
 */
public class DateTime {
    protected String day;
    protected String month;
    protected String year;
    protected String time;
    protected String period;

    /**
     * Initializes a DateTime object and converts the input String into the correct datetime format
     * @param input The datetime input in the format of "DD/MM/YYYY HHmm"
     */
    public DateTime(String input) {
        String[] months = {"January", "February", "March", "April", "May", "June", "July", "August",
                "September", "October", "November", "December"};
        String[] dateAndTime = input.split(" ", 2);
        String date = dateAndTime[0];
        String time = dateAndTime[1];
        String[] dayMonthYear = date.split("/", 3);
        switch(dayMonthYear[0]) {
            case "1":
                this.day = "1st";
                break;
            case "2":
                this.day = "2nd";
                break;
            case "3":
                this.day = "3rd";
                break;
            default:
                this.day = dayMonthYear[0] + "th";
                break;
        }
        int monthInt = Integer.parseInt(dayMonthYear[1]);
        this.month = months[monthInt-1];
        this.year = dayMonthYear[2];
        int timeInt = Integer.parseInt(time);
        if (timeInt >= 1200) {
            this.period = "pm";
        } else {
            this.period = "am";
        }
        int hour = (timeInt/100) % 12;
        if (hour == 0) {
            hour = 12;
        }
        int min = (timeInt % 100);
        this.time = hour + ":" + String.format("%02d", min);
    }

    @Override
    public String toString() {
        return day + " of " + month + " " + year + ", " + time + period;
    }
}
