---
layout: default
title: Hujer
permalink: /articles/rules
---

Rules
=====

Rule 1: There is global tick
----------------------------

Tickator's global tick is the same concept as clock signal to the [Synchronous circuit](https://en.wikipedia.org/wiki/Synchronous_circuit). Ticklets may do whatever they want all the time, but they can exchange data only on ticks.


Rule 2: Ticklets react on inputs change
---------------------------------------

Ticklets are pasive and wait for changed input, which starts processing and may produce output. Some ticklets may depend on external asynchronous source like clock or keyboard, but they may change it's output only on tick.

Rule 3: Ticklet may directly change only internal state
-------------------------------------------------------

Ticklet cannot change value of its inputs or another ticklets. It may just change its internal state and produce output.

Rule 4: Ticklet external state stay invariant during tick
---------------------------------------------------------

Outputs, once send, are guaranted to be stable during whole tick.
