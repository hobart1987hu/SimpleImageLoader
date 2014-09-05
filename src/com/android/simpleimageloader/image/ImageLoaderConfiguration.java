package com.android.simpleimageloader.image;

import android.content.Context;

import com.android.simpleimageloader.image.ImageCache.ImageCacheParams;

public class ImageLoaderConfiguration {

    // Default memory cache size in kilobytes
    private static final int DEFAULT_MEM_CACHE_SIZE  = 1024 * 5;        // 5MB

    // Default disk cache size in bytes
    private static final int DEFAULT_DISK_CACHE_SIZE = 1024 * 1024 * 10; // 10MB

    public ImageFetcher      mImageFetcher;

    public ImageLoaderConfiguration(Builder builder){
        ImageCache.ImageCacheParams params = new ImageCacheParams(builder.context, builder.diskCacheDirectoryName);
        params.memCacheSize = builder.memCacheSize;
        params.diskCacheSize = builder.diskCacheSize;
        ImageCache imageCache = new ImageCache(params);
        mImageFetcher = new ImageFetcher(builder.context, imageCache);
    }

    public static class Builder {

        public int     memCacheSize  = DEFAULT_MEM_CACHE_SIZE;
        public int     diskCacheSize = DEFAULT_DISK_CACHE_SIZE;

        public Context context;

        public String  diskCacheDirectoryName;

        public Builder(Context context, String diskCacheDirectoryName){
            this.context = context;
            this.diskCacheDirectoryName = diskCacheDirectoryName;
        }

        /*
         * Sets the memory cache size based on a percentage of the max available VM memory. Eg. setting percent to 0.2
         * would set the memory cache to one fifth of the available memory. Throws {@link IllegalArgumentException} if
         * percent is < 0.01 or > .8. memCacheSize is stored in kilobytes instead of bytes as this will eventually be
         * passed to construct a LruCache which takes an int in its constructor. This value should be chosen carefully
         * based on a number of factors Refer to the corresponding Android Training class for more discussion:
         * http://developer.android.com/training/displaying-bitmaps/
         * @param percent Percent of available app memory to use to size memory cache
         */
        public Builder setMemCacheSizePercent(float percent) {
            if (percent < 0.01f || percent > 0.8f) {
                throw new IllegalArgumentException("setMemCacheSizePercent - percent must be "
                                                   + "between 0.01 and 0.8 (inclusive)");
            }
            memCacheSize = Math.round(percent * Runtime.getRuntime().maxMemory() / 1024);

            return this;
        }

        public Builder setDiskCacheSize(int size) {
            diskCacheSize = size;
            return this;
        }

        public ImageLoaderConfiguration Build() {
            return new ImageLoaderConfiguration(this);
        }
    }
}
