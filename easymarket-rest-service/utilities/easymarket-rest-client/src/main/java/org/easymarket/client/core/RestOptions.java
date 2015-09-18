package org.easymarket.client.core;

/**
 * Class used to pass query parameters and header values and other to request rest.
 */
public class RestOptions {

    //QueryParameters
    private MultiValueMap parameters=new MultiValueMap();

    //Header parameters
    //TODO

    public void add(String key,String value){
        getParameters().put(key, value);
    }

    public MultiValueMap getParameters() {
        return parameters;
    }

    public void setParameters(MultiValueMap parameters) {
        this.parameters = parameters;
    }

    public static RestOptions query(String name,String value){
        RestOptions options=new RestOptions();

        options.add(name,value);

        return options;
    }
}
