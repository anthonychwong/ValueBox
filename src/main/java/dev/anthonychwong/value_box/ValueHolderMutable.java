package dev.anthonychwong.value_box;

final class ValueHolderMutable<T, V> implements ValueHolder<T, V> {
    private T value;

    @Override
    public T getValue() {
        return value;
    }

    void setValue(T value) {
        this.value = value;
    }
}
