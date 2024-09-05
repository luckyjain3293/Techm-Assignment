To run this code in your system :-
- Java 17 and SpringBoot is required
------------------------------------------------------
Curl request :-

curl --location 'http://localhost:8080/api/order' \
--header 'Content-Type: application/json' \
--data '{
  "beverages": ["small", "large", "small", "orange juice","small","large","hum"],
  "snacks": ["bacon roll","bacon roll"],
  "extras": ["extra milk", "foamed milk"]
    }'

--------------------------------------------------------
 Response :-

 ---- Receipt ----
 Small Coffee                    2.50 CHF
 Large Coffee                    3.50 CHF
 Small Coffee                    2.50 CHF
 Freshly Squeezed Orange Juice       3.95 CHF
 Small Coffee                    0.00 CHF (Free)
 Large Coffee                    3.50 CHF
 Bacon Roll                      4.50 CHF
 Bacon Roll                      4.50 CHF
 Extra Milk                      0.00 CHF (Free)
 Foamed Milk                     0.00 CHF (Free)

 Total:      24.95 CHF
 -------------------


