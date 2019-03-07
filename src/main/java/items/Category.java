package items;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Category implements Serializable {
    String code;
    String name;
    Value cost;

    public Category() {
    }

    public Category(String code) {
        this.code = code;
    }

    public Category(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public Category(String code, String name, Value cost) {
        this.code = code;
        this.name = name;
        this.cost = cost;
    }

    public Category(String code, Value cost) {
        this.code = code;
        this.cost = cost;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Value getCost() {
        return cost;
    }

    public void setCost(Value cost) {
        this.cost = cost;
    }
}
