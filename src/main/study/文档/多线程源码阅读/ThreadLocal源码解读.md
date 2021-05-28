1.ThreadLocal不remove会发生什么情况?

2.ThreadLocal 本身并不存储值，它只是作为一个 key 来让线程从 ThreadLocalMap 获取 value。值得注意的是图中的虚线，表示 ThreadLocalMap 是使用 ThreadLocal 的弱引用作为 Key 的，弱引用的对象在 GC 时会被回收。

3.'ThreadLocalMap'的存储'k&v'的方式不是'**链表法**'而是'**开地址法**',为什么呢?

入口:

## set的逻辑:

1.先创建一个大小为16的数组.

2.确定第一个槽位,firstKey.threadLocalHashCode & (INITIAL_CAPACITY - 1) = 10(1253254570)

3.设置threshold = len * 2 / 3;

4.

```java
//1640531527 
public void set(T value) {
     	//获取当前线程
        Thread t = Thread.currentThread();
     	//getMap.线程已经有ThreadLocalMap了.
        ThreadLocalMap map = getMap(t);
     	//不为空,直接set
        if (map != null)
            map.set(this, value);
        else	
            //为空直接创建个map.入参thread value
            createMap(t, value);
    }
```

```java
//获取该线程的threadLocals
ThreadLocalMap getMap(Thread t) {
    return t.threadLocals;
}
```

```java
   	//
	void createMap(Thread t, T firstValue) {
        //new一个map.t接受新值.再传入一个
        t.threadLocals = new ThreadLocalMap(this, firstValue);
    }

```

```java
private Entry[] table;
ThreadLocalMap(ThreadLocal<?> firstKey, Object firstValue) {
    //初始化一个table
    table = new Entry[INITIAL_CAPACITY];
    //索引位置
    int i = firstKey.threadLocalHashCode & (INITIAL_CAPACITY - 1);
    //
    table[i] = new Entry(firstKey, firstValue);
    size = 1;
    setThreshold(INITIAL_CAPACITY);
}
```

## Entry内部类

弱引用.

```java
//弱引用  
static class Entry extends WeakReference<ThreadLocal<?>> {
     /** The value associated with this ThreadLocal. */
     Object value;
     Entry(ThreadLocal<?> k, Object v) {
         super(k);
         value = v;
      }
}
```



## get的逻辑



## remove的逻辑

```java
public void remove() {
    ThreadLocalMap m = getMap(Thread.currentThread());
    if (m != null)
        m.remove(this);
}
```

```java
//删除key,是一个threadLocal
private void remove(ThreadLocal<?> key) {
    		//获取table.
            Entry[] tab = table;
    		//获取长度
            int len = tab.length;
    		//位置
            int i = key.threadLocalHashCode & (len-1);
    		//如何遍历.先找到i的位置,如若不为空获取下一个index.why.
    		//tab[i]如何偏移的?
            for (Entry e = tab[i];e != null;e = tab[i = nextIndex(i, len)]) {
                //难道是在remove的时候
                if (e.get() == key) {
                    e.clear();
                    expungeStaleEntry(i);
                    return;
                }
            }
        }
```

```java
/* Treated specially by GC */
private T referent;   
public void clear() {
    this.referent = null;
}
```

```java
private static int nextIndex(int i, int len) {
    return ((i + 1 < len) ? i + 1 : 0);
}
```

清空该槽位.背后的思想.

Expunge a stale entry by rehashing any possibly colliding entries lying between staleSlot and the next null slot. This also expunges any other stale entries encountered before the trailing null. See Knuth, Section 6.4
Params:
staleSlot – index of slot known to have null key
Returns:
the index of the next null slot after staleSlot (all between staleSlot and this slot will have been checked for expunging).

逻辑总结:把当前位和后面位空的全部删除掉.

1.把staleSlot位置的k,v全部删除掉.然后size减1.

2.

```java
private int expungeStaleEntry(int staleSlot) {
    Entry[] tab = table;
    int len = tab.length;
    // expunge entry at staleSlot
    //value清空
    tab[staleSlot].value = null;
    //槽位清空
    tab[staleSlot] = null;
    //大小减1
    size--;
    //Rehash until we encounter null
    Entry e;
    int i;
    //获取下一个索引
    for (i = nextIndex(staleSlot, len);
         //如果e==null就执行操作,然后获取下一个索引.
         (e = tab[i]) != null;
         //或许下一个
         i = nextIndex(i, len)) {
        //e.get
        ThreadLocal<?> k = e.get();
        //如果为空
        if (k == null) {
            e.value = null;
            tab[i] = null;
            size--;
        } else {
            //新的索引位.
            int h = k.threadLocalHashCode & (len - 1);
            //如果h不在i的位置
            if (h != i) {
                //tab[i]置空
                tab[i] = null;
                // Unlike Knuth 6.4 Algorithm R, we must scan until
                // null because multiple entries could have been stale.
                while (tab[h] != null)//直到找到tab[i]为空的
                    h = nextIndex(h, len);
                //把e赋值给tab[h]
                tab[h] = e;
            }
        }
    }
    return i;
}
```

**深入分析 ThreadLocal 内存泄漏问题**

原文地址：http://www.importnew.com/22039.html

**摘要**

ThreadLocal 内存泄露

**前言**

