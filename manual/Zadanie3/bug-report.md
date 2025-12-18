# 3 - Bug Report: Country filter returns empty results

## Bug ID
BUG_COUNTRY_FILTER_001

## Title
Country search filter returns empty result list for valid country input

## Environment
- Web application
- Browser: Not specified
- Environment: Not specified

## Preconditions
- User has access to the web application.
- Country multi-select filter is available.
- List of countries exists in the system.

## Steps to Reproduce
1. Open the web application.
2. Open the country multi-select filter.
3. Focus on the search input field within the country filter.
4. Type a valid country name (e.g. "Poland").

## Expected Result
- The list is filtered and displays matching countries.
- The typed country (e.g. "Poland") is visible in the result list and can be selected.

## Actual Result
- The result list is empty.
- Message "The list is empty!" is displayed despite entering a valid country.

## Severity
Major

## Priority
High

## Frequency
Always

## Attachments
- Screenshot provided in the task description.

## Notes / Assumptions
- The issue occurs for valid country names that should exist in the system.
- Exact list of supported countries is not provided, but at least one valid country is expected to be available.