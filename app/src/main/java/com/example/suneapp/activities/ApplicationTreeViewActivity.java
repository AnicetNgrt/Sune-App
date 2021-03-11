package com.example.suneapp.activities;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.suneapp.R;

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
        root = new DefaultTreeNode<String>("User application");
        root.addChild(new DefaultTreeNode<String>("com.google.gmail"));
        Log.d(TAG, "root's depth: " + root.getDepth());

        DefaultTreeNode<String> child2 = new DefaultTreeNode<>("com.facebook");
        //Important: after create a node your should immediately add it.
        root.addChild(child2);

        DefaultTreeNode<String> childA = new DefaultTreeNode<>("ChildA");
        child2.addChild(childA);
        child2.addChild(new DefaultTreeNode<>("ChildB"));
        child2.addChild(new DefaultTreeNode<>("ChildC"));


        Log.d(TAG, "childA's depth: " + childA.getDepth());
        Log.d(TAG, "child2's depth: " + child2.getDepth());
        root.addChild(new DefaultTreeNode<>("Child3"));
        root.addChild(new DefaultTreeNode<>("Child4"));
    }

    private void initView() {
        TreeView treeView = findViewById(R.id.tree_view);
        treeView.setRoot(root);
        treeView.setDefaultAnimation(true);
        treeView.requestLayout();
    }
}