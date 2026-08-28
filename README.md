# java-utils: Custom Java Utility Library

Reusable implementations of core Java components for learning, interviews, and
system-design exercises.

## Implemented utilities

| Module | Class | Description |
| --- | --- | --- |
| `collections` | `CustomHashMap` | Basic hash map using an array and chaining |
| `concurrency` | `CustomThreadPool` | Simple fixed-size thread pool |
| `retry` | `RetryExecutor` | Retry logic with backoff and a failure limit |
| `text` | `TextUtils` | Common, Unicode-aware text operations |

`TextUtils` provides:

- `isPalindrome` checks text while ignoring case and non-alphanumeric characters.
- `countWords` counts groups of non-whitespace characters.
- `reverse` reverses text safely by Unicode code point.
- `capitalizeWords` capitalizes the first letter of each whitespace-separated word.
- `removeDuplicateSpaces` trims text and replaces whitespace runs with one space.

All `TextUtils` methods reject `null` with `IllegalArgumentException`.

## Requirements

- Java 17 or newer
- Maven 3.8 or newer

## Run the tests

```shell
mvn clean test
```

