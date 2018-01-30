package com.borunovv;

import com.borunovv.html5.HtmlDiagramm;

import java.io.FileWriter;

public class Main {

    public static void main(String[] args) throws Exception {
        HtmlDiagramm diagramm = new HtmlDiagramm(800, 200);
        for (int i = 0; i < 30; ++i) {
            double x = 0.1 * i * Math.PI * 2.0;
            double y = Math.sin(x) * Math.sin(x * x);
            diagramm.addPoint(x, y, "" + i);
        }
        String html = diagramm.render();
        System.out.println(html);

        FileWriter fileWriter = new FileWriter("test.html");
        fileWriter.write(html);
        fileWriter.close();
    }
}