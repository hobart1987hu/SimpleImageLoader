package com.android.simpleimageloader.image;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap.CompressFormat;

public class DisplayOptions {

    public CompressFormat mCompressFormat;
    public int            mCompressQuality;

    public Bitmap         mLoadingBitmap;

    public boolean        mFadeInBitmap;

    public DisplayOptions(Builder builder){
        mCompressFormat = builder.compressFormat;
        mCompressQuality = builder.compressQuality;
        mLoadingBitmap = builder.mLoadingBitmap;
        mFadeInBitmap = builder.mFadeInBitmap;
    }

    public static class Builder {

        private static final CompressFormat DEFAULT_COMPRESS_FORMAT  = CompressFormat.JPEG;
        private static final int            DEFAULT_COMPRESS_QUALITY = 70;

        public CompressFormat               compressFormat           = DEFAULT_COMPRESS_FORMAT;
        public int                          compressQuality          = DEFAULT_COMPRESS_QUALITY;

        public Bitmap                       mLoadingBitmap;

        public boolean                      mFadeInBitmap;

        public Context                      mContext;

        private Resources                   mResources;

        public Builder(Context context){
            mContext = context;
            mResources = mContext.getResources();
        }

        /**
         * If set to true, the image will fade-in once it has been loaded by the background thread.
         */
        public Builder setImageFadeIn(boolean fadeIn) {
            mFadeInBitmap = fadeIn;
            return this;
        }

        public Builder setLoadingImage(Bitmap bitmap) {
            mLoadingBitmap = bitmap;
            return this;
        }

        public Builder setLoadingImage(int resId) {
            mLoadingBitmap = BitmapFactory.decodeResource(mResources, resId);
            return this;
        }

        public Builder setCompressFormat(CompressFormat format) {
            compressFormat = format;
            return this;
        }

        public Builder setCompressQuality(int quality) {
            compressQuality = quality;
            return this;
        }
        public DisplayOptions builder() {

            return new DisplayOptions(this);
        }
    }
}
