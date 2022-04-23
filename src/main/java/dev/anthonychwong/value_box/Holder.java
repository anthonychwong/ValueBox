package dev.anthonychwong.value_box;

public sealed interface Holder<T, V> permits ValueHolder, VoidHolder {
    boolean hasValue();
}
