---
layout: default
title: Tickator&#58; Blog
---

{% for post in site.posts %}
  <small>{{ post.date | date: "%b %-d, %Y" }}</small>
  <div>
     {{ post.content }}
  </div>
  <hr/>
{% endfor %}

