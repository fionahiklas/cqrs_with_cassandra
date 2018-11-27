package com.hiklas.mucking.around.cqrswithcassandra.integration;


import com.datastax.driver.core.Cluster;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@Category(IntegrationTests.class)
public class TestCassandraConnection {

    private static final Logger log = LogManager.getLogger();

    @Test
    public void dummyTest() {
        log.debug("Ran dummy test");
    }

    @Test
    public void testCassandraConnection() {
        Cluster cluster = Cluster.builder()
                .withClusterName("Test Cluster")
                .addContactPoint("127.0.0.1")
                .withPort(9042)
                .build();
    }

}
