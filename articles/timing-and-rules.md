---
layout: default
title: Tickator&#58; Timing and rules
permalink: /articles/timing-and-rules
---

Timing and rules
================

Rule 1: There is global tick
----------------------------

Tickator's global tick is the same concept as clock signal to the [Synchronous circuit](https://en.wikipedia.org/wiki/Synchronous_circuit). Ticklets may do whatever they want all the time, but they can exchange data only on ticks. After tick must be non-internal (any other ticklet can see it) data stable until next tick.


Rule 2: Ticklets react on inputs change
---------------------------------------

Ticklets are pasive and wait for changed input, which starts processing and may produce output. Some ticklets may depend on external asynchronous source like clock or keyboard, but they may change it's output only on tick.

Rule 3: Ticklet may directly change only internal state
-------------------------------------------------------

Ticklet cannot change value of its inputs or another ticklets. It may just change its internal state and produce output.

Rule 4: Ticklet external state stay invariant during tick
---------------------------------------------------------

Outputs, once send, are guaranted to be stable during whole tick.


Timing is following:

1.   All ticklets whose inputs were changed are collected
2.   All are executed, one by one. Each can change just own internal state and future state of its outputs.
3.   Tickator ticks and all changed outputs are set to new value. That means some inputs were changed. Go to 1.
