package dev.anthonychwong.value_box;

final class VoidHolderMutable<T, V> implements VoidHolder<T, V> {
    private V payload;

    @Override
    public V getVoidPayload() {
        return payload;
    }

    void setPayload(V payload) {
        this.payload = payload;
    }
}
