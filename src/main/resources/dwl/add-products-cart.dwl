%dw 2.0
output application/json
---

{
  "cartItem": {
    "sku": vars.originalPayload.data.sku,
    "qty": vars.originalPayload.data.qty,
    "quote_id": payload.quote_id
  }
}