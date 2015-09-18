package org.easymarket.main;

import java.io.IOException;

import org.easymarket.client.core.RestClient;
import org.easymarket.ti.edm.v1.ITProduct;
import org.easymarket.ti.edm.v1.People;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

public class MainClient {

    public static void main(String arg[]) throws JsonParseException,
    JsonMappingException, IOException {

        RestClient client = new RestClient("http://localhost:8080","",ITProduct.class,ITProduct[].class);

        workWithProduct(client);
        //workWithPerson(client);
    }

    private static void workWithProduct(RestClient client)
            throws JsonParseException, JsonMappingException, IOException {
        String path = "products";
        /*Product[] readValue = (Product[]) client
				.getArray(path, Product[].class);

		for (Product obj : readValue) {
			System.out.println(obj.getName());
		}
         */
        ITProduct p = new ITProduct();
        p.setName("ADR11");

        ITProduct post = (ITProduct) client.post(path, p);
    }

    private static void workWithPerson(RestClient client)
            throws JsonParseException, JsonMappingException, IOException {
        String path = "people";
        People[] readValue = (People[]) client.getArray(path, People[].class);

        for (People obj : readValue) {
            System.out.println(obj.getFullName());
        }

        People p = new People();
        p.setFullName("ADR11");

        People post = (People) client.post(path, p);
    }
}
