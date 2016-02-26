---
layout: default
title: Hujer
permalink: /articles/architecture
---

Architecture
============

Architecture of Tickator consists of 3 concepts and list of [Timing and rules](/articles/rules).

Concept 1: Global tick
----------------------

Global tick is ubiquitous concept in Tickator architecture. Its role is exactly the same as in [Synchronous circuits](https://en.wikipedia.org/wiki/Synchronous_circuit). It synchronizes moments when atomic components of Tickator - ticklets - interchange data. Therefore is visible by all ticklets in Tickator instance.

There may be multiple global ticks in single application. Just create multiple Tickator instances and each will tick in different moment. This may be used when you need to assure latency for some mission critical logic - so you can move it into separate Tickator. Multiple instances of Tickator may interchange data, but [Timing and rules](/articles/rules) must be assured. So typically there are some buffers and queues between them.

Concept 2: Ticklet
------------------

Ticklet is atomic block of Tickator computation. It has input and output ports and possibly internal state. You can imagine it as small [integrated circuit](https://en.wikipedia.org/wiki/Integrated_circuit) with defined functionality, some input/output pins and one pin for global tick that synchronizes it with others in system.

Tickator execution has two phases:

1.   Execute - This phase is definable by programmer. All inputs are readable and assured stable. Internal state may be changed and future values for outputs may be prepared.
2.   Tick - future outputs are converted into current outputs and new cycle may start.

Ticklet is executed every time any input was changed. Or is executed if it asks for it - for example when asynchronous event was received (keyboard, timer, ...) or asychnronous calculation finished.

Ticklets should have only basic functionality and therefore there may be at most hundreds of them. In the same way as instructions in CPUs. There are hundreds of instructions and then programmers build programs by composing them into components/methods/functions and then use mostly this high-level concepts. Keeping number of ticklets low will assure simple design of future CPUs that will support Tickator model.

Concept 3: Component
--------------------

Component is logical concept for creating more advanced functionality. It is composed of another components and ticklets. Components has no meaning during execution - in the end they are translated to set of interconnected ticklets.

In ticklet you have to typically handle all variants of inputs/outputs only for single tick. But in component it is different. Component will typically operate on more than one tick. And programmer must assure that during time it process inputs there will not arrive new inputs that would corrupt results.  For example imagine component that will calculate Fibonnaci number for given input. Internally it will probably iterate input number and sum temporal result. Now imagine that new input number would arrive and somehow influent calculation. Fortunatelly this problem can be used by local lock and queue for caching pending requests.
