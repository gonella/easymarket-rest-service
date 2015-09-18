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

import com.easymarket.core.SupplierDepartment;
import com.easymarket.dao.SupplierDepartmentDAO;
import com.easymarket.resources.v1.ProductResourceV1;
import com.easymarket.resources.v1.SupplierDepartmentResourceV1;
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
public class SupplierDepartmentResourceV1Test {
    private static final Logger LOGGER = LoggerFactory.getLogger(SupplierDepartmentResourceV1Test.class);

    private static final String URL = "/" + TestEnvironment.V1 + "/" + EndpointsExpected.SUPPLIERDEPARTMENTS;
    private static final SupplierDepartmentDAO dao = mock(SupplierDepartmentDAO.class);
    @ClassRule
    public static final ResourceTestRule resources = ResourceTestRule.builder().addResource(new SupplierDepartmentResourceV1(dao))
    .build();
    @Captor
    private ArgumentCaptor<SupplierDepartment> captor;
    private SupplierDepartment item;

    @Before
    public void setUp() {
        item = new SupplierDepartment();
        item.setName("name");
    }

    @After
    public void tearDown() {
        reset(dao);
    }

    @Test
    public void create()
            throws JsonProcessingException {
        when(dao.create(any(SupplierDepartment.class))).thenReturn(item);
        final Response response = resources.client().target(URL).request(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.entity(item, MediaType.APPLICATION_JSON_TYPE));

        assertThat(response.getStatusInfo()).isEqualTo(Response.Status.OK);
        verify(dao).create(captor.capture());
        assertThat(captor.getValue()).isEqualTo(item);
    }

    @Test
    public void list()
            throws Exception {
        final ImmutableList<SupplierDepartment> list = ImmutableList.of(item);
        when(dao.findAll()).thenReturn(list);

        final List<SupplierDepartment> response = resources.client().target(URL).request()
                .get(new GenericType<List<SupplierDepartment>>() {
                });

        verify(dao).findAll();
        assertThat(response).containsAll(list);
    }

}
