class Object
{
public:
    long unsigned int hashVal;

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
    virtual long unsigned int hash()
    {
    }

    // Generates a hash value for this Object
    virtual long unsigned int hash_me()
    {
    }
};
