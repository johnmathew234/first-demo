%dw 2.0
output application/json
---
{
  "addressInformation": {
    "shipping_address": {
      "region": payload.region,
      "region_id": payload.region_id,
      "region_code": payload.region_code,
      "country_id": payload.country_id,
      "street": [
        payload.street
      ],
      "postcode": payload.postcode,
      "city": payload.city,
      "firstname": payload.firstname,
      "lastname": payload.lastname,
      "email": payload.email,
      "telephone": payload.telephone
    },
    "billing_address": {
      "region": payload.billing_region,
      "region_id": payload.billing_region_id default " ",
      "region_code": payload.billing_region_code default " ",
      "country_id": payload.billing_coutry_id,
      "street": [
        payload.billing_street
      ],
      "postcode": payload.billing_postcode,
      "city": payload.billing_city,
      "firstname": payload.billing_firstname,
      "lastname": payload.billing_lastname,
      "email": payload.billing_email,
      "telephone": payload.billing_telephone
    },
    "shipping_carrier_code": "tablerate",
    "shipping_method_code": "bestway",
    "extension_attributes":{"buyer_gst_number":""}
  }
}
