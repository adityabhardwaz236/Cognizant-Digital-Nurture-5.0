# REST - Get country exceptional scenario

## Implementation summary
- Added exception handling for unknown country codes.
- The service throws a custom exception mapped to HTTP 404.

## Sample request
GET /countries/az

## Expected response
{
  "timestamp": "...",
  "status": 404,
  "error": "Not Found",
  "message": "Country not found",
  "path": "/countries/az"
}
