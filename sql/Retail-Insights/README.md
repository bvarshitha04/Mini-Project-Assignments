# Retail Insights Analytics 🛒

Welcome to my SQL data analysis project! Here, I acted as a Data Analyst for a regional supermarket chain called "FreshMart." They were losing money on bad inventory, so I built a database and wrote queries to help them optimize their stock.

### What it does:
- **Database Schema:** Created the structural blueprint with `Products`, `Categories`, and `SalesTransactions` tables.
- **Expiring Soon Report:** Automatically flags products that are about to expire in the next 7 days but still have too much stock leftover.
- **Dead Stock Analysis:** Identifies products sitting on the shelves that haven't had a single sale in the last two months.
- **Revenue Checker:** Calculates exactly which department made the most money last month.

### How I built it:
- **Language:** SQL
- **Concepts Used:** Primary/Foreign Keys for table relationships, `JOIN`s to connect data, and aggregate functions like `SUM()` and `GROUP BY` to calculate totals. I also used SQL Date functions to dynamically check timeframes.

### Screenshots
*(Screenshots of the SQL output tables coming soon!)*
![SQL Output](screenshots/output.png)

### How to test the code:
1. Copy the code from `retail_insights.sql`.
2. Paste it into an online compiler like [DB Fiddle](https://www.db-fiddle.com/) (make sure it's set to MySQL).
3. Hit Run and see the reports generate instantly!
