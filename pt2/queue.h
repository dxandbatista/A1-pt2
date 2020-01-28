//lang::CwC

#include "object.h"

class Queue : public Object
{
public:
    Object *data_[];         // The Object data held in this queue
    long unsigned int size_; // How many items are in this queue currently

    // Constructor
    Queue()
    {
    }

    // Deconstructor
    virtual ~Queue()
    {
    }

    // Adds a new Object to the Queue
    void enqueue(Object *item_)
    {
    }

    // Removes the head Object from the Queue
    // Returns the removed head
    Object *dequeue()
    {
    }

    // Returns the head Object of the Queue without removing it
    Object *peek()
    {
    }

    // Checks to see if the Queue contains a specific Object already
    bool contains(Object *o)
    {
    }

    // Combines a given Queue into this Queue
    void enqueue_all(Queue *q)
    {
    }

    // Removes all items from this Queue
    void dequeue_all()
    {
    }

    // Returns size of queue
    size_t queue_size()
    {
    }

    // Prints contents of the Queue
    void print_queue()
    {
    }

    // Checks for equality between this Queue and the Object passed in
    bool equals(Object *o)
    {
    }

    // Generates a hash value for this Object
    long unsigned int hash_me()
    {
    }
};
