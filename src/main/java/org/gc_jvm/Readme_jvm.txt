JVM Memory Areas :

| Area              | Purpose                                        | Notes                               |
| ----------------- | ---------------------------------------------- | ----------------------------------- |
| **Heap**          | Stores objects & arrays                        | Shared among threads, GC-managed    |
| **Stack**         | Stores method frames, local variables          | One per thread                      |
| **Metaspace**     | Stores class metadata (class structures)       | Replaced PermGen since JDK 8        |
| **PC Registers**  | Stores current instruction address             | One per thread                      |
| **Native Memory** | For JVM internal structures, JIT compiled code | Managed by OS, off-heap allocations |

Heap Layout

    Young Generation
        Eden: new objects created here
        Survivor spaces (S0/S1): short-lived objects before promotion

    Old Generation / Tenured
        Long-lived objects

    Permanent Generation (PermGen) → Metaspace
        Class metadata, static variables

Class Loading Mechanism

JVM uses a class loader hierarchy:

    1. Bootstrap ClassLoader → core Java classes (rt.jar)
    2. Platform ClassLoader → JDK 9+ platform classes (java.sql, java.xml)
    3. Application / System ClassLoader → user classes from classpath
    4. Custom ClassLoaders → frameworks (Tomcat, Spring) use for hot deployment

Class loading steps:
1. Load → Read .class bytecode
2. Link → Verify, prepare (memory allocation), resolve
3. Initialize → Static blocks, static fields initialized

JIT = Just-In-Time Compiler

    1. It’s a part of the JVM that dynamically converts Java bytecode into native machine code at runtime.
    2. Java is compiled into bytecode (.class files) which is platform-independent.
    3. The JVM can interpret bytecode, but interpreting is slower than running native code.
    4. JIT improves performance by compiling “hot” (frequently executed) code into optimized machine code.

