package com.borunovv.html5.canvas;

import java.util.Arrays;
import java.util.List;

public class Color implements IRenderable {
    public Color() {
    }

    public Color(String hexColor) {
        set(hexColor);
    }

    public void set(String hexColor) {
        this.hexColor = hexColor;
    }

    public List<String> render() {
        String command = "context.strokeStyle = \"#" + hexColor + "\";";
        return Arrays.asList(command);
    }

    private String hexColor = "000000";
}
