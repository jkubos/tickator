---
layout: default
title: Tickator&#58; Why I believe it works
permalink: /articles/why-i-believe-it-works
---

Why I believe it works
======================

I have **guts feeling :-)** Since 2011 I made few attempts to test concepts of Tickator in software I designed in both spare time and at work. I can name two.

Naïve implementation
--------------------

In 2011 I was working on architecture of [application](http://www.ambergtechnologies.ch/en/products/tunnel-seismics/tsp-203plus/) used for seimic prediction in tunneling industry. When I collected all requirements and start thining about design I reallized that implement some parts will be tough. Beside hard math there were visual editors - for tunnel shape, sensors geometry, for shortest path visualization, results visualization, etc. Some information like tunnel shape or sensor was visible in multiple editors. What's worse in various visualizations simulaneously: 3D, 2D, top view, side view, printed reports, tables, ... It was also possible to change information in multiple locations - eg. drag&drop in 2D view and by editing in table.

I have never before designing architecture of commercial application but I have seen enoug to remeber some problems that this single-model/multiple-views can cause. I have seen programmers refreshing "everything" because they were not able to say what minor change in model should change in all views. I have seen "refresh" command that helps user if something was not refreshed. I have seen users reopening editor to see changes they made. I have seen editors slow as hell.

And I did't want to have this in **my application**. At that time collegue of mine explained me some aspects of his rendering library. He described model that he uses to update views from his presentation-models. That inspired me together with [Loius Savaign](/articles/credits) to try to use similar approach. It was quite a risk but I had guts feeling that it may work.

To make long story short: Using approach way less mature than Tickator we were able to implement application quite easily. We wrote all models once and higly reuse them. We had single model for selected objects in all editors. We had never wrote explicit update command. It was fast. I was impressed. And my colleagues too.

Job offers crawler
------------------

In 2013 I have started writing my startup project [mrakyprace.cz](https://www.mrakyprace.cz). It is job offers site. But special one - it crawles internet and search for jobs on pages of employers. So no simple grabber from other job offers sites but generic detector of job offer. Unfortunatelly, from many reasons is this project commercial failure.

But I learned a lot for Tickator. I created way more mature version of engine that was used for seismic application described above. And I used it for two purposes: in knowledge editor application and in crawler. In knowledge editor it worked great, as expected. But in crawler it outperformed my expectations.

Crawler is greatly modular, in the same way as application with good dependency injection is. I was easily able to create multiple modes of crawler: "run on test data", "crawle and store test data", "crawle internet", ... Modules are independent on each other and I proved it when I sometimes needed to swap 2 blocks of processing. It was just about change of two lines of configuration.

Other aspect was speed. Because we tried to be low cost project, we decided to purchase only extremely weak server - Atom D2700, 4GB of RAM and 64GB SSD storage. In this configuration is our server able to crawle 1.5 million domains of Czech internet in 14 days. With power consumption of 14W!

This second success with global tick convinced me that it is promissing concept. And I should try to provide thins experience also to other programmers. That is the why I created this open source project.


