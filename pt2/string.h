//lang::CwC

#pragma once
#include "object.h"

class String : public Object
{
public:
	/*
		 * Constructor
		 *
		 * @param str the pointer to the characters in this String
		 */
	String(const char *str){};

	/*
		 * Deconstructor
		 */
	~String(){};

	/*
		 * The length of this String
		 *
		 * @return the length
		 */
	size_t size(){};

	/*
		 * Is this String equal to the given Object?
		 *
		 * @param o the Object to be compared
		 *
		 * @return true if the two are equal, false if they are not
		 */
	bool equals(Object *other){};

	/*
		 * Compares this String to the given one
		 *
		 * @param other The String to be compared with this one
		 *
		 * @return 	< 0 if this String has a smaller lexicographic value, 0 if they have an equal value, and > 0 if this
		 * 			String has a larger value
		 */
	int compare(String *other){};

	/*
		 * Returns a hash value unique to this String
		 *
		 * @return the hash
		 */
	size_t hash(){};

	/*
		 * Concatenates this String with the given one, and returns the result as a new String.
		 *
		 * @param other The String to be appended to the end of this one
		 *
		 * @return A new String with the result of concatenating the two Strings
		 */
	String *concat(String *other){};

	/*
		 * Getter for this String object's char*
		 *
		 * @return this class's char* field
		 */
	char *to_string(){};

	/*
		 * Print this string on stdout.
		 */
	void print(){};
};
