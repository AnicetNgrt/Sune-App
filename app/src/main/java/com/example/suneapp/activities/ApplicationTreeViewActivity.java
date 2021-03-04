package com.example.suneapp.activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import com.example.suneapp.R;

import java.util.List;

import moe.leer.tree2view.TreeView;
import moe.leer.tree2view.module.DefaultTreeNode;

public class ApplicationTreeViewActivity extends AppCompatActivity {

    private static final String TAG = "TreeViewActivity";
    private DefaultTreeNode<String> root;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application_tree_view);
        initData();
        initView();

//        // run on android devices with max os version at 10
//        PackageManager packageManager = this.getApplicationContext().getPackageManager();
//        @SuppressLint("QueryPermissionsNeeded") List<ApplicationInfo> packages =  packageManager.getInstalledApplications(PackageManager.GET_META_DATA);
//        packages.forEach(app -> Log.d(TAG, "Package name:" + app.packageName));
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