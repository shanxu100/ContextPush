package edu.scut.luluteam.ubclibrary.bean;

import com.google.gson.Gson;

/**
 * @author Guan
 * @date Created on 2018/4/23
 */
public class BaseInfo {


    public String toJson() {
        String json = new Gson().toJson(this);
        return json;
    }

}
