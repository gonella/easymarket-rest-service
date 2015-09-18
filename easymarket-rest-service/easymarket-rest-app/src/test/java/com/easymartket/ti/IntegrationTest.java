package com.easymartket.ti;

import static org.assertj.core.api.Assertions.assertThat;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.easymarket.api.Saying;
import com.easymarket.core.Person;
import com.easymarket.core.SupplierDepartment;
import com.google.common.base.Optional;

public class IntegrationTest extends IntegrationTestCommon{


    @BeforeClass
    public static void migrateDb() throws Exception {
        //RULE.getApplication().run("db", "migrate", CONFIG_PATH);
    }

    @Before
    public void setUp() throws Exception {
        client = ClientBuilder.newClient();
    }

    @After
    public void tearDown() throws Exception {
        client.close();
    }


    @Test
    public void testHelloWorld() throws Exception {
        final Optional<String> name = Optional.fromNullable("Dr. IntegrationTest");
        final Saying saying = client.target("http://localhost:" + RULE.getLocalPort() + "/hello-world")
                .queryParam("name", name.get())
                .request()
                .get(Saying.class);
        assertThat(saying.getContent()).isEqualTo(RULE.getConfiguration().buildTemplate().render(name));
    }

    @Test
    public void testPostPerson() throws Exception {
        final Person person = new Person("Dr. IntegrationTest", "Chief Wizard");
        final Person newPerson = client.target("http://localhost:" + RULE.getLocalPort() + "/people")
                .request()
                .post(Entity.entity(person, MediaType.APPLICATION_JSON_TYPE))
                .readEntity(Person.class);
        assertThat(newPerson.getId()).isNotNull();
        assertThat(newPerson.getFullName()).isEqualTo(person.getFullName());
        assertThat(newPerson.getJobTitle()).isEqualTo(person.getJobTitle());
    }

    //@Test
    public void testPostSupplierDepartament()
            throws Exception {
        final SupplierDepartment obj = new SupplierDepartment();
        obj.setName("Department1");
        final SupplierDepartment newObj = client.target("http://localhost:" + RULE.getLocalPort() + "/supplierdepartments")
                .request()
                .post(Entity.entity(obj, MediaType.APPLICATION_JSON_TYPE))
                .readEntity(SupplierDepartment.class);
        assertThat(newObj.getId()).isNotNull();
        assertThat(newObj.getName()).isEqualTo(obj.getName());
    }
}
