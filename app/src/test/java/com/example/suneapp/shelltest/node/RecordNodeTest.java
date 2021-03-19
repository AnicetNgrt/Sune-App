package com.example.suneapp.shelltest.node;

import com.example.suneapp.utils.shelltest.node.RecordNode;
import com.example.suneapp.utils.shelltest.node.exceptions.NodeException;

import org.junit.Assert;
import org.junit.Test;

public class RecordNodeTest {

    @Test
    public void testGetName() {
        String path = "super.salut.ca marche";
        String base = "base";
        RecordNode node = RecordNode.getInstance(base);
        node.add(path);
        try {
            Assert.assertEquals(base + "." + path, node.get(path).getName());
            Assert.assertEquals(base + "." + path, node.getRecursive("ca marche").getName());
        } catch (NodeException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDataStorage() {
        String salut = "salut";
        RecordNode node = RecordNode.getInstance("base");
        node.add("node1.oui");
        try {
            node.getRecursive("oui").setData(salut);
            Assert.assertEquals(salut, node.get("node1.oui").getData());
        } catch (NodeException e) {
            e.printStackTrace();
        }
    }
}