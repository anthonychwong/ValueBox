package dev.anthonychwong.value_box;

public sealed interface VoidHolder<T, V> extends Holder<T, V> permits VoidHolderImmutable, VoidHolderMutable {
    V getVoidPayload();

    @Override
    default boolean hasValue() {
        return false;
    };
}
