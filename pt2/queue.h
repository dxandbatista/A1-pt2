#include "object.h"

class Queue : public Object
{
public:
    Object *data_; // The Object data held in this queue

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

    // Returns size of queue
    bool queue_size()
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
