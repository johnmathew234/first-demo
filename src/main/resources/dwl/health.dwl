%dw 2.0
output application/json
---
{
	servicename: "Magento",
	status: if(payload == null)
	"down"
	else
	"up" 
}