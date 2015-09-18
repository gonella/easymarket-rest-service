package com.easymarket.init;

import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;

import com.easymarket.cli.RenderCommand;
import com.easymarket.core.Advertising;
import com.easymarket.core.Bid;
import com.easymarket.core.Person;
import com.easymarket.core.Product;
import com.easymarket.core.Setting;
import com.easymarket.core.Supplier;
import com.easymarket.core.SupplierDepartment;
import com.easymarket.core.Template;
import com.easymarket.core.User;
import com.easymarket.dao.AdvertisingDAO;
import com.easymarket.dao.BidDAO;
import com.easymarket.dao.PersonDAO;
import com.easymarket.dao.ProductDAO;
import com.easymarket.dao.SettingDAO;
import com.easymarket.dao.SupplierDAO;
import com.easymarket.dao.SupplierDepartmentDAO;
import com.easymarket.dao.UserDAO;
import com.easymarket.filter.DateRequiredFeature;
import com.easymarket.health.TemplateHealthCheck;
import com.easymarket.resources.FilteredResource;
import com.easymarket.resources.HelloWorldResource;
import com.easymarket.resources.PeopleResource;
import com.easymarket.resources.ProtectedResource;
import com.easymarket.resources.ViewResource;
import com.easymarket.resources.v1.AdvertisingResourceV1;
import com.easymarket.resources.v1.BidResourceV1;
import com.easymarket.resources.v1.ProductResourceV1;
import com.easymarket.resources.v1.SettingResourceV1;
import com.easymarket.resources.v1.SupplierDepartmentResourceV1;
import com.easymarket.resources.v1.SupplierResourceV1;
import com.easymarket.resources.v1.UserResourceV1;
import com.google.common.collect.ImmutableMap;

import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;

public class EasyMarketApplication extends Application<EastMarketConfiguration> {

    public static void main(String[] args) throws Exception {
        new EasyMarketApplication().run(args);
    }

    private final HibernateBundle<EastMarketConfiguration> hibernateBundle = new HibernateBundle<EastMarketConfiguration>(
            //
            //
            // ADD ENTITIES HERE
            //
            //
            Person.class,
            Product.class,
            User.class,
            Supplier.class,
            Bid.class,
            Advertising.class,
            Setting.class,
            SupplierDepartment.class

            ) {
        @Override
        public DataSourceFactory getDataSourceFactory(
                EastMarketConfiguration configuration) {
            return configuration.getDataSourceFactory();
        }
    };

    @Override
    public String getName() {
        return "hello-world";
    }

    @Override
    public void initialize(Bootstrap<EastMarketConfiguration> bootstrap) {
        // Enable variable substitution with environment variables

        bootstrap
        .setConfigurationSourceProvider(new SubstitutingSourceProvider(
                bootstrap.getConfigurationSourceProvider(),
                new EnvironmentVariableSubstitutor(false)));

        bootstrap.addCommand(new RenderCommand());
        bootstrap.addBundle(new AssetsBundle());
        bootstrap.addBundle(new MigrationsBundle<EastMarketConfiguration>() {
            @Override
            public DataSourceFactory getDataSourceFactory(
                    EastMarketConfiguration configuration) {
                return configuration.getDataSourceFactory();
            }
        });
        bootstrap.addBundle(hibernateBundle);
        bootstrap.addBundle(new ViewBundle<EastMarketConfiguration>() {
            @Override
            public ImmutableMap<String, ImmutableMap<String, String>> getViewConfiguration(
                    EastMarketConfiguration configuration) {
                return configuration.getViewRendererConfiguration();
            }
        });
        bootstrap.addBundle(new SwaggerBundle<EastMarketConfiguration>() {
            @Override
            protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(EastMarketConfiguration configuration) {
                // this would be the preferred way to set up swagger, you can also construct the object here programtically if you want
                return configuration.getSwaggerBundleConfiguration();
            }
        });



    }

