package com.kinesioapp.kinesioapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

public class ExpandableListAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<String> listDataHeader;
    private HashMap<String,List<String>> listHashMap;

    public ExpandableListAdapter(Context context, List<String> listDataHeader, HashMap<String, List<String>> listHashMap) {
        this.context = context;
        this.listDataHeader = listDataHeader;
        this.listHashMap = listHashMap;
    }

    @Override
    public int getGroupCount() {
        return listDataHeader.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return listHashMap.get(listDataHeader.get(i)).size();
    }

    @Override
    public Object getGroup(int i) {
        return listDataHeader.get(i);
    }

    @Override
    public Object getChild(int groupItem, int childItem) {
        return listHashMap.get(listDataHeader.get(groupItem)).get(childItem);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean isExpanded, View view, ViewGroup viewGroup) {
        String headerTitle = (String)getGroup(i);
        if(view == null) {
            LayoutInflater inflater = (LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.drawer_list_group,null);
        }
        TextView drawerGroupItem = (TextView) view.findViewById(R.id.drawer_group_item);
        drawerGroupItem.setText(headerTitle);
        setIndicator(view, isExpanded, getChildrenCount(i));
        setGroupIconIfNotSet((ImageView) view.findViewById(R.id.group_icon), i);
        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean isExpanded, View view, ViewGroup viewGroup) {
        final String childText = (String)getChild(i,i1);
        if(view == null) {
            LayoutInflater inflater = (LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.drawer_child_item,null);
        }
        TextView drawerChildItem = (TextView) view.findViewById(R.id.drawer_child_item);
        drawerChildItem.setText(childText);
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }

    private void setIndicator(View view, boolean expanded, int childrenCount) {
        ImageView indicator = (ImageView) view.findViewById(R.id.indicator);
        indicator.setVisibility(childrenCount == 0 ? View.INVISIBLE : View.VISIBLE);
        indicator.setImageResource(expanded ? R.drawable.ic_keyboard_arrow_up_black_24dp : R.drawable.ic_keyboard_arrow_down_black_24dp);
    }

    private void setGroupIconIfNotSet(ImageView groupIcon, int i) {
        //TODO: check if already set
        if (i == 0) {
            groupIcon.setImageResource(R.drawable.ic_home_black_24dp);
        } else {
            groupIcon.setImageResource(R.drawable.ic_star_border_black_24dp);
        }
    }
}