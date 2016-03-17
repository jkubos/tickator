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

### Non-determinism

Program executed on the same data is not executed in the exactly timing even if it uses single thread. You cannot say what will be program computing 10 seconds after its start, do you? It depends on current load of your computer, scheduling of OS, etc. Having multiple threads makes situation worse - it is extremely hard to use locking to assure that processing will happen in good order.

### Poor encapsulation

Each piece of memory is potentially accessible from every location in source code - global variables, objects used from all parts of complex system, etc. Therefore is extremely hard to foreseen all impacts of change. Everything is related to everything. Despite all you-name-it methodologies. This makes hard to understand whole system, because it is hard to drill down/roll up levels of details - almost everything are text files.

### Poor parallelism of threads

Although we consider threads as tool for parallel computing they are somewhat limiting. When you think in threads terms you are still describing sequential programming, but just on less granular level. I will do "A->B->C" in parallel with "D->E->F". That is not **real** parallelism. 

Tickator
--------

I am afraid that I currently have more intuitive feelings than real arguments about Tickator advantages. So I will try to show you some immature arguments. Once I will have better evidence and understanding of Tickator technology roots I will come back and update this article.

### Determinism

Reaction on event is absolutelly deterministic in Tickator. You can write into manual: "When input A arrive there is output in 42 ticks." This can be done even on complex component. When running your program twice on the same data, you will get same results, inluding number of ticks. This feature may be used also for security. You can detect that calculation took more time than expected and therefore someone may trying to change your data.

On interfaces of every Ticklet or component you must consider only **finite** number of possible input events. For two inputs, one, second, both or none input may arrive in the same tick.

### Hierarchical structure

Would you say that Internet network is complex? Is our body complex? Is country goverment complex? Is hardware of your computer complex? Well, yes and no. Depends on granularity you use.

Internet have layers and protocols - when looking on one it seems simple - there is some format of headers, timing and routing. When looking on whole Internet (many layers and protocols simultaneously) is is incredibly complex.

One single cell of your body can be quite described and understood, including iterations with neighbour systems. When looking on all those organs and systems playing together it seems as incredibly complex system.

Why is goverment organized into hierarchy? City part, city, district, region, country. That is because each level shields most of problems below and upper level can meaningfully handle it. So person handling upper levels, like prime minister or president, must be able to handle more than small city major, but not that much. Just remeber how "well" works centrally planed goverment - like one in North Korea.

Hardware is similar tool as software - both are used for computation. In fact, software is extension of hardware. When looking on hardware on arbitrary level, there is only limited complexity. Bus has protocol, CPU is composed from a few cores, core from execution units, execution unit from logic blocks, logic block from gates, gates from transistors. Again: when looking on whole system it seems incredibly complex. When looking on single level, you can prepare simple documentation that describe it. Typically it can be rendered as simple picture.

Hopefully Tickator will behave similarly to mentioned systems as it is mimic them.

### True parallelism

Every ticklet and component works by default in parallel. No need to explicitly mark them. In case part of your computation is sequential then just connect ticklets and components into chain. Later items are executed as soon as their predcessors finish. 
