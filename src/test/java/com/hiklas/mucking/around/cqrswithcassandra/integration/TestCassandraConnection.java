package com.hiklas.mucking.around.cqrswithcassandra.integration;


import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Session;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static org.junit.Assert.*;


@Category(IntegrationTests.class)
public class TestCassandraConnection {

    private static final Logger log = LogManager.getLogger();

    private Cluster cluster;

    @Before
    public void before() {
        log.debug("Creating cluster");
        cluster = Cluster.builder()
                .withClusterName("Test Cluster")
                .addContactPoint("127.0.0.1")
                .withPort(9042)
                .build();
        log.debug("Cluster created");
    }

    @Test
    public void dummyTest() {
        log.debug("Ran dummy test");
    }

    @Test
    public void testCassandraConnection() {
        Session cassandraSession = cluster.connect();
        assertNotNull("Valid session", cassandraSession);
    }

    @Test
    public void testCassandraSelectQuery() {
        log.debug("Connecting to cluster");
        Session cassandraSession = cluster.connect("counters");

        log.debug("Execute query");
        ResultSet resultSet = cassandraSession.execute("SELECT \"counterValue\" FROM eventcounters WHERE \"counterName\" = 'cqrsEvent'");

        log.debug("Get the counter value");
        Long counterValue = resultSet.one().get("\"counterValue\"", Long.class);

        log.debug("Got counterValue: {}", counterValue);

        assertNotNull("Result set should be valid", resultSet);
        assertNotNull("Counter value should be valid", counterValue);
    }

    @Test
    public void testCassandraUpdateCounterQuery() {
        log.debug("Connecting to cluster");
        Session cassandraSession = cluster.connect("counters");

        log.debug("Execute update");
        ResultSet resultSet = cassandraSession.execute("UPDATE  eventcounters SET \"counterValue\" = \"counterValue\" + 1 WHERE \"counterName\" = 'cqrsEvent'");

        log.debug("Get the counter value");
        Long counterValue = resultSet.one().get("\"counterValue\"", Long.class);

        log.debug("Got counterValue: {}", counterValue);

        assertNotNull("Result set should be valid", resultSet);
        assertNotNull("Counter value should be valid", counterValue);
    }

}
