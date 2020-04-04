package com.example.moviesapp.custom_control;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.moviesapp.R;

public class MyanProgressDialog {

    private Dialog dialog;

    public MyanProgressDialog(Context context)
    {
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.progress_dialog);
        dialog.setCancelable(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        ImageView imageView = dialog.findViewById(R.id.ivImage);

        Glide.with(context)
                .load(R.drawable.popcorngif)
                .into(imageView);
    }

    public void showDialog()
    {
        dialog.show();
    }

    public void hideDialog()
    {
        dialog.dismiss();
    }

}
