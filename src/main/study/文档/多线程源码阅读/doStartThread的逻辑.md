ThreadExecutorMap这个类的作用是什么呢?

## doStartThread

executor:ThreadExecutorMap

```
executor.execute();
```

然后调用

```java
public static Executor apply(final Executor executor, final EventExecutor eventExecutor) {
    ObjectUtil.checkNotNull(executor, "executor");
    ObjectUtil.checkNotNull(eventExecutor, "eventExecutor");
    return new Executor() {
        @Override
        public void execute(final Runnable command) {
            //会调用threadFactory.newThread(command).start();
            executor.execute(apply(command, eventExecutor));
        }
    };
}
//ThreadPerTaskExecutor:threadFactory.newThread(command).start();
public static Runnable apply(final Runnable command, final EventExecutor eventExecutor) {
        ObjectUtil.checkNotNull(command, "command");
        ObjectUtil.checkNotNull(eventExecutor, "eventExecutor");
        return new Runnable() {
            @Override
            public void run() {
                setCurrentEventExecutor(eventExecutor);
                try {
                    command.run();
                } finally {
                    setCurrentEventExecutor(null);
                }
            }
        };
    }
```

```java
//This interface provides a way of decoupling task submission from the mechanics of how each task will be //run, including details of thread use, scheduling, etc.
public interface Executor {
	 void execute(Runnable command);
}
```

