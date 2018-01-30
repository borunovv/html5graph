package com.borunovv.html5;

import com.borunovv.html5.canvas.CompaundRenderable;

public class FillRect extends CompaundRenderable {

    public FillRect draw(double left, double top, double width, double height) {
        addCommand("context.fillRect(" + left + ", " + top
                + ", " + width + ", " + height + ");");
        return this;
    }
}
