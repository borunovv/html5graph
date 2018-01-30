package com.borunovv.html5.canvas;

import java.util.Arrays;
import java.util.List;

public class Font implements IRenderable {

    public static final String SANS_SERIF_8PX = "8px sans-serif";
    public static final String SANS_SERIF_10PX = "10px sans-serif";
    public static final String SANS_SERIF_12PX = "12px sans-serif";
    public static final String SANS_SERIF_16PX = "16px sans-serif";
    public static final String SANS_SERIF_24PX = "24px sans-serif";

    public Font() {
    }

    // Example: "bold 12px sans-serif"
    public Font(String font) {
       this.font = font;
    }

    public List<String> render() {
       return  Arrays.asList("context.font = \"" + font + "\";");
    }

    private String font = "12px sans-serif";
}
