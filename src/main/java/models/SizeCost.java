package models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SizeCost {
    private String size;
    private double cost;

    public SizeCost(String size, double cost) {
        this.size = size;
        this.cost = cost;
    }

}
