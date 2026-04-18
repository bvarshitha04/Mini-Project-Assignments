# Movie Recommendation System 🍿

Hey! This is a SQL database project where I designed the backend logic for a basic streaming platform (like Netflix). It stores users, movies, and watch history, and then uses that data to figure out what people should watch next!

### What it does:
- **Tracks History:** Keeps a log of every user, what movies they watched, and how many stars they rated them.
- **Finds the Best:** Automatically calculates the top-rated movies and the most popular genres across the whole platform.
- **Recommendation Engine:** Uses a query to recommend new movies to a user based on what *other* people with similar tastes liked.
- **Trending Now:** Detects which movies are currently blowing up by checking watches in the last 7 days.

### How I built it:
- **Language:** SQL
- **Concepts Used:** Designed a 4-table relational database (`Users`, `Movies`, `Ratings`, `Watch_History`). I used complex nested queries (Subqueries), `JOIN`s, and `AVG()` / `COUNT()` functions to analyze the viewing patterns.

### Screenshots
*(Screenshots of the recommendation outputs coming soon!)*
![Movie Output](screenshots/output.png)

### How to test the code:
1. Copy the code from `movie_recommendation.sql`.
2. Paste the top half (Tables & Inserts) into the Schema panel of an online compiler like [DB Fiddle](https://www.db-fiddle.com/).
3. Paste the bottom half (the 5 task queries) into the Query panel and hit Run!
