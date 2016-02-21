---
layout: default
title: Hujer
permalink: /articles/architecture
---

Architecture
============

Global tick
-----------

Global tick is ubiquitous concept in Tickator architecture. Its role is exactly the same as in [Synchronous circuits](https://en.wikipedia.org/wiki/Synchronous_circuit). It synchronizes moments when atomic components of Tickator - ticklets - interchange data.

Timing is following:

1.   All ticklets whose inputs were changed are collected
2.   All are executed, one by one. Each can change just own internal state and future state of its outputs.
3.   Tickator ticks and all changed outputs are set to new value. That means some inputs were changed. Go to 1.

Ticklet
-------

Ticklet is atomic block of computation.

internal state


Component
---------

Component is logical concept for creating more advanced functionality. It is composed of another components and ticklets. Components has no meaning during execution - in the end they are translated to set of interconnected ticklets.

In ticklet you have to handle all variants of inputs/outputs only for single tick. But in component it is different. Component will typically operate more than one tick. And programmer must assure that during time it process inputs there will not arrive new inputs that would corrupt results. 

For example imagine component that will calculate Fibonnaci number for given input. Internally it will probably iterate input number and sum temporal result. Now imagine that new input number would arrive and somehow influent calculation. Fortunatelly this problem can be used by local lock and queue for caching pending requests.
