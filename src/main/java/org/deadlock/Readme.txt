Deadlock occurs when two or more threads wait forever for locks held by each other.

4 Necessary Conditions (Coffman Conditions)

    1. Mutual exclusion – only one thread can hold a lock
    2. Hold and wait – thread holds one lock and waits for another
    3. No preemption – locks can’t be forcibly taken
    4. Circular wait – circular chain of waiting threads