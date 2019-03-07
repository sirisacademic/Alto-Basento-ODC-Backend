package items;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Candidate implements Serializable {
    String name;
    Double percentage;

    public Candidate() {
    }

    public Candidate(String name) {
        this.name = name;
    }

    public Candidate(String name, Double percentage) {
        this.name = name;
        this.percentage = percentage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPercentage() {
        return percentage;
    }

    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Candidate candidate = (Candidate) o;
        return Objects.equals(name, candidate.name) &&
                Objects.equals(percentage, candidate.percentage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, percentage);
    }

    @Override
    public String toString() {
        return "Candidate{" +
                "name='" + name + '\'' +
                ", percentage=" + percentage +
                '}';
    }
}
