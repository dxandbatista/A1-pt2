//lang::CwC

class Object
{
public:
	/*
         * Constructor.
         */
	Object(){};

	/*
         * Deconstructor.
         */
	virtual ~Object(){};

	/*
         * Is this Object equal to the given one?
         *
         * @param other The object to check against this.
         *
         * @return true if other equates this, false if not.
         */
	virtual bool equals(Object *other){};

	/*
         * Generates a hash code unique to this object.
         * 
         * @return a numberical representation of this object.
         */
	virtual size_t hash(){};

	/*
         * Return a newly allocated string describing the object 
         */
	virtual char *to_string(){};

	/*
         * Prints a string representation of this object to the console.
         */
	virtual void print(){};
};
