package com.borunovv.html5.canvas;

public class Text extends CompaundRenderable {

    public Text() {
        align(Align.LEFT);
        baseline(Baseline.TOP);
    }

    public Text print(String text, double x, double y) {
        addCommand("context.fillText(\"" + text + "\", " + x + "," + y + ");");
        return this;
    }

    public Text print(String text, double x, double y, Align align, Baseline baseline) {
        align(align);
        baseline(baseline);
        addCommand("context.fillText(\"" + text + "\", " + x + ", " + y + ");");
        return this;
    }

    public Text baseline(Baseline baseLine) {
        addCommand("context.textBaseline = \""
                + (baseLine == Baseline.TOP ? "top" : "bottom")
                + "\";");
        return this;
    }

    public Text align(Align alignValue) {
        addCommand("context.textAlign = \""
                + (alignValue == Align.LEFT ?
                "left" :
                alignValue == Align.RIGHT ?
                        "right" :
                        "center")
                + "\";");
        return this;
    }

    public enum Align {LEFT, RIGHT, CENTER}
    public enum Baseline {TOP, BOTTOM}
}
