package com.dev.controletiro.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dev.controletiro.R;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class Util {

    public static String converteData(Long selection){
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("America/Sao_Paulo"));
        calendar.setTimeInMillis(selection);
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        return format.format(calendar.getTime());
    }

    public static void showMsgToast(Activity activity, String txt) {
        LayoutInflater inflater = activity.getLayoutInflater();
        View lytToast = inflater.inflate(R.layout.toast_template, (ViewGroup) activity.findViewById(R.id.lytToast));
        TextView txtToast = (TextView) lytToast.findViewById(R.id.txtToast);
        txtToast.setText(txt);

        Toast toast = new Toast(activity);
        toast.setView(lytToast);
        toast.show();
    }

    public static void showMsgConfirm(final Activity activity, String titulo, String txt, DialogInterface.OnClickListener listener) {
        int theme = 0, icone = 0;
        theme = R.style.Base_Theme_Material3_Dark;
        icone = R.drawable.ic_alert;

        final AlertDialog alertDialog = new AlertDialog.Builder(activity, theme).create();
        alertDialog.setTitle(titulo);
        alertDialog.setMessage(txt);
        alertDialog.setIcon(icone);

        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK", listener);
        alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancelar", (dialog, which) -> alertDialog.dismiss());

        WindowManager.LayoutParams params = new WindowManager.LayoutParams();
        params.copyFrom(alertDialog.getWindow().getAttributes());
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        alertDialog.show();
        alertDialog.getWindow().setAttributes(params);
    }

    public static Bitmap converterBlobBitmap(byte[] image){
        Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0 ,image.length);
        return bitmap;
    }

    public static byte[] converterBitmapBlob(ImageView imageView){
        Bitmap bitmap = ((BitmapDrawable)imageView.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 80, stream);
        byte bytes[] = stream.toByteArray();
        return bytes;
    }


}
