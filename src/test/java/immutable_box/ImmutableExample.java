package immutable_box;

import dev.anthonychwong.value_box.Box;
import dev.anthonychwong.value_box.BoxImmutable;
import dev.anthonychwong.value_box.ValueHolder;
import dev.anthonychwong.value_box.VoidHolder;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public final class ImmutableExample {
    private static String MSG_DIVIDED_BY_ZERO = "Division by zero";

    public static Box<BigDecimal, Exception> getValue() {
        return BoxImmutable.withValue(BigDecimal.ONE);
    }

    public static Box<BigDecimal, Exception> getVoid() {
        try {
            return BoxImmutable.withValue(BigDecimal.ONE.divide(BigDecimal.ZERO));
        } catch (final Exception e) {
            return BoxImmutable.withVoidPayload(e);
        }
    }

    @Test
    public void testValue() {
        final var actual = getValue();

        if(actual.unbox() instanceof ValueHolder<BigDecimal, Exception> valueHolder) {
            assertEquals(BigDecimal.ONE, valueHolder.getValue());
        } else {
            fail();
        }
    }

    @Test
    public void testVoid() {
        final var actual = getVoid();

        if(actual.unbox() instanceof VoidHolder<BigDecimal, Exception> voidHolder) {
            assertInstanceOf(ArithmeticException.class, voidHolder.getVoidPayload());
            assertEquals(MSG_DIVIDED_BY_ZERO, voidHolder.getVoidPayload().getMessage());
        } else {
            fail();
        }
    }
}
