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

<img src="/img/oscilloscope.jpg" class="floating-img"/>

Global tick is ubiquitous concept in Tickator architecture. Its role is exactly the same as in [Synchronous circuits](https://en.wikipedia.org/wiki/Synchronous_circuit). It synchronizes moments when atomic components of Tickator - ticklets - interchange data. Therefore is visible by all ticklets in Tickator instance.

There may be multiple global ticks in single application. Just create multiple Tickator instances and each will tick in different moment. This may be used when you need to assure latency for some mission critical logic - so you can move it into separate Tickator. Multiple instances of Tickator may interchange data, but [Timing and rules](/articles/rules) must be assured. So typically there are some buffers and queues between them.

Concept 2: Ticklet
------------------

<img src="/img/ic555.jpg" class="floating-img"/>

Ticklet is atomic block of Tickator computation. It has input and output ports and possibly internal state. You can imagine it as small [integrated circuit](https://en.wikipedia.org/wiki/Integrated_circuit) with defined functionality, some input/output pins and one pin for global tick that synchronizes it with others in system.

<span class="clearfix"></span>

Tickator execution has two phases:

1.   Execute - This phase is definable by programmer. All inputs are readable and assured stable. Internal state may be changed and future values for outputs may be prepared.
2.   Tick - future outputs are converted into current outputs and new cycle may start.

Ticklet is executed every time any input was changed. Or is executed if it asks for it - for example when asynchronous event was received (keyboard, timer, ...) or asychnronous calculation finished.

Ticklets should have only basic functionality and therefore there may be at most hundreds of them. In the same way as instructions in CPUs. There are hundreds of instructions and then programmers build programs by composing them into components/methods/functions and then use mostly this high-level concepts. Keeping number of ticklets low will assure simple design of future CPUs that will support Tickator model.

Concept 3: Component
--------------------

<img src="/img/module.jpg" class="floating-img"/>

Component is logical concept for creating more advanced functionality. It is composed of another components and ticklets. Components has no meaning during execution - in the end they are translated to set of interconnected ticklets.

In ticklet you have to typically handle all variants of inputs/outputs only for single tick. But in component it is different. Component will typically operate on more than one tick. And programmer must assure that during time it process inputs there will not arrive new inputs that would corrupt results.  For example imagine component that will calculate Fibonnaci number for given input. Internally it will probably iterate input number and sum temporal result. Now imagine that new input number would arrive and somehow influent calculation. Fortunatelly this problem can be used by local lock and queue for caching pending requests.

<hr/>

<small>
Images &copy; [Oscilloscope](https://www.flickr.com/photos/krunkwerke/5246202794/in/photolist-4QFQbF-4QFQhv-4QL2Bu-4QL2Ff-9cazGY-4QL2G1-m5WF44-m5WGgp-dMWxZ5-m5Y8vQ-m5Xn4c-m5YxCh-m5YsBf-m5WRyT-qTqSpJ-bkYjRC-m5WHjg-m5Xr5P-m5Xz94-nHK26H-gWL8D-m5Xpv6-cwHYmq-jvacni-96UpQQ-6X2P1z-6X6PxU-7Z6VXg-5C4YAS-5AeKh4-m5YB4f-m5WZux-m5Xabr-m5XMMk-m5Ym6u-m5YvcL-m5X81K-597ZMe-96RnaR-8ZAbd7-8ZAajj-uJYd8V-tpLFoU-ssK7AZ-59cbDu-7Ha6JC-6igACZ-8Zx6cz-8ZA9pE-8Zx4yp) [IC555](https://www.flickr.com/photos/oskay/5884233332/in/photolist-9XVrTn-kU5kiF-9XYciC-9XYbX3-9XVgGR-9XYapy-9XYf2Y-9XVh1T-8iogjg-9XYbuq-9XVnqn-6Ednzr-jgVeVZ-nLTYfT-hy7YYT-jnJTbt-9XVg46-8iogxM-8irvhG-8irvBu-9XVqnc-9XYdxm-8iogvR-7hai1Z-9XVk1X-8irvjw-9XVkfc-9j9gGC-8irvAf-2aQNVk-9XVisP-8irvsh-9XVmu2-9XVj4P-9XVjK8-9XViNM-8irvp7-8iognt-8irvCQ-8iogrF-8ioghi-9XYeyQ-9j68Ga-8irvzo-8irvt5-eUivnU-uHXx6U-AJjWj-8irvw5-vdvKmm) [Module](https://www.flickr.com/photos/sparkfun/13272634175/in/photolist-jfWnLd-jfYBTu-jfYBU1-jfYBT9-gSDkDj-22Vcb-ehTRxN-ewq6Wu-73uien-73uigk-mdSuba-vGnK66-uJZ8E3-mdTryf-mdRHJg-mdSupB-mdSuma-mdTreY-mdSudp-mdRHtr)
</small>
