# [Guava cache使用总结](https://www.cnblogs.com/rickiyang/p/11074159.html)

缓存分为本地缓存和远端缓存。常见的远端缓存有Redis，MongoDB；本地缓存一般使用map的方式保存在本地内存中。一般我们在业务中操作缓存，都会操作缓存和数据源两部分。如：put数据时，先插入DB，再删除原来的缓存；ge数据时，先查缓存，命中则返回，没有命中时，需要查询DB，再把查询结果放入缓存中 。如果访问量大，我们还得兼顾本地缓存的线程安全问题。必要的时候也要考虑缓存的回收策略。

今天说的 Guava Cache 是google guava中的一个内存缓存模块，用于将数据缓存到JVM内存中。他很好的解决了上面提到的几个问题：

- 很好的封装了get、put操作，能够集成数据源 ；
- 线程安全的缓存，与ConcurrentMap相似，但前者增加了更多的**元素失效策略**，后者只能显示的移除元素；
- Guava Cache提供了三种基本的缓存回收方式：1.基于容量回收、2.定时回收和3.基于引用回收。定时回收有两种：按照写入时间，最早写入的最先回收；按照访问时间，最早访问的最早回收；
- 监控缓存加载/命中情况

Cache存储的是键值对的集合，不同时是还需要处理缓存过期、动态加载等算法逻辑，需要额外信息实现这些操作，对此根据面向对象的思想，还需要做方法与数据的关联性封装，主要实现的缓存功能有：自动将节点加载至缓存结构中，当缓存的数据超过最大值时，使用LRU算法替换；它具备根据节点上一次被访问或写入时间计算缓存过期机制，缓存的key被封装在WeakReference引用中，缓存的value被封装在WeakReference或SoftReference引用中；还可以统计缓存使用过程中的命中率、异常率和命中率等统计数据。

## 过期数据清除和触发监听的原理

调用过程:

get->

```java
 @Override
    public V get(K key) throws ExecutionException {
      return localCache.getOrLoad(key);
    }
```



