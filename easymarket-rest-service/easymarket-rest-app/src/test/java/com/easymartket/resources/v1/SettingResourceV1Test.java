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

import com.easymarket.core.Setting;
import com.easymarket.dao.SettingDAO;
import com.easymarket.resources.v1.SettingResourceV1;
import com.easymartket.configuration.TestEnvironment;
import com.easymartket.expected.EndpointsExpected;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.common.collect.ImmutableList;

/**
 * 
 * Unit tests for {@link SettingResourceV1}.
 * 
 * @author Adriano
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class SettingResourceV1Test {
	private static final String URL = "/" + TestEnvironment.V1
			+ "/"+EndpointsExpected.SETTINGS;
	private static final SettingDAO dao = mock(SettingDAO.class);
	@ClassRule
	public static final ResourceTestRule resources = ResourceTestRule.builder()
			.addResource(new SettingResourceV1(dao)).build();
	@Captor
	private ArgumentCaptor<Setting> captor;
	private Setting setting;

	@Before
	public void setUp() {
		setting = new Setting();
		setting.setName("name");
	}

	@After
	public void tearDown() {
		reset(dao);
	}

	@Test
	public void createSetting() throws JsonProcessingException {
		when(dao.create(any(Setting.class))).thenReturn(setting);
		final Response response = resources.client().target(URL)
				.request(MediaType.APPLICATION_JSON_TYPE)
				.post(Entity.entity(setting, MediaType.APPLICATION_JSON_TYPE));

		assertThat(response.getStatusInfo()).isEqualTo(Response.Status.OK);
		verify(dao).create(captor.capture());
		assertThat(captor.getValue()).isEqualTo(setting);
	}

	@Test
	public void listResource() throws Exception {
		final ImmutableList<Setting> list = ImmutableList.of(setting);
		when(dao.findAll()).thenReturn(list);

		final List<Setting> response = resources.client().target(URL)
				.request().get(new GenericType<List<Setting>>() {
				});

		verify(dao).findAll();
		assertThat(response).containsAll(list);
	}
}
