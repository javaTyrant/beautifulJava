package ref;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
 * @author lumac
 * @since 2020/6/22
 */
public class RefDemo {
    static class A {
        private C c;

        public A(C c) {
            this.c = c;
        }

        public C getC() {
            return c;
        }

        @Override
        public void finalize() {
            System.out.println("A cleaned");
        }
    }

    static class B {

        @Override
        public void finalize() {
            System.out.println("B cleaned");
        }
    }

    static class C {
        private D d;

        public C(B b) {
            d = new D(new WeakReference<B>(b));
        }

        public D getD() {
            return d;
        }

        @Override
        public void finalize() {
            System.out.println("C cleaned");
        }
    }

    static class D {
        private WeakReference<B> bRef;

        public D(WeakReference<B> bRef) {
            this.bRef = bRef;
        }

        public B getB() {
            return bRef.get();
        }

        @Override
        public void finalize() {
            System.out.println("D cleaned");
        }
    }

    public static void main(String[] args) {
        B b = new B();
        WeakReference<B> bRef = new WeakReference<B>(b);
        C c = new C(b);
        A a = new A(c);
        b = null;
        WeakReference<String> stringWeakReference = new WeakReference<>("hello");
        char[] chars = {'a', 'b', 'c'};
        WeakReference<String> r = new WeakReference<>(new String(chars).intern());
        SoftReference<String> r1 = new SoftReference<>(new String(chars).intern());
        System.out.println("Run gc");
        Runtime.getRuntime().gc();
        System.out.println(stringWeakReference.get());
        System.out.println("被清除了吗" + r.get());
        System.out.println("r1被清除了吗" + r1.get());
        System.out.println("bRef's referent:" + bRef.get());
        System.out.println("bRef's referent thru a->c->d->bRef:" + a.getC().getD().getB());
    }
}
