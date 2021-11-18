%dw 2.0
output application/json
---
{
  "customer": {
    "email": payload.email,
    "firstname": payload.firstname,
    "lastname": payload.lastname,
    "addresses": [
      {
        "defaultShipping": payload.defaultShipping,
        "defaultBilling": payload.defaultBilling,
        "firstname": payload.firstname,
        "lastname": payload.lastname,
        "region": {
          "regionCode": payload.regionCode,
          "region": payload.region,
          "regionId": payload.regionId 
        },
        "postcode": payload.postcode,
        "street": [
          payload.street
        ],
        "city": payload.city,
        "telephone": payload.telephone,
        "countryId": payload.countryId
      }
    ]
  },
  "password": payload.password
}