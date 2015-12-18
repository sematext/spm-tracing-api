# spm-tracing-api
Java Tracing API for [SPM Client](https://sematext.atlassian.net/wiki/display/PUBSPM/Transaction+Tracing).


SPM Client has transaction tracing capability. This allows to get insights about performance bottlenecks of application. Since SPM Client uses bytecode instrumentation technique to trace application, to reduce runtime overhead tracing capabilities are limited by [supported frameworks](https://sematext.atlassian.net/wiki/display/PUBSPM/Transaction+Tracing#TransactionTracing-SupportedTechnologies). To allow developers get more insights about performance bottlenecks in their code we provide API to mark methods which should be included in transaction traces as well as custom parameters for this methods.

## Usage

### Add maven dependency

    <dependency>
    	<groupId>com.sematext.spm</groupId>
    	<artifactId>spm-tracing-api</artifactId>
    	<version>1.29.4</version>
    </dependency>

### Mark traced methods

To mark methods which should be traced use `com.sematext.spm.client.tracing.Trace` annotation:

    @Trace
    public void createUser() {
      ....
      dao.createUser();
    }


In this case `createUser()` method will be traced if there exists active transaction. To force transaction creation, `force` annotation parameter should be set to true (false by default):

    @Trace(force = true)
    public void doBackgroundJob() {
      ...
    }

### Transaction level parameters

`SPM.setTransactionName("customName")` allows set custom transaction name, by default it is entry-point method name.

    SPM.setTransactionName("transaction-name-1");

![enter image description here](http://i.imgur.com/N3MFyx6.png)

`SPM.setCustomTransactionParameter("key", value")` allows to add custom transaction parameter

    SPM.setCustomTransactionParameter("random-seed", "731132938");
    SPM.setCustomTransactionParameter("user", "Kyle");

![enter image description here](http://i.imgur.com/zGbrnig.png)

`SPM.ignoreTransaction()` don't tracks current transaction. Could be useful to ignore transactions depending on environment (dev/test), or for particular user/session.

### Method level parameters

`SPM.setCustomMethodParameter("key-value", "key-value")` allows to set custom parameter for currently traced method

	  @Trace
	  def getTweets(user: String): List[Tweet] = {
	    SPM.setCustomMethodParameter("user", user)
	    TimeUnit.SECONDS.sleep(1)
	    Tweet("What a lovely day!")::Nil
	  }

![enter image description here](http://i.imgur.com/58OrB1x.png)


## License

Copyright 2015 Sematext Group, Inc.

Licensed under the Apache License, Version 2.0: http://www.apache.org/licenses/LICENSE-2.0
