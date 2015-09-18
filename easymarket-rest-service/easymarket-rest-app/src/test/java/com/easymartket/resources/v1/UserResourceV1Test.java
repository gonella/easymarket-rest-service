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
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.runners.MockitoJUnitRunner;

import com.easymarket.core.User;
import com.easymarket.dao.UserDAO;
import com.easymarket.resources.v1.UserResourceV1;
import com.easymartket.configuration.TestEnvironment;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.common.collect.ImmutableList;

/**
 * 
 * Unit tests for {@link UserResourceV1}.
 * 
 * @author Adriano
 *
 */
//@RunWith(MockitoJUnitRunner.class)
public class UserResourceV1Test {
	/*private static final String URL = "/" + TestEnvironment.V1 + "/users";
	private static final UserDAO dao = mock(UserDAO.class);
	@ClassRule
	public static final ResourceTestRule resources = ResourceTestRule.builder()
			.addResource(new UserResourceV1(dao)).build();
	@Captor
	private ArgumentCaptor<User> captor;
	private User user;

	@Before
	public void setUp() {
		user = new User();
		user.setName("name");
	}

	@After
	public void tearDown() {
		reset(dao);
	}

	//@Test
	public void createUser() throws JsonProcessingException {
		when(dao.create(any(User.class))).thenReturn(user);
		final Response response = resources.client().target(URL)
				.request(MediaType.APPLICATION_JSON_TYPE)
				.post(Entity.entity(user, MediaType.APPLICATION_JSON_TYPE));

		assertThat(response.getStatusInfo()).isEqualTo(Response.Status.OK);
		verify(dao).create(captor.capture());
		assertThat(captor.getValue()).isEqualTo(user);
	}

	//@Test
	public void listResource() throws Exception {
		final ImmutableList<User> list = ImmutableList.of(user);
		when(dao.findAll()).thenReturn(list);

		final List<User> response = resources.client().target(URL).request()
				.get(new GenericType<List<User>>() {
				});

		verify(dao).findAll();
		assertThat(response).containsAll(list);
	}*/
}
