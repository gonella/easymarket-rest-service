package org.easymarket.it.util;

import java.util.HashMap;
import java.util.Set;

import org.easymarket.client.core.MultiValueMap;
import org.easymarket.ti.edm.v1.param.IQueryBeanParam;
import org.easymarket.ti.edm.v1.param.ITSupplierRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ParamUtil {


    public static <T> MultiValueMap toMap(IQueryBeanParam object,T type){

        MultiValueMap props=new MultiValueMap();
        if(object!=null){
            ObjectMapper m = new ObjectMapper();
            props = m.convertValue(object, MultiValueMap.class);
        }

        return props;
    }

    public static void main(String arg[]){
        Logger LOGGER = LoggerFactory.getLogger(ParamUtil.class);

        LOGGER.info("Param {}",0);

        ITSupplierRequest request=new ITSupplierRequest();
        request.setGpsCoordinates("OK");

        HashMap<String, String> map = toMap(request, ITSupplierRequest.class);

        Set<String> keySet = map.keySet();
        for (String string : keySet) {

            System.out.println("key :"+string+" - "+map.get(string));

        }
    }

}
