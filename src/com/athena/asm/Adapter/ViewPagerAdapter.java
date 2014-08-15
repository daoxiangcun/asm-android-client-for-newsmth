package com.athena.asm.Adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.athena.asm.R;
import com.athena.asm.aSMApplication;
import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;

import uk.co.senab.photoview.PhotoView;

import java.util.ArrayList;

public class ViewPagerAdapter extends PagerAdapter {
    private Context mContext;
    ArrayList<String> mImageUrls;

    public ViewPagerAdapter(ArrayList<String> imgUrls, Context context) {
        mImageUrls = imgUrls;
        mContext = context;
    }

    @Override
    public int getCount() {
        if(mImageUrls != null)
            return mImageUrls.size();
        return 0;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
    }

    @Override
    public void finishUpdate(ViewGroup container) {
        super.finishUpdate(container);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LinearLayout.LayoutParams mParams = new LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        // set parameters for UrlImageViewHelper
        UrlImageViewHelper.setUseZoomIn(false); // enable zoom in
        UrlImageViewHelper.setUseZoomOut(false); // don't zoom out
        UrlImageViewHelper.setMaxImageSize(0); // load all size

        // initialize PhotoView
        String imageUrl = mImageUrls.get(position);
        PhotoView iv = new PhotoView(mContext);
        iv.setLayoutParams(mParams);
        iv.setOnLongClickListener((OnLongClickListener)mContext);
        if (aSMApplication.getCurrentApplication().isNightTheme()) {
            UrlImageViewHelper.setErrorResource(R.drawable.failure_night);
            UrlImageViewHelper.setUrlDrawable(iv, imageUrl, R.drawable.loading_night);
        } else {
            UrlImageViewHelper.setErrorResource(R.drawable.failure_day);
            UrlImageViewHelper.setUrlDrawable(iv, imageUrl, R.drawable.loading_day);
        }
        ((ViewPager) container).addView(iv, 0);
        return iv;
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return (arg0 == arg1);
    }

}
