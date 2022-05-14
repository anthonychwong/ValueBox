package mutable_box;

import dev.anthonychwong.value_box.BoxMutable;
import dev.anthonychwong.value_box.ValueHolder;
import dev.anthonychwong.value_box.VoidHolder;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class CallerManagedBoxesExamples {
    private static String MSG_DIVIDED_BY_ZERO = "Division by zero";

    public static BoxMutable<BigDecimal, Exception> getValue(final BoxMutable<BigDecimal, Exception> output) {
        output.setValue(BigDecimal.ONE);
        return output;
    }

    public static BoxMutable<BigDecimal, Exception> getVoid(final BoxMutable<BigDecimal, Exception> output) {
        try {
            output.setValue(BigDecimal.ONE.divide(BigDecimal.ZERO));
        } catch (final Exception e) {
            output.setVoidWithPayload(e);
        }

        return output;
    }

    @Test
    public void testValue() {
        final var box = new BoxMutable<BigDecimal, Exception>(new IllegalStateException());

        if(getValue(box).unbox() instanceof final ValueHolder<BigDecimal, Exception> valueHolder) {
            assertEquals(BigDecimal.ONE, valueHolder.getValue());
        } else {
            fail();
        }
    }

    @Test
    public void testVoid() {
        final var box = new BoxMutable<BigDecimal, Exception>(new IllegalStateException());

        if(getVoid(box).unbox() instanceof final VoidHolder<BigDecimal, Exception> voidHolder) {
            assertEquals(ArithmeticException.class, voidHolder.getVoidPayload().getClass());
            assertEquals(MSG_DIVIDED_BY_ZERO, voidHolder.getVoidPayload().getMessage());
        } else {
            fail();
        }
    }

    @Test
    public void testMultipleUpdates() {
        final var box = new BoxMutable<BigDecimal, Exception>(new IllegalStateException());

        if(getVoid(box).unbox() instanceof final VoidHolder<BigDecimal, Exception> voidHolder) {
            assertEquals(ArithmeticException.class, voidHolder.getVoidPayload().getClass());
            assertEquals(MSG_DIVIDED_BY_ZERO, voidHolder.getVoidPayload().getMessage());
        } else {
            fail();
        }

        if(getValue(box).unbox() instanceof final ValueHolder<BigDecimal, Exception> valueHolder) {
            assertEquals(BigDecimal.ONE, valueHolder.getValue());
        } else {
            fail();
        }
    }
}
