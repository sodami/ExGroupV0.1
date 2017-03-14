package com.google.slashb410.exgroup.ui.group.room;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.google.slashb410.exgroup.ui.group.room.tabs.GroupCalendarFragment;
import com.google.slashb410.exgroup.ui.group.room.tabs.GroupShotsFragment;
import com.google.slashb410.exgroup.ui.group.room.tabs.GroupGraphFragment;
import com.google.slashb410.exgroup.ui.group.room.tabs.GroupRankFragment;

/**
 * Created by Tacademy on 2017-02-02.
 */

public class TabPagerAdapter extends FragmentStatePagerAdapter {
    // Count number of tabs
    private int tabCount;

    public TabPagerAdapter(FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount = tabCount;
    }

    @Override
    public Fragment getItem(int position) {

        // Returning the current tabs
        switch (position) {
            case 0:
                GroupRankFragment groupRankFragment = new GroupRankFragment();
                return groupRankFragment;
            case 1:
                GroupGraphFragment groupGraphFragment = new GroupGraphFragment();
                return groupGraphFragment;
            case 2:
                GroupShotsFragment groupShotsFragment = new GroupShotsFragment();
                return groupShotsFragment;
            case 3:
                GroupCalendarFragment groupCalendarFragment = new GroupCalendarFragment();
                return groupCalendarFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }

}
