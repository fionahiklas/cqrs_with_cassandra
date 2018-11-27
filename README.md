# CQRS with Cassandra

## Overview

Simple example of an application implementing [Command Query Responsibility Separation](https://martinfowler.com/bliki/CQRS.html).

The basic idea is to store events in a Cassandra keyspace, since these are essentially insert-only and will be 
distributed around the cluster, it should be a fairly high performance setup.

Essentially this is just the event sourcing component for a CQRS solution.

The tricky bit is having a distributed counter which can handle updates from multiple clients that won't result in 
duplicates or other glitches.  For now just using write and read consistency of "ALL" to a row that contains the 
current counter and adding an IF clause.  I don't think this is ideal at all and probably not performant, going to
test to make sure it at least works.

Writing things using IoC and DI but not going to drag in Spring or anything big/complicated like that.





## Step-by-step

### Creating Project

* Using IntelliJ 
* Create a blank Maven project
* Don't use archetypes
* Added the following to the pom to set the compiler version

```
    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.5.1</version>
                <configuration>
                    <source>1.8</source>
                    <target>1.8</target>
                </configuration>
            </plugin>
        </plugins>
    </build>
```
* To make sure that the dependencies downloaded ran the following

```
mvn verify
```


## References

### Cassandra

* [Apache Cassandra](http://cassandra.apache.org/)
* [Tutorial](https://teddyma.gitbooks.io/learncassandra/content/index.html)


### Spark

* [Docs](http://sparkjava.com/documentation)

### Logging

* [log4j tutorial](https://www.baeldung.com/java-logging-intro)

### Testing

* [Mark integration tests](https://stackoverflow.com/questions/2606572/junit-splitting-integration-test-and-unit-tests)
* [Maven surefire plugin](http://maven.apache.org/surefire/maven-surefire-plugin/index.html)
* [Maven profiles](https://maven.apache.org/guides/introduction/introduction-to-profiles.html)