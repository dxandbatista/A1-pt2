#include <string.h>

class Object
{
public:
    size_t hashVal;

    // Constructor
    Object()
    {
    }

    // Deconstructor
    virtual ~Object()
    {
    }

    // Checks for equality between two Objects
    virtual bool equals(Object *o)
    {
    }

    // Generates a hash value for this Object if it doesn't already have one
    virtual size_t hash()
    {
    }

    // Generates a hash value for this Object
    virtual size_t hash_me()
    {
    }

    //toString, getClass
};
