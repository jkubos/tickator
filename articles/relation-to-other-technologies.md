---
layout: default
title: Tickator&#58; Relation to other technologies
permalink: /articles/relation-to-other-technologies
---

Relation to other technologies
==============================

Functional programming
----------------------

Ticklet has state. Function does not have state.

Observer pattern
----------------

Observer pattern suffers of threading symptoms described in [Program flow](/articles/program-flow).

Threads
-------

Ticklets are completely opposite concept that threads.

<table class='table'>
    <tr>
        <th>Ticklet</th>
        <th>Thread</th>
    </tr>
    <tr>
        <td>deterministic</td>
        <td>nondeterministic</td>
    </tr>
    <tr>
        <td>implicitly parallel</td>
        <td>explicitly parallel</td>
    </tr>
    <tr>
        <td>explicitly sequential</td>
        <td>implicitly sequential</td>
    </tr>
    <tr>
        <td>reactive/change driven</td>
        <td>procedural</td>
    </tr>
</table>

See [Program flow](/articles/program-flow) for main difference between threads and ticklets.

Only important relation between threads and Tickator is, that current implementation uses threads. That will be removed once there will be CPU that will execute ticklets natively.

Digital circuits
----------------

Tickator is inspired by digital circuits. See [Architecture](/articles/architecture). Tickator may however differ in aspects that are not doable on wire level. For example it is possible to connect multiple outputs to single input. That is not doable on hardware level.

VHDL
----

VHDL is used for programming digital circuits and Tickator is inspired by digital circuits. So Tickator is similar to VHDL. But just a little bit. Tickator is abstracted from physical hardware so there are no hardware limitations:

*    More than one outputs can be connected to single input (not doable on hardware due to logical 0/1 clash)
*    Complex structure can be sent over wire (only single bit can be sent on physical wire)
*    Tickator program may be dynamic, typical VHDL program is static one that is written in ROM or used for physical chip design.

Also, Tickator is supposed to be generic programming tool. VHDL is typically used just for hardware and FPGA programming.

Global tick in simulations and games
------------------------------------

I know that global tick is used in [games](http://gamedev.stackexchange.com/questions/81608/what-is-a-tick-in-the-context-of-game-development) and [simulations](https://www.it.uu.se/edu/course/homepage/oopjava/st08/assign/simulering-eng.html). There is major difference between those and Tickator - in **granularity**.

Tickator uses global tick on **smallest possible granularity**. In best case there are thousands of small processors that sychronizes at each instruction by global tick.

Games and simulations ticks on high levels - one frame or simulation cycle. In between them they behaves as normal software.
