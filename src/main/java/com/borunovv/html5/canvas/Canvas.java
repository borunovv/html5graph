package com.borunovv.html5.canvas;

import com.borunovv.html5.FillRect;
import com.borunovv.html5.canvas.util.ListUtils;

import java.util.LinkedList;
import java.util.List;

public class Canvas {

    private String id;
    private final int width;
    private final int height;

    public Canvas(String id, int width, int height) {
        this.id = id;
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String getFuncName() {
        return "draw_" + id;
    }

    public void clear() {
        drawers.clear();
    }

    public Line line() {
        Line line = new Line();
        drawers.add(line);
        return line;
    }

    public FillRect fillRect() {
        FillRect rect = new FillRect();
        drawers.add(rect);
        return rect;
    }

    public Canvas color(String hexColor) {
        drawers.add(new Color(hexColor));
        return this;
    }

    public Canvas font(String font) {
        drawers.add(new Font(font));
        return this;
    }

    public Canvas fillStyle(String colorHex) {
        drawers.add(new FillStyle(colorHex));
        return this;
    }

    public Text text() {
        Text text = new Text();
        drawers.add(text);
        return text;
    }

    public void render(StringBuilder sb) {
        renderHeader(sb);
        renderCommands(sb);
        renderFooter(sb);
    }

    private void renderHeader(StringBuilder sb) {
        sb.append("function " + getFuncName() + "() {\n" +
                "\tvar canvas = document.getElementById(\"" + id + "\");\n" +
                "\tvar context = canvas.getContext(\"2d\");\n" +
                "\tcanvas.width = " + width + ";\n" +
                "\tcanvas.height = " + height + ";\n");
    }

    private void renderCommands(StringBuilder sb) {
        //sb.append("\tcontext.beginPath();").append(LN_DELIM);
        for (IRenderable drawer : drawers) {
            ListUtils.toString(sb, drawer.render(), "\t", LN_DELIM);
        }
    }

    private void renderFooter(StringBuilder sb) {
        sb.append("};\n");
    }

    private List<IRenderable> drawers = new LinkedList<IRenderable>();
    private final static String LN_DELIM = "\r\n";
}
