---
layout: default
title: Tickator&#58; Blog
---

{% for post in site.posts %}
  <a href="{{post.url}}"><small>{{ post.date | date: "%b %-d, %Y" }}</small></a>
  <div>
     {{ post.content }}
  </div>
  <hr/>
{% endfor %}
