1. used to minimize memory usage by sharing common state among a large number of similar objects

Problem (Why Flyweight?)

Imagine:

Thousands / millions of objects

Most of their data is identical

Huge memory waste

Flyweight Structure :

Flyweight (shared)
   ↑
FlyweightFactory → manages cache
   ↑
Client → passes extrinsic state
