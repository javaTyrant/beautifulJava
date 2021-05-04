package safeRecursion;

import java.util.function.Supplier;

/**
 * @author lumac
 * @since 2020-05-13
 */
public abstract class TailCall<T> {
    public abstract TailCall<T> resume();

    public abstract T eval();

    public abstract boolean isSuspend();

    private static class Return<T> extends TailCall {
        private final T t;

        public Return(T t) {
            this.t = t;
        }

        @Override
        public TailCall resume() {
            throw new IllegalStateException("return has no resume");
        }

        @Override
        public Object eval() {
            return t;
        }

        @Override
        public boolean isSuspend() {
            return false;
        }
    }

    private static class Suspend<T> extends TailCall<T> {
        private final Supplier<TailCall<T>> resume;

        public Suspend(Supplier<TailCall<T>> resume) {
            this.resume = resume;
        }

        @Override
        public TailCall<T> resume() {
            return resume.get();
        }

        @Override
        public T eval() {
            TailCall<T> tail = this;
            while (tail.isSuspend()) {
                tail = tail.resume();
            }
            return tail.eval();
        }

        @Override
        public boolean isSuspend() {
            return true;
        }
    }

    public static <T> Suspend<T> sus(Supplier<TailCall<T>> supplier) {
        return new Suspend<>(supplier);
    }

    public static <T> Return<T> ret(T t) {
        return new Return<>(t);
    }
}
