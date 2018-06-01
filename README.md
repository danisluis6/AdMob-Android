# ADMOD GOOGLE [![Build Status](https://travis-ci.org/nomensa/jquery.hide-show.svg)](https://travis-ci.org/nomensa/jquery.hide-show.svg?branch=master)

   ```Reactive code can simplify the process of writing async code.```

<p><i>It is hard to explain reactive programming in a nutshell. Well, at least I couldn’t find any. You can say that reactive programming is an <b>Observer template</b> on steroids. Or rather, reactive programming is programming aimed at <b>flows</b>. The main idea is in presenting <b>events</b> and <b>data</b> as flows that can be <b>unified<b>, <b>filtered</b>, <b>transformed</b>, and <b>separated</b>. Sounds quite <b>vague</b> but I hope some of the examples below will help make sense of it.``</i></p>
  
## Understand `Observable`

``The basic building blocks of reactive code are Observables and Subscribers. The Observable class is the <b>source of data</b> and the Subscriber class is <b>the consumer</b>.``
![alt text](https://github.com/danisluis6/RxJava-Introduction/blob/level_research_reactive/Deeply/1.png)

## We Research about "Observe Design Pattern"



When to use it:
1. When one object changes its state,then all other dependents object must automatically change their state to maintain consistency
2. When subject doesn't know about number of observers it has.
3. When an object should be able to notify other objects without knowing who objects are.
