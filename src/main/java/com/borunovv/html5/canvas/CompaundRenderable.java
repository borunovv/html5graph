package com.borunovv.html5.canvas;

import java.util.LinkedList;
import java.util.List;

public class CompaundRenderable implements IRenderable {

    public List<String> render() {
        return commands;
    }

    protected void addCommand(String cmd) {
        commands.add(cmd);
    }

    private List<String> commands = new LinkedList<String>();
}
