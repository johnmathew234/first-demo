%dw 2.0
output application/json
---
{
  "address": {
    "region": payload.region,
    "region_id": payload.'region_id',
    "region_code": payload.'region_code',
    "country_id": payload.'country_id',
    "street": [
      payload.street
    ],
    "postcode": payload.postcode,
    "city": payload.city,
    "firstname": payload.firstname,
    "lastname": payload.lastname,
    "customer_id": payload.'customer_id',
    "email": payload.email,
    "telephone": payload.telephone,
    "same_as_billing": if(payload.'same_as_billing' == true) (1) else if(payload.'same_as_billing' == false) (0) else (1)
  }
}