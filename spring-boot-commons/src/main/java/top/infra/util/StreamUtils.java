package top.infra.util;

import java.util.Enumeration;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Consumer;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class StreamUtils {

    public static <T> Stream<T> enumerationAsStream(Enumeration<T> e) {
        // see: https://stackoverflow.com/questions/33242577/how-do-i-turn-a-java-enumeration-into-a-stream

        return StreamSupport.stream(
            new Spliterators.AbstractSpliterator<T>(Long.MAX_VALUE, Spliterator.ORDERED) {
                public boolean tryAdvance(Consumer<? super T> action) {
                    if (e.hasMoreElements()) {
                        action.accept(e.nextElement());
                        return true;
                    }
                    return false;
                }

                public void forEachRemaining(Consumer<? super T> action) {
                    while (e.hasMoreElements()) action.accept(e.nextElement());
                }
            }, false);
    }
}
