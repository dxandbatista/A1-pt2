#pragma once
#include "object.h" // our implementation of Object for A1
#include "queue.h"  // our implementation of Queue for A1
#include "string.h" // our implementation of String from Warmup

void FAIL()
{
    exit(1);
}
void OK(const char *m)
{ /** print m */
}
void t_true(bool p)
{
    if (!p)
        FAIL();
}
void t_false(bool p)
{
    if (p)
        FAIL();
}

// test enqueue
void test1()
{
    String *s = new String("Hello");
    String *t = new String("World");
    Queue *q1 = new Queue();

    t_true(q1->queue_size() == 0);
    q1->enqueue(s);
    q1->enqueue(t);
    t_true(q1->queue_size() == 2);
    OK("1");
}

//test dequeue
void test2()
{
    String *s = new String("Hello");
    String *t = new String("World");
    String *u = s->concat(t);
    Queue *q1 = new Queue();

    q1->enqueue(s);
    q1->enqueue(t);
    q1->enqueue(u);
    t_true(q1->dequeue()->equals(s));
    t_true(q1->dequeue()->equals(t));
    t_false(q1->dequeue()->equals(s));
    OK("1");
}

//test equals
void test3()
{
    String *s = new String("Hello");
    String *t = new String("World");
    String *u = s->concat(t);
    Queue *q1 = new Queue();
    Queue *q2 = new Queue();

    t_true(q1->equals(q2));
    q1->enqueue(s);
    q1->enqueue(t);
    q2->enqueue(s);
    q2->enqueue(t);
    t_true(q1->equals(q2));
    q1->enqueue(u);
    t_false(q1->equals(q2));
    OK("1");
}

//test hash_me
void test4()
{
    String *s = new String("Hello");
    String *t = new String("World");
    String *u = s->concat(t);
    Queue *q1 = new Queue();
    Queue *q2 = new Queue();

    q1->enqueue(s);
    q2->enqueue(t);
    t_true(q1->hash_me() > 0);
    t_true(q1->hash_me == q1->hash_me);
    t_false(q2->hash_me() <= 0);
    OK("1");
}

int main()
{
    test1();
    test2();
    test3();
    test4();
    return 0;
}
