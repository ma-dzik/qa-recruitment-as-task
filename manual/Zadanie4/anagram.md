# 4 — Anagram check

## Description
Anagram is a word or phrase made by rearranging the letters of another word or phrase. For example, "listen" is an anagram of "silent". The following method checks whether two given strings are anagrams. Works with Unicode characters (e.g., Polish diacritics). For edge cases involving different Unicode normalization forms (composed vs decomposed), additional normalization may be required.

## Assumptions
- Comparison is case-insensitive.
- Whitespace is ignored.
- Only alphabetical characters are considered.

## Example
Input:
- "listen"
- "silent"

Output:
- true

## Java-like implementation

```java
boolean areAnagrams(String firstWord, String secondWord) {

    if (firstWord == null || secondWord == null) {
        return false;
    }

    String normalizedFirstWord = firstWord.toLowerCase().replaceAll("\\s+", "");
    String normalizedSecondWord = secondWord.toLowerCase().replaceAll("\\s+", "");

    if (normalizedFirstWord.length() != normalizedSecondWord.length()) {
        return false;
    }

    char[] firstWordArray = normalizedFirstWord.toCharArray();
    char[] secondWordArray = normalizedSecondWord.toCharArray();

    Arrays.sort(firstWordArray);
    Arrays.sort(secondWordArray);

    return Arrays.equals(firstWordArray, secondWordArray);
}