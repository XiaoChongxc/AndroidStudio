package baizhuan.hangzhou.com.android5study.ExpandListView;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import java.util.List;

import baizhuan.hangzhou.com.android5study.R;
import baizhuan.hangzhou.com.android5study.util.Const;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * User:    Xiaoc
 * 项目名:  AndroidStudio2
 * Package: baizhuan.hangzhou.com.android5study.ExpandListView
 * Date:    2016-07-08
 * Time:    14:31
 * 类描述：
 */
public class ExpandListActivity extends Activity {
    @Bind(R.id.expand)
    ExpandableListView expand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expand_list);
        ButterKnife.bind(this);

        List<String> groupList = Const.getList();
        List<String> childList = Const.getList2();

        BaseExpandableListAdapter adapter = new MyExpandListAdapter(this, groupList, childList);
        expand.setAdapter(adapter);
    }


    class MyExpandListAdapter extends BaseExpandableListAdapter {
        List<String> groupList;
        List<String> childList;
        Context ctx;

        public MyExpandListAdapter(Context ctx, List<String> groupList, List<String> childList) {
            this.ctx = ctx;
            this.groupList = groupList;
            this.childList = childList;
        }

        @Override
        public int getGroupCount() {
            return groupList.size();
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            return childList.size();
        }

        @Override
        public String getGroup(int groupPosition) {
            return groupList.get(groupPosition);
        }

        @Override
        public String getChild(int groupPosition, int childPosition) {
            return childList.get(childPosition);
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return 0;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            View view = null;
            if (convertView == null) {
                view = LayoutInflater.from(ctx).inflate(android.R.layout.simple_list_item_1, parent, false);
            } else {
                view = convertView;
            }
            ((TextView) view).setText(groupList.get(groupPosition));
            return view;
        }

        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            View view = null;
            if (convertView == null) {
                view = LayoutInflater.from(ctx).inflate(android.R.layout.simple_list_item_1, parent, false);
            } else {
                view = convertView;
            }
            ((TextView) view).setText(childList.get(childPosition));
            return view;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return false;
        }
    }


}
