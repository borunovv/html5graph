package com.borunovv.html5;

import com.borunovv.html5.canvas.Canvas;

import java.util.LinkedList;
import java.util.List;

public class Script {

    public void addCanvas(Canvas c) {
        canvasList.add(c);
    }

    public void render(StringBuilder sb) {
        renderHeader(sb);
        renderBody(sb);
        renderFooter(sb);
    }

    private void renderBody(StringBuilder sb) {
        renderOnLoadFunction(sb);
        renderSubFunctions(sb);
    }

    private void renderSubFunctions(StringBuilder sb) {
        for (Canvas c: canvasList) {
            c.render(sb);
        }
    }

    private void renderOnLoadFunction(StringBuilder sb) {
        sb.append("window.onload = function() {\n");
        for (Canvas c: canvasList) {
            sb.append("\t").append(c.getFuncName()).append("();\n");
        }
        sb.append("}\n");
    }

    private void renderHeader(StringBuilder sb) {
        sb.append("<script>\n");
    }

    private void renderFooter(StringBuilder sb) {
        sb.append("</script>\n");
    }

    private List<Canvas> canvasList = new LinkedList<Canvas>();
}
