package com.borunovv.html5.canvas;

import java.util.Arrays;
import java.util.List;

public class FillStyle implements IRenderable {

    public FillStyle(String colorHex) {
       color(colorHex);
    }

    public void color(String colorHex) {
       this.color = colorHex;
    }

    public List<String> render() {
        return Arrays.asList("context.fillStyle = \'#" + color + "\'");
    }

    private String color = "000000"; // Black
}
