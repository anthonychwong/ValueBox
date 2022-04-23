package dev.anthonychwong.value_box;

public final class BoxImmutable<T, V> implements Box<T, V> {
    public static<T, V> BoxImmutable<T, V> withValue(final T value) {
        return new BoxImmutable<>(new ValueHolderImmutable<>(value));
    }

    public static<T, V> BoxImmutable<T, V> withVoidPayload(final V payload) {
        return new BoxImmutable<>(new VoidHolderImmutable<>(payload));
    }

    private final Holder<T, V> holder;

    private BoxImmutable(final Holder<T, V> payload) {
        this.holder = payload;
    }

    @Override
    public Holder<T, V> unbox() {
        return holder;
    }
}
