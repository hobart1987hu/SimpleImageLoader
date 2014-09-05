package com.android.simpleimageloader.image;

import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.widget.ImageView;

public class ImageLoader {

    private ImageLoaderConfiguration mloaderConfig;

    static class SingletonHolder {

        static ImageLoader instance = new ImageLoader();
    }

    public static ImageLoader getInstance() {
        return SingletonHolder.instance;
    }

    public synchronized void init(ImageLoaderConfiguration configuration) {
        // init
        if (null == mloaderConfig) {
            mloaderConfig = configuration;
        }
    }

    private void checkConfig() {
        if (null == mloaderConfig) {
            if (null == mloaderConfig) {
                throw new IllegalStateException("null mLoaderConfiguration...");
            }
        }
    }

    public void display(ImageView imageView, String object, DisplayOptions options, IImageLoadCallback listener) {

        checkConfig();

        if (null == listener) {
            listener = emptyListener;
        }
        mloaderConfig.mImageFetcher.loadImage(object, imageView, options);
    }

    public void setPauseWork(boolean isPause) {
        mloaderConfig.mImageFetcher.setPauseWork(isPause);
    }

    public void onResume() {
        mloaderConfig.mImageFetcher.setExitTasksEarly(false);
    }

    public void onPause() {
        mloaderConfig.mImageFetcher.setPauseWork(false);
        mloaderConfig.mImageFetcher.setExitTasksEarly(true);
        mloaderConfig.mImageFetcher.flushCache();
    }
    
    public void finishCache(){
        mloaderConfig.mImageFetcher.closeCache();
    }

    public void onDestory() {
       
    }

    private IImageLoadCallback emptyListener = new IImageLoadCallback() {

                                                 @Override
                                                 public void publishProgress(int totalSize, int progress) {

                                                 }

                                                 @Override
                                                 public void onLoadingStarted(Object data, View view) {

                                                 }

                                                 @Override
                                                 public void onLoadingFailed(Object data, View view, String reason) {

                                                 }

                                                 @Override
                                                 public void onLoadingComplete(Object data, View view,
                                                                               BitmapDrawable drawable) {

                                                 }

                                                 @Override
                                                 public void onLoadingCancelled(Object data, View view) {

                                                 }
                                             };

}
