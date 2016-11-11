---
layout: default
title:  "First ticks"
date:   2016-11-11 22:03:34 +0100
categories: tickator
---

First ticks
===========

Prototype of Tickator reached the point when it is able to perform simplest possible
calculations. On image below it sums 41 and 1 and prints result to the console.

<a href="/img/first-ticks.png" target="_blank_">
  <img src="/img/first-ticks.png" style="width: 600px;"/>
</a>

You may notice that 42 is printed in tick 2. Why? In tick 0 sends both Consts
value to their outputs. In tick 1 Sum calculated its result. And in tick 2 Print
printed result.

For long time I was struggling with attempts to prepare full fledged IDE but it
seemed terribly hard. Handling all the dependencies, updates on change, etc. Then
I came to the idea that **for now** it should work in similar way as React,
compilation of source code or make, ... You just prepare definition of things - React
components, files with source code, make script,... and then start the tool, which
takes this definitions, validates them and build target.

Various optimizations are possible - in the same way React just update elements in DOM,
compilation just build changes and make execute just tasks with changed inputs.
Tickator is for now using Webpack's
[Hot Module Replacement](https://github.com/webpack/docs/wiki/hot-module-replacement-with-webpack).
That means that after 1 second after storing changed code you have result shown
in browser without necessity to reload. It is incredibly fast process. On two monitor
machine it is fastest development I have ever done.

I am aware that final target is completely graphical IDE that does not force you
to write code. But I am not able to handle complexity of this task for now. So let's
progress step by step.

I have prepared custom [DSL](https://en.wikipedia.org/wiki/Domain-specific_language)
for definition of [components and ticklets](/articles/architecture). It has several
advantages:

*  Format without burden of unnecessary syntax
*  Perfect reporting of problem - definition is checked as soon as possible
*  Configuration may be dynamic - for example value of property may be result of JavaScript
   function executed on initialization of component/ticklet

Ticklet definition looks like:

<pre><code>
export default class Sum extends Ticklet {

  static define(b) {
    b.klass(Sum)

    b.comment("Sums two numbers")

    b.input(b=>{
      b.name("a")
      b.validate(Validate.isNumber)
      b.position('top', 0.5)
    })

    b.input(b=>{
      b.name("b")
      b.validate(Validate.isNumber)
      b.position('bottom', 0.5)
    })

    b.output(b=>{
      b.name("res")
      b.validate(Validate.isNumber)
      b.position('right', 0.5)
    })
  }

  tick() {
    const a = this.in().a().get()
    const b = this.in().b().get()
    this.out().res().set(a+b)
  }
}
</code></pre>

Component definition looks like:

<pre><code>
export default function defineFunction(b) {
  b.name('Root')

  b.instance(b=>{
    b.name('c1')
    b.ticklet('Const')
    b.property('value', 1)
    b.x(100)
    b.y(100)
  })

  b.instance(b=>{
    b.name('c2')
    b.ticklet('Const')
    b.property('value', 41)
    b.x(100)
    b.y(300)
  })

  b.instance(b=>{
    b.name('s')
    b.ticklet('Sum')
    b.x(300)
    b.y(200)
  })

  b.instance(b=>{
    b.name('p')
    b.ticklet('Print')
    b.x(500)
    b.y(200)
  })

  b.connect(b=>{
    b.fromInstance('c1')
    b.fromOutput('res')
    b.toInstance('s')
    b.toInput('a')
  })

  b.connect(b=>{
    b.fromInstance('c2')
    b.fromOutput('res')
    b.toInstance('s')
    b.toInput('b')
  })

  b.connect(b=>{
    b.fromInstance('s')
    b.fromOutput('res')
    b.toInstance('p')
    b.toInput('val')
  })
}
</code></pre>

There is no problem with Ticklets - there will be finite amount of them and their
complexity is low. You just define inputs, outputs, properties and implementation. But as you may
noticed when it comes to components it may become nasty. Even such simple calculation
as 41+1 takes 50 lines of code. But in UI it is simple: put 4 componens, drag&drop
3 wires, fill 2 properties and it is done. Everyone can do that. But in code it
is much, much harder. Let's hope that rapid development process with hot reload will help.

Stay tuned!
