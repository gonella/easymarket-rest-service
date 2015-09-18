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

import com.easymarket.core.Bid;
import com.easymarket.dao.BidDAO;
import com.easymarket.resources.v1.BidResourceV1;
import com.easymartket.configuration.TestEnvironment;
import com.easymartket.expected.EndpointsExpected;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.common.collect.ImmutableList;

/**
 * 
 * Unit tests for {@link BidResourceV1}.
 * 
 * @author Adriano
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class BidResourceV1Test {
	private static final String URL = "/" + TestEnvironment.V1
			+ "/"+EndpointsExpected.BIDS;
	private static final BidDAO dao = mock(BidDAO.class);
	@ClassRule
	public static final ResourceTestRule resources = ResourceTestRule.builder()
			.addResource(new BidResourceV1(dao)).build();
	@Captor
	private ArgumentCaptor<Bid> captor;
	private Bid bid;

	@Before
	public void setUp() {
		bid = new Bid();
		bid.setName("name");
	}

	@After
	public void tearDown() {
		reset(dao);
	}

	@Test
	public void createBid() throws JsonProcessingException {
		when(dao.create(any(Bid.class))).thenReturn(bid);
		final Response response = resources.client().target(URL)
				.request(MediaType.APPLICATION_JSON_TYPE)
				.post(Entity.entity(bid, MediaType.APPLICATION_JSON_TYPE));

		assertThat(response.getStatusInfo()).isEqualTo(Response.Status.OK);
		verify(dao).create(captor.capture());
		assertThat(captor.getValue()).isEqualTo(bid);
	}

	@Test
	public void listResource() throws Exception {
		final ImmutableList<Bid> list = ImmutableList.of(bid);
		when(dao.findAll()).thenReturn(list);

		final List<Bid> response = resources.client().target(URL)
				.request().get(new GenericType<List<Bid>>() {
				});

		verify(dao).findAll();
		assertThat(response).containsAll(list);
	}
}
