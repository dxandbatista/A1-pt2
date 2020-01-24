#include "object.h"

class Queue : public Object
{
public:
    unsigned int back;     // The next free position in this queue
    unsigned int front;    // The head in this queue
    unsigned int size;     // Number of elements in the queue
    unsigned int capacity; // Max number of elements in this queue
    Object *data;          // The Object data held in this queue

    // Constructor
    Queue()
    {
    }

    // Deconstructor
    virtual ~Queue()
    {
    }

    // Create a Queue based on the max capacity it can hold
    // Returns pointer to created Queue
    Queue *create_queue(unsigned int capacity)
    {
    }

    // Adds a new Object to the Queue
    // Returns 0 if successful, -1 if failed
    int enqueue(Queue *q, Object *item)
    {
    }

    // Removes the head Object from the Queue
    // Returns the removed head
    Object dequeue(Queue *q)
    {
    }

    // Checks if Queue is empty
    bool queue_empty(Queue *q)
    {
    }

    // Checks if Queue is full
    bool queue_full(Queue *q)
    {
    }

    // Prints contents of the Queue
    void print_queue(Queue *q)
    {
    }

    // Checks for equality between this Queue and the Object passed in
    bool equals(Object *o)
    {
    }

    // Generates a hash value for this Object
    size_t hash_me()
    {
    }
};
