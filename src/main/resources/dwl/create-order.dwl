%dw 2.0
output application/json
---
{
  "paymentMethod": {
    "method": payload.paymentMethod
  },
  "billing_address": {
    "email": payload.email,
    "region": payload.region,
    "region_id": payload.region_id,
    "region_code": payload.region_code,
    "country_id": payload.country_id,
    "street": [
      payload.street
    ],
    "postcode": payload.postcode,
    "city": payload.city,
    "telephone": payload.telephone,
    "firstname": payload.firstname,
    "lastname": payload.lastname
  }
}