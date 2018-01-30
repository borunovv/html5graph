package com.borunovv.html5.canvas;

import java.util.LinkedList;
import java.util.List;

public class Line extends CompaundRenderable {

    public Line moveTo(double x, double y) {
        addCommand("context.moveTo(" + x + ", " + y + ");");
        return this;
    }

    public Line lineTo(double x, double y) {
        addCommand("context.lineTo(" + x + ", " + y + ");");
        return this;
    }

    @Override
    public List<String> render() {
        List<String> commands = super.render();

        List<String> result = new LinkedList<String>(commands);
        result.add(0, "context.beginPath();");
        result.add("context.stroke();");

        return result;
    }
}
