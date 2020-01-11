package adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import ui.fragments.PopularFragment;
import ui.fragments.AllFragment;
import ui.fragments.NewFragment;

public class MainPagerAdapter extends FragmentStatePagerAdapter {

    private static final int TAB_COUNT = 3;
    private String[] tabTitles = {"Popular", "New", "All"};

    public MainPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        Fragment frag = null;
        switch (position) {
            case 0:
                frag = PopularFragment.newInstance(tabTitles[position]);
                break;
            case 1:
                frag = NewFragment.newInstance(tabTitles[position]);
                break;
            case 2:
                frag = AllFragment.newInstance(tabTitles[position]);
                break;

        }
        return frag;
    }

    @Override
    public int getCount() {
        return TAB_COUNT;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
