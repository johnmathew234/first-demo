%dw 2.0
output application/json
---
{
    "username" : payload.username,
    "password" : payload.password
}