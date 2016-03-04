---
layout: default
title: Tickator&#58; Timing and rules
permalink: /articles/timing-and-rules
---

Timing and rules
================

One tick of Tickator consists of 4 phases.

Phase 1: Add asynchronous execution requests
--------------------------------------------

In this phase are to execution plan added execution requests of ticklets send during previous tick. When requests are added and execution plan is still empty then Tickator will sleep a little while and try again.

Phase 2: Execute ticklets
-------------------------

All ticklers scheduled in execution plan are executed in parallel. There is no limitation regarding number of ticklets executed in parallel. And there is even no requirement on order of execution - system stay deterministic anyway.

This phase is only phase when is executed code of Ticklet defined by programmer. Programmer of ticklet is allowed to:

*   Read values of inputs, values are assured to be immutable during execution
*   Change internal state of ticklet
*   Compute future values for ticklet outputs
*   Read or modify whatever outside Tickator (threads, UI, OS, ...)

Ticklet execution should be as fast as possible. Important parameter of Tickator is number of ticks per second. But one long running execution may easily ruin this parameter because Tickator will not start new tick until all ticklets are executed. Therefore it is recommended to do potentionally long running tasks asynchronously. When done, ticklet may be woke up by requesting execution in next tick and handle result.

Potentionally problematic activities:

*   Long calculation in library outside Tickator
*   Filesystem access
*   Database access
*   Network access

Phase 3: Update Tickator schema
-------------------------------

In this phase are collected changes to Tickator schema collected in Phase 2 and this changes are applied. Ticklets may be added or removed. And connections may be created or removed.

Phase 3 preceed Phase 4 because it is important that newly created tickator/connection can react on changes generated in current tick.

Phase 4: Handle changed values on ports
---------------------------------------

Execution plan is cleaned and all ticklets depending on changed output ports are added into execution plan.

Scheduling ticklet execution asynchronously
-------------------------------------------

It is not possible to modify Tickator instance outside of it. You can't add ticklets, connections, etc. But sometimes it is necessary to inform ticklet about external event. For example date changed, user pressed key, file was read into buffer, etc. For this situation there is possibility to ask for ticklet execution from outside Tickator instance. It can be asked in any time, but it is processed only in Phase 1.




