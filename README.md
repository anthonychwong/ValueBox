# ValueBox

## What is it?

Value box can hold either a value, or otherwise additional information, named void payload. To access the content inside, you first need to determine if it holds a value or not, than "unbox" it to access the content inside, either value, or void payload.

## Simple Examples

### Producer

```Java
// assume an REST API call created an resource and return the ID
final var valueResponse = BoxImmutable.<Long, Integer>withValue(1098L);

// when calling an REST API and oops, not found
final var voidResponse = BoxImmutable.<Long, Integer>withVoidPayload(404);
```

### Consumer

```Java
// unbox and get the created ID (value) from it
if(valueResponse.unbox() instanceof final ValueHolder<Long, Integer> resourceCreatedHolder) {
    System.out.println("remote created resource with id: " + resourceCreatedHolder.getValue());
}

// unbox and check the error code (void payload)
if(voidResponse.unbox() instanceof final VoidHolder<Long, Integer> voidHolder) {
    System.out.println("server return with error code: " + voidHolder.getVoidPayload());
}
```

Check test cases for more examples.

## How does it different from Optional in Java?

* Optional is immutable, while value boxes also come with mutable counterpart, and both immutable and mutable boxes can be used as unmodifiable boxes.

* Optional only represents `null` or empty, but value boxes allow additional information, e.g. error code or exceptions, to be delivered with the empty result.

* Optional may throw `NoSuchElementException` when try to get the value, while value boxes force you the check if it holds value before you can perform get operation.

## TODO

- [ ] confirm compatibility with Java 8
- [ ] thread safety
- [ ] more lambda friendly
