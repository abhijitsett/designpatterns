StampedLock is a high-performance lock designed to make reads extremely fast when writes are rare.

The core idea (intuition)

Traditional locks say:

“If you want to read, you must lock.”

StampedLock says:

“Try reading without locking.
If no one writes while you read, you’re good.
If someone writes, I’ll tell you and you retry safely.”

That’s the entire point of StampedLock.

StampedLock provides three modes:

Write lock – exclusive

Read lock – shared

Optimistic read – non-blocking, fastest


| Feature          | Read Lock | Optimistic Read |
| ---------------- | --------- | --------------- |
| Blocks writers   | ✅ Yes     | ❌ No            |
| Blocks readers   | ❌ No      | ❌ No            |
| Actual lock held | ✅ Yes     | ❌ No            |
| Needs validation | ❌ No      | ✅ Yes           |
| Performance      | Medium    | 🔥 Highest      |
| Risk of retry    | ❌ No      | ✅ Yes           |
