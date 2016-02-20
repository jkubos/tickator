---
layout: default
title: Hujer
permalink: /articles/properties
---

Properties
==========

Lock-free
---------

Masivelly parallel
------------------

Because Tickator obeys all fundamental rules, it may be executed by any number of units - threads, CPU cores, processes, separate CPUs, ... So if you have single core CPU in your computer - fine. If you have 32 CPU beast - fine.

In far future we may reach optimal state - there will be 1:1 mapping between CPU core and ticklet. But there is a long way to have it.

Change driven
-------------

Ticklets are executed on change. So if there is no change in input there is no need to recompute anything. That can save a lot of performance. You probably know the [TwoHardThings](http://martinfowler.com/bliki/TwoHardThings.html) sentence about cache. This is not true in Tickator - caching is for free! Problem with naming things unfortunatelly persists.

Deterministic
-------------

Implicitly parallel
-------------------

There is no need to explictly mark parallelism. Just place two ticklets next to each other, connect them to inputs and they will work in parallel.

Explicitly sequential
---------------------

If you need to do sequential task it is easy - just connect outputs of one ticklet to inputs of another... And you have sequence.

Schema based
------------

I am afraid this goal will not be reach immediatelly after start of Tickator project. But in future programmer should just create components, connect them to other, etc. No need to write text source code, compile it, etc. Imagine touch screen where you just compose your program from prepared components.

Ticklets are ideal components
-----------------------------
