package com.easymartket.resources.v1;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import io.dropwizard.testing.junit.ResourceTestRule;

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

import com.easymarket.core.Advertising;
import com.easymarket.dao.AdvertisingDAO;
import com.easymarket.resources.v1.AdvertisingResourceV1;
import com.easymartket.configuration.TestEnvironment;
import com.easymartket.expected.EndpointsExpected;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.common.collect.ImmutableList;

/**
 * 
 * Unit tests for {@link AdvertisingResourceV1}.
 * 
 * @author Adriano
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class AdvertisingResourceV1Test {
	private static final String URL_AdvertisingS = "/" + TestEnvironment.V1
			+ "/"+EndpointsExpected.ADVERTISINGS;
	private static final AdvertisingDAO dao = mock(AdvertisingDAO.class);
	@ClassRule
	public static final ResourceTestRule resources = ResourceTestRule.builder()
			.addResource(new AdvertisingResourceV1(dao)).build();
	@Captor
	private ArgumentCaptor<Advertising> captor;
	private Advertising advertising;

	@Before
	public void setUp() {
		advertising = new Advertising();
		advertising.setName("name");
	}

	@After
	public void tearDown() {
		reset(dao);
	}

	@Test
	public void createAdvertising() throws JsonProcessingException {
		when(dao.create(any(Advertising.class))).thenReturn(advertising);
		final Response response = resources.client().target(URL_AdvertisingS)
				.request(MediaType.APPLICATION_JSON_TYPE)
				.post(Entity.entity(advertising, MediaType.APPLICATION_JSON_TYPE));

		assertThat(response.getStatusInfo()).isEqualTo(Response.Status.OK);
		verify(dao).create(captor.capture());
		assertThat(captor.getValue()).isEqualTo(advertising);
	}

	@Test
	public void listResource() throws Exception {
		final ImmutableList<Advertising> list = ImmutableList.of(advertising);
		when(dao.findAll()).thenReturn(list);

		final List<Advertising> response = resources.client().target(URL_AdvertisingS)
				.request().get(new GenericType<List<Advertising>>() {
				});

		verify(dao).findAll();
		assertThat(response).containsAll(list);
	}
}
