# Text Utility Toolkit

A small Java library for practicing reusable utility methods, edge-case handling,
documentation, and unit testing.

## Utilities

- `isPalindrome` checks text while ignoring case and non-alphanumeric characters.
- `countWords` counts groups of non-whitespace characters.
- `reverse` reverses text safely for Unicode code points.
- `capitalizeWords` capitalizes the first letter of each whitespace-separated word.
- `removeDuplicateSpaces` trims text and replaces whitespace runs with one space.

All methods reject `null` with `IllegalArgumentException`.

## Run the tests

```shell
mvn test
```

