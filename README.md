# spm-tracing-api
Java/Scala/Clojure/Groovy Tracing API for [SPM Client](https://sematext.atlassian.net/wiki/display/PUBSPM/Transaction+Tracing).


SPM Client has transaction tracing capability. This lets you get
insights about performance bottlenecks of an application. SPM
Client uses bytecode instrumentation for tracing. To reduce runtime overhead tracing capabilities are targeted at specific
[supported
frameworks](https://sematext.atlassian.net/wiki/display/PUBSPM/Transaction+Tracing#TransactionTracing-SupportedTechnologies). To
let developers get more insights about performance bottlenecks in
*their* code we provide an API to mark methods that should be included in
transaction traces, as well as setting custom parameters for such methods.

## Usage

### Add maven dependency

    <dependency>
    	<groupId>com.sematext.spm</groupId>
    	<artifactId>spm-tracing-api</artifactId>
    	<version>1.29.4</version>
    </dependency>

### Mark traced methods

To mark methods that should be traced use `com.sematext.spm.client.tracing.Trace` annotation:

    @Trace
    public void createUser() {
      ....
      dao.createUser();
    }


In this case `createUser()` method will be traced when it is called in an active transaction. To force transaction creation, `force` annotation parameter should be set to true (false by default):

    @Trace(force = true)
    public void doBackgroundJob() {
      ...
    }

### Transaction level parameters

`SPM.setTransactionName("customName")` allows setting of a custom transaction name.  By default a transaction name is set to the name of the entry-point method.

    SPM.setTransactionName("transaction-name-1");

![enter image description here](http://i.imgur.com/N3MFyx6.png)

`SPM.setCustomTransactionParameter("key", value")` allows adding of a custom transaction parameter

    SPM.setCustomTransactionParameter("random-seed", "731132938");
    SPM.setCustomTransactionParameter("user", "Kyle");

![enter image description here](http://i.imgur.com/zGbrnig.png)

Calling `SPM.ignoreTransaction()` means the current transaction will not be traced. This can be useful if you want to ignore transactions depending on environment (dev/test), or for particular user/session, etc.

### Method level parameters

`SPM.setCustomMethodParameter("key-value", "key-value")` allows adding of a custom parameter for currently traced method

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
