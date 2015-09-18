package org.easymarket.client.core;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.easymarket.it.util.ParamUtil;
import org.easymarket.ti.edm.v1.param.IQueryBeanParam;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.filter.LoggingFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RestClient<E> {

    // http://codesolid.com/a-simple-jersey-rest-tutorial/
    private static final Logger LOGGER = LoggerFactory
            .getLogger(RestClient.class);

    private String APPLICATION_TYPE = MediaType.APPLICATION_JSON;
    private String STANDARDCHARSET = StandardCharsets.UTF_8.name();
    private ObjectMapper mapper = null;

    private ClientConfig config = new ClientConfig();
    private Client client = ClientBuilder.newClient(config);
    private WebTarget target;
    private String urlHost;
    private String endpoint;

    private Class<E> generic;
    private Class<E[]> genericArray;

    public RestClient(String urlHost, String endpoint, Class<E> generic, Class<E[]> genericArray) {
        this.genericArray = genericArray;
        this.setGeneric(generic);
        this.setEndpoint(endpoint);
        this.setUrlHost(urlHost);

        this.config = new ClientConfig();
        //Printing REST REQUEST
        config.register(new LoggingFilter());

        this.client = ClientBuilder.newClient(config);
        setTarget(getClient().target(urlHost));

    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public WebTarget getTarget() {
        return target;
    }

    public void setTarget(WebTarget target) {
        this.target = target;
    }
    public Builder request(String path) {
        return request(path,null,null);
    }
    public Builder request(String path, MultiValueMap map, RestOptions options) {
        WebTarget webPath = getTarget().path(path);

        //TODO - Improve
        //Two options, from bean parameter or query parameter directly.
        webPath = addingParameters(map, webPath);
        if(options!=null){
            MultiValueMap parameters = options.getParameters();
            webPath = addingParameters(parameters, webPath);
        }
        //TOOD - Add header

        Builder request = webPath.request();

        Builder builder = request
                .accept(APPLICATION_TYPE).acceptEncoding(STANDARDCHARSET);

        return builder;
    }

    private WebTarget addingParameters(MultiValueMap map, WebTarget webPath) {
        if (map != null) {
            Set<String> keySet = map.keySet();
            for (String key : keySet) {
                webPath = webPath.queryParam(key, map.get(key));
            }
        }
        return webPath;
    }

    public ObjectMapper getMapper() {

        if (mapper == null) {
            mapper = new ObjectMapper();
            mapper = mapper.setSerializationInclusion(Include.NON_NULL);
            mapper.configure(
                    DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
                    false);
            mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);

        }
        return mapper;
    }

    public <E> E post(String path, Object object)
            throws IOException {
        String json = getMapper().writeValueAsString(object);
        String response = request(path).post(
                Entity.entity(json, APPLICATION_TYPE),
                String.class);

        E obj = (E) getMapper().readValue(response, getGeneric());

        return obj;
    }

    public <E> E delete(String path)
            throws IOException {
        String response = (String) request(path).delete(getGeneric());

        LOGGER.info("Entity delete ? Response :" + response);
        //PENDING
        //T obj = getMapper().readValue(response, type);

        return null;
    }

    public String getUrlHost() {
        return urlHost;
    }

    public void setUrlHost(String urlHost) {
        this.urlHost = urlHost;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public <E> E[] getArray(String path, Class<E[]> typeReturned)
            throws JsonParseException, JsonMappingException, IOException {

        Response response = buildMethodGet(path,null,null);
        String json = response.readEntity(String.class);
        E[] objs = getMapper().readValue(json, typeReturned);

        return objs;
    }

    public <E> List<E> getList(String path, Class<E[]> typeReturned)
            throws JsonParseException, JsonMappingException, IOException {
        E[] result = getArray(path, typeReturned);
        List<E> asList = Arrays.asList(result);
        return asList;
    }

    public <E> List<E> getList(String path)
            throws JsonParseException, JsonMappingException, IOException {
        E[] result = (E[]) getArray(path, getGenericArray());
        List<E> asList = Arrays.asList(result);
        return asList;
    }

    public <E> List<E> getList()
            throws JsonParseException, JsonMappingException, IOException {
        List<E> list = getList(getEndpoint());
        return list;
    }
    public <E> E get(String path) throws JsonParseException,
    JsonMappingException, IOException {
        E result = (E) get(path, getGeneric());
        return result;
    }

    public <E> E get(String path, Class<E> typeReturned)
            throws JsonParseException,
            JsonMappingException, IOException {
        E result = get(path, typeReturned, null, null);
        return result;
    }

    public <E> E get(String path, Class<E> typeReturned, IQueryBeanParam queryParamaters)
            throws JsonParseException, JsonMappingException, IOException {
        E result = get(path, typeReturned, queryParamaters, null);
        return result;
    }

    public <E> E get(String path, IQueryBeanParam queryParamaters)
            throws JsonParseException, JsonMappingException, IOException {
        E result = (E) get(path, getGeneric(), null, null);
        return result;
    }

    public <E> E get(String path, Class<E> typeReturned, RestOptions options)
            throws JsonParseException, JsonMappingException, IOException {
        E result = get(path, typeReturned, null, options);
        return result;
    }

    /**
     *
     * @param path
     * @param typeReturned
     * @param queryParamaters - developer has a chance to sent a bean parameters, it will be converted into query parameters in the end.
     * @param options - Passing manual query parameters and header values.
     * @return
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     */
    private <E> E get(String path, Class<E> typeReturned, IQueryBeanParam queryParamaters, RestOptions options)
            throws JsonParseException,
            JsonMappingException, IOException {

        MultiValueMap map=null;

        //Object param converting into query parameter
        if(queryParamaters!=null && typeReturned!=null){
            map = ParamUtil.toMap(queryParamaters, typeReturned);
        }
        //Rest options. passing manuall query parameters or header values

        Response response = buildMethodGet(path, map,options);

        String json = response.readEntity(String.class);
        //TODO check json empty
        E result = getMapper().readValue(json, typeReturned);
        return result;
    }

    /*
     * public <E> E get(String path, IQueryBeanParam queryParamaters)
     * throws JsonParseException,
     * JsonMappingException, IOException {
     * MultiValueMap map = ParamUtil.toMap(queryParamaters, getGeneric());
     * Response response = buildMethodGet(path, map);
     * String json = response.readEntity(String.class);
     * //TODO check json empty
     * E result = (E) getMapper().readValue(json, getGeneric());
     * return result;
     * }
     */



    public Response buildMethodGet(String path, MultiValueMap mapFromBeanParam, RestOptions options) {
        Builder request = request(path, mapFromBeanParam,options);
        Response response = request.get();
        return response;
    }

    public Class<E> getGeneric() {
        return generic;
    }

    public void setGeneric(Class<E> generic) {
        this.generic = generic;
    }

    public E create(Object toCreate)
            throws IOException {
        E productCreated = post(getEndpoint(), toCreate);
        return productCreated;
    }

    public Class<E[]> getGenericArray() {
        return genericArray;
    }

    public void setGenericArray(Class<E[]> genericArray) {
        this.genericArray = genericArray;
    }

}
