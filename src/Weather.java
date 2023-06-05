// data model
public class Weather {

    private double tempC;
    private double tempF;
    private String condition;
    private String iconURL;
    private String location;

    public Weather(double tempC, double tempF, String condition, String iconURL, String location) {
        this.tempC = tempC;
        this.tempF = tempF;
        this.condition = condition;
        this.iconURL = iconURL;
        this.location = location;
    }

    public double getTempC() {
        return tempC;

    }
    public double getTempF() {
        return tempF;
    }

    public String getCondition() {
        return condition;
    }

    public String getIconURL() {
        return iconURL;
    }

    public String getLocation() {
        return location;
    }
}
