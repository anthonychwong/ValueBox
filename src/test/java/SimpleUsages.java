import dev.anthonychwong.value_box.BoxImmutable;
import dev.anthonychwong.value_box.ValueHolder;
import dev.anthonychwong.value_box.VoidHolder;
import dev.anthonychwong.value_box.BoxMutable;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.util.Optional;

public class SimpleUsages {
    public enum BigDecimalVoidTag
    {
        Undefined,
        NaN,
        Infinity,
        OtherErrors
    }

    @Test
    public void mutableBoxTestBed() {
        final BoxMutable<BigDecimal, BigDecimalVoidTag> subject = new BoxMutable<>(BigDecimalVoidTag.Undefined);

        if(subject.unbox() instanceof final VoidHolder<BigDecimal, BigDecimalVoidTag> voidHolder) {
            System.out.println("initially, box is void with payload: " + voidHolder.getVoidPayload());
        }

        // say, some calculation results in NaN
        subject.setVoidWithPayload(BigDecimalVoidTag.NaN);

        if(subject.unbox() instanceof final VoidHolder<BigDecimal, BigDecimalVoidTag> voidHolder) {
            System.out.println("after some calculation, box is still void with payload: " + voidHolder.getVoidPayload());
        }

        // now we have a value
        subject.setValue(BigDecimal.ONE);

        if(subject.unbox() instanceof final ValueHolder<BigDecimal, BigDecimalVoidTag> valueHolder) {
            System.out.println("finally, after another calculation, box comes with a value: " + valueHolder.getValue());
        }
    }

    @Test
    public void immutableBoxTestBed() {
        final var voidResponse = BoxImmutable.<Long, Integer>withVoidPayload(404);

        if(voidResponse.unbox() instanceof final VoidHolder<Long, Integer> voidHolder) {
            System.out.println("server return with error code: " + voidHolder.getVoidPayload());
        }

        final var valueResponse = BoxImmutable.<Long, Integer>withValue(1098L);

        if(valueResponse.unbox() instanceof final ValueHolder<Long, Integer> resourceCreatedHolder)
        {
            System.out.println("remote created resource with id: " + resourceCreatedHolder.getValue());
        }
    }
}
