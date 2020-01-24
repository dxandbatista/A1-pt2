//lang::CwC

#pragma once
#include "object.h"

class String : public Object
{
public:
	char *val_;				 // the array of chars to be represented as a string
	long unsigned int size_; // the size of the String

	// Default constructor
	String()
	{
	}

	// Constructor given an array of chars to initialize a String with
	String(const char *hold)
	{
	}

	// Deconstructor
	~String()
	{
	}

	// Generates a hash value for this Object
	long unsigned int hash_me()
	{
	}

	// Checks for equality between this Queue and the Object passed in
	bool equals(Object *o)
	{
	}

	// Concatenates two Strings together
	String *concat(String *end)
	{
	}

	// Compares the values of two Strings alphanumerically
	int strcmp(char *val)
	{
	}
};
