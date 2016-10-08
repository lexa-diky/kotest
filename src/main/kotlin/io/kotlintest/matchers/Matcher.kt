package io.kotlintest.matchers

interface Matcher<T> {

  fun test(value: T): Result

  infix fun and(other: Matcher<T>): Matcher<T> = object : Matcher<T> {
    override fun test(value: T): Result {
      val r = this@Matcher.test(value)
      if (!r.passed)
        return r
      else
        return other.test(value)
    }
  }

  infix fun or(other: Matcher<T>): Matcher<T> = object : Matcher<T> {
    override fun test(value: T): Result {
      val r = this@Matcher.test(value)
      if (r.passed)
        return r
      else
        return other.test(value)
    }
  }
}

data class Result(val passed: Boolean, val message: String)