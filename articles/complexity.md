---
layout: default
title: Tickator&#58; Complexity
permalink: /articles/complexity
---

Complexity
==========

Everyone from IT can name plenty of incidents caused by software. From crashes of satellites to crash of your grandma computer. Why so rich industry produces that deffective products? Why there is no simple way out of it?

Classical software
------------------

There are some factors that makes developing software hard. Let's discuss some of them.

First factor is non-determinism. Program executed on the same data is not executed in the exactly timing even if it uses single thread. You cannot say what will be program computing 10 seconds after its start, do you? It depends on current load of your computer, scheduling of OS, etc. Having multiple threads makes situation worse - it is extremely hard to use locking to assure that processing will happen in good order.

Second factor is poor encapsulation. Each piece of memory is potentially accessible from every location in source code - global variables, objects used from all parts of complex system, etc. Therefore is extremely hard to foreseen all impacts of change. Everything is related to everything. Despite all you-name-it methodologies. This makes hard to understand whole system, because it is hard to drill down/roll up levels of details - almost everything are text files.

Last factor I would like to mention is "strange parallelism of threads". Although we consider threads as tool for parallel computing they are somewhat limiting. When you think in threads terms you are still describing sequential programming, but just on less granular level. I will do "A->B->C" in parallel with "D->E->F". That is not **real** parallelism. 

Tickator
--------

I am afraid that I currently have more intuitive feelings than real arguments about Tickator advantages. So I will try to show you some immature arguments. Once I will have better evidence and understanding of Tickator technology roots I will come back and update this article.

> Talk is cheap. Show me the code. [Linus Torvalds]

determinism

complex system is hierarchical

real parallelism

