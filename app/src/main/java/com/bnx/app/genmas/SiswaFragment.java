package com.bnx.app.genmas;


import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class SiswaFragment extends Fragment {

    TextView text;

    public SiswaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_siswa, container, false);

        text = (TextView) view.findViewById(R.id.content_uraian);
        new doTaskStart().execute("data/siswa.txt");

        return view;
    }

    private void setData(String content){
        Spanned spanned = Html.fromHtml(content);
        text.setText(spanned);
    }

    private class doTaskStart extends AsyncTask<String, Void, String> {
        ProgressDialog dialog;

        @Override
        protected String doInBackground(String... str) {
            return AppUtils.readFile(getContext(), str[0]);
        }

        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
            setData(result);
            dialog.dismiss();
        }

        @Override
        protected void onPreExecute() {
            dialog = ProgressDialog.show(getContext(), "", "Mengambil Data ...", true);
            dialog.setCancelable(false);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
        }
    }

}
