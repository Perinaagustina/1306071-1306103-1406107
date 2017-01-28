package com.bnx.app.genmas;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if (savedInstanceState == null) {
            displayView(R.id.nav_home);
        }

        getSupportFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {

            @Override
            public void onBackStackChanged() {
                FragmentManager fm = getSupportFragmentManager();
                Fragment f = fm.findFragmentById(R.id.content_frame);
                if (f != null) {
                    updateTitleAndDrawer(MainActivity.this, navigationView, f);
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
                finish();
            } else {
                super.onBackPressed();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
            FragmentManager manager = getSupportFragmentManager();
            AboutFragment fragment = new AboutFragment();
            if (fragment != null) {
                replaceFragment(manager, fragment, getResources().getString(R.string.action_settings));
            }

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        displayView(id);

        return true;
    }


    public void displayView(int viewId) {
        Fragment fragment = null;
        String title = getString(R.string.app_name);

        if (viewId == R.id.nav_home) {
            fragment = new HomeFragment();
            title = getResources().getString(R.string.menu_beranda);
            navigationView.setCheckedItem(R.id.nav_home);
        } else if (viewId == R.id.nav_profil) {
            fragment = new ProfileFragment();
            title = getResources().getString(R.string.menu_profil);
            navigationView.setCheckedItem(R.id.nav_profil);
        } else if (viewId == R.id.nav_guru) {
            fragment = new PengajarFragment();
            title = getResources().getString(R.string.menu_pengajar);
            navigationView.setCheckedItem(R.id.nav_guru);
        } else if (viewId == R.id.nav_adm) {
            fragment = new AdministrasiFragment();
            title = getResources().getString(R.string.menu_adm);
            navigationView.setCheckedItem(R.id.nav_adm);
        } else if (viewId == R.id.nav_siswa) {
            fragment = new SiswaFragment();
            title = getResources().getString(R.string.menu_siswa);
            navigationView.setCheckedItem(R.id.nav_siswa);
        } else if (viewId == R.id.nav_galery) {
            fragment = new GalleryFragment();
            title = getResources().getString(R.string.menu_gallery);
            navigationView.setCheckedItem(R.id.nav_galery);
        }

        if (fragment != null) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
            FragmentManager manager = getSupportFragmentManager();
            replaceFragment(manager, fragment, title);
        }
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }

    private void replaceFragment(FragmentManager manager, Fragment fragment, String title) {
//        String backStateName =  fragment.getClass().getName();
        String backStateName = title;
        String fragmentTag = backStateName;

        boolean fragmentPopped = manager.popBackStackImmediate(backStateName, 0);

        if (!fragmentPopped && manager.findFragmentByTag(fragmentTag) == null) { //fragment not in back stack, create it.
            FragmentTransaction ft = manager.beginTransaction();
            ft.replace(R.id.content_frame, fragment, fragmentTag);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ft.addToBackStack(backStateName);
            ft.commit();
        }
    }

    public static void updateTitleAndDrawer(AppCompatActivity activity, NavigationView navigationView, Fragment fragment) {
        String fragClassName = fragment.getClass().getName();

        String title = "";
        if (fragClassName.equals(HomeFragment.class.getName())) {
            title = activity.getResources().getString(R.string.menu_beranda);
            navigationView.setCheckedItem(R.id.nav_home);
        } else if (fragClassName.equals(ProfileFragment.class.getName())) {
            title = activity.getResources().getString(R.string.menu_profil);
            navigationView.setCheckedItem(R.id.nav_profil);
        } else if (fragClassName.equals(PengajarFragment.class.getName())) {
            title = activity.getResources().getString(R.string.menu_pengajar);
            navigationView.setCheckedItem(R.id.nav_guru);
        } else if (fragClassName.equals(AdministrasiFragment.class.getName())) {
            title = activity.getResources().getString(R.string.menu_adm);
            navigationView.setCheckedItem(R.id.nav_adm);
        } else if (fragClassName.equals(SiswaFragment.class.getName())) {
            title = activity.getResources().getString(R.string.menu_siswa);
            navigationView.setCheckedItem(R.id.nav_siswa);
        } else if (fragClassName.equals(GalleryFragment.class.getName())) {
            title = activity.getResources().getString(R.string.menu_gallery);
            navigationView.setCheckedItem(R.id.nav_galery);
        }
        if (activity.getSupportActionBar() != null) {
            activity.getSupportActionBar().setTitle(title);
        }

        DrawerLayout drawer = (DrawerLayout) activity.findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }
}
