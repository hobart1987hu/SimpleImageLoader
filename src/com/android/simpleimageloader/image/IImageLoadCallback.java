package com.android.simpleimageloader.image;

import android.graphics.drawable.BitmapDrawable;
import android.view.View;

public interface IImageLoadCallback {

    void onLoadingStarted(Object data, View view);

    void onLoadingFailed(Object data, View view, String reason);

    void onLoadingComplete(Object data, View view, BitmapDrawable drawable);

    void onLoadingCancelled(Object data, View view);

    void publishProgress(int totalSize, int progress);

}
