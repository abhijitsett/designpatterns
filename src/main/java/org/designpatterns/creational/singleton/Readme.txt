Ensure only one instance of a class exists and provide a global access point.

Inner static class is NOT loaded immediately

SingleTon class is loaded when first referenced

Holder class is NOT loaded at the same time

SingleTon.getInstance(); // <-- only now Holder is touched


📌 JVM loads Holder only when Holder.INSTANCE is accessed

This gives lazy initialization

2️⃣ Class loading in Java is thread-safe

The Java Language Specification guarantees:

Class initialization is synchronized by the JVM

That means:

Only ONE thread can initialize a class

Other threads block until initialization finishes

So when this line runs:

private static final SingleTon INSTANCE = new SingleTon();


✔ JVM ensures it happens once and only once, even with 100 threads

3️⃣ What happens if multiple threads call getInstance()?
Scenario

Thread T1 → calls getInstance()

Thread T2 → calls getInstance() at same time

JVM behavior

T1 triggers loading of Holder

JVM locks class initialization

T1 creates INSTANCE

JVM marks Holder as initialized

T2 gets the same INSTANCE

🚫 No race condition
🚫 No double creation
🚫 No synchronized needed