package com.easymartket.ti;

import java.io.File;
import java.io.IOException;

import javax.ws.rs.client.Client;

import org.junit.ClassRule;

import com.easymarket.init.EastMarketConfiguration;
import com.easymarket.init.EasyMarketApplication;

import io.dropwizard.testing.ConfigOverride;
import io.dropwizard.testing.ResourceHelpers;
import io.dropwizard.testing.junit.DropwizardAppRule;

public class IntegrationTestCommon {

    private static final String TMP_FILE = createTempFile();
    private static final String CONFIG_PATH = ResourceHelpers.resourceFilePath("test-example.yml");

    @ClassRule
    public static final DropwizardAppRule<EastMarketConfiguration> RULE = new DropwizardAppRule<>(
            EasyMarketApplication.class, CONFIG_PATH,
            ConfigOverride.config("database.url", "jdbc:h2:" + TMP_FILE));

    public Client client;
    private static String createTempFile() {
        try {
            return File.createTempFile("test-example", null).getAbsolutePath();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
}