ThreadLocal 的作用是提供线程内的局部变量，这种变量在线程的生命周期内起作用，减少同一个线程内多个函数或者组件之间一些公共变量的传递的复杂度。但是如果滥用ThreadLocal，就可能会导致内存泄漏。下面，我们将围绕三个方面来分析ThreadLocal 内存泄漏的问题

- ThreadLocal 实现原理
- ThreadLocal为什么会内存泄漏
- ThreadLocal 最佳实践

**ThreadLocal 实现原理**

ThreadLocal

ThreadLocal的实现是这样的：每个Thread 维护一个 ThreadLocalMap 映射表，这个映射表的 key 是 ThreadLocal实例本身，value 是真正需要存储的 Object。

也就是说 ThreadLocal 本身并不存储值，它只是作为一个 key 来让线程从 ThreadLocalMap 获取 value。值得注意的是图中的虚线，表示 ThreadLocalMap 是使用 ThreadLocal 的弱引用作为 Key 的，弱引用的对象在 GC 时会被回收。

**ThreadLocal为什么会内存泄漏**

ThreadLocalMap使用ThreadLocal的弱引用作为key，如果一个ThreadLocal没有外部强引用来引用它，那么系统 GC 的时候，这个ThreadLocal势必会被回收，这样一来，ThreadLocalMap中就会出现key为null的Entry，就没有办法访问这些key为null的Entry的value，如果当前线程再迟迟不结束的话，这些key为null的Entry的value就会一直存在一条强引用链：Thread Ref -> Thread -> ThreaLocalMap -> Entry -> value永远无法回收，造成内存泄漏。

其实，ThreadLocalMap的设计中已经考虑到这种情况，也加上了一些防护措施：在ThreadLocal的get(),set(),remove()的时候都会清除线程ThreadLocalMap里所有key为null的value。

但是这些被动的预防措施并不能保证不会内存泄漏：

- 使用static的ThreadLocal，延长了ThreadLocal的生命周期，可能导致的内存泄漏（参考ThreadLocal 内存泄露的实例分析）。
- 分配使用了ThreadLocal又不再调用get(),set(),remove()方法，那么就会导致内存泄漏。

**为什么使用弱引用**

从表面上看内存泄漏的根源在于使用了弱引用。网上的文章大多着重分析ThreadLocal使用了弱引用会导致内存泄漏，但是另一个问题也同样值得思考：为什么使用弱引用而不是强引用？

我们先来看看官方文档的说法：

*To help deal with very large and long-lived usages, the hash table entries use WeakReferences for keys.*
*为了应对非常大和长时间的用途，哈希表使用弱引用的 key。*

下面我们分两种情况讨论：

- **key** **使用强引用**：引用的ThreadLocal的对象被回收了，但是ThreadLocalMap还持有ThreadLocal的强引用，如果没有手动删除，ThreadLocal不会被回收，导致Entry内存泄漏。
- **key** **使用弱引用**：引用的ThreadLocal的对象被回收了，由于ThreadLocalMap持有ThreadLocal的弱引用，即使没有手动删除，ThreadLocal也会被回收。value在下一次ThreadLocalMap调用set,get，remove的时候会被清除。

比较两种情况，我们可以发现：由于ThreadLocalMap的生命周期跟Thread一样长，如果都没有手动删除对应key，都会导致内存泄漏，但是使用弱引用可以多一层保障：**弱引用ThreadLocal不会内存泄漏，对应的value在下一次ThreadLocalMap调用set,get,remove的时候会被清除**。

因此，ThreadLocal内存泄漏的根源是：由于**ThreadLocalMap**的生命周期跟Thread一样长，如果没有手动删除对应key就会导致内存泄漏，而不是因为弱引用。

**ThreadLocal** **最佳实践**

综合上面的分析，我们可以理解ThreadLocal内存泄漏的前因后果，那么怎么避免内存泄漏呢？

- 每次使用完ThreadLocal，都调用它的remove()方法，清除数据。

在使用线程池的情况下，没有及时清理ThreadLocal，不仅是内存泄漏的问题，更严重的是可能导致业务逻辑出现问题。所以，使用ThreadLocal就跟加锁完要解锁一样，用完就清理。

**PermGen exhaustions** in combination with `ThreadLocal` are often caused by **classloader leaks**.

*An example:*
Imagine an application server which has a pool of *worker threads*.
They will be kept alive until application server termination.
A deployed web application uses a **static** `ThreadLocal` in one of its classes in order to store some thread-local data, an instance of another class (lets call it `SomeClass`) of the web application. This is done within the worker thread (e.g. this action originates from a *HTTP request*).

**Important:**
[By definition](http://docs.oracle.com/javase/6/docs/api/java/lang/ThreadLocal.html), a reference to a `ThreadLocal` *value* is kept until the "owning" thread dies or if the ThreadLocal itself is no longer reachable.

If the web application **fails to clear the reference** to the `ThreadLocal` **on shutdown**, bad things will happen:
Because the worker thread will usually never die and the reference to the `ThreadLocal` is static, the `ThreadLocal` value **still references** the instance of `SomeClass`, a web application's class - **even if the web application has been stopped!**

As a consequence, the web application's **classloader cannot be garbage collected**, which means that **all classes** (and all static data) of the web application **remain loaded** (this affects the PermGen memory pool as well as the heap).
Every redeployment iteration of the web application will increase permgen (and heap) usage.

**=> This is the permgen leak**

One popular example of this kind of leak is [this bug](https://issues.apache.org/bugzilla/show_bug.cgi?id=50486) in log4j (fixed in the meanwhile).