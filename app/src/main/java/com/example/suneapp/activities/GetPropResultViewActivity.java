package com.example.suneapp.activities;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.suneapp.R;
import com.example.suneapp.adapter.TreeViewAdapter;
import com.example.suneapp.model.LogApplication;
import com.example.suneapp.utils.shelltest.GetpropResultParser;
import com.example.suneapp.utils.shelltest.node.RecordNode;

import moe.leer.tree2view.TreeView;
import moe.leer.tree2view.module.DefaultTreeNode;

public class GetPropResultViewActivity extends AppCompatActivity {

    private static final String TAG = "TreeViewActivity";
    private DefaultTreeNode root;
    private LogApplication log;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application_tree_view);
        log = (LogApplication) getIntent().getSerializableExtra("logApplication");
        initData();
        initView();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void initData() {
        RecordNode rn = GetpropResultParser.parse(log.getData());

        root = rn.toTreeView(log.getId());


    }

    private void initView() {
        TreeView treeView = findViewById(R.id.tree_view);
        treeView.setRoot(root);
        treeView.setDefaultAnimation(true);
        treeView.requestLayout();
        treeView.setTreeAdapter(new TreeViewAdapter(this, root, R.layout.log_item_tree_view));
    }
}