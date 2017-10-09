
package com.facebook.product;

import com.facebook.ads.sdk.*;
import com.google.gson.annotations.SerializedName;
import com.opencsv.bean.BeanFieldPrimitiveTypes;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvCustomBindByName;

import java.util.List;

/**
 * A simpler writable version of ProductItem class
 */
public class ProductItem {

    private List<String> mAdditionalImageUrls = null;
    private String mAvailability = null;
    private String mBrand = null;
    private String mCondition = null;
    private String mDescription = null;
    private String mId = null;
    private String mImageUrl = null;
    private String mName = null;
    private String mPrice = null;
    private String mUrl = null;
    private String mPostUrl = null;
    private String mGoogleProductCategory = null;

    public List<String> getmAdditionalImageUrls() {
        return mAdditionalImageUrls;
    }

    public void setmAdditionalImageUrls(List<String> mAdditionalImageUrls) {
        this.mAdditionalImageUrls = mAdditionalImageUrls;
    }

    public String getmAvailability() {
        return mAvailability;
    }

    public void setmAvailability(String mAvailability) {
        this.mAvailability = mAvailability;
    }

    public String getmBrand() {
        return mBrand;
    }

    public void setmBrand(String mBrand) {
        this.mBrand = mBrand;
    }

    public String getmCondition() {
        return mCondition;
    }

    public void setmCondition(String mCondition) {
        this.mCondition = mCondition;
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }

    public String getmImageUrl() {
        return mImageUrl;
    }

    public void setmImageUrl(String mImageUrl) {
        this.mImageUrl = mImageUrl;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmPrice() {
        return mPrice;
    }

    public void setmPrice(String mPrice) {
        this.mPrice = mPrice;
    }

    public String getmUrl() {
        return mUrl;
    }

    public void setmUrl(String mUrl) {
        this.mUrl = mUrl;
    }

    public String getmPostUrl() {
        return mPostUrl;
    }

    public void setmPostUrl(String mPostUrl) {
        this.mPostUrl = mPostUrl;
    }

    public String getmGoogleProductCategory() {
        return mGoogleProductCategory;
    }

    public void setmGoogleProductCategory(String mGoogleProductCategory) {
        this.mGoogleProductCategory = mGoogleProductCategory;
    }

    ProductItem() {
    }

    public ProductItem(String id) {
        this.mId = id;
    }



}
