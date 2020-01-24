//lang::CwC

#pragma once
#include "object.h"
#include <iostream>

class String : public Object
{
public:
	char *val_;
	size_t size_;

	String()
	{
		size_ = 0;
		val_ = new char[size_];
	}

	String(const char *hold)
	{
		size_ = sizeof(hold);
		val_ = new char[size_];

		for (size_t i = 0; i < size_; i++)
		{
			val_[i] = hold[i];
		}
	}

	~String()
	{
		delete[] val_;
	}

	size_t hash_me()
	{
		size_t res = 0;

		for (size_t i = 0; i < size_; i++)
		{
			res = res + val_[i];
		}

		return res;
	}

	bool equals(Object *o)
	{
		if (o == nullptr)
			return false;
		String *s = dynamic_cast<String *>(o);
		if (s == nullptr)
			return false;

		return strcmp(s->val_) == 0;
	}

	String *concat(String *end)
	{
		size_t newSize = this->size_ + end->size_;
		char *newVal = new char[newSize];

		for (size_t i = 0; i < this->size_; i++)
		{
			newVal[i] = this->val_[i];
		}

		for (size_t j = this->size_; j < newSize; j++)
		{
			newVal[j] = end->val_[j - this->size_];
		}

		String *result = new String(newVal);
		return result;
	}

	int strcmp(char *val)
	{
		for (size_t i = 0; i < size_; i++)
		{
			if (val[i] != this->val_[i])
			{
				return this->val_[i] - val[i];
			}
		}
	}
};
