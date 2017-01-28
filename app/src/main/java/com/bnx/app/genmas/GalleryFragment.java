package com.bnx.app.genmas;


import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class GalleryFragment extends Fragment {

    ArrayList<ImageItem> objs = new ArrayList<>();
    FotoAdapter adapter;

    public GalleryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_gallery, container, false);

        GridView gridView = (GridView) view.findViewById(R.id.foto_gridView);
        adapter = new FotoAdapter(getContext());
        gridView.setAdapter(adapter);
        if (!objs.isEmpty()) {
            adapter.setData(objs);
        }else {
            new doTaskStart().execute("foto");
        }
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                ImageItem item = (ImageItem) parent.getItemAtPosition(position);

                FragmentManager manager = getActivity().getSupportFragmentManager();
//                ArsipFragment fragment = new ArsipFragment();
                Bundle args = new Bundle();
                ImageItem obj = objs.get(position);
                if (obj != null) {
                    args.putParcelableArrayList("foto_list", objs);
                    args.putInt("foto_posisi", position);
                }
//                FotoDetailFragment fragment = FotoDetailFragment.newInstance();
//                if (fragment != null) {
//                    fragment.setArguments(args);
//                    FragmentUtils.replaceFragment(manager, fragment, IConstants.TITLE_FOTO_DETAIL);
//                }
            }
        });

        return view;
    }

    private class doTaskStart extends AsyncTask<String, Void, ArrayList<ImageItem>> {
        ProgressDialog dialog;

        @Override
        protected ArrayList<ImageItem> doInBackground(String... str) {
            ArrayList<ImageItem> result = new ArrayList<>();
            List<String> names = AppUtils.getImageList(getContext(), str[0]);

            for (String name : names) {
                ImageItem obj = new ImageItem(AppUtils.getBitmapBytesFromAsset(getContext(), str[0] + "/" + name), name);
                result.add(obj);
            }

            return result;
        }

        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(ArrayList<ImageItem> result) {
            objs = result;
            dialog.dismiss();
            adapter.setData(objs);
        }

        @Override
        protected void onPreExecute () {
            dialog = ProgressDialog.show(getContext(), "", "Mengambil Data ...", true);
            dialog.setCancelable(false);
        }

        @Override
        protected void onProgressUpdate (Void...values){
        }
    }

}
