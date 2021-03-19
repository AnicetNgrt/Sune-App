package com.example.suneapp.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.suneapp.R;

import moe.leer.tree2view.TreeUtils;
import moe.leer.tree2view.adapter.TreeAdapter;
import moe.leer.tree2view.module.DefaultTreeNode;

public class TreeViewAdapter extends TreeAdapter<String> {

    public TreeViewAdapter(Context context, DefaultTreeNode<String> root, int resourceId) {
        super(context, root, resourceId);
    }

    @Override
    public void toggle(Object... objects) {
    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (mNodesList == null) {
            mNodesList = TreeUtils.getVisibleNodesD(super.mRoot);
            notifyDataSetChanged();
        }
        DefaultTreeNode node = mNodesList.get(position);
        ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(mContext)
                    .inflate(mResourceId, parent, false);
            holder = new ViewHolder();
            holder.setName((TextView) convertView.findViewById(R.id.tree_view_item_name));
            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();

        int icon = R.drawable.ic_baseline_arrow_right_24;
        if (!node.isHasChildren())
            icon = R.drawable.ic_baseline_settings_24;

        holder.getName()
                .setCompoundDrawablesRelativeWithIntrinsicBounds(icon, 0, 0, 0);

        holder.getName().setText(node.getElement().toString());
        int depth = node.getDepth();
        //set view indent
        setPadding(holder.getName(), depth, -1);
        //toggle
        toggle(holder, node);
        return convertView;
    }


    static class ViewHolder {
        private TextView name;

        public TextView getName() {
            return name;
        }

        public void setName(TextView name) {
            this.name = name;
        }
    }
}
