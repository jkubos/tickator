---
layout: default
title: Tickator&#58; Alive all the time
permalink: /articles/alive-all-the-time
---

Alive all the time
==================

There is major difference between traditional software and Tickator. In Tickator are all elements - ticklets - **still alive**. That means, in every tick element can't react on input or produce output. It works in the same way as transistor in your CPU or cell in your body. Each of such element is just still alive - there is no **sleeping** time when it is sleeping and is not responsible. Of course it may decide not to react on environment events but it is purely its internal and conscious choice.

> In Tickator you don't have to care about flow in your program.
> You just connect elements and change is spread automatically and in
> parallel.

This image shows activated cells (blue) and spreading change (arrow):

<img src="/img/cells-tickator.png"/>


Compare this with threads used in traditional software - no matter if your program is functional or procedural. There is one or more threads - executed by processor cores - that for **short time** woke up software elements (objects, functions, methods, ...) from **sleeping state**. Right after execution elements again **fall asleep**. One can say number of active elements is equal to number of CPU cores. Programmer must control execution in the way that threads reaches **proper elements in proper time**.

Imagine how would that work in case of your body cells if only small fractions can run simultaneously.

* When some of your stomach cells are doing their job after lunch then neurons in your brain are sleeping and not able to react on potential danger. What would be scheduling algorithm?
* In what order should be body cells traversed when looking for one that needs acting?
* If event (e.g. hormone reaches cell) occurs during processing of different part - should it be stored somewhere and handled later, or lost?
* How would be handled that single cell cannot be executed by multiple "threads"?
* How slow it would be to traverse billions of cells with a few "threads"?
* If you need to distribute changed to more than once cell - how you would do that? Thread can continue to single cell. Keep in mind that starting new thread is not a solution - all available threads are busy already with processing billions of cells...

This is how it would look like:
<img src="/img/cells.png"/>

Can you see analogy to traditional software systems?

* You have to make sure two threads are not processing the same element
* You have to make sure you are executing only elements that needs execution - otherwise you are wasting limited computation power
* You have to select what will be processed first and what later, despite both may be processed in parallel - see [Program flow](/articles/program-flow)
* ...

Of course Tickator uses threads for implementation - it is easiest way for now, but performance and energy efficiency are suffering. Various optimizations are used which allows it to run on nowadays CPUs. There is map of connections between ticklets, so when output is changed it exactly knows which ticklets should be activated in next tick.

In near future may be build dedicated Tickator processor based of FPGA, in far future even physical made of silicon. One may imagine processor consisted of thousands cores, each executing one (best case) to many ticklets.
