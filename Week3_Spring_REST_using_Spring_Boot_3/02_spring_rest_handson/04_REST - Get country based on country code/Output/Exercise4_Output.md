# REST - Get country based on country code

## Implementation summary
- Added a REST endpoint to fetch a country by code.
- The lookup is case-insensitive.

## Sample request
GET /countries/in

## Expected response
{
  "code": "IN",
  "name": "India"
}
