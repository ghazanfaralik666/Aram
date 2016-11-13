package com.example.ghazanfarali.asani;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ghazanfarali on 09/10/2016.
 */

public class ServiceResponse {
    /*@SerializedName("Android")
    private int page;*/
    @SerializedName("Android")
    private List<ServiceData> results;
    /*@SerializedName("total_results")
    private int totalResults;
    @SerializedName("total_pages")
    private int totalPages;*/

}
