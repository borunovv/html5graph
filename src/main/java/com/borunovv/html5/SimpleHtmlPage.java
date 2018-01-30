package com.borunovv.html5;

public class SimpleHtmlPage {

    public String render(Script script, String body) {
        StringBuilder sb = new StringBuilder();
        renderHead(sb, script);
        renderBody(sb, body);
        return sb.toString();
    }

    private void renderHead(StringBuilder sb, Script script) {
        sb.append("<!DOCTYPE HTML>\r\n");
        sb.append("<html>\r\n");
        sb.append("<head>\r\n");
        script.render(sb);
        sb.append("</head>");
    }

    private void renderBody(StringBuilder sb, String body) {
        sb.append("<body>\r\n");
        sb.append(body);
        sb.append("</body>\r\n</html>");
    }
}
