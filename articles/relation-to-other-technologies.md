---
layout: default
title: Hujer
permalink: /articles/relation-to-other-technologies
---

Relation to other technologies
==============================

Observer

Eventing

Actors

Digital circuits

Global tick in simulations and games
------------------------------------

I know that global tick is used in [games](http://gamedev.stackexchange.com/questions/81608/what-is-a-tick-in-the-context-of-game-development) and [simulations](https://www.it.uu.se/edu/course/homepage/oopjava/st08/assign/simulering-eng.html). There is major difference between those and Tickator - in **granularity**.

Tickator uses global tick on **smallest possible granularity**. In best case there are thousands of small processors that sychronizes at each instruction by global tick. 

Games and simulations ticks on high levels - one frame or simulation cycle. In between them they behaves as normal software.
