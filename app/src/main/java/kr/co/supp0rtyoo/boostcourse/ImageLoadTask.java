package kr.co.supp0rtyoo.boostcourse;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.net.URL;
import java.util.HashMap;

public class ImageLoadTask extends AsyncTask<Void, Void, Bitmap> {
    private String imageUrl;
    private ImageView imageView;

    public ImageLoadTask(String imageUrl, ImageView imageView) {
        this.imageUrl = imageUrl;
        this.imageView = imageView;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);

        imageView.setImageBitmap(bitmap);
        imageView.invalidate();
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected Bitmap doInBackground(Void... voids) {
        Bitmap bitmap = null;
        try {
            URL url = new URL(imageUrl);
            bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());

        } catch (Exception e) {
            e.printStackTrace();
        }

        return bitmap;
    }
}
