---
layout: default
title:  "Looks like IDE"
date:   2016-11-30 21:03:34 +0100
categories: tickator
---

Looks like IDE
==============

So I pushed Tickator IDE again a bit forward. It now looks much more like an IDE.
There is support for simple control of program run, logging infrastructure and basic
traversing of project structure.

But there are news in Tickator itself too. From now on it is possible to define
component, including properties and inputs/outputs. Such component can be used in
another component.

In example below, there is __WrappedSum__ which is implemented using
ticklet __Sum__ (green boxes with cog wheel are ticklets). __WrappedSum__ is then used
component __EmbeddedWrappedSum__ (blue boxes with piece of puzzle are component instances),
fed by two constants (with values 1 and 41) and output is sent to printer that prints result
into log console.

There is also example with feedback loop - __Iteration__. Simple __Sum__ ticklet is fed by
__Const__ with value 1 and own output. That causes printing infinite row of numbers
incremented by one.

<a href="/img/looks-like-ide.gif">
  <img src="/img/looks-like-ide.gif" style="width: 600px;"/>
</a>

PS: I promise - next sample I will show here will be meaningful ;-)
