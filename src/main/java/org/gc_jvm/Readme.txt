| GC                           | Introduced / Available    | Description                                       | Best for                                  |
| ---------------------------- | ------------------------- | ------------------------------------------------- | ----------------------------------------- |
| **Serial GC**                | All JDK versions          | Single-threaded, stops all application threads    | Small apps, low-memory devices            |
| **Parallel GC (Throughput)** | Default until JDK 17      | Multi-threaded, focuses on high throughput        | Batch apps, high CPU, throughput-critical |
| **G1 GC**                    | JDK 9+, default in JDK 17 | Low-pause, concurrent, heap split into regions    | General-purpose apps, large heaps         |
| **ZGC**                      | JDK 11+                   | Low-latency, concurrent, supports heaps up to TBs | Ultra-low pause, large heaps              |
| **Shenandoah**               | JDK 12+                   | Low-pause, concurrent compaction                  | Apps with strict pause time requirements  |
| **Epsilon GC**               | JDK 11+                   | “No-op” GC, allocates memory without freeing      | Benchmarking, testing                     |
| **C2 + JEP 433** (Java 21)   | JDK 21                    | Generational + continuous improvements            | High-performance workloads                |


| Scenario                                   | Recommended GC |
| ------------------------------------------ | -------------- |
| Small memory, simple app                   | Serial GC      |
| Batch processing, high throughput          | Parallel GC    |
| General-purpose apps, moderate latency     | G1 GC          |
| Very large heap (>10GB), low pause (<10ms) | ZGC            |
| Low pause for responsive apps              | Shenandoah GC  |


How to tune GC for performance

1. Enable GC logs
java -Xlog:gc*:file=gc.log:time,uptime,level,tags

2. Choose GC
A. G1 (default JDK 17+):
-XX:+UseG1GC

B. ZGC
-XX:+UseZGC

C. Shenandoah:
-XX:+UseShenandoahGC

3. Set heap size
-Xms4g  -Xmx8g

Step 4: Tuning G1 GC (most common)
-XX:MaxGCPauseMillis=200      # target pause time in ms
-XX:InitiatingHeapOccupancyPercent=45  # triggers concurrent GC
-XX:G1HeapRegionSize=16m      # tune region size for large heap

Step 5: Tuning ZGC
-XX:MaxHeapSize=16g
-XX:+UseZGC
-XX:SoftMaxHeapSize=12g       # optional soft max heap

Step 6: Shenandoah tuning
-XX:+UseShenandoahGC
-XX:ShenandoahGCHeuristics=adaptive
-XX:ShenandoahGuaranteedGCInterval=60000



GC tuning tips

    Measure first: Always profile using jcmd <pid> GC.heap_info or JDK Mission Control.
    Heap sizing matters: Avoid too small heap → frequent GC, too large → long pause in Serial/Parallel.
    Pause time vs throughput tradeoff:
    Small pause → more CPU for GC → lower throughput
    Higher throughput → longer pauses
    Concurrent GCs (G1, ZGC, Shenandoah) reduce pause but increase CPU usage.
    Avoid over-tuning: Default G1/ZGC is very good for most applications in Java 17+.

