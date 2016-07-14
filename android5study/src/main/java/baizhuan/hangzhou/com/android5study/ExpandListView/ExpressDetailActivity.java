package baizhuan.hangzhou.com.android5study.ExpandListView;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import baizhuan.hangzhou.com.android5study.BaseActivity;
import baizhuan.hangzhou.com.android5study.R;
import baizhuan.hangzhou.com.android5study.util.Const;

/**
 * Created by hb on 2016/7/7.
 */
public class ExpressDetailActivity extends BaseActivity {
    private Activity ac;
    private Toolbar mToolbar;
    private TextView title;
    private NoScrollExpandableListView mExpandableListView;
    private ExpressDetailAdapter mAdapter;
    List<String> groupList;
    List<String> childList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_express_detail);
        ac = this;
        initView();
    }

    private void initView() {

        mExpandableListView = (NoScrollExpandableListView) findViewById(R.id.detail_expand);
        groupList = Const.getList();
        childList = Const.getList2();

        mExpandableListView.setAdapter(mAdapter = new ExpressDetailAdapter());
        //设置默认全部展开状态
        for (int i = 0; i < groupList.size(); i++) {
            mExpandableListView.expandGroup(i);
        }

        //去掉箭头
        mExpandableListView.setGroupIndicator(null);
        //屏蔽掉group布局的关闭动作
//        mExpandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
//            @Override
//            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
//                return true;
//            }
//        });

    }

    class ExpressDetailAdapter extends BaseExpandableListAdapter {
        @Override
        public int getGroupCount() {
            return groupList.size();
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            return childList.size();
        }

        @Override
        public Object getGroup(int groupPosition) {
            return groupList.get(groupPosition);
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return childList.get(childPosition);
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

        class GroupHolder {
            ImageView iv_express_group_icon;
            TextView tv_express_group_num;
            TextView tv_express_group_name;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            GroupHolder groupHolder = null;
            if (convertView == null) {
                convertView = LayoutInflater.from(ac).inflate(R.layout.express_detail_group_item, null);
                groupHolder = new GroupHolder();
                groupHolder.iv_express_group_icon = (ImageView) convertView.findViewById(R.id.express_detail_shop_img);
                groupHolder.tv_express_group_num = (TextView) convertView.findViewById(R.id.express_detail_order_num);
                groupHolder.tv_express_group_name = (TextView) convertView.findViewById(R.id.express_detail_shop_name);
                convertView.setTag(groupHolder);
            } else {
                groupHolder = (GroupHolder) convertView.getTag();
            }
//            groupHolder.iv_express_group_icon.setImageURI(Uri.parse(mDetailBean.getData().getOrders().get(groupPosition).getShop_items().get(0).getShop_url()));
            groupHolder.tv_express_group_num.setText("订单号：" + groupList.get(groupPosition));
            groupHolder.tv_express_group_name.setText("*****" + groupList.get(groupPosition));

            return convertView;
        }

        class ChildHolder {
            ImageView iv_express_child_icon;
            TextView tv_express_child_name;
            TextView tv_express_child_rank;
            TextView tv_express_child_price;
            TextView tv_express_child_num;
        }

        @Override
        public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            ChildHolder childHolder = null;
            if (convertView == null) {
                convertView = LayoutInflater.from(ac).inflate(R.layout.express_detail_child_item, null);
                childHolder = new ChildHolder();
                childHolder.tv_express_child_name = (TextView) convertView.findViewById(R.id.child_goods_name);
                childHolder.tv_express_child_rank = (TextView) convertView.findViewById(R.id.child_goods_rank);
                childHolder.tv_express_child_price = (TextView) convertView.findViewById(R.id.child_goods_price);
                childHolder.tv_express_child_num = (TextView) convertView.findViewById(R.id.child_goods_num);
                convertView.setTag(childHolder);
            } else {
                childHolder = (ChildHolder) convertView.getTag();
            }
            childHolder.tv_express_child_name.setText(childList.get(childPosition));
            childHolder.tv_express_child_rank.setText(childList.get(childPosition));
            childHolder.tv_express_child_price.setText((childList.get(childPosition)));
            childHolder.tv_express_child_num.setText(childList.get(childPosition));
            return convertView;
        }

        @Override
        public boolean isChildSelectable(int i, int i1) {
            return false;
        }
    }
}
