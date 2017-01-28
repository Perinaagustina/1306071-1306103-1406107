package com.bnx.app.genmas;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_home, container, false);

        TextView app = (TextView) view.findViewById(R.id.home_app_title);
        app.setText(getResources().getString(R.string.app_name));
        TextView motto = (TextView) view.findViewById(R.id.home_app_motto);
        motto.setText(getResources().getString(R.string.app_sub_name));

        TextView nick = (TextView) view.findViewById(R.id.home_company_nick);
        nick.setText(getResources().getString(R.string.company_name));
        TextView email = (TextView) view.findViewById(R.id.home_company_motto);
        email.setText(getResources().getString(R.string.semboyan));
        TextView name = (TextView) view.findViewById(R.id.home_company_name);
        name.setText(getResources().getString(R.string.company_sub_name));

        return view;
    }

}
