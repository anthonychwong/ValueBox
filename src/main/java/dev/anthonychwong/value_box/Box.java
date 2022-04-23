package dev.anthonychwong.value_box;

public interface Box<T, V> {
    Holder<T, V> unbox();
}
