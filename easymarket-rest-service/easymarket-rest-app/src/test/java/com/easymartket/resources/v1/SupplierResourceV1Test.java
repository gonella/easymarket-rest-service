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

import com.easymarket.core.Supplier;
import com.easymarket.dao.SupplierDAO;
import com.easymarket.resources.v1.SupplierResourceV1;
import com.easymartket.configuration.TestEnvironment;
import com.easymartket.expected.EndpointsExpected;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.common.collect.ImmutableList;

import io.dropwizard.testing.junit.ResourceTestRule;

/**
 *
 * Unit tests for {@link SupplierResourceV1}.
 *
 * @author Adriano
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class SupplierResourceV1Test {
    private static final String URL_SupplierS = "/" + TestEnvironment.V1
            + "/"+EndpointsExpected.SUPPLIERS;
    private static final SupplierDAO dao = mock(SupplierDAO.class);
    @ClassRule
    public static final ResourceTestRule resources = ResourceTestRule.builder()
    .addResource(new SupplierResourceV1(dao)).build();
    @Captor
    private ArgumentCaptor<Supplier> captor;
    private Supplier supplier;

    @Before
    public void setUp() {
        supplier = new Supplier();
        supplier.setName("name");
    }

    @After
    public void tearDown() {
        reset(dao);
    }

    @Test
    public void createSupplier() throws JsonProcessingException {
        when(dao.create(any(Supplier.class))).thenReturn(supplier);
        final Response response = resources.client().target(URL_SupplierS)
                .request(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.entity(supplier, MediaType.APPLICATION_JSON_TYPE));

        assertThat(response.getStatusInfo()).isEqualTo(Response.Status.OK);
        verify(dao).create(captor.capture());
        assertThat(captor.getValue()).isEqualTo(supplier);
    }

    @Test
    public void listResource() throws Exception {
        final ImmutableList<Supplier> list = ImmutableList.of(supplier);
        when(dao.findAll()).thenReturn(list);

        final List<Supplier> response = resources.client().target(URL_SupplierS)
                .request().get(new GenericType<List<Supplier>>() {
                });

        verify(dao).findAll();
        assertThat(response).containsAll(list);
    }
}
