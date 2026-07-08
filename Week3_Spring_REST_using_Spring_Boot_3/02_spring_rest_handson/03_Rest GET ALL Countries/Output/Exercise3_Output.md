# REST - Get all countries

## Implementation summary
- Added a Spring Boot REST endpoint for retrieving all countries.
- The endpoint returns the list of countries loaded from country.xml.

## Sample request
GET /countries

## Expected response
[
  { "code": "IN", "name": "India" },
  { "code": "US", "name": "United States" },
  { "code": "JP", "name": "Japan" },
  { "code": "DE", "name": "Germany" }
]
