package com.easymarket.util;

import java.util.ArrayList;
import java.util.List;

import com.easymarket.core.BaseResource;

public class DaoUtil {

    public static Long[] getIds(List<? extends BaseResource> list){

        List<Long> listLong=new ArrayList<Long>();
        for (BaseResource baseResource : list) {
            listLong.add(baseResource.getId());
        }
        Long[] array = listLong.toArray(new Long[listLong.size()]);

        return array;
    }
}
