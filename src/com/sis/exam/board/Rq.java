package com.sis.exam.board;

import java.util.Map;

public class Rq {
    String url;

    Map<String,String> params;
    String urlPath;

    public Rq(String url) {
        this.url = url;
        params = Util.getParamsFromUrl(url);
        urlPath = Util.getUrlPathFromUrl(url);
    }

    public Map<String, String> getParams() {
        return params;
    }

    public String getUrlPath() {
        return urlPath;
    }
}
