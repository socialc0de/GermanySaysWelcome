package com.github.socialc0de.gsw;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import com.github.socialc0de.gsw.fragments.DashboardFragment;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    private Drawer mDrawer;
    private ArrayList<Fragment> fragmentList = new ArrayList<Fragment>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Navigation Drawer Initializing

        //new DrawerBuilder().withActivity(this).build();

        ArrayList<Fragment> newFragmentItems = new ArrayList<Fragment>();
        newFragmentItems.add(new DashboardFragment());
        newFragmentItems.add(new DashboardFragment());

        fillDataIntoFragmentList(newFragmentItems);

        createNavigationDrawer(mDrawer);
        SpannableString s = new SpannableString(getString(R.string.app_name));
        s.setSpan(new com.github.socialc0de.gsw.TypefaceSpan(getApplicationContext(), "bebaskai.otf"), 0, s.length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        ((Toolbar) findViewById(R.id.app_bar)).setTitle(s);

        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragmentList.get(0)).commit();


    }

    private void fillDataIntoFragmentList(ArrayList<Fragment> fragmentAddList){
        for (int i = 0; i<fragmentAddList.size(); i++){
            this.fragmentList.add(fragmentAddList.get(i));
        }
    }

    private void createNavigationDrawer(Drawer mDrawer){
        if (mDrawer == null) {
            this.mDrawer = new DrawerBuilder()
                    .withActivity(this)
                    .withToolbar((Toolbar) findViewById(R.id.app_bar))
                    .withActionBarDrawerToggle(true)

                    .addDrawerItems(
                            new PrimaryDrawerItem().withName("DashboardFragment"),
                            new PrimaryDrawerItem().withName("DashboardFragment 2"))
                    .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                        @Override
                        public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                            // do something with the clicked item :D
                            getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                            Log.d("onItemClick called: ", "position: "+position);
                            getSupportFragmentManager().beginTransaction().replace(R.id.container, fragmentList.get(position)).addToBackStack(null).commit();
                            // closes Drawer
                            return false;
                        }
                    })
                    .build();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
