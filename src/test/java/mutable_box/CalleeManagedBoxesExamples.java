package mutable_box;

import dev.anthonychwong.value_box.Box;
import dev.anthonychwong.value_box.BoxMutable;
import dev.anthonychwong.value_box.ValueHolder;
import dev.anthonychwong.value_box.VoidHolder;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class CalleeManagedBoxesExamples {
    public static UUID MOCK_UUID = UUID.randomUUID();
    public static int ERROR_CODE = 418;
    private static BoxMutable<UUID, Integer> responseBox = new BoxMutable<>(0);

    public static Box<UUID, Integer> createItemWithSuccess() {
        responseBox.setValue(MOCK_UUID);
        return responseBox;
    }

    public static Box<UUID, Integer> createItemWithFailureCode() {
        responseBox.setVoidWithPayload(ERROR_CODE);
        return responseBox;
    }

    @Test
    public void testValue() {
        final var actual = createItemWithSuccess();

        if(actual.unbox() instanceof final ValueHolder<UUID, Integer> valueHolder) {
            assertEquals(MOCK_UUID, valueHolder.getValue());
        } else {
            fail();
        }
    }

    @Test
    public void testVoid() {
        final var actual = createItemWithFailureCode();

        if(actual.unbox() instanceof final VoidHolder<UUID, Integer> valueHolder) {
            assertEquals(ERROR_CODE, valueHolder.getVoidPayload());
        } else {
            fail();
        }
    }
}
