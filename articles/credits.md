---
layout: default
title: Tickator&#58; Credits
permalink: /articles/credits
---

Credits
=======

Did I invent this? **No.**

Synchronous circuit
-------------------

**Global tick** is concept used in [Synchronous circuit](https://en.wikipedia.org/wiki/Synchronous_circuit) which is hearth of majority of electronics. CPUs, GPUs, computer buses, ... There is global signal that synchronize all components - devices on bus, logic gates in CPU, ... 

It is **wonder** that synchronous circuits are used in computers to run software that uses **completely different paradigm** of parallelism.

Louis Savain
------------

First time I have met global tick used in software on [blog](http://rebelscience.blogspot.com/) of Louis Savain. He literally opened my eyes. Here are some articles that are **really** worth reading:

*   [Project COSA](http://www.rebelscience.org/Cosas/COSA.htm)
*   [The Silver Bullet](http://www.rebelscience.org/Cosas/Reliability.htm)
*   [Devil's Advocate](http://www.rebelscience.org/Cosas/objections.htm)
*   [Project COSA - book](http://www.rebelscience.org/download/cosa002.pdf)

Check out also his other posts - he blogs, besides parallelism, also about AI and physics. He is kind of [special guy](http://rebelscience.blogspot.cz/2007/10/who-am-i-what-are-my-credentials.html) but please throw away your judgements and focus on technical aspects.

Unfortunatelly there is no implementation of project COSA although there were some [attempts](http://www.rebelscience.org/discussion/viewforum.php?f=1).

Ivan Sutherland
---------------

[Ivan Sutherland](https://en.wikipedia.org/wiki/Ivan_Sutherland) is computer pioneer. By many called "father of computer graphics". There is [nice presentation](http://www.infoq.com/presentations/The-Sequential-Prison) where he tries to explain some concepts that software could adopt from hardware.

*   Software miss synchronization pulse that solves a lot of issues in hardware. Moment when input signal arive. One, other, both, none. But never **half of signal** - as it may happen in thread/process based parallelism.
*   Software should be more about **configuration** than programming.
*   Configuration should be done in form of **diagrams**, not text.
*   In pioneer ages was logic (vacuum tubes) expensive and wiring (copper wires) cheap. That was changed to cheap logic (bilions of tranzistors) and expensive wiring (done during manufacturing and cannot be changed later).
