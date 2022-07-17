package net.clesperanto.converters.implementations;

import net.clesperanto.clicwrapper.clesperantojWrapper;
import net.clesperanto.converters.AbstractConverter;
import net.clesperanto.converters.ConverterPlugin;
import net.imglib2.Cursor;
import net.imglib2.RandomAccessibleInterval;
import net.imglib2.type.numeric.RealType;
import net.imglib2.type.numeric.integer.ByteType;
import net.imglib2.type.numeric.integer.ShortType;
import net.imglib2.type.numeric.integer.UnsignedByteType;
import net.imglib2.type.numeric.integer.UnsignedShortType;
import net.imglib2.type.numeric.real.FloatType;
import net.imglib2.view.Views;
import org.scijava.plugin.Plugin;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

@Plugin(type = ConverterPlugin.class)
public class RandomAccessibleIntervalToObjectJConverter extends AbstractConverter<RandomAccessibleInterval, clesperantojWrapper.ObjectJ> {

    @Override
    public clesperantojWrapper.ObjectJ convert(RandomAccessibleInterval source) {
        return convert_int(source);
    }

    private <T extends RealType<T>> clesperantojWrapper.ObjectJ convert_int(RandomAccessibleInterval source) {
        long[] dimensions = new long[source.numDimensions()];
        source.dimensions(dimensions);

        long[] dimensions3d = {1,1,1};
        long numberOfPixels = 1;
        for (int i = 0; i < dimensions.length; i++) {
            numberOfPixels *= dimensions[i];
            dimensions3d[i] = dimensions[i];
        }
        RealType pixel = (RealType) (Views.iterable(source).firstElement());

        int count = 0;
        Cursor<T> cursor = Views.iterable(source).cursor();

        if (pixel instanceof UnsignedByteType ||
            pixel instanceof ByteType) {

            byte[] inputArray = new byte[(int) numberOfPixels];
            while (cursor.hasNext()) {
                inputArray[count] = (byte) cursor.next().getRealFloat();
                count++;
            }
            ByteBuffer byteBuffer = ByteBuffer.wrap(inputArray);
            return cle._native.CharPush(byteBuffer, (int)dimensions3d[0], (int)dimensions3d[1], (int)dimensions3d[2]);
        } else if (pixel instanceof ShortType ||
                   pixel instanceof UnsignedShortType) {

            short[] inputArray = new short[(int) numberOfPixels];
            while (cursor.hasNext()) {
                inputArray[count] = (short) cursor.next().getRealFloat();
                count++;
            }
            ShortBuffer shortBuffer = ShortBuffer.wrap(inputArray);
            return cle._native.ShortPush(shortBuffer, (int)dimensions3d[0], (int)dimensions3d[1], (int)dimensions3d[2]);
        } else if (pixel instanceof FloatType) {
            float[] inputArray = new float[(int) numberOfPixels];
            while (cursor.hasNext()) {
                inputArray[count] = cursor.next().getRealFloat();
                count++;
            }
            FloatBuffer floatBuffer = FloatBuffer.wrap(inputArray);
            return cle._native.FloatPush(floatBuffer, (int)dimensions3d[0], (int)dimensions3d[1], (int)dimensions3d[2]);
        } else {
            throw new IllegalArgumentException("Cannot copy content of buffer because of unknown type.");
        }
    }

    @Override
    public Class<RandomAccessibleInterval> getSourceType() {
        return RandomAccessibleInterval.class;
    }

    @Override
    public Class<clesperantojWrapper.ObjectJ> getTargetType() {
        return clesperantojWrapper.ObjectJ.class;
    }
}
