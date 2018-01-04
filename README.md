# Funky

`Funky` is a `java`library that lets you run `clojure` code within your `java` app.

## Idea
Object orientated and functional languages have there benefits. One of the big advantage of functional languages is the immutability. This gets handy e.g. when you have to deal with money values. No ones likes unnoticed side effets when calculating with money.

At this point `Funky` comes into play. Use the advantages of `Java` and `Clojure` and keep the languages completely seperated. 

## Main concept
Create some clojure source you like, e.g.
```clojure
(ns de.windler.example)

(def multi (fn [x y] (* x y)))
```

To make this accessable from Java you have to create a enum extending `FunkyFunction`
```java
public enum ClojureFunctions implements FunkyFunction {
	CLOJ_MULTI("multi", "de.windler.example");

	private final String name;
	private final String namespace;

	private ClojureFunctions(final String name, final String namespace) {
		this.name = name;
		this.namespace = namespace;
	}

	@Override
	public final String getName() {
		return name;
	}

	@Override
	public String getNamespace() {
		return namespace;
	}
}
```

Next, you a need a java class that extending the `FunkyBridge` which will be annotated to use our created clojure functin
```java
@Funky(driver = FunkyDriver.CLOJURE, target = "de/windler/example/calculations.clj")
public class SimpleEngine extends FunkyBridge {

	public SimpleEngine() throws FunkyException {
		super();
	}

	public long multiply(int x, in y) throws FunkyException {
		return invoke(ClojureFunctions.CLOJ_MULTI, Long.class, x, y)
				.getValue();
	}
}
```

Thats it. You can now run your clojure functions by calling your java method
```java
(new SimpleEngine()).multiply(10,5);
```

### Async execution
You can also run `async` function like the following
```clojure
(defn async [i] 
  (if (= i 0) 1
    (+ 1 (async (- i 1)))))
```

Instead of waiting for the result you can now use the `invokeAndNotify` instead of the `invoke` method within your java service
```java
public void calcAsync(FunkyInvocationFinishedListener<?> listener) throws FunkyException {
	invokeAndNotify(listener, ClojureFunctions.CLOJ_ADD_ASYNC, 1000);
}
```

When the executions finishes the `FunkyInvocationFinishedListener` will be called.

### Load clojure source on demand
When the `FunkyBridge` is created the source file will be parsed. If you want to parse it when `invoke` is called then just annotate `@FunkyLoadOnDemand`.

## Initialize bridge in thread
If you want to init the `FunkyBridge` immidiatly but in background you can annotate `@FunkyInitializeInThread`. By calling `FunkyBridge#addInitializedListener` you can register a callback that is called when the thread finished.

### Constants from clojure
If you want a field in your `FunkyBridge` to be a constant from the clojure source then just create a field with the name of the `def` to call and annotate it with `@FunkyConstant`. Remmeber that this `def` must not need any arguments. Alternatively, you can annotate `@FunkyImmutableConstant` and make the field of type `FunkyImmutableValue`.

```clojure
(defn pi [] (/ 3.14 1))
```

```java 
//...extends FunkyBridge
//(...)

@FunkyConstant
private Double pi;

@FunkyImmutableConstant
private FunkyImmutableValue<Double> pi;
```

## Future work
This projects was a prrof of concept is is no longer under development. If you like the prject, feel free to contact me, create issues or fork the project. There are also some trivial examples located within the sources.
