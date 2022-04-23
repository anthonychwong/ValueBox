package dev.anthonychwong.value_box;

public sealed interface ValueHolder<T, V> extends Holder<T, V> permits ValueHolderMutable, ValueHolderImmutable {
    T getValue();

    @Override
    default boolean hasValue() {
        return true;
    }
}
