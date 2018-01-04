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
