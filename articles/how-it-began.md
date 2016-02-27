---
layout: default
title: Tickator&#58; How it all began
permalink: /articles/how-it-began
---

How it all began
================

<img src="/img/assembler.jpg" class="floating-img"/>

A long time ago, in age of Windows 95, I was freshly student of high school and started programming. Because I didn't know any programmer I had no other chance than being self-learner. Therefore I started without any architectural insight. After short attempt with QuickBASIC I moved to SGP Baltazar (C preprocessor and interpreter; programs were written in form of Jackson diagrams). Later I moved to Microsoft/Macro Assembler (MASM) where I touched Win32Api for the first time. After I found out it would be extremely hard to write application using MASM I picked C++ and continue with Win32Api exploration.

When I was thinking about various algorithms I always felt that it's execution must be slow and there is not much room for optimization. This was based on intuitively understood concept of machine doing only single step of my algorthm at time. I was a bit confused - because at least my operating system was able to run multiple applications at the same time. In 1999 Internet started to be widely available in Czech Republic for feasible price. That widen my horizons because I had finaly access to online articles and manuals.

First I understood concept of OS. That there are processes and OS has some HW tools to share single CPU among applications - but only one application has computing power at any time. That knowledge surprised me even more - it was even worse than single application capable of doing single step at time!

<img src="/img/threads.jpg" class="floating-img"/>

Then I met "thread" keyword in Win32Api documentation. First I ignored it because with my poor knowledge of English I translated it into Czech wrongly - in the meaning of strand (used with needle). But later on I found article written in Czech that explained to me that thread is tool you can use to execute multiple parts of your application at the same time. I had the 'AHA' moment and finally I had feeling architecture makes a sense. I thought I can create 100000 of threads and let them compute. When I tried to do that my application crashed together with my Windows 98 (32MB RAM, single core Pentinum)... That moment was crushing. I got feeling that this concept is wrong. Why CPU does not contain more than one core executing programs? Of course I was not aware of things like CPU caches, deadlocks, bus&memory contention, synchronization issues.

17 years passed, I become professional programmer and I learned how to cope with existing architecture. What has changed? We have CPUs that does have more that one core - but still about dozen at most. The reason is that it is extremely hard to write applications using more cores. In some cases such applications may be slower than single thread applications - see [Disruptor library](https://lmax-exchange.github.io/disruptor/) - they found out that their stock exchange transactions works best when running on single core. Or take a look on [why UI libraries are rarely multithreaded](http://tecnologia.revistacocktel.com/multithreaded-toolkits-a-failed-dream/). And some explanation [why are threads problematic](https://web.stanford.edu/~ouster/cgi-bin/papers/threads.pdf). The original architecture that works for single core just does not scale well for multiple cores. I will talk about this more in later articles.

There is one more widely known technology - GPU cards. They may have thousands of processors so it seems they may provide solution for me. But, it has some major drawbacks. Because it has so many processors and only single memory they content about it. So memory access is only fast when all cores are accessing memory in the same way - so GPU can group this accesses to achieve better performance. Also, GPU cores are not really design to execute different programs. Compute units uses [SIMD](https://en.wikipedia.org/wiki/SIMD) approach. So when one core enter branch, like if-condition, all other cores of compute units wait for it unless it reach point where all units can continue. So this is not an aswer too.

I am aiming to something else. Imagine you can allocate micro processors in the same way you allocate objects in OOP. Then you define how they are connected to each other and they start to work. Once any of inputs is changed, operation is performed and if there is result it is propagated by outputs. It is processing unit and cache in the same time.

<hr/>

<small>
Images &copy; [Assembler](https://www.flickr.com/photos/pablobd/4760973863/in/photolist-8fHeEg-mE9M1m-BnVSof-oc2LjU-ddUrbL-dwb3QW-fzgubK-brU27G-5WRMm-cod1m3-dAiqQZ-5FEHuM-boUnyR-eQJuoV-CEvaJ-qQWgUq-71daZo-oe4NCp-5pTbAS-Cqy98G-3gFY3R-hJHTb8-nTMLqF-oNaHQv-bC7zvR-7Tu9a2-8aW5xQ-dw5rgr-atoj8U-3gLmAh-8eYq92-dib9MC-aCUdde-bCp3TX-BoQMuE-AvmL-as6DHT-5MC98e-7VhBRq-edJZLL-aFjGHZ-dwApWr-8tN8A3-ATGRXB-7Tu5L6-3RLxWV-f53qkm-5tGfxE-5xrZsw-m4Cgs7) [Threads](https://www.flickr.com/photos/ndanger/2744507570/in/photolist-5bwju3-9Av72w-3ScbYu-9AscDz-3Sccxf-9xmQyS-9Av7cj-4RJsgS-b25Pq6-9Av7HG-8wm2jQ-31V1f-rgaevq-dxJaqq-bparyB-6U7svq-bAr3H8-73p4V4-5WvZrj-5prZKP-e9hWJ3-6mDt6-LkqnT-7zwCXd-jSbB3-2Ay9fc-i4cwj-azLkG4-HJPhB-7TkwCt-aE3xwB-BNk4ye-bAFsAn-ejmT7Q-rUyG2-pppdSC-873DBk-kiKUzU-6di6VQ-33DBmT-NaWZs-LnSTd-aUjLoM-bBpzgd-876SKd-dTxfnx-6wDYoh-37C2pj-azPaTY-8vHt7g/)
</small>
