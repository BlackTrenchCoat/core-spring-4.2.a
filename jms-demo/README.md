# jms-demo

Five demo applications showing different ways of sending and processing
messages with JMS.

All but one of these examples is configured using XML.

To see the tests working, look for `INFO : samples.jms.xxx - >> ...` log messages
on the console.

Ignore errors like `ERROR: org.apache.activemq.broker.BrokerService - Temporary Store limit ...`

## MessageDrivenPojoTestsXml

Uses a `JmsTemplate` to send a String, an array of bytes, a Map and
a `Serializable` object.  Each will be sent as a different type of
JMS Message (`TextMeessage`, `ByteMessage`, `MapMessage` and
`ObjectMessge` respectively)

The `SimpleLogger` is used as a message-driven POJO and contains 4
different overloadings of `log()`, one for each of the possible message
payloads (a String, an array of bytes, a Map and a Serializable).

This example is configured using `samples/jms/mdp/applicationContext.xml`
in `src.test/resources`.  Because the `SimpleLogger` has four message-driven
methods, `@JmsListener` cannot easily be used (see below). So XML
configuration is used instead:

```
	<jms:listener-container concurrency="5">
		<jms:listener ref="logger" destination="sampleQueue" method="log"/>
	</jms:listener-container>
```

One of the nice features of XML vs Java configuration for a JMS Listener
container is the ability to use multiple overloadings of the same method.
The container is clever enough to invoke the right method to match the
incoming payload.

## MessageDrivenPojoTests

Performs the same tests as `MessageDrivenPojoTestsXml` (see above) but
is configured using JavaConfiguration and `@JmsListener`.

The `@JmsListener` annotation can only be aplied to a single method on
`SimpleLogger` so we have added it to `SimpleLogger.log(Object)` because
`



## MessageCreatorExampleTests

Show the use of `jmsTemplate.send(messageCreator)` to create a message
manually, including setting some of the message properties.

The `LoggingMessageListener` is a classing message-driven bean implementing
`MessageListener`.  In its `onMessage()` method we can access both the
message and its properties.

Configured using `samples/jms/messagecreator/applicationContext.xml`.

## ProducerCallbackExampleTests

Uses `jmsTemplate.execute(new ProducerCallback<?>)` to generate and send
multiple messages, each with a different correlation id.

Messages also received and processed by `LoggingMessageListener`.

Configured using `samples/jms/producercallback/applicationContext.xml`.

## SessionCallbackExampleTests

Uses `jmsTemplate.execute(new SessionCallback<?>)` to generate and send
a messages.  This is the ultimate fallback allowing full use of the JMS
API, but the template still handles sessions and connections to ensure
they are automatically closed to avoid memory-leaks.

Messages also received and processed by `LoggingMessageListener`.

Configured using `samples/jms/sessioncallback/applicationContext.xml`.


