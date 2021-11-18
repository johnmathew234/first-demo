%dw 2.0
output application/json
---
payload.items map {
    "id" : $.id,
    "sku" : $.sku,
    "name" : $.name,
    "price" : "Rs." ++ $.price
}