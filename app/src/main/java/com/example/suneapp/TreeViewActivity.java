package com.example.suneapp;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import moe.leer.tree2view.TreeView;
import moe.leer.tree2view.module.DefaultTreeNode;

public class TreeViewActivity extends AppCompatActivity {

    private static final String TAG = "TreeViewActivity";
    private DefaultTreeNode<String> root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tree_view);
        initData();
        initView();
    }

    private void initData() {
        root = new DefaultTreeNode<String>("Root");
        root.addChild(new DefaultTreeNode<String>("Child1"));
        Log.d(TAG, "root's depth: " + root.getDepth());

        DefaultTreeNode<String> child2 = new DefaultTreeNode<String>("Child2");
        //Important: after create a node your should immediately add it.
        root.addChild(child2);

        DefaultTreeNode<String> childA = new DefaultTreeNode<String>("ChildA");
        child2.addChild(childA);
        child2.addChild(new DefaultTreeNode<String>("ChildB"));
        child2.addChild(new DefaultTreeNode<String>("ChildC"));


        Log.d(TAG, "childA's depth: " + childA.getDepth());
        Log.d(TAG, "child2's depth: " + child2.getDepth());
        root.addChild(new DefaultTreeNode<String>("Child3"));
        root.addChild(new DefaultTreeNode<String>("Child4"));
    }

    private void initView() {
        TreeView treeView = findViewById(R.id.tree_view);
        treeView.setRoot(root);
        treeView.setDefaultAnimation(true);
        treeView.requestLayout();
    }

}