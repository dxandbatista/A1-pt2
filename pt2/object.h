//lang::CwC

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

		// Checks for equality between two Objects, if two objects are equal their hash codes
		// should be equal as well
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
