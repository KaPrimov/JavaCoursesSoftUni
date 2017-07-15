package kermen.models.people;

import kermen.models.misc.Device;

import java.util.List;

public class Child  {
    private List<Device> toysAndFood;


    public Child( List<Device> toysAndFood) {
        this.toysAndFood = toysAndFood;
    }

    public double returnCosts() {
        return this.toysAndFood.stream().mapToDouble(Device::getCost).sum();
    }
}
