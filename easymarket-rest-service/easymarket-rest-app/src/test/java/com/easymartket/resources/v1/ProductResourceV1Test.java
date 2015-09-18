package com.easymartket.resources.v1;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.After;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.easymarket.core.Product;
import com.easymarket.dao.ProductDAO;
import com.easymarket.dao.SupplierDAO;
import com.easymarket.resources.v1.ProductResourceV1;
import com.easymartket.configuration.TestEnvironment;
import com.easymartket.expected.EndpointsExpected;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.common.collect.ImmutableList;

import io.dropwizard.testing.junit.ResourceTestRule;

/**
 *
 * Unit tests for {@link ProductResourceV1}.
 *
 * @author Adriano
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class ProductResourceV1Test {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductResourceV1Test.class);

    private static final String URL = "/" + TestEnvironment.V1 + "/" + EndpointsExpected.PRODUCTS;
    private static final ProductDAO productDAO = mock(ProductDAO.class);
    private static final SupplierDAO supplierDAO = mock(SupplierDAO.class);

    @ClassRule
    public static final ResourceTestRule resources = ResourceTestRule.builder()
    .addResource(new ProductResourceV1(productDAO, supplierDAO))
    .build();
    @Captor
    private ArgumentCaptor<Product> captor;
    private Product product;

    @Before
    public void setUp() {
        product = new Product();
        product.setName("name");
    }

    @After
    public void tearDown() {
        reset(productDAO);
    }

    @Test
    public void createProduct()
            throws JsonProcessingException {
        when(productDAO.create(any(Product.class))).thenReturn(product);
        final Response response = resources.client().target(URL).request(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.entity(product, MediaType.APPLICATION_JSON_TYPE));

        assertThat(response.getStatusInfo()).isEqualTo(Response.Status.OK);
        verify(productDAO).create(captor.capture());
        assertThat(captor.getValue()).isEqualTo(product);
    }

    @Test
    public void listResource()
            throws Exception {
        final ImmutableList<Product> list = ImmutableList.of(product);
        when(productDAO.findAll()).thenReturn(list);

        final List<Product> response = resources.client().target(URL).request().get(new GenericType<List<Product>>() {
        });

        verify(productDAO).findAll();
        assertThat(response).containsAll(list);
    }

}
