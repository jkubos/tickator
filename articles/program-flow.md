---
layout: default
title: Tickator&#58; Program flow
permalink: /articles/program-flow
---

Program flow
============

By program flow I mean something like:

*   There is information X that changes
*   That information is displayed on different places of UI
*   There are other information that depends on X those are displayed too
*   If visualization changes layout of UI must be recalculated
*   ...

Let's take a look how is this change executed in classical software and Tickator.

Classical software
------------------

On this image you can see typical propagation of change in classical software:

<center>
  <img src="/img/classic-flow.png"/>
</center>
<br/>

Change propagates by "depth-first" method because there is no simple way how to split execution on branches. You can use threads for this but then you face bunch of threading synchronization issues.

In our UI sample above this would mean, that UI is layouted twice - in step 3. and 8. Also component 4 is handled later than component 8, despite it is much closer to source of change. I find hard to work programs like this.

Tickator
--------

In Tickator is change spread in waves:

<center>
  <img src="/img/tickator-flow.png"/>
</center>
<br/>

As you can see change propagates by "breadth-first" method. Information flows splits if necessary - and no explicit action is required!

In out sample above that means tha UI is layouted once - information reach component 8 from 2 branches simultaneously. In real case it is bit harder to have such nice timing, but it is much easier than in classical software.
