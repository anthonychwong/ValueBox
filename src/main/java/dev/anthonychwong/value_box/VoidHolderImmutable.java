package dev.anthonychwong.value_box;

record VoidHolderImmutable<T, V>(V getVoidPayload) implements VoidHolder<T, V> { }
