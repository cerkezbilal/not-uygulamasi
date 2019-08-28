package com.bilal.karademir.notuygulamasi;

/**
 * Created by kasimadalan on 27.02.2017.
 */

public class ApiUtils {

    public static final String BASE_URL = "https://mobildenemebilal.tk/";

    //bu base link ana link olmalı alt kolları interface içerisinde belirtilir.

    public static NotlarInterface getNotlarDaoInterfeace() {
        return RetrofitClient.getClient(BASE_URL).create(NotlarInterface.class);
    }
}


