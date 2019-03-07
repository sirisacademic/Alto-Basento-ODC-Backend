package items;

import java.io.Serializable;

public class Period implements Serializable {
    String startDate;
    String endDate;
    String maximumExtent;
    Integer duration;

    public Period(String isoDate, String contractDuration) {
        this.startDate = isoDate;
        this.duration = Integer.valueOf(contractDuration);
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getMaximumExtent() {
        return maximumExtent;
    }

    public void setMaximumExtent(String maximumExtent) {
        this.maximumExtent = maximumExtent;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }
}
