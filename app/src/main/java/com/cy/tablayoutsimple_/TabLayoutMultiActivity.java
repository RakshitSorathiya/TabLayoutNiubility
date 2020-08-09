package com.cy.tablayoutsimple_;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

import com.cy.tablayoutniubility.BaseFragPageAdapterVp2;
import com.cy.tablayoutniubility.FragPageAdapterVp2;
import com.cy.tablayoutniubility.FragPageAdapterVp2NoScroll;
import com.cy.tablayoutniubility.ITabAdapter;
import com.cy.tablayoutniubility.IViewHolder;
import com.cy.tablayoutniubility.TabAdapter;
import com.cy.tablayoutniubility.TabAdapterNoScroll;
import com.cy.tablayoutniubility.TabLayoutMulti;
import com.cy.tablayoutniubility.TabMediatorMulti;
import com.cy.tablayoutniubility.TabMediatorVp2NoScroll;
import com.cy.tablayoutniubility.TabNoScrollViewHolder;
import com.cy.tablayoutniubility.TabViewHolder;

import java.util.ArrayList;
import java.util.List;

public class TabLayoutMultiActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_layout_multi);
        ViewPager2 viewPager2 = findViewById(R.id.view_pager);
        TabLayoutMulti tabLayoutMulti = findViewById(R.id.tablayout);
//        tabLayoutLine.setSpace_horizontal(0).setSpace_vertical(0);
        List<String> list = new ArrayList<>();
        list.add("关注");
        list.add("推荐");
        list.add("上课");
        list.add("抗疫");
        list.add("文化");
//        list.add("经济");
//        list.add("幸福里");

        //根据item个数设置是否需要滚动
        if (list.size() > 6) tabLayoutMulti.setScrollable(true);

        BaseFragPageAdapterVp2 fragmentPageAdapter;

        if (tabLayoutMulti.isScrollable()) {
            fragmentPageAdapter = new FragPageAdapterVp2<String>(this) {
                @Override
                public Fragment createFragment(String bean, int position) {
                    return FragmentTab2.newInstance(FragmentTab2.TAB_NAME2, getList_bean().get(position));
                }

                @Override
                public void bindDataToTab(TabViewHolder holder, int position, String bean, boolean isSelected) {
                    TextView textView = holder.getView(R.id.tv);
                    if (isSelected) {
                        textView.setTextColor(0xffe45540);
                        textView.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                    } else {
                        textView.setTextColor(0xff444444);
                        textView.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                    }
                    textView.setText(bean);
                }

                @Override
                public int getTabLayoutID(int position, String bean) {
                    return R.layout.item_tab_center;
                }
            };
        }else {
            fragmentPageAdapter = new FragPageAdapterVp2NoScroll<String>(this) {
                @Override
                public Fragment createFragment(String bean, int position) {
                    return FragmentTab2.newInstance(FragmentTab2.TAB_NAME2, getList_bean().get(position));
                }

                @Override
                public void bindDataToTab(TabNoScrollViewHolder holder, int position, String bean, boolean isSelected) {
                    LogUtils.log("bindDataToTab");
                    TextView textView = holder.getView(R.id.tv);
                    if (isSelected) {
                        textView.setTextColor(0xffe45540);
                        textView.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                    } else {
                        textView.setTextColor(0xff444444);
                        textView.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                    }
                    textView.setText(bean);
                }

                @Override
                public int getTabLayoutID(int position, String bean) {
                    return R.layout.item_tab_center;
                }
            };
        }


        ITabAdapter tabAdapter = new TabMediatorMulti<String>(tabLayoutMulti).setAdapter(viewPager2, fragmentPageAdapter);
        fragmentPageAdapter.add(list);
        tabAdapter.add(list);
        //或者
//        if(tabLayoutMulti.isScrollable()){
//            TabAdapter tabAdapt= (TabAdapter) tabAdapter.getAdapter();
//            tabAdapt.add(list);
//        }else {
//            TabAdapterNoScroll tabAdapt= (TabAdapterNoScroll) tabAdapter.getAdapter();
//            tabAdapt.add(list);
//        }
    }
}