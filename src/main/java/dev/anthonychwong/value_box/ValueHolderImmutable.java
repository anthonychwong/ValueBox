package dev.anthonychwong.value_box;

record ValueHolderImmutable<T, V>(T getValue) implements ValueHolder<T, V> { }
