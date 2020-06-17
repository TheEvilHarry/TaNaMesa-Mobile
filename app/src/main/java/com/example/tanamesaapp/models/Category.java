package com.example.tanamesaapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Category implements Serializable {

    @SerializedName("categories")
    @Expose
    private List<Category> categories;

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    @SerializedName("englishName")
    @Expose
    private String englishName;
    @SerializedName("idCategory")
    @Expose
    private String idCategory;
    @SerializedName("strCategory")
    @Expose
    private String strCategory;
    @SerializedName("strCategoryDescription")
    @Expose
    private String strCategoryDescription;
    @SerializedName("strCategoryThumb")
    @Expose
    private String strCategoryThumb;

    public String getEnglishName() {
        return englishName;
    }

    public String getIdCategory() {
        return idCategory;
    }

    public String getStrCategory() {
        return strCategory;
    }

    public String getStrCategoryDescription() {
        return strCategoryDescription;
    }

    public String getStrCategoryThumb() {
        return strCategoryThumb;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public void setIdCategory(String idCategory) {
        this.idCategory = idCategory;
    }

    public void setStrCategory(String strCategory) {
        this.strCategory = strCategory;
    }

    public void setStrCategoryDescription(String strCategoryDescription) {
        this.strCategoryDescription = strCategoryDescription;
    }

    public void setStrCategoryThumb(String strCategoryThumb) {
        this.strCategoryThumb = strCategoryThumb;
    }
}
