package com.bnx.app.genmas;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Admin on 20/04/2016.
 */
public class FotoAdapter extends ArrayAdapter<ImageItem> {

    LayoutInflater inflater = null;
    Context context;
    FragmentActivity activity;

    public FotoAdapter(Context context) {
        super(context, R.layout.foto_grid_item_layout);
        this.context = context;
    }

    public void setData(List<ImageItem> data) {
        clear();
        if (data != null) {
            for (ImageItem appEntry : data) {
                add(appEntry);
            }
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.foto_grid_item_layout, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.foto = (ImageView) convertView.findViewById(R.id.foto_image);
            viewHolder.deskripsi = (TextView) convertView.findViewById(R.id.foto_deskripsi);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final ImageItem obj = getItem(position);

        if (obj.getImage() == null) {
            viewHolder.foto.setImageResource(R.drawable.ic_no_image);
        } else {
            viewHolder.foto.setImageBitmap(AppUtils.getBitmap(context, obj.getImage()));
        }
        viewHolder.deskripsi.setText(obj.getDeskripsi());

        return convertView;
    }

    private static class ViewHolder {
        ImageView foto;
        TextView deskripsi;
    }

}
