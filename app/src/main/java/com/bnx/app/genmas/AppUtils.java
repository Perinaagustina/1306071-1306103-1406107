package com.bnx.app.genmas;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Admin on 15/04/2016.
 */
public class AppUtils {

    public static void showToastFragment(final Context context, final String message){
        Handler h = new Handler(Looper.getMainLooper());
        h.post(new Runnable() {
            public void run() {
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static void showToast(final Context context, final String message){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static void showAlertDialog(Context context, String title, String message, Boolean status) {
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();

        // Setting Dialog Title
        alertDialog.setTitle(title);

        // Setting Dialog Message
        alertDialog.setMessage(message);

        // Setting alert dialog icon
//        alertDialog.setIcon((status) ? R.drawable.success : R.drawable.fail);

        // Setting OK Button
        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        // Showing Alert Message
        alertDialog.show();
    }

    public static String readFile(Context context, String fileName){
//        ProgressDialog dialog = ProgressDialog.show(context, "", "Mengambil Data ...", true);
//        dialog.setCancelable(false);
        String result = "";
        AssetManager am = context.getAssets();
        try {
            StringBuilder text = new StringBuilder();
            InputStream inputStream = am.open(fileName);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            String line;
            while ((line = reader.readLine()) != null) {
//                if(line.contains("[")){
//                    line = "<larger><b>" + line.substring(line.indexOf("[") + 1, line.indexOf("]")) + "</b></larger>";
//                }
//                line = line.replaceAll("-", "\u002D");
                text.append(line);
                text.append("\n");
            }
//            text.append("<br>");
            reader.close();
            result = text.toString();

            //InputStream is = context.getResources().openRawResource(R.raw.test);//test.txt
        } catch (IOException e) {
            e.printStackTrace();
        }
//        dialog.dismiss();
        return result;
    }

    public static List<String> getFilesList(Context context, String path){
        String [] list;
        List<String> result = new ArrayList<>();
        try {
            list = context.getAssets().list(path);
            if (list.length > 0) {
                for (String file : list) {
                    if(file.lastIndexOf('.') != -1) {
                        file = file.substring(0, file.lastIndexOf('.'));
//                        Log.i("XX_FILES", file);
                        result.add(file);
                    }
                }
            }
        } catch (IOException e) {
        }
        Collections.sort(result, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                int indexOf = s1.indexOf(".");
                String substring = s1.substring(0, indexOf);
                int indexOf1 = s2.indexOf(".");
                String substring1 = s2.substring(0, indexOf1);
                return Integer.valueOf(substring) - Integer.valueOf(substring1);
            }
        });
        return result;
    }

    public static void sortName(List<String> objs, final String delimiter){
        Collections.sort(objs, new Comparator<String>() {
            @Override public int compare(String p1, String p2) {
                int indexOf = p1.indexOf(delimiter);
                String substring = p1.substring(0, indexOf-1);
                int indexOf1 = p2.indexOf(delimiter);
                String substring1 = p2.substring(0, indexOf1-1);
                return Integer.valueOf(substring) - Integer.valueOf(substring1);
            }

        });
    }

    public static List<String> getImageList(Context context, String path){
        String [] list;
        List<String> result = new ArrayList<>();
        try {
            list = context.getAssets().list(path);
            if (list.length > 0) {
                for (String file : list) {
                    result.add(file);
                }
            }
        } catch (IOException e) {
        }
//        Collections.sort(result, new Comparator<String>() {
//            @Override
//            public int compare(String s1, String s2) {
//                int indexOf = s1.indexOf(".");
//                String substring = s1.substring(0, indexOf);
//                int indexOf1 = s2.indexOf(".");
//                String substring1 = s2.substring(0, indexOf1);
//                return Integer.valueOf(substring) - Integer.valueOf(substring1);
//            }
//        });
        return result;
    }

    public static Bitmap getBitmapFromAsset(Context context, String strName)
    {
        AssetManager assetManager = context.getAssets();
        InputStream istr = null;
        try {
            istr = assetManager.open(strName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Bitmap bitmap = BitmapFactory.decodeStream(istr);
        return bitmap;
    }

    public static Bitmap getBitmap(Context context, String fileName){
        if(fileName == null){
            return BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_no_image);
        }
        Bitmap bitmap = BitmapFactory.decodeFile(fileName);
        if(bitmap == null){
            return BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_no_image);
        }
        return bitmap;
    }

    public static Bitmap getBitmap(Context context, byte[] bitmapdata){
        if(bitmapdata == null){
            return BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_no_image);
        }
        return BitmapFactory.decodeByteArray(bitmapdata, 0, bitmapdata.length);
    }
    public static byte[] getBitmapBytes(Bitmap bitmapdata) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmapdata.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }

    public static byte[] getBitmapBytesFromAsset(Context context, String strName)
    {
        AssetManager assetManager = context.getAssets();
        byte[] fileBytes = new byte[0];
        try {
            InputStream istr = assetManager.open(strName);
            fileBytes = new byte[istr.available()];
            istr.read( fileBytes);
            istr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileBytes;
    }

}
