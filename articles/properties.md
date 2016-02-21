---
layout: default
title: Hujer
permalink: /articles/properties
---

Properties
==========

Lock-free
---------

There is no reason to use system-wide lock that you know from thread-based processing. System wide locks slows down whole system, see for reference:

*   [Java Lock Implementations](http://mechanical-sympathy.blogspot.cz/2011/11/java-lock-implementations.html) with benchmarks
*   [Global interpreter lock](https://en.wikipedia.org/wiki/Global_interpreter_lock)
*   [Giant lock](https://en.wikipedia.org/wiki/Giant_lock)

In Tickator is **global tick the locking mechanism**. Because of [rules](/articles/rules) there is no way two ticklets may contend for the same data:

*   Only ticklet can alter its internal state
*   Ticklet outputs are stable during tick
*   New port values are calculated during tick and published in next tick. So there is no chance receiver will receive half-done value.

There are situations when it is necessary to use locking in Tickator. For exaple you create complex ticklet component with many ticklets inside and you want to assure that no new input handling will be started unless previous is finished. This situation may be always solved by local lock composed of ticklets.

Masivelly parallel
------------------

Because Tickator obeys all fundamental [rules](/articles/rules), it may be executed by any number of units - threads, CPU cores, processes, separate CPUs, ... There is no chance two units will contend for the same data. So if you have single core CPU in your computer - fine. If you have 32 CPU beast - fine.

In far future we may reach optimal state - there will be 1:1 mapping between CPU core and ticklet. But there is a long way to have it.

Change driven
-------------

Ticklets are executed on change. So if there is no change in input there is no need to recompute anything. That can save a lot of performance. You probably know the [TwoHardThings](http://martinfowler.com/bliki/TwoHardThings.html) sentence about cache. This is not true in Tickator - caching is for free! Problem with naming things unfortunatelly persists.

Deterministic
-------------

In thread-based parallelism there is no determinism. Every time you execute your program will thread process data in different order and timing. In Tickator will computation behave exactly the same - if initial conditions are same. You will ever know that for some input there should be output in exactly 124 ticks. This property can be used for testing and security purposes.

Implicitly parallel
-------------------

There is no need to explictly mark parallelism. Just place two ticklets next to each other, connect them to inputs and they will work in parallel.

Explicitly sequential
---------------------

If you need to do sequential task it is easy - just connect outputs of one ticklet to inputs of another... And you have sequence.

Schema based
------------

I am afraid this goal will not be reach immediatelly after start of Tickator project. But in future programmer should just create components, connect them to other in diagram, etc. No need to write text source code, compile it, etc. Imagine touch screen where you just compose your program from prepared components.

Ticklets are ideal components
-----------------------------

Ticlet component has strictly defined inputs and outputs and there is no other way to influence its behavior. Therefore you can replace component with another one with the same inputs and outputs.
