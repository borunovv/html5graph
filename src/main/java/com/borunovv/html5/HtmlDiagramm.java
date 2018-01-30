package com.borunovv.html5;

import com.borunovv.html5.canvas.Canvas;
import com.borunovv.html5.canvas.Font;
import com.borunovv.html5.canvas.Line;
import com.borunovv.html5.canvas.Text;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class HtmlDiagramm {

    public HtmlDiagramm(int width, int height) {
        canvas = new Canvas("myCanvas", width, height);
    }

    public void addPoint(double x, double y, String text) {
        points.add(new AxisPoint(x, y, text));
    }

    public void addPoint(double x, double y) {
        points.add(new AxisPoint(x, y));
    }

    public String render() {
        canvas.clear();
        boundBox = getBoundBox();

        List<Point> mappedPoints = calcMappedPoints();

        drawAxis(mappedPoints);
        drawMedian();
        drawPoints(mappedPoints);
        drawText(mappedPoints);

        return toHtml();
    }

    private void drawAxis(List<Point> mappedPoints) {
        canvas.color("222255");

        canvas.line().moveTo(0, 0)
                .lineTo(0, canvas.getHeight())
                .lineTo(canvas.getWidth(), canvas.getHeight());

        canvas.color("EEEECC");

        canvas.font(Font.SANS_SERIF_8PX).fillStyle("8888AA");
        Text text = canvas.text();

        Iterator<AxisPoint> origPointIter = points.iterator();
        int index = 0;
        for (Point p : mappedPoints) {
            if (index > 0) {
                canvas.line().moveTo(p.x, p.y).lineTo(p.x, canvas.getHeight() - 1);
            }

            AxisPoint ap = origPointIter.next();
            String tip = ap.text.isEmpty() ?
                    formatValue(ap.pt.x) :
                    ap.text;

            Text.Align align = index == 0 ? Text.Align.LEFT :
                    index == points.size() - 1 ?
                            Text.Align.RIGHT :
                            Text.Align.CENTER;
            text.print(tip, p.x, canvas.getHeight() - 2, align, Text.Baseline.BOTTOM);

            index++;
        }
    }

    private void drawMedian() {
        if (points.isEmpty()) return;

        double median = 0;
        for (AxisPoint p : points) {
            median += p.pt.y;
        }
        median /= points.size();
        double mappedMedian = map(canvas.getHeight(), boundBox.min.y, boundBox.max.y, median);

        canvas.color("AACCAA");
        canvas.line().moveTo(1, mappedMedian).lineTo(canvas.getWidth(), mappedMedian);
        canvas.fillStyle("00AA00").font(Font.SANS_SERIF_10PX)
                .text().print(formatValue(median), 1, mappedMedian + 1, Text.Align.LEFT, Text.Baseline.TOP);
    }

    private void drawPoints(List<Point> mappedPoints) {
        canvas.color("EE5522").fillStyle("EE5522");

        Line line = canvas.line();
        FillRect fillRect = canvas.fillRect();

        boolean started = false;
        for (Point p : mappedPoints) {
            if (!started) {
                line.moveTo(p.x, p.y);
                started = true;
            } else {
                line.lineTo(p.x, p.y);
            }
            fillRect.draw(p.x - 1, p.y - 1, 3, 3);
        }
    }

    private void drawText(List<Point> mappedPoints) {
        canvas.font(Font.SANS_SERIF_10PX).fillStyle("000055");
        Text text = canvas.text();

        Iterator<AxisPoint> origPointIter = points.iterator();
        for (Point p : mappedPoints) {
            double value = origPointIter.next().pt.y;
            Text.Align align = (p.x < canvas.getWidth() / 2) ? Text.Align.LEFT : Text.Align.RIGHT;
            Text.Baseline baseline = (p.y < canvas.getHeight() / 2) ? Text.Baseline.TOP : Text.Baseline.BOTTOM;

            text.print(formatValue(value), p.x, p.y, align, baseline);
        }
    }

    private String formatValue(double value) {
        return String.format("%.2f", value);
    }

    private List<Point> calcMappedPoints() {
        final double W = canvas.getWidth();
        final double H = canvas.getHeight();

        List<Point> res = new ArrayList<Point>(points.size());

        for (AxisPoint p : points) {
            double x = map(W, boundBox.min.x, boundBox.max.x, p.pt.x);
            double y = H - map(H, boundBox.min.y, boundBox.max.y, p.pt.y);
            res.add(new Point(x, y));
        }

        return res;
    }

    private double map(double windowSize, double min, double max, double value) {
        return (value - min) / (max - min) * windowSize;
    }

    private BoundBox getBoundBox() {
        BoundBox bb = new BoundBox();

        for (AxisPoint p : points) {
            if (p.pt.x < bb.min.x) bb.min.x = p.pt.x;
            if (p.pt.y < bb.min.y) bb.min.y = p.pt.y;
            if (p.pt.x > bb.max.x) bb.max.x = p.pt.x;
            if (p.pt.y > bb.max.y) bb.max.y = p.pt.y;
        }
        return bb;
    }

    private String toHtml() {
        Script script = new Script();
        script.addCanvas(canvas);
        SimpleHtmlPage page = new SimpleHtmlPage();
        String body = "<canvas id=\"myCanvas\"></canvas>\r\n";
        return page.render(script, body);
    }

    private static class AxisPoint {
        public Point pt = new Point(0, 0);
        public String text = "";

        public AxisPoint(double x, double y) {
            this(x, y, null);
        }

        public AxisPoint(double x, double y, String text) {
            this.pt.x = x;
            this.pt.y = y;
            this.text = (text == null) ? "" : text;
        }
    }

    private static class Point {
        public double x;
        public double y;

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    private static class BoundBox {
        public Point min = new Point(Double.MAX_VALUE, Double.MAX_VALUE);
        public Point max = new Point(Double.MIN_VALUE, Double.MIN_VALUE);
    }

    private List<AxisPoint> points = new LinkedList<AxisPoint>();
    private BoundBox boundBox;
    private Canvas canvas;
}