    @Override
    public void run(EastMarketConfiguration configuration,
            Environment environment) {

        final Template template = configuration.buildTemplate();

        environment.healthChecks().register("template",
                new TemplateHealthCheck(template));
        environment.jersey().register(DateRequiredFeature.class);
        /*environment
				.jersey()
				.register(
						new AuthDynamicFeature(
								new BasicCredentialAuthFilter.Builder<User, ExampleAuthenticator>()
										.setAuthenticator(
												new ExampleAuthenticator())
										.setAuthorizer(new ExampleAuthorizer())
										.setRealm("Username/Password")
										.buildAuthFilter()));
		environment.jersey().register(
				new AuthValueFactoryProvider.Binder(User.class));
         */
        /*
         * environment.jersey().register(AuthFactory.binder(new
         * BasicAuthFactory<String>(new ExampleAuthenticator(),
         * "SUPER SECRET STUFF", String.class)));
         */


        // Initialize DAO instance
        final PersonDAO personDao = new PersonDAO(
                hibernateBundle.getSessionFactory());
        final ProductDAO productDao = new ProductDAO(
                hibernateBundle.getSessionFactory());
        final UserDAO userDao = new UserDAO(
                hibernateBundle.getSessionFactory());
        final SupplierDAO supplierDao = new SupplierDAO(
                hibernateBundle.getSessionFactory());
        final BidDAO bidDao = new BidDAO(
                hibernateBundle.getSessionFactory());
        final AdvertisingDAO advertisingDao = new AdvertisingDAO(
                hibernateBundle.getSessionFactory());
        final SettingDAO settingDao = new SettingDAO(
                hibernateBundle.getSessionFactory());
        final SupplierDepartmentDAO supplierDepartmentDao = new SupplierDepartmentDAO(
                hibernateBundle.getSessionFactory());


        // Add more controllers here
        environment.jersey().register(RolesAllowedDynamicFeature.class);

        //Examples
        environment.jersey().register(new HelloWorldResource(template));
        environment.jersey().register(new ViewResource());
        environment.jersey().register(new ProtectedResource());
        environment.jersey().register(new PeopleResource(personDao));
        //environment.jersey().register(new PersonResource(personDao));
        environment.jersey().register(new FilteredResource());
        //environment.jersey().register(new AuthenticateResource());

        //EasyMarket Endpoints
        environment.jersey().register(new ProductResourceV1(productDao,supplierDao));
        environment.jersey().register(new UserResourceV1(userDao));
        environment.jersey().register(new SupplierResourceV1(supplierDao));
        environment.jersey().register(new BidResourceV1(bidDao));
        environment.jersey().register(new AdvertisingResourceV1(advertisingDao));
        environment.jersey().register(new SettingResourceV1(settingDao));
        environment.jersey().register(new SupplierDepartmentResourceV1(supplierDepartmentDao));


        //TODO - On going - Tentative to incorporate Spring in dropwizard
        //The best approach is use framework - https://github.com/xvik/dropwizard-guicey

        // Start Spring context based on the provided location
        // TODO Externalise this into the configuration - Spring provides too
        // much to ignore

        /*ApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { SPRING_SPRING_CONTEXT_XML });

		environment.jersey().register(RolesAllowedDynamicFeature.class);
		environment.jersey().register(context.getBean(ProductResourceV1.class));
		environment.jersey()
				.register(context.getBean(HelloWorldResource.class));
		environment.jersey().register(context.getBean(ViewResource.class));
		environment.jersey().register(context.getBean(ProtectedResource.class));
		environment.jersey().register(context.getBean(PeopleResource.class));
		environment.jersey().register(context.getBean(PersonResource.class));
		environment.jersey().register(context.getBean(FilteredResource.class));
		environment.jersey().register(context.getBean(AuthenticateResource.class));*/

        //FIXME
        /*ResourceConfig resourceConfig =
                new ResourceConfig(SupplierResourceV1.class, EasyMarketApplicationEventListener.class)
                .setApplicationName("my-monitored-application");
        environment.jersey().register(resourceConfig);*/
    }

}
