package dev.anthonychwong.value_box;

import net.jcip.annotations.NotThreadSafe;

@NotThreadSafe
public final class BoxMutable<T, V> implements Box<T, V> {
    private final ValueHolderMutable<T, V> valueHolder = new ValueHolderMutable<>();
    private final VoidHolderMutable<T, V> voidHolder = new VoidHolderMutable<>();

    private Holder<T, V> holder;

    public BoxMutable(final V payload) {
        setVoidWithPayload(payload);
    }

    public void setVoidWithPayload(final V payload) {
        voidHolder.setPayload(payload);
        holder = voidHolder;
    }

    public void setValue(final T value) {
        valueHolder.setValue(value);
        holder = valueHolder;
    }

    @Override
    public Holder<T, V> unbox() {
        return holder;
    }
}
