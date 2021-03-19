package com.example.suneapp.utils.shelltest.node;

import com.example.suneapp.utils.shelltest.node.exceptions.NodeException;

import java.util.HashMap;
import java.util.Map;

import moe.leer.tree2view.module.DefaultTreeNode;

public class RecordNode {
    private static HashMap<String, RecordNode> trees;

    private HashMap<String, RecordNode> leaves;
    private RecordNode father;
    private String name;

    private String data;

    public RecordNode(String name, RecordNode father) {
        leaves = new HashMap<>();
        this.name = name;
        this.father = father;
    }

    public static RecordNode getInstance(String name) {
        if (trees == null) {
            trees = new HashMap<>();
        }
        if (!trees.containsKey(name)) {
            trees.put(name, new RecordNode(name, null));
        }
        return trees.get(name);
    }

    private static String decorativeTabs(int deepness) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < deepness - 1; i++) {
            res.append("    ");
        }
        res.append("   ⊢");
        return res.toString();
    }

    public RecordNode add(String path) {
        String[] tabParse = path.split("\\.");
        if (leaves.containsKey(tabParse[0])) {
            if (tabParse.length > 1) {
                return leaves.get(tabParse[0]).add(path.substring(tabParse[0].length() + 1));
            } else {
                return null;
            }
        }
        RecordNode newChild = new RecordNode(tabParse[0], this);
        leaves.put(tabParse[0], newChild);
        if (tabParse.length > 1) {
            return leaves.get(tabParse[0]).add(path.substring(tabParse[0].length() + 1));
        } else {
            return newChild;
        }
    }

    /**
     * retourne le noeuds désignié par le path. Vous pouvez obtenir les sous noeuds
     * avec le charactère '.'.
     *
     * @param path Le chemin du noeud
     * @return Le noeud désignié par le path
     * @throws NodeException dans le cas ou le noeud n'existe pas.
     */
    public RecordNode get(String path) throws NodeException {
        String[] tabParse;
        if (path.contains(".")) {
            tabParse = path.split("\\.");
        } else {
            tabParse = new String[]{path};
        }
        if (!leaves.containsKey(tabParse[0])) {
            throw new NodeException("Node not found");
        }
        if (tabParse.length > 1) {
            return leaves.get(tabParse[0]).get(path.substring(tabParse[0].length() + 1));
        } else {
            return leaves.get(path);
        }
    }

    public RecordNode getRecursive(String path) throws NodeException {
        String[] tabParse;
        if (path.contains(".")) {
            tabParse = path.split("\\.");
        } else {
            tabParse = new String[]{path};
        }
        if (leaves.size() == 0) {
            throw new NodeException("Node not found");
        }
        if (leaves.containsKey(tabParse[0])) {
            try {
                return this.get(path);
            } catch (NodeException ignored) {
            }
        }
        for (Map.Entry<String, RecordNode> entry : leaves.entrySet()) {
            try {
                return entry.getValue().getRecursive(path);
            } catch (NodeException ignored) {
            }
        }
        throw new NodeException("Node not found");
    }

    /**
     * Donne le chemin du noeud a partir de la racine.
     *
     * @return le path sous forme de String
     */
    public String getName() {
        if (this.father == null) {
            return name;
        }
        return father.getName() + "." + name;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getNodeName() {
        String[] nameParts = getName().split("\\.");
        return nameParts[nameParts.length - 1];
    }

    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(getName()).append(": ").append(getData()).append("\n");
        for (String key : leaves.keySet()) {
            res.append(leaves.get(key).toStringDeep(1)).append("\n");
        }
        return res.toString();
    }

    private String toStringDeep(int deepness) {
        StringBuilder res = new StringBuilder();
        res.append(decorativeTabs(deepness));
        res.append(getNodeName()).append(": ").append(getData()).append("\n");
        for (String key : leaves.keySet()) {
            res.append(leaves.get(key).toStringDeep(deepness + 1)).append("\n");
        }
        return res.toString();
    }

    public DefaultTreeNode toTreeView(String id) {
        DefaultTreeNode<String> dtn = new DefaultTreeNode<String>(id);
        for (String key : leaves.keySet()) {
            leaves.get(key).toTreeViewRec(dtn);
        }
        return dtn;
    }

    private void toTreeViewRec(DefaultTreeNode parent) {
        String dataStr = "";
        if (getData() != null) {
            dataStr = ": " + getData();
        }
        DefaultTreeNode self = new DefaultTreeNode(getNodeName() + dataStr);
        parent.addChild(self);
        for (String key : leaves.keySet()) {
            leaves.get(key).toTreeViewRec(self);
        }
    }
}
